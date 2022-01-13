package it.unicam.cs.pa.formula1;

import it.unicam.cs.pa.formula1.model.circuit.location.CircuitLocation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CircuitLocationTest {
    @Test
    public void checkIfPointsAreNeighbour(){
        CircuitLocation l1 = new CircuitLocation(0,0);
        CircuitLocation l2 = new CircuitLocation(-1,0);
        CircuitLocation l3 = new CircuitLocation(-1,-1);
        CircuitLocation l4 = new CircuitLocation(0,1);
        CircuitLocation l5 = new CircuitLocation(2,0);
        CircuitLocation l6 = new CircuitLocation(-2,-2);
        CircuitLocation l7 = new CircuitLocation(3,-2);
        CircuitLocation l8 = new CircuitLocation(2,1);
        CircuitLocation l9 = new CircuitLocation(3,-1);

        assertTrue(l1.isNeighbour(l2));
        assertTrue(l1.isNeighbour(l3));
        assertTrue(l1.isNeighbour(l4));

        assertFalse(l1.isNeighbour(l5));
        assertFalse(l1.isNeighbour(l6));
        assertFalse(l1.isNeighbour(l7));

        assertFalse(l5.isNeighbour(l4));
        assertTrue(l5.isNeighbour(l9));
        assertTrue(l5.isNeighbour(l8));
    }
}
