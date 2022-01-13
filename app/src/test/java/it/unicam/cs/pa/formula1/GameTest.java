package it.unicam.cs.pa.formula1;

import it.unicam.cs.pa.formula1.model.RacetrackGame;
import it.unicam.cs.pa.formula1.model.circuit.checkpoint.CheckPointType;
import it.unicam.cs.pa.formula1.model.circuit.checkpoint.CircuitCheckPoint;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void checkNullPointerAndIllegalArgumentExceptions() {
        assertThrows(NullPointerException.class, () -> new RacetrackGame(null, null, null));
        assertThrows(IllegalArgumentException.class, () -> new CircuitCheckPoint(0, 1, 0, CheckPointType.NORMAL));
        assertThrows(IllegalArgumentException.class, () -> new CircuitCheckPoint(3, 0, -5, CheckPointType.NORMAL));
    }
}
