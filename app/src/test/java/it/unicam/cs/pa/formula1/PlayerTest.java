package it.unicam.cs.pa.formula1;

import it.unicam.cs.pa.formula1.model.circuit.location.CircuitLocation;
import it.unicam.cs.pa.formula1.model.player.GamePlayer;
import it.unicam.cs.pa.formula1.model.player.PlayerStatus;
import it.unicam.cs.pa.formula1.model.player.car.Color;
import it.unicam.cs.pa.formula1.model.player.car.RaceCar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PlayerTest {

    @Test
    public void checkBotSpeed(){
        RaceCar car = new RaceCar(new Color("BLUE","#0000FF"),new CircuitLocation(0,0));
        GamePlayer bot = new GamePlayer("NAME", car,false);
        bot.moveCar(new CircuitLocation(1,0), bot.isSmart());
        bot.setStatus(PlayerStatus.OFF_ROAD);
        assertEquals(0, bot.getCar().getSpeed(), 0.0);
    }

    @Test
    public void checkBotNullPointerException(){
        assertThrows(NullPointerException.class, () -> new GamePlayer("NAME", null,false));
        assertThrows(IllegalArgumentException.class, () -> {
            RaceCar c = new RaceCar(new Color("BLUE","#0000FF"), new CircuitLocation(0, 0));
            new GamePlayer("u", c,false);
        });
    }

}
