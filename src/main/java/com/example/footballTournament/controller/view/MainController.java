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

import java.util.ArrayList;
import java.util.List;
//todo выполнить init метод через один запрос в базу данных
//todo если база данных пуста, высветить пройдите по пути /init для первоначальной инициализации
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
        // Creating a list of 12 team names
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
    private void createGroup(String groupName, List<String> teamNameList) {
        //step 1
        // creating a group in the database, because we need its ID to use it for link the group and the command
        List<Team> tempTeamList = new ArrayList<>();
        groupService.saveGroup(new Group(groupName, tempTeamList));
        Group group = groupService.getByGroupName(groupName);

        //step 2
        //creating a collection of teams whitout id and games
        List<Team> teamList = new ArrayList<>();
        for (String teamName : teamNameList) {
            teamList.add(new Team(teamName, null, group.getId()));
        }
        //saved in the database to get the ID
        for (Team team : teamList) {
            teamService.saveTeam(team);
        }
        //get collection with id from db
        teamList = teamService.getAllTeamsByGroupId(group.getId());

        //step 3
        //creating list of 6 games in group where winner is first team
        //according to the task
        // " Within the group, each team plays with each, i.e. we have 6 games in each group.
        // The match can ONLY be between teams in the same group."
        List<Game> gameList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 4; k++) {
                gameList.add(new Game(
                        teamList.get(i).getId(),    //firstTeam
                        teamList.get(k).getId(),    //secondTeam
                        teamList.get(i).getId()));  //winner
            }
        }
        //step 3
        //saving games in database
        for (Game game : gameList) {
            gameService.saveGame(game);
        }

        //step 4
        //set list of games to team by team id
        for(Team team : teamList) {
            team.setGameList(gameService.getAllGamesByTeamId(team.getId()));
        }

        //update data in base
        for (Team team : teamList) {
            teamService.updateTeam(team);
        }

        //step 6
        //set teams in this group and save in data base this group
        group.setTeams(teamService.getAllTeamsByGroupId(group.getId()));
        groupService.updateGroup(group);
    }
}
