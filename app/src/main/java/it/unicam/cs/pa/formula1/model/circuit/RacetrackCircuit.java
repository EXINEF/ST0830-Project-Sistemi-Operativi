package it.unicam.cs.pa.formula1.model.circuit;

import it.unicam.cs.pa.formula1.model.circuit.checkpoint.CheckPointType;
import it.unicam.cs.pa.formula1.model.circuit.checkpoint.CircuitCheckPoint;
import it.unicam.cs.pa.formula1.model.circuit.location.Location;

import java.util.List;

/**
 * Responsibility : This class provide the implementation of the RaceCircuit
 *
 * @author Pierdominici
 */
public class RacetrackCircuit implements Circuit<CheckPointType, Location>{
    private final List<CircuitCheckPoint> allCheckpoints;
    private final CircuitType type;
    private final int laps;
    private final int width;
    private final int height;
    private final int obligatoryCheckPoints;

    public RacetrackCircuit(List<CircuitCheckPoint> allCheckpoints, CircuitType type, int laps, int width, int height) {
        if(type==null) throw new NullPointerException("Circuit Type can't be null...");
        if(allCheckpoints==null) throw new NullPointerException("CheckPoints can't be null...");
        if(laps<0) throw new IllegalArgumentException("Laps must be positive integer...");
        if(type==CircuitType.LINEAR && laps != 1) throw new IllegalArgumentException("Linear Circuit must have only 1 lap...");
        if(width<1||height<1)throw new IllegalArgumentException("Circuit width and height must be bigger than 0...");
        this.allCheckpoints = allCheckpoints;
        obligatoryCheckPoints = calculateObligatoryCheckPoint();
        this.type = type;
        this.laps = laps;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getObligatoryCheckPoints() {
        return obligatoryCheckPoints;
    }

    @Override
    public List<CircuitCheckPoint> getCheckPoints() {
        return allCheckpoints;
    }

    @Override
    public CircuitType getType() {
        return type;
    }

    @Override
    public int getLaps() {
        return laps;
    }

    @Override
    public CheckPointType containsPosition(Location location) {
        for (CircuitCheckPoint allCheckpoint : allCheckpoints) {
            if (allCheckpoint.containsLocation(location)) {
                return allCheckpoint.getType();
            }
        }
        return null;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return  "\n\tTYPE=" + type +
                "\n\tLAPS=" + laps +
                "\n\tCHECKPOINTS: "+ allCheckpoints;
    }

    private int calculateObligatoryCheckPoint(){
        int counter = 0;
        for (CircuitCheckPoint allCheckpoint : allCheckpoints) {
            if (allCheckpoint.getType() == CheckPointType.OBLIGATORY)
                counter++;
        }
        return counter;
    }
}
