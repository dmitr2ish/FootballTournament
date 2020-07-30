package com.example.footballTournament.controller.rest;

import com.example.footballTournament.entity.Group;
import com.example.footballTournament.entity.Team;
import com.example.footballTournament.service.GameService;
import com.example.footballTournament.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Group> getListGroup() {
        return service.getAllGroups();
    }

    @GetMapping("/team/{id}")
    public List<Team> getAllTeamInGroup(@PathVariable(name = "id")Long id) {
        Group group = service.getByGroupId(id);
        return group.getTeams();
    }
}
