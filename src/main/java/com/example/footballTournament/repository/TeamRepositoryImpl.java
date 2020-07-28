package com.example.footballTournament.repository;

import com.example.footballTournament.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    private EntityManager entityManager;
    private Logger log;

    @Autowired
    public TeamRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.log = Logger.getLogger(TeamRepositoryImpl.class.getName());
    }


    @Override
    public List<Team> getAllTeams() {
        return entityManager.createQuery("select c from Team c").getResultList();
    }

    @Override
    public void deleteAllTeams() {

    }

    @Override
    public Team getByTeamId(Long id) {
        return null;
    }

    @Override
    public Team saveTeam(Team team) {
        try {
            entityManager.persist(team);
            entityManager.flush();
        } catch (Exception e) {
            log.warning("EXCEPTION IN ADD METHOD, TEAM: " + team.getId() + ", " + e);
        }
        return team;
    }

    @Override
    public void updateTeam(Team team) {

    }
}
