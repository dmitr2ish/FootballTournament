package com.example.footballTournament.repository;

import com.example.footballTournament.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;


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
    public List<Game> getAllGamesByTeamName(String teamName) {
        return entityManager.createQuery("select c from Game c where c.firstTeam = :teamName or c.secondTeam = :teamName")
                .setParameter("teamName", teamName)
                .getResultList();
    }

    @Override
    public void deleteAllGames() {

    }

    @Override
    public Game getByGameId(Long id) {
        return null;
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
    public void updateGame(Game game) {

    }

    @Override
    public Integer getNumOfWins(String teamName) {
        return entityManager.createQuery("select c from Game c  WHERE c.winner = :teamName")
                .setParameter("teamName", teamName)
                .getResultList().size();
    }

    @Override
    public Integer getNumOfLose(String teamName) {
        int totalNumOfGames = entityManager.createQuery("select c from Game c WHERE c.firstTeam = :teamName or c.secondTeam = :teamName")
                .setParameter("teamName", teamName)
                .getResultList().size();

        log.info("----===== COUNT TOTAL GAMES: " + totalNumOfGames + "=====-----");

        int numOfWin = entityManager.createQuery("select c from Game c  WHERE c.winner = :teamName")
                .setParameter("teamName", teamName)
                .getResultList().size();

        log.info("----===== COUNT WIN GAMES: " + numOfWin + "=====-----");

        int numOfDraw = getNumOfDraw(teamName);

        log.info("----===== COUNT DRAW GAMES: " + numOfDraw + "=====-----");

        return totalNumOfGames - (numOfWin + numOfDraw);
    }

    @Override
    public Integer getNumOfDraw(String teamName) {
        return entityManager.createQuery(
                "select c from Game c WHERE " +
                        "c.winner = 'draw' and " +
                        "c.firstTeam = :teamName or " +
                        "c.secondTeam = :teamName")
                .setParameter("teamName", teamName)
                .getResultList().size();
    }
}
