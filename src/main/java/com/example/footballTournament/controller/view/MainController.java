package com.example.footballTournament.controller.view;

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
import org.springframework.web.servlet.ModelAndView;

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
        Team team1 = new Team("ORKS", gameService.getAllGames());
        Team team2 = new Team("HOBBITS", gameService.getAllGames());
        teamService.saveTeam(team1);
        teamService.saveTeam(team2);
        Group groupA = new Group("A", teamService.getAllTeams());
        groupService.saveGroup(groupA);
        return "redirect:/";
    }

    @GetMapping("/")
    public ModelAndView getMainPage() {
        return new ModelAndView("main");
    }

}
