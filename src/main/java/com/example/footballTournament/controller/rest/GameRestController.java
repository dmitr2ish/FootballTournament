package com.example.footballTournament.controller.rest;

import com.example.footballTournament.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Integer getNumOfWin(@PathVariable(name = "name") String name) {
        return service.getNumOfWins(name);
    }

    @GetMapping("/draw/team/{name}")
    public Integer getNumOfDraw(@PathVariable(name = "name") String name) {
        return service.getNumOfDraw(name);
    }

    @GetMapping("/lose/team/{name}")
    public Integer getNumOfLose(@PathVariable(name = "name") String name) {
        return service.getNumOfLose(name);
    }
}
