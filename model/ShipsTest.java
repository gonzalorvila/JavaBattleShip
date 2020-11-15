package model;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ShipsTest {
    
    @Test
    public void checkLength() {
        Ships ship = new Ships(3, 23, 25, true);
        int result = ship.getLength();
        assertEquals(3, result);
    }

    @Test
    public void checkStartLocation() {
        Ships ship = new Ships(3, 23, 25, true);
        int result = ship.getStartLocation();
        assertEquals(23, result);
    }

    @Test
    public void checkEndLocation() {
        Ships ship = new Ships(3, 23, 25, true);
        int result = ship.getEndLocation();
        assertEquals(25, result);
    }

    @Test
    public void checkDirection() {
        Ships ship = new Ships(3, 23, 25, true);
        boolean result = ship.getDirection();
        assertEquals(true, result);
    }

}
