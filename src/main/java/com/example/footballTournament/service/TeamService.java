package com.example.footballTournament.service;

import com.example.footballTournament.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();

    List<Team> getAllTeamsByGroupId(Long groupId);

    void deleteAllTeams();

    void deleteById(Long id);

    Team getByTeamId(Long id);

    Team saveTeam(Team team);

    Team updateTeam(Team team);
}
