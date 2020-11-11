package model;

import org.junit.*;
import static org.junit.Assert.*;

public class OpponentTest {

        @Test
        public void isOpponentMoveWorkingTestOne() {
                ArrayList<Integer> locations = new ArrayList<Integer>();
                Opponent opp = new Opponent(locations);
                opp.opponentMove();
                assertNotNull(locations);
        }

        @Test
        public void isOpponentMoveWorkingTestTwo() {
                //tests whether or not it accurately chooses a move after a hit has occurred by adding 1 to the location guess
                ArrayList<Integer> l = new ArrayList<Integer>();
                Opponent opp = new Opponent(l);
                opp.opponentMove();
                opp.addToMoveResultMakeTrue(0);
                opp.opponentMove();
                int prevResult = opp.getLocations(0);
                int result = opp.getLocations(1);
                assertEquals(prevResult + 1, result);
        }

        @Test
        public void willOpponentGuessDown() {
                ArrayList<Integer> l = new ArrayList<Integer>();
                Opponent opp = new Opponent(l);
                opp.opponentMove();
                opp.addToMoveResultMakeTrue(0);
                opp.opponentMove();
                opp.makeMoveResultFalse(1);
                opp.opponentMove();
                int guessHit = opp.getLocations(0);
                int result = opp.getLocations(2);
                assertEquals(guessHit + 10, result);
        }

	@Test
	public void testSetOpponentShips() {
		ArrayList<Integer> l = new ArrayList<Integer>();
		Opponent opp = new Opponent();
		opp.setOpponentShips();
		assertEquals(5, opponentShips.size());
	}
}
