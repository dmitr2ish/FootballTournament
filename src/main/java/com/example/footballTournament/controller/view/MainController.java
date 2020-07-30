package com.example.footballTournament.controller.view;

import com.example.footballTournament.entity.Game;
import com.example.footballTournament.entity.Group;
import com.example.footballTournament.entity.Team;
import com.example.footballTournament.service.GameService;
import com.example.footballTournament.service.GroupService;
import com.example.footballTournament.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String backDoorInit() {
        //Creating 12 names of teams
        List<String> teamsFromLorOfTheRing = new ArrayList<>();
        teamsFromLorOfTheRing.add("Orks");
        teamsFromLorOfTheRing.add("Hobbits from Shir");
        teamsFromLorOfTheRing.add("Saruman and Co");
        teamsFromLorOfTheRing.add("People from Gondor");

        List<String> teamsFromStarWars = new ArrayList<>();
        teamsFromStarWars.add("Sithi");
        teamsFromStarWars.add("Orden of Jedies");
        teamsFromStarWars.add("Empire");
        teamsFromStarWars.add("Federation");

        List<String> teamsFromHarryPottersWorld = new ArrayList<>();
        teamsFromHarryPottersWorld.add("Slytherin");
        teamsFromHarryPottersWorld.add("Gryffindor");
        teamsFromHarryPottersWorld.add("Hufflepuff");
        teamsFromHarryPottersWorld.add("Ravenclaw");

        List<String> teamsFromAzeroth = new ArrayList<>();
        teamsFromAzeroth.add("Hord");
        teamsFromAzeroth.add("Alliance");
        teamsFromAzeroth.add("Pandarens");
        teamsFromAzeroth.add("Undead");

        List<String> teamsFrom80sFootball = new ArrayList<>();
        teamsFrom80sFootball.add("Ussr");
        teamsFrom80sFootball.add("China");
        teamsFrom80sFootball.add("Usa");
        teamsFrom80sFootball.add("England");

        List<String> teamsFromEnglishAlphabet = new ArrayList<>();
        teamsFromEnglishAlphabet.add("A");
        teamsFromEnglishAlphabet.add("B");
        teamsFromEnglishAlphabet.add("C");
        teamsFromEnglishAlphabet.add("D");

        List<String> teamsFromMyImagination = new ArrayList<>();
        teamsFromMyImagination.add("Robots");
        teamsFromMyImagination.add("Humans");
        teamsFromMyImagination.add("Hybrid");
        teamsFromMyImagination.add("AI");

        List<String> teamsFromTerminatorFirst = new ArrayList<>();
        teamsFromTerminatorFirst.add("Sara Connor's team");
        teamsFromTerminatorFirst.add("simple T-800");
        teamsFromTerminatorFirst.add("Son of Sara Connor");
        teamsFromTerminatorFirst.add("Skynet and enough");

        createGroup("A", teamsFromLorOfTheRing);
        createGroup("B", teamsFromStarWars);
        createGroup("C", teamsFromHarryPottersWorld);
        createGroup("D", teamsFromAzeroth);
        createGroup("E", teamsFrom80sFootball);
        createGroup("F", teamsFromEnglishAlphabet);
        createGroup("G", teamsFromMyImagination);
        createGroup("H", teamsFromTerminatorFirst);
        return "redirect:/";
    }

    @GetMapping("/")
    public ModelAndView getMainPage() {
        return new ModelAndView("main");
    }

    /* This method is the helper for backDoorInit creates a group, teams, games within the group,
     * each team plays with each and saves to the database.
     */
    private void createGroup(String groupName, List<String> teamList) {
        //step 1
        // creating a group in the database, because we need its ID to use it for  link the group and the command
        Group group = groupService.saveGroup(new Group(groupName, null));
        //step 2
        //creating list of 6 games in group where winner is first team
        List<Game> gameList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 4; k++) {
                gameList.add(new Game(
                        teamList.get(i),    //firstTeam
                        teamList.get(k),    //secondTeam
                        teamList.get(i)));  //winner
            }
        }
        //step 3
        //saving games in database
        for (Game game : gameList) {
            gameService.saveGame(game);
        }
        //step 4
        //creating list of 4 teams from one group
        List<Team> teamListGroup = new ArrayList<>();
        for (String teamName : teamList) {
            teamListGroup.add(new Team(
                    teamName,
                    gameService.getAllGamesByTeamName(teamName),
                    group.getId()));
        }
        //step 5
        //saving team in database
        for (Team teamName : teamListGroup) {
            teamService.saveTeam(teamName);
        }
        //step 6
        //set teams in this group and save in data base this group
        group.setTeams(teamService.getAllTeamsByGroupName(group.getId()));
        groupService.updateGroup(group);
    }
}
