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
    public List<Game> getAllGames() {
        return entityManager.createQuery("select c from Game c").getResultList();
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
}
