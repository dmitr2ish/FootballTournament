package com.example.footballTournament.service;

import com.example.footballTournament.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();

    void deleteAllGroups();

    Group getByGroupId(Long id);

    Group saveGroup(Group group);

    void updateGroup(Group group);
}
