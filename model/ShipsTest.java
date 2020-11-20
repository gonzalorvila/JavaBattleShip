package model;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ShipsTest {
    
    @Test
    public void checkStartColumn() {
        Ships ship = new Ships(4, 7, 6, 7, 3);
        int result = ship.getStartColumn();
        assertEquals(7, result);
    }    

    @Test
    public void checkStartRow() {
        Ships ship = new Ships(4, 7, 6, 7, 3);
        int result = ship.getStartRow();
        assertEquals(4, result);
    }

    @Test
    public void checkEndColumn() {
        Ships ship = new Ships(4, 7, 6, 7, 3);
        int result = ship.getEndColumn();
        assertEquals(7, result);
    }

    @Test
    public void checkEndRow() {
        Ships ship = new Ships(4, 7, 6, 7, 3);
        int result = ship.getEndRow();
        assertEquals(6, result);
    }

    @Test
    public void checkLength() {
        Ships ship = new Ships(4, 7, 6, 7, 3);
        int result = ship.getShipLength();
        assertEquals(3, result);
    }

    @Test
    public void checkScore() {
        Ships ship = new Ships(4, 7, 6, 7, 3);
        ship.setScore(5);
        int result = ship.getScore();
        assertEquals(5, result);
    }

    @Test
    public void checkStoringRowsFilled() {
        Ships ship = new Ships(4, 7, 6, 7, 3);
        int[] rowsFilled = ship.StoringRowsFilled();
        int result = rowsFilled.length;
        assertEquals(3, result);
    }

    @Test
    public void checkStoringColumnsFilled() {
        Ships ship = new Ships(4, 7, 6, 7, 3);
        int[] columnsFilled = ship.storingColumnsFilled();
        int result = columnsFilled.length;
        assertEquals(3, result);
    }

}
