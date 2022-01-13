package it.unicam.cs.pa.formula1.model.player.car;

import it.unicam.cs.pa.formula1.model.circuit.location.Location;

/**
 * Responsibility : This interface stores the information about the Car
 *
 * @author Pierdominici
 */
public interface Car {

    /**
     * Return Color who identify Car univocal
     *
     * @return Car's color
     */
    Color getColor();

    /**
     *  Return Start Location
     * @return start Location
     */
    Location getStartLocation();

    /**
     * Return the instantaneous speed of the car
     *
     * @return speed
     */
    double getSpeed();

    /**
     * Set Speed
     *
     * @param speed new speed
     */
    void setSpeed(double speed);
}
