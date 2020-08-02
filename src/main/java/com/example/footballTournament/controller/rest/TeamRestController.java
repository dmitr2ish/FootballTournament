package com.example.footballTournament.controller.rest;

import com.example.footballTournament.entity.Team;
import com.example.footballTournament.service.GroupService;
import com.example.footballTournament.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamRestController {

    final private TeamService service;
    final private GroupService groupService;

    @Autowired
    public TeamRestController(TeamService service, GroupService groupService) {
        this.service = service;
        this.groupService = groupService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable(name = "id") Long id) {
        Team team = service.getByTeamId(id);
        return (team != null)
                ? new ResponseEntity<>(team, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Team>> getAllTeam() {
        List<Team> list = service.getAllTeams();
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/teamsGroup/{id}")
    public ResponseEntity<List<Team>> getAllTeamInOneGroup(@PathVariable(name = "id") Long id) {
        Team team = service.getByTeamId(id);
        Long groupNumber = team.getIdOfGroup();
        List<Team> teamList = groupService.getByGroupId(groupNumber).getTeams();
        return !teamList.isEmpty()
                ? new ResponseEntity<>(teamList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        Team currentTeam = service.getByTeamId(team.getId());
        currentTeam.setName(team.getName());
        Team teamUpdate = service.updateTeam(currentTeam);
        return (teamUpdate != null)
                ? new ResponseEntity<>(teamUpdate, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTeam(@RequestBody Team team) {
        service.deleteById(team.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
