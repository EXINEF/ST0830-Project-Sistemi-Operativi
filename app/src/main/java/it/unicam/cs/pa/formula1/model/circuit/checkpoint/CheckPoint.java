package it.unicam.cs.pa.formula1.model.circuit.checkpoint;

import java.util.List;

/**
 * Responsibility : This interface stores the information about the CheckPoint
 *
 * @author Pierdominici
 */
public interface CheckPoint<T,L> {

    /**
     * Return all CheckPoints Points
     *
     * @return all Points of CheckPoint
     */
    List<? extends L> getPoints();

    /**
     * Checks if given Map Point is part of this checkpoint
     *
     * @param location searched point
     *
     * @return true if checkpoint contains it, false otherwise
     */
    boolean containsLocation(L location);

    /**
     * Return Type of CheckPoint
     *
     * @return type
     */
    T getType();

    /**
     * Return Start Point of CheckPoint, lower y
     * @return Start Point of CheckPoint
     */
    L getStartPoint();

    /**
     * Return End Point of CheckPoint, lower y
     * @return End Point of CheckPoint
     */
    L getEndPoint();

}
