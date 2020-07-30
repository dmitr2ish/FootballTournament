package com.example.footballTournament.controller.rest;

import com.example.footballTournament.entity.Group;
import com.example.footballTournament.entity.Team;
import com.example.footballTournament.service.GameService;
import com.example.footballTournament.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupRestController {

    final private GroupService service;

    @Autowired
    public GroupRestController(GroupService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Group>> getListGroup() {
        List<Group> groupList =service.getAllGroups();
        return (groupList != null && !groupList.isEmpty())
                ? new ResponseEntity<>(groupList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //show all teams in current group by id
    @GetMapping("/team/{id}")
    public ResponseEntity<List<Team>> getAllTeamInGroup(@PathVariable(name = "id")Long id) {
        List<Team> teamList = service.getByGroupId(id).getTeams();
        return (teamList != null && !teamList.isEmpty())
                ? new ResponseEntity<>(teamList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
