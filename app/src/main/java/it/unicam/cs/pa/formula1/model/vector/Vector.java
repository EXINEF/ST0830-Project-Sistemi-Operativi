package it.unicam.cs.pa.formula1.model.vector;

import it.unicam.cs.pa.formula1.model.circuit.location.Location;

/**
 * Responsibility : This class provide the implementation of the MovementVector
 *
 * @author Pierdominici
 */
public class Vector {

    private final Location startPoint;
    private final Location endPoint;
    private final double length;

    public Vector(Location startPoint, Location endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.length = calculateLength(startPoint,endPoint);
    }

    public Location getStartPoint() {
        return startPoint;
    }
    public Location getEndPoint() {
        return endPoint;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return String.format("\n\t\t\t\t\t{(%d,%d)to(%d,%d) vel:%f m/s}",startPoint.getX(),startPoint.getY(),endPoint.getX(),endPoint.getY(),length);
    }

    /**
     * Calculate length of Vector using Pythagoras' Theorem
     *
     * @param startPoint Vector Start Point
     * @param endPoint Vector End Point
     *
     * @return Vector's Length
     */
    private double calculateLength(Location startPoint, Location endPoint){
        int xA = startPoint.getX();
        int yA = startPoint.getY();
        int xB = endPoint.getX();
        int yB = endPoint.getY();
        double res = Math.sqrt((xB-xA)*(xB-xA) + (yB-yA)*(yB-yA));
        return Math.floor(res * 100) / 100;
    }
}
