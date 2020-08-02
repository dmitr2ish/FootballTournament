package com.example.footballTournament.controller.rest;

import com.example.footballTournament.entity.Team;
import com.example.footballTournament.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
public class TeamRestController {

    final private TeamService service;

    @Autowired
    public TeamRestController(TeamService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable(name = "id") Long id) {
        Team team = service.getByTeamId(id);
        return (team != null)
                ? new ResponseEntity<>(team, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        Team teamUpdate = service.updateTeam(team);
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
