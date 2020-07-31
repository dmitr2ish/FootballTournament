package com.example.footballTournament.service;

import com.example.footballTournament.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();

    void deleteAllGroups();

    Group getByGroupId(Long id);

    Group getByGroupName(String name);

    Group saveGroup(Group group);

    Group updateGroup(Group group);

    void deleteGroup(Group group);

    void deleteGroupById(Long id);
}
