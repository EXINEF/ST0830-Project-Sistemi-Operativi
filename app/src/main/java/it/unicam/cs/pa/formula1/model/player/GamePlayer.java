package it.unicam.cs.pa.formula1.model.player;

import it.unicam.cs.pa.formula1.model.circuit.location.Location;
import it.unicam.cs.pa.formula1.model.player.car.Car;
import it.unicam.cs.pa.formula1.model.player.car.RaceCar;
import it.unicam.cs.pa.formula1.model.circuit.checkpoint.CircuitCheckPoint;
import it.unicam.cs.pa.formula1.model.engine.VectorEngine;
import it.unicam.cs.pa.formula1.model.circuit.location.CircuitLocation;
import it.unicam.cs.pa.formula1.model.vector.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Responsibility : This class provide the implementation of the BotPlayer
 *
 * @author Pierdominici
 */
public class GamePlayer implements Player<PlayerStatus, Location>{

    private final String name;
    private final RaceCar car;
    private final List<Vector> movements;
    private final List<CircuitCheckPoint> crossedCheckPoint;
    private final boolean smart;
    private PlayerStatus status;
    private int turnCounter;
    private int lapCounter;

    public GamePlayer(String name, RaceCar car, boolean smart) {
        if(name==null)
            throw new NullPointerException("Bot's name can't be null...");
        if(car==null)
            throw new NullPointerException("Car's name can't be null...");
        if (name.length()<3)
            throw new IllegalArgumentException("Bot name must be 3 character length at least");
        this.name = name;
        this.car = car;
        this.smart = smart;
        movements = new ArrayList<>();
        crossedCheckPoint = new ArrayList<>();
        status = PlayerStatus.IN_GAME;
        turnCounter = 0;
        lapCounter = 0;
    }

    public boolean isSmart() {
        return smart;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Car getCar() {
        return car;
    }

    @Override
    public List<Vector> allMovements() {
        return movements;
    }

    @Override
    public PlayerStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(PlayerStatus newStatus) {
        if(newStatus!=PlayerStatus.IN_GAME)
            car.setSpeed(0);
        status = newStatus;
    }

    @Override
    public void addMovement(Vector movementVector){
        movements.add(movementVector);
    }

    @Override
    public Vector lastMovement(){
        return movements.get(movements.size()-1);
    }

    @Override
    public int getTurn() {
        return turnCounter;
    }

    @Override
    public int getLap() {
        return lapCounter;
    }

    @Override
    public void addLap() {
        lapCounter++;
    }

    @Override
    public void moveCar(Location bestMove, boolean isSmart) {
        turnCounter++;
        if(allMovements().size()==0){
            int x = car.getStartLocation().getX();
            int y = car.getStartLocation().getY();
            addMovement(new Vector(new CircuitLocation(x,y),new CircuitLocation(x,y)));
        }
        Vector newMovement = VectorEngine.newAutonomousVector(lastMovement(), bestMove,isSmart);
        allMovements().add(newMovement);
        car.setSpeed(newMovement.getLength());
    }

    @Override
    public String toStringScoreboard() {
        return "\tNAME:'" + name + '\'' +
                "\tCAR:" + car.getColor() +
                "\tSPEED:" + car.getSpeed() +
                "\tTURN:" + turnCounter+
                "\tLAP:" + lapCounter+
                "\tSTATUS:"+ status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePlayer botPlayer = (GamePlayer) o;
        return Objects.equals(name, botPlayer.name) && Objects.equals(car, botPlayer.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, car);
    }

    @Override
    public List<CircuitCheckPoint> getCrossedCheckPoint() {
        return crossedCheckPoint;
    }

    @Override
    public void resetCrossedCheckPoint(){
        if (crossedCheckPoint.size() > 0) {
            crossedCheckPoint.subList(0, crossedCheckPoint.size()).clear();
        }
    }
}
