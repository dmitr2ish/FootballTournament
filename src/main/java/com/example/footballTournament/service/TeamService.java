package com.example.footballTournament.service;

import com.example.footballTournament.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeamsByGroupId(Long groupId);

    List<Team> getAllTeams();

    void deleteAllTeams();

    Team getByTeamId(Long id);

    Team saveTeam(Team team);

    void updateTeam(Team team);
}
