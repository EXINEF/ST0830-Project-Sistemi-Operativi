package it.unicam.cs.pa.formula1.model.engine;

import it.unicam.cs.pa.formula1.model.RacetrackGame;
import it.unicam.cs.pa.formula1.model.player.GamePlayer;
import it.unicam.cs.pa.formula1.model.circuit.checkpoint.CheckPointType;
import it.unicam.cs.pa.formula1.model.circuit.CircuitType;
import it.unicam.cs.pa.formula1.model.circuit.RacetrackCircuit;

import java.util.List;

/**
 * Responsibility : This class checks if the Loaded Game is valid,
 * otherwise throws Exception, NullPointerException or IllegalArgumentException
 *
 * @author Pierdominici
 */
public class JsonConfigChecker {

    /**
     * Check if Input File Game Information are valid or not, check with its sub-methods
     *
     * @param game racetrack game
     * @throws NullPointerException NullPointerException
     * @throws IllegalArgumentException IllegalArgumentException
     */
    public static void checkGame(RacetrackGame game) throws NullPointerException, IllegalArgumentException{
        if(game==null)
            throw new NullPointerException("Game can't be null");
        // all other null object have been checked in their specific class

        verifyCheckPointsComposition(game.getCircuit());
        verifyPlayersStartPosition(game.getCircuit(),game.getPlayers());
    }

    /**
     * Verify right type of checkpoints for circuit type
     *
     * @param circuit Race Circuit
     * @throws IllegalArgumentException IllegalArgumentException
     */
    private static void verifyCheckPointsComposition(RacetrackCircuit circuit) throws IllegalArgumentException {
        int startC = 0;
        int normalC = 0;
        int finishC = 0;
        int start_finishC = 0;
        for(int i=0; i<circuit.getCheckPoints().size();i++){
            if (circuit.getCheckPoints().get(i).getType()== CheckPointType.START)
                startC++;
            if(circuit.getCheckPoints().get(i).getType()==CheckPointType.NORMAL)
                normalC++;
            if(circuit.getCheckPoints().get(i).getType()==CheckPointType.FINISH)
                finishC++;
            if(circuit.getCheckPoints().get(i).getType()==CheckPointType.START_FINISH)
                start_finishC++;
        }
        if(normalC<1)
            throw new IllegalArgumentException("Normal CheckPoint must be at least 1...");
        if(circuit.getType()== CircuitType.LINEAR){
            if(startC!=1 && finishC!=1)
                throw new IllegalArgumentException("A Linear circuit must have 1 start checkpoint and 1 finish checkpoint...");
            if(start_finishC!=0)
                throw new IllegalArgumentException("A Linear circuit can't have a start-finish checkpoint...");
        }
        if(circuit.getType()==CircuitType.CIRCULAR){
            if(start_finishC!=1)
                throw new IllegalArgumentException("A Circular circuit must have 1 start-finished checkpoint, there are "+start_finishC+" start-finished checkpoints...");
            if(startC!=0 && finishC!=0)
                throw new IllegalArgumentException("A Circular circuit can't have start checkpoint or finish checkpoint...");
        }
    }

    /**
     * Verify all players start on the same x coordinate, to avoid benefits
     *
     * @param players players
     */
    private static void verifyPlayersStartPosition(RacetrackCircuit circuit, List<? extends GamePlayer> players){
        int position = players.get(0).getCar().getStartLocation().getX();

        for (int i=0; i<players.size(); i++){
            if(!circuit.getCheckPoints().get(0).containsLocation(players.get(i).getCar().getStartLocation()))
                throw new IllegalArgumentException("Player "+i+" must start on a point of StartCheckPoint");
            if(players.get(i).getCar().getStartLocation().getX()!=position)
                throw new IllegalArgumentException("Players must start on the same x coordinate, to avoid benefits...");
        }
    }

}
