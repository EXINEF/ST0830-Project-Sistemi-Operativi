package it.unicam.cs.pa.formula1.model.circuit.location;

import java.util.Objects;

/**
 * Responsibility : This class provide the implementation of the Location
 *
 * @author Pierdominici
 */
public class CircuitLocation implements Location{

    private final int x;
    private final int y;

    public CircuitLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean isNeighbour(Location location) {
        if(this.equals(location))
            return false;
        return Math.abs(this.getX() - location.getX()) < 2 && Math.abs(this.getY() - location.getY()) < 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircuitLocation that = (CircuitLocation) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString(){
        return String.format("(%d,%d)",x,y);
    }


}


