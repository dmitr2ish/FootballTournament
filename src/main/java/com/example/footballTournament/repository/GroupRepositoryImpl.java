package com.example.footballTournament.repository;

import com.example.footballTournament.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @SuppressWarnings("unchecked")
    public List<Group> getAllGroups() {
        return entityManager.createQuery("select c from Group c").getResultList();
    }

    @Override
    public void deleteAllGroups() {
        entityManager.createQuery("delete from Group").executeUpdate();
    }

    @Override
    public Group getByGroupId(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public Group getByGroupName(String name) {
        return (Group) entityManager.createQuery("select c from Group c where c.name = :name")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    @Transactional
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
    public Group updateGroup(Group group) {
        try {
            entityManager.merge(group);
            entityManager.flush();
        } catch (Exception e) {
            log.warning("EXCEPTION IN UPDATE METHOD, GROUP: " + group.getId() + ", " + e);
        }
        return group;
    }

    @Override
    public void deleteGroup(Group group) {
        entityManager.remove(group);
    }

    @Override
    public void deleteGroupById(Long id) {
        entityManager.createQuery("delete from Group c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
