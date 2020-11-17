package model;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class GameBoardStateTest {
    
    @Test
    public void checkDifficulty() {
        GameBoardState gbState = new GameBoardState();
        int result = gbState.setDifficulty(1);
        assertEquals(1, result);
    }

    @Test
    public void checkUserScore() {
        GameBoardState gbState = new GameBoardState();
        gbState.setScore(5, 3);
        int result = gbState.getUserScore();
        assertEquals(5, result);
    }

    @Test
    public void checkOpponentScore() {
        GameBoardState gbState = new GameBoardState();
        gbState.setScore(5, 3);
        int result = gbState.getOpponentScore();
        assertEquals(3, result);
    }

    @Test
    public void checkIsHitTrue() {
        GameBoardState gbState = new GameBoardState();
        ArrayList<Integer> locationsArray = new ArrayList<Integer>();
        locationsArray.add(23);
        locationsArray.add(24);
        locationsArray.add(25);
        boolean result = gbState.isHit(locationsArray, 23);
        assertEquals(true, result);
    }

    @Test
    public void checkIsHitFalse() {
        GameBoardState gbState = new GameBoardState();
        ArrayList<Integer> locationsArray = new ArrayList<Integer>();
        locationsArray.add(23);
        locationsArray.add(24);
        locationsArray.add(25);
        boolean result = gbState.isHit(locationsArray, 19);
        assertEquals(false, result);
    }

    @Test
    public void checkIsSunkFalse() {
        GameBoardState gbState = new GameBoardState();
        ArrayList<Integer> locationsArray = new ArrayList<Integer>();
        locationsArray.add(23);
        locationsArray.add(24);
        locationsArray.add(25);
        boolean result = gbState.isSunk(locationsArray, 24);
        assertEquals(true, result);
    }

    @Test
    public void checkIsSunkTrue() {
        GameBoardState gbState = new GameBoardState();
        ArrayList<Integer> locationsArray = new ArrayList<Integer>();
        locationsArray.add(23);
        locationsArray.add(24);
        locationsArray.add(25);
        gbState.isSunk(locationsArray, 23);
        gbState.isSunk(locationsArray, 24);
        boolean result = gbState.isSunk(locationsArray, 25);
        assertEquals(true, result);
    }

}
