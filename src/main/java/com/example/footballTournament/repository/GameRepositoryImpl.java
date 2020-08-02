package com.example.footballTournament.repository;

import com.example.footballTournament.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

//TODO сделать с роллбэками и логгером на экзепшенах во всех имплементациях

@Repository
public class GameRepositoryImpl implements GameRepository {

    private EntityManager entityManager;
    private Logger log;

    @Autowired
    public GameRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.log = Logger.getLogger(GameRepositoryImpl.class.getName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Game> getAllGames() {
        return entityManager.createQuery("select c from Game c").getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Game> getAllGamesByTeamId(Long teamId) {
        return entityManager.createQuery("select c from Game c where c.firstTeamId = :teamId or c.secondTeamId = :teamId")
                .setParameter("teamId", teamId)
                .getResultList();
    }

    @Override
    public boolean isExist(Long id) {
        List<Integer> result = entityManager.createQuery("select c from Game c where c.id = :id")
                .setParameter("id", id)
                .getResultList();
        return !result.isEmpty()
                ? true
                : false;
    }

    @Override
    public void deleteAllGames() {
        entityManager.createQuery("delete from Game ");
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from Game c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Game getByGameId(Long id) {
        return entityManager.find(Game.class, id);
    }

    @Override
    public Game saveGame(Game game) {
        try {
            entityManager.persist(game);
            entityManager.flush();
        } catch (Exception e) {
            log.warning("EXCEPTION IN ADD METHOD, GAME: " + game.getId() + ", " + e);
        }
        return game;
    }

    @Override
    public Game updateGame(Game game) {
        try {
            entityManager.merge(game);
            entityManager.flush();
        } catch (Exception e) {
            log.warning("EXCEPTION IN UPDATE METHOD, GAME: " + game.getId() + ", " + e);
        }
        return game;
    }

    @Override
    public Integer getNumOfWins(Long teamId) {
        return entityManager.createQuery("select c from Game c  WHERE c.winnerId = :teamId")
                .setParameter("teamId", teamId)
                .getResultList().size();
    }

    @Override
    public Integer getNumOfLose(Long teamId) {
        int totalNumOfGames = entityManager.createQuery("select c from Game c WHERE c.firstTeamId = :teamId or c.secondTeamId = :teamId")
                .setParameter("teamId", teamId)
                .getResultList().size();

        log.info("----===== COUNT TOTAL GAMES: " + totalNumOfGames + "=====-----");

        int numOfWin = entityManager.createQuery("select c from Game c  WHERE c.winnerId = :teamId")
                .setParameter("teamId", teamId)
                .getResultList().size();

        log.info("----===== COUNT WIN GAMES: " + numOfWin + "=====-----");

        int numOfDraw = getNumOfDraw(teamId);

        log.info("----===== COUNT DRAW GAMES: " + numOfDraw + "=====-----");

        return totalNumOfGames - (numOfWin + numOfDraw);
    }

    @Override
    public Integer getNumOfDraw(Long teamId) {
        int drawsWhenFirst = entityManager.createQuery(
                "select c from Game c WHERE " +
                        "c.winnerId = 0L and " +
                        "c.firstTeamId = :teamId")
                .setParameter("teamId", teamId)
                .getResultList().size();
        int drawsWhenSecond = entityManager.createQuery(
                "select c from Game c WHERE " +
                        "c.winnerId = 0L and " +
                        "c.secondTeamId = :teamId")
                .setParameter("teamId", teamId)
                .getResultList().size();
        return drawsWhenFirst + drawsWhenSecond;
    }
}
