package com.example.footballTournament.repository;

import com.example.footballTournament.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

    private EntityManager entityManager;
    private Logger log;

    @Autowired
    public GroupRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        log = Logger.getLogger(GroupRepositoryImpl.class.getName());
    }


    @Override
    public List<Group> getAllGroups() {
        return entityManager.createQuery("select c from Group c").getResultList();
    }

    @Override
    public void deleteAllGroups() {

    }

    @Override
    public Group getByGroupId(Long id) {
        return null;
    }

    @Override
    public Group saveGroup(Group group) {
        try {
            entityManager.persist(group);
            entityManager.flush();
        } catch (Exception e) {
            log.warning("EXCEPTION IN ADD METHOD, GROUP: " + group.getId() + ", " + e);
        }
        return group;
    }

    @Override
    public void updateGroup(Group team) {

    }
}
