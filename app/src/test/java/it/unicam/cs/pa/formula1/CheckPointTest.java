package it.unicam.cs.pa.formula1;

import it.unicam.cs.pa.formula1.model.circuit.checkpoint.CheckPointType;
import it.unicam.cs.pa.formula1.model.circuit.checkpoint.CircuitCheckPoint;
import it.unicam.cs.pa.formula1.model.circuit.location.CircuitLocation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckPointTest {

    @Test
    public void checkPointsContainsOwnPositions(){
        CircuitCheckPoint loc = new CircuitCheckPoint(3,0,5, CheckPointType.NORMAL);
        assertTrue(loc.containsLocation(new CircuitLocation(3,0)));
        assertTrue(loc.containsLocation(new CircuitLocation(3,1)));
        assertTrue(loc.containsLocation(new CircuitLocation(3,2)));
        assertTrue(loc.containsLocation(new CircuitLocation(3,3)));
        assertTrue(loc.containsLocation(new CircuitLocation(3,4)));
        assertTrue(loc.containsLocation(new CircuitLocation(3,5)));
        assertFalse(loc.containsLocation(new CircuitLocation(3,-1)));
        assertFalse(loc.containsLocation(new CircuitLocation(3,6)));
        assertFalse(loc.containsLocation(new CircuitLocation(2,5)));
    }
}
