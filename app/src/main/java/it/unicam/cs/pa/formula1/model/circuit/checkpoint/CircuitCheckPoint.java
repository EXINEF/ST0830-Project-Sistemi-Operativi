package it.unicam.cs.pa.formula1.model.circuit.checkpoint;

import it.unicam.cs.pa.formula1.model.circuit.location.CircuitLocation;
import it.unicam.cs.pa.formula1.model.circuit.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Responsibility : This class provide the implementation of the CircuitCheckPoint
 *
 * @author Pierdominici
 */
public class CircuitCheckPoint implements CheckPoint<CheckPointType, Location> {

    private final List<CircuitLocation> allPoints;
    private final CheckPointType type;

    public CircuitCheckPoint(int x, int y1, int y2, CheckPointType type) throws IllegalArgumentException{
        this.type = type;
        if(Math.abs(y1-y2)<2)
            throw new IllegalArgumentException("A CheckPoint must be larger then 1...");
        if(y1>y2)
            throw new IllegalArgumentException("Y Start Point coordinate must be lower than Y End Point Coordinate...");
        this.allPoints = generateAllCheckPointPoints(x,y1,y2);
    }

    @Override
    public List<CircuitLocation> getPoints() {
        return allPoints;
    }

    @Override
    public boolean containsLocation(Location location) {
        for (Location allPoint : allPoints) {
            if (allPoint.getX() == location.getX() && allPoint.getY() == location.getY()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CheckPointType getType() {
        return type;
    }

    @Override
    public Location getStartPoint() {
        return allPoints.get(0);
    }

    @Override
    public Location getEndPoint() {
        return allPoints.get(allPoints.size()-1);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\n\t\tCHECKPOINT TYPE:" + type);
        for (CircuitLocation allPoint : allPoints) {
            s.append("\t").append(allPoint.toString());
        }
        return s.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(allPoints);
    }

    private List<CircuitLocation> generateAllCheckPointPoints(int x, int y1, int y2){
        List<CircuitLocation> allPoints = new ArrayList<>();
        allPoints.add(new CircuitLocation(x,y1));
        for (int i = y1+1; i<y2; i++){
            allPoints.add(new CircuitLocation(x,i));
        }
        allPoints.add(new CircuitLocation(x,y2));
        return allPoints;
    }
}
