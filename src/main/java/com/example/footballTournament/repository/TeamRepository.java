package com.example.footballTournament.repository;

import com.example.footballTournament.entity.Team;

import java.util.List;

public interface TeamRepository {
    List<Team> getAllTeams();

    List<Team> getAllTeamsByGroupName(String groupName);

    void deleteAllTeams();

    Team getByTeamId(Long id);

    Team saveTeam(Team team);

    void updateTeam(Team team);
}
