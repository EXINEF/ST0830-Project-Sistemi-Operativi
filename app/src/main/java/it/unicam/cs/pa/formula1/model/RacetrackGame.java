package it.unicam.cs.pa.formula1.model;

import it.unicam.cs.pa.formula1.model.engine.JsonConfigChecker;
import it.unicam.cs.pa.formula1.model.circuit.location.CircuitLocation;
import it.unicam.cs.pa.formula1.model.circuit.RacetrackCircuit;
import it.unicam.cs.pa.formula1.model.player.GamePlayer;
import it.unicam.cs.pa.formula1.model.player.PlayerStatus;

import java.util.*;

/**
 * Responsibility : This class provide the implementation of RaceTraceGame
 *
 * @author Pierdominici
 */
public class RacetrackGame implements Game<CircuitLocation> {

    private final RacetrackCircuit circuit;
    private final List<GamePlayer> players;
    private final List<CircuitLocation> bestPossibleRoad;
    private int generalTurn;
    private boolean finished;

    public RacetrackGame(RacetrackCircuit circuit, List<GamePlayer> players, List<CircuitLocation> bestPossibleRoad) {
        if(circuit==null)
            throw new NullPointerException("Race Circuit can't be null...");
        if(players==null)
            throw new NullPointerException("Players List can't be null...");
        this.circuit = circuit;
        this.players = players;
        this.bestPossibleRoad = bestPossibleRoad;
        finished = false;
        generalTurn = 1;
        JsonConfigChecker.checkGame(this);
    }

    @Override
    public RacetrackCircuit getCircuit() {
        return circuit;
    }

    @Override
    public List<GamePlayer> getPlayers() {
        return players;
    }

    @Override
    public int getGeneralTurn() {
        return generalTurn;
    }

    @Override
    public void addGeneralTurn() {
        generalTurn++;
    }

    @Override
    public List<CircuitLocation> getBestPossibleRoad() {
        return bestPossibleRoad;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void setFinished() {
        finished = true;
    }

    @Override
    public String getScoreBoard() {
        StringBuilder scoreboard= new StringBuilder("\nSCOREBOARD:");
        for (GamePlayer player : players) {
            scoreboard.append("\n").append(player.toStringScoreboard());
        }
        return scoreboard.toString();
    }

    @Override
    public boolean allPlayersFinished(){
        for (GamePlayer player:players) {
            if(player.getStatus()==PlayerStatus.IN_GAME)
                return false;
        }
        return true;
    }
}
