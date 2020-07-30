package com.example.footballTournament.controller.view;

import com.example.footballTournament.entity.Game;
import com.example.footballTournament.entity.Group;
import com.example.footballTournament.entity.Team;
import com.example.footballTournament.service.GameService;
import com.example.footballTournament.service.GroupService;
import com.example.footballTournament.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
        Game game1 = new Game("ORKS", "HOBBITS", "draw");
        Game game2 = new Game("ORKS", "HOBBITS", "ORKS");
        Game game3 = new Game("ORKS", "HOBBITS", "ORKS");
        Game game4 = new Game("ORKS", "HOBBITS", "HOBBITS");
        Game game5 = new Game("ORKS", "HOBBITS", "draw");
        Game game6 = new Game("SarumanAndCo", "HOBBITS", "HOBBITS");
        gameService.saveGame(game1);
        gameService.saveGame(game2);
        gameService.saveGame(game3);
        gameService.saveGame(game4);
        gameService.saveGame(game5);
        gameService.saveGame(game6);

        Team team1 = new Team("ORKS", gameService.getAllGamesByTeamName("ORKS"));
        Team team2 = new Team("HOBBITS", gameService.getAllGamesByTeamName("HOBBITS"));
        Team team3 = new Team("SarumanAndCo", gameService.getAllGamesByTeamName("SarumanAndCo"));

        teamService.saveTeam(team1);
        teamService.saveTeam(team2);
        teamService.saveTeam(team3);

        //FIXME переделать entity чтобы команды брались по имени группы
        Group groupA = new Group("A", teamService.getAllTeams());
        groupService.saveGroup(groupA);
        return "redirect:/";
    }

    @GetMapping("/")
    public ModelAndView getMainPage() {
        return new ModelAndView("main");
    }

}
