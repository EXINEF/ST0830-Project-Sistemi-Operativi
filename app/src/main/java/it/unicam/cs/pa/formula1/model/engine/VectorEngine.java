package it.unicam.cs.pa.formula1.model.engine;

import it.unicam.cs.pa.formula1.model.circuit.location.CircuitLocation;
import it.unicam.cs.pa.formula1.model.circuit.location.Location;
import it.unicam.cs.pa.formula1.model.vector.Vector;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsibility : This class provide the Vector Movement System
 *
 * @author Pierdominici
 */
public class VectorEngine {

    /**
     * Bot use this method to move autonomously, depending from it's difficulty
     *
     * @param vector bot' s vector
     *
     * @return new Vector
     */
    public static Vector newAutonomousVector(Vector vector, Location bestMove, boolean isSmart){
        Vector newVector = newVector(vector,0,0);
        int x,y;
        if(isSmart){
            x = bestMove.getX() - newVector.getEndPoint().getX();
            y = bestMove.getY() - newVector.getEndPoint().getY();
        }
        else{
            x = bestMove.getX() - newVector.getEndPoint().getX() + getIntegerBetween(-1,1);
            y = bestMove.getY() - newVector.getEndPoint().getY() + getIntegerBetween(-1,1);
        }

        return newVector(vector,chooseCoordinate(x),chooseCoordinate(y));
    }

    /**
     * Generate a new Array
     *
     * @param vector previous vector
     * @param x -1, 0, +1
     * @param y -1, 0, +1
     *
     * @return new MovementVector
     *
     * @throws NullPointerException if given Vector is NULL
     * @throws IllegalArgumentException if x or y are not -1,0,1
     */
    public static Vector newVector(Vector vector, int x, int y) throws NullPointerException,IllegalArgumentException{
        if(x<-1||x>1)
            throw new IllegalArgumentException("This is an unfair move, x can only be -1,0 or 1...");
        if(y<-1||y>1)
            throw new IllegalArgumentException("This is an unfair move, y can only be -1,0 or 1...");

        int startX = vector.getStartPoint().getX();
        int startY = vector.getStartPoint().getY();
        int endX = vector.getEndPoint().getX();
        int endY = vector.getEndPoint().getY();

        Location newEndPoint = new CircuitLocation(getNewCoordinate(startX,endX)+x, getNewCoordinate(startY,endY)+y);
        return new Vector(vector.getEndPoint(), newEndPoint);
    }

    /**
     * If coordinate>1 return 1, if coordinate <-1 return -1, else return c,
     * to avoid IllegalArgumentException in newVector
     *
     * @param c coordinate
     *
     * @return new coordinate
     */
    private static int chooseCoordinate(int c){
        if(c>1)
            return 1;
        else if (c<-1)
            return -1;
        else
            return c;
    }

    /**
     * Calculate new coordinate, of movement vector
     *
     * @param start start Coordinate
     * @param end end Coordinate
     *
     * @return new Coordinate
     */
    private static int getNewCoordinate(int start, int end){
        if(end<start)
            return end - Math.abs(start-end);
        else if(end>start)
            return end + Math.abs(start-end);
        else
            return start;
    }

    /**
     * Return an Integer between two given numbers
     *
     * @param min First Number
     * @param max Second Number
     * @return random Number
     */
    private static int getIntegerBetween(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max +1);
    }
}
