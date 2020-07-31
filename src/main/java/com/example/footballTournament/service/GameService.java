package com.example.footballTournament.service;

import com.example.footballTournament.entity.Game;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();

    List<Game> getAllGamesByTeamId(Long teamId);

    void deleteAllGames();

    Game getByGameId(Long id);

    Game saveGame(Game game);

    Game updateGame(Game game);

    Integer getNumOfWins(Long teamId);

    Integer getNumOfLose(Long teamId);

    Integer getNumOfDraw(Long teamId);
}
