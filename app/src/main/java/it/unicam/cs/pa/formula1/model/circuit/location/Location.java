package it.unicam.cs.pa.formula1.model.circuit.location;

/**
 * Responsibility : This interface stores the information about Location
 *
 * @author Pierdominici
 */
public interface Location {

    /**
     * Get x of this Location
     *
     * @return x
     */
    int getX();

    /**
     * Get y of this Location
     *
     * @return y
     */
    int getY();

    /**
     * Check if a given point is neighbour of this
     *
     * @param location location
     * @return ifIsNeighbour
     */
    boolean isNeighbour(Location location);
}
