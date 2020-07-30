package com.example.footballTournament.service;

import com.example.footballTournament.entity.Game;
import com.example.footballTournament.entity.Group;
import com.example.footballTournament.entity.Team;
import com.example.footballTournament.repository.GameRepository;
import com.example.footballTournament.repository.GroupRepository;
import com.example.footballTournament.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService, GameService, TeamService {

    private GroupRepository groupRepository;
    private TeamRepository teamRepository;
    private GameRepository gameRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, TeamRepository teamRepository, GameRepository gameRepository) {
        this.groupRepository = groupRepository;
        this.teamRepository = teamRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    @Override
    public void deleteAllGroups() {

    }

    @Override
    public Group getByGroupId(Long id) {
        return groupRepository.getByGroupId(id);
    }

    @Override
    public Group saveGroup(Group group) {
        return groupRepository.saveGroup(group);
    }

    @Override
    public void updateGroup(Group group) {

    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }

    @Override
    public List<Game> getAllGamesByTeamName(String teamName) {
        return gameRepository.getAllGamesByTeamName(teamName);
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
        return gameRepository.saveGame(game);
    }

    @Override
    public void updateGame(Game game) {

    }

    @Override
    public Integer getNumOfWins(String teamName) {
        return gameRepository.getNumOfWins(teamName);
    }

    @Override
    public Integer getNumOfLose(String teamName) {
        return gameRepository.getNumOfLose(teamName);
    }

    @Override
    public Integer getNumOfDraw(String teamName) {
        return gameRepository.getNumOfDraw(teamName);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.getAllTeams();
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
        return teamRepository.saveTeam(team);
    }

    @Override
    public void updateTeam(Team team) {

    }
}
