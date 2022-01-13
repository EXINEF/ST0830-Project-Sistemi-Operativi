package it.unicam.cs.pa.formula1.model;

import it.unicam.cs.pa.formula1.model.circuit.RacetrackCircuit;
import it.unicam.cs.pa.formula1.model.player.GamePlayer;

import java.util.List;

/**
 * Responsibility : This interface stores the information about the Game
 *
 * @author Pierdominici
 */
public interface Game<L> {

    /**
     * Return RaceCircuit of this Game
     *
     * @return circuit
     */
    RacetrackCircuit getCircuit();

    /**
     * Return List of all players of this game
     *
     * @return List of players
     */
    List<GamePlayer> getPlayers();

    /**
     * Return general turn of this game
     *
     * @return general turn
     */
    int getGeneralTurn();

    /**
     * Increase general turn of this game of 1
     */
    void addGeneralTurn();

    /**
     * Return best possible road for this game and its circuit
     *
     * @return best possible road
     */
    List<L> getBestPossibleRoad();

    /**
     * Return if the game is finished or not
     *
     * @return if is finished
     */
    boolean isFinished();

    /**
     * Terminate the Game
     */
    void setFinished();

    /**
     * Return a simple ScoreBoard with all Players
     *
     * @return scoreboard
     */
    String getScoreBoard();

    /**
     * Check if all players are not in game
     *
     * @return if all player have finished or crashed
     */
    boolean allPlayersFinished();
}
