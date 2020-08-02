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
public class ServiceImpl implements GroupService, GameService, TeamService {

    private GroupRepository groupRepository;
    private TeamRepository teamRepository;
    private GameRepository gameRepository;

    @Autowired
    public ServiceImpl(GroupRepository groupRepository, TeamRepository teamRepository, GameRepository gameRepository) {
        this.groupRepository = groupRepository;
        this.teamRepository = teamRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }

    @Override
    public List<Game> getAllGamesByTeamId(Long teamId) {
        return gameRepository.getAllGamesByTeamId(teamId);
    }

    @Override
    public boolean isExist(Long id) {
        return gameRepository.isExist(id);
    }

    @Override
    public void deleteAllGames() {
        gameRepository.getAllGames();
    }

    @Override
    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Game getByGameId(Long id) {
        return gameRepository.getByGameId(id);
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.saveGame(game);
    }

    @Override
    public Game updateGame(Game game) {
        return gameRepository.updateGame(game);
    }

    @Override
    public Integer getNumOfWins(Long teamId) {
        return gameRepository.getNumOfWins(teamId);
    }

    @Override
    public Integer getNumOfLose(Long teamId) {
        return gameRepository.getNumOfLose(teamId);
    }

    @Override
    public Integer getNumOfDraw(Long teamId) {
        return gameRepository.getNumOfDraw(teamId);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.getAllTeams();
    }

    @Override
    public List<Team> getAllTeamsByGroupId(Long groupId) {
        return teamRepository.getAllTeamsByGroupId(groupId);
    }

    @Override
    public void deleteAllTeams() {
        teamRepository.deleteAllTeams();
    }

    @Override
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public Team getByTeamId(Long id) {
        return teamRepository.getByTeamId(id);
    }

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.saveTeam(team);
    }

    @Override
    public Team updateTeam(Team team) {
        return teamRepository.updateTeam(team);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    @Override
    public void deleteAllGroups() {
        groupRepository.deleteAllGroups();
    }

    @Override
    public Group getByGroupId(Long id) {
        return groupRepository.getByGroupId(id);
    }

    @Override
    public Group getByGroupName(String name) {
        return groupRepository.getByGroupName(name);
    }

    @Override
    public Group saveGroup(Group group) {
        return groupRepository.saveGroup(group);
    }

    @Override
    public Group updateGroup(Group group) {
        return groupRepository.updateGroup(group);
    }

    @Override
    public void deleteGroup(Group group) {
        groupRepository.deleteGroup(group);
    }

    @Override
    public void deleteGroupById(Long id) {
        groupRepository.deleteGroupById(id);
    }
}
