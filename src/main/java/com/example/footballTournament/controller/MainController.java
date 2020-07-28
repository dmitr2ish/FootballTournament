package com.example.footballTournament.controller;

import com.example.footballTournament.entity.Game;
import com.example.footballTournament.entity.Group;
import com.example.footballTournament.entity.Team;
import com.example.footballTournament.service.GameService;
import com.example.footballTournament.service.GroupService;
import com.example.footballTournament.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private GroupService groupService;
    private GameService gameService;
    private TeamService teamService;

    @Autowired
    public MainController(GroupService groupService, GameService gameService, TeamService teamService) {
        this.groupService = groupService;
        this.gameService = gameService;
        this.teamService = teamService;
    }

    @GetMapping("/init")
    public String backDoorPage() {
        Game game1 = new Game("ORKS",10);
        Game game2 = new Game("HOBBITS",10);
        gameService.saveGame(game1);
        gameService.saveGame(game2);
        Group groupA = new Group("A", new ArrayList<Team>());
        Team team1 = new Team("ORKS", gameService.getAllGames(), groupA);
        Team team2 = new Team("HOBBITS", gameService.getAllGames(), groupA);
        teamService.saveTeam(team1);
        teamService.saveTeam(team2);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getMainPage(ModelMap modelMap) {
        List<Group> groupList = groupService.getAllGroups();
        modelMap.addAttribute("groupList", groupList);
        return "main";
    }

}
