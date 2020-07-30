package com.example.footballTournament.controller.rest;

import com.example.footballTournament.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/game")
public class GameRestController {

    final private GameService service;

    @Autowired
    public GameRestController(GameService service) {
        this.service = service;
    }

    @GetMapping("/win/team/{name}")
    public ResponseEntity<Integer> getNumOfWin(@PathVariable(name = "name") String name) {
        Integer numOfWins = service.getNumOfWins(name);
        return (numOfWins != null)
                ? new ResponseEntity<>(numOfWins, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/draw/team/{name}")
    public ResponseEntity<Integer> getNumOfDraw(@PathVariable(name = "name") String name) {
        Integer numOfDraw = service.getNumOfDraw(name);
        return (numOfDraw != null)
                ? new ResponseEntity<>(numOfDraw, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/lose/team/{name}")
    public ResponseEntity<Integer> getNumOfLose(@PathVariable(name = "name") String name) {
        Integer numOfLose = service.getNumOfLose(name);
        return (numOfLose != null)
                ? new ResponseEntity<>(numOfLose, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
