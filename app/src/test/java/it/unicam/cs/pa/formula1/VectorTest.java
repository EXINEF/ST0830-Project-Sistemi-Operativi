package it.unicam.cs.pa.formula1;

import it.unicam.cs.pa.formula1.model.circuit.location.CircuitLocation;
import it.unicam.cs.pa.formula1.model.engine.VectorEngine;
import it.unicam.cs.pa.formula1.model.vector.Vector;
import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void testNewGeneratedVector() {
        CircuitLocation start = new CircuitLocation(3, 3);
        CircuitLocation end = new CircuitLocation(2, 5);
        Vector oldVector = new Vector(start, end);
        Vector newVector = VectorEngine.newVector(oldVector, 0, 0);
        assertSame(oldVector.getEndPoint(), newVector.getStartPoint());
        assertEquals(newVector.getEndPoint(), new CircuitLocation(1, 7));

        CircuitLocation start1 = new CircuitLocation(1, 1);
        CircuitLocation end1 = new CircuitLocation(4, 8);
        Vector oldVector1 = new Vector(start1, end1);
        Vector newVector1 = VectorEngine.newVector(oldVector1, 0, 0);
        assertSame(oldVector1.getEndPoint(), newVector1.getStartPoint());
        assertEquals(newVector1.getEndPoint(), new CircuitLocation(7, 15));
    }

    @Test
    public void testCalculateVectorLength(){
        CircuitLocation start = new CircuitLocation(3, 4);
        CircuitLocation end = new CircuitLocation(0, 0);
        Vector vector = new Vector(start, end);
        assertEquals(5, vector.getLength(), 0.0);
        CircuitLocation start1 = new CircuitLocation(-3, -4);
        CircuitLocation end1 = new CircuitLocation(0, 0);
        Vector vector1 = new Vector(start1, end1);
        assertEquals(5, vector1.getLength(), 0.0);
    }

}
