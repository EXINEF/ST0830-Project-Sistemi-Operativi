package it.unicam.cs.pa.formula1.model.circuit;

import it.unicam.cs.pa.formula1.model.circuit.checkpoint.CircuitCheckPoint;

import java.util.List;

/**
 * Responsibility : This interface stores the information about the Circuit
 *
 * @author Pierdominici
 */
public interface Circuit<T,L> {

    /**
     * Return All CheckPoint of this circuit
     *
     * @return All CheckPoint
     */
    List<CircuitCheckPoint> getCheckPoints();

    /**
     * Return Circuit Type
     *
     */
    CircuitType getType();

    /**
     * Return number of Laps, if is Linear return 1
     *
     * @return Laps' number
     */
    int getLaps();

    /**
     * Check if Circuit contains a certain location on the map
     *
     * @param location location
     *
     * @return checkpoint type who contains this location
     */
    T containsPosition(L location);

    /**
     * Return the width of the circuit
     *
     * @return width
     */
    int getWidth();

    /**
     * Return the height of the circuit
     *
     * @return height
     */
    int getHeight();

    /**
     * Return number of obligatory checkpoints
     *
     * @return obligatory checkpoints number
     */
    int getObligatoryCheckPoints();



}
