package it.unicam.cs.pa.formula1.model.player;

import it.unicam.cs.pa.formula1.model.circuit.checkpoint.CircuitCheckPoint;
import it.unicam.cs.pa.formula1.model.player.car.Car;
import it.unicam.cs.pa.formula1.model.vector.Vector;

import java.util.List;

/**
 * Responsibility : This interface stores the information about the Player
 *
 * @author Pierdominici
 */
public interface Player<S,L> {

    /**
     * Return Name who identify Bot univocal
     *
     * @return Bot's name
     */
    String getName();

    /**
     * Return Car driven by the Bot
     *
     * @return Car driven by Bot
     */
    Car getCar();

    /**
     *  Get Status of Player in the Game
     *
     * @return Status
     */
    S getStatus();

    /**
     * Set new player Status
     * @param newStatus new Status
     */
    void setStatus(S newStatus);

    /**
     * Return all the vector movements done by the player
     *
     * @return List VectorMovement
     */
    List<Vector> allMovements();

    /**
     * Add a new MovementVector to List
     *
     * @param movementVector MovementVector to add
     */
    void addMovement(Vector movementVector);

    /**
     * Return last vector added to the List
     *
     * @return Last MovementVector
     */
    Vector lastMovement();

    /**
     * Return number of turn done
     *
     * @return turns
     */
    int getTurn();

    /**
     * Get Lap Done of the player
     *
     * @return num of lap done
     */
    int getLap();

    /**
     * Increase Lap of 1
     */
    void addLap();

    /**
     * Move the car autonomously
     */
    void moveCar(L bestMove, boolean isSmart);

    /**
     * Return Player Info for ScoreBoard info
     *
     * @return player info for ScoreBoard
     */
    String toStringScoreboard();

    /**
     * Return all Obligatory CheckPoint that are crossed
     *
     * @return Obligatory CheckPoint that are crossed
     */
    List<CircuitCheckPoint> getCrossedCheckPoint();

    /**
     * Reset all obligatory crossed checkpoint List
     */
    void resetCrossedCheckPoint();

    /**
     * Return if player is smart or not
     *
     * @return smart
     */
    default boolean isSmart(){
        return false;
    }

}
