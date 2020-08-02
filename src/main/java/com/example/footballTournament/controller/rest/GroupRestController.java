package com.example.footballTournament.controller.rest;

import com.example.footballTournament.entity.Game;
import com.example.footballTournament.entity.Group;
import com.example.footballTournament.entity.Team;
import com.example.footballTournament.service.GameService;
import com.example.footballTournament.service.GroupService;
import com.example.footballTournament.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupRestController {

    final private GroupService groupService;
    final private TeamService teamService;
    final private GameService gameService;

    @Autowired
    public GroupRestController(GroupService groupService, TeamService teamService, GameService gameService) {
        this.groupService = groupService;
        this.teamService = teamService;
        this.gameService = gameService;
    }


    @GetMapping("/list")
    public ResponseEntity<List<Group>> getListGroup() {
        List<Group> groupList = groupService.getAllGroups();
        return (groupList != null && !groupList.isEmpty())
                ? new ResponseEntity<>(groupList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroup(@PathVariable(name = "id") Long id) {
        Group group = groupService.getByGroupId(id);
        return (group != null)
                ? new ResponseEntity<>(group, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group) {
        List<Team> currentTeamList = groupService.getByGroupId(group.getId()).getTeams();
        group.setTeams(currentTeamList);
        return (groupService.updateGroup(group) != null)
                ? new ResponseEntity<>(group, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //delete group and his child
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteGroup(@RequestBody Group group) {
        Group currentGroup = groupService.getByGroupId(group.getId());
        groupService.deleteGroupById(currentGroup.getId());

        List<Team> teams = teamService.getAllTeamsByGroupId(currentGroup.getId());
        List<Long> gamesId = new ArrayList<>();
        for (Team team : teams) {
            List<Game> games = team.getGameList();
            teamService.deleteById(team.getId());
            for (Game game : games) {
                gamesId.add(game.getId());
            }
        }
        for (Long id : gamesId) {
            if (gameService.isExist(id)) {
                gameService.deleteGameById(id);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //show all teams in current group by id
    @GetMapping("/team/{id}")
    public ResponseEntity<List<Team>> getAllTeamInGroup(@PathVariable(name = "id") Long id) {
        Group group = groupService.getByGroupId(id);
        List<Team> teamList = group.getTeams();
        return (teamList != null && !teamList.isEmpty())
                ? new ResponseEntity<>(teamList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
