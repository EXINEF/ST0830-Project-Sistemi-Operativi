package it.unicam.cs.pa.formula1.controller;

import it.unicam.cs.pa.formula1.model.RacetrackGame;

/**
 * Responsibility : This interface stores the information about the Game Controller that is able to interact with the Game
 *
 * @author Pierdominici
 */
public interface Controller {

    /**
     * Return RaceTrack Game
     *
     * @return RaceTrack Game
     */
    RacetrackGame getGame();

    /**
     * Return Game's Scoreboard using game method
     *
     * @return game's Scoreboard
     */
    String getGameScoreBoard();

    /**
     * Create a new Game
     */
    void newGame();

    /**
     * Established which player should moves, then move the player
     *
     * @return status of move
     */
    String nextMove();
}

