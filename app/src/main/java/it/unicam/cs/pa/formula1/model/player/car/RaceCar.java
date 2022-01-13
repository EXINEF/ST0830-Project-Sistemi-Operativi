package it.unicam.cs.pa.formula1.model.player.car;

import it.unicam.cs.pa.formula1.model.circuit.location.CircuitLocation;
import java.util.Objects;

/**
 * Responsibility : This class provide the implementation of the RaceCar
 *
 * @author Pierdominici
 */
public class RaceCar implements Car{
    private final Color color;
    private final CircuitLocation startLocation;
    private double speed;

    public RaceCar(Color color, CircuitLocation startLocation){
        if(color==null)
            throw new NullPointerException("Color can't be null...");
        if(startLocation==null)
            throw new NullPointerException("Start Position can't be null...");
        this.color = color;
        this.startLocation = startLocation;
        speed = 0;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public CircuitLocation getStartLocation(){
        return startLocation;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceCar raceCar = (RaceCar) o;
        return Objects.equals(color, raceCar.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return "\n\t\t\tCOLOR=" + color +
                "\n\t\t\tSPEED=" + speed;
    }
}
