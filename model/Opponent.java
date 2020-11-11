package model;
import java.util.*;
import java.util.ArrayList;

public class Opponent
{
	private int oppGuess;
	private ArrayList prevMoves; // = new ArrayList();
	private ArrayList<Boolean> moveResults; // = new ArrayList();
	private int numOfMoves = 0;
	private boolean result;
	private ArrayList<Integer> locations;
	private GameBoardState gbs;
	private int[] shipLengths;
	private ArrayList<Ships> opponentShips;
	private Ships s;
	private int startLocation;
	private int endLocation;

	public Opponent(ArrayList<Integer> shipLocations) 
	{
		this.gbs = new GameBoardState();
		this.locations = shipLocations;
		this.moveResults = new ArrayList<Boolean>();
		this.shipLengths = new int[]{5, 4, 3, 3, 2};
	}

	public void opponentMove()
	{
		if (prevMoves.isEmpty()) 
		{
			Random num = new Random();
			oppGuess = num.nextInt(101);
			if (oppGuess == 0)
				oppGuess += 1;
		} else {

			if (moveResults.get(numOfMoves - 1)) 
			{
				oppGuess = (int)prevMoves.get(numOfMoves - 1) + 1;
				if (oppGuess > 100) 
				{
					oppGuess = (int)prevMoves.get(numOfMoves - 1) - 1;
				}
			}
			else if (moveResults.get(numOfMoves - 2)) 
			{
				oppGuess = (int)prevMoves.get(numOfMoves - 2) + 10;
				if (oppGuess > 100) {
					oppGuess = (int)prevMoves.get(numOfMoves - 2) - 10;
				}
			}
			else 
			{
				Random num = new Random();
				oppGuess = num.nextInt(101);
				if (oppGuess == 0)
					oppGuess += 1;
			}
		}
                prevMoves.add(oppGuess);
                result = checkOppMove(oppGuess);
                moveResults.add(result);
		numOfMoves++;
	}

	public boolean checkOppMove(int oppMove) {
		boolean result = gbs.isHit(locations, oppMove);
		return result;
	}

	public void setOpponentShips() {
		for (int i = 0; i < 5; i++) {
			int length = shipLengths[i];
			Random num = new Random();
			startLocation = num.nextInt(101);
			if (startLocation == 0) 
				startLocation += 1;
			boolean direction = num.nextBoolean();
			if (direction) {
				if (startLocation % 10 < 6) {
					endLocation = startLocation + (length -1);
				}
				else if (startLocation % 10 > 4) {
					endLocation = startLocation - (length -1);
				}
			} else {
				if (startLocation/10 < 6) {
					endLocation = startLocation + ((length - 1) * 10);
				} else if (startLocation/10 > 4) {
					endLocation = startLocation - ((length-1)*10);
				}
			}
			opponentShips.add(new Ships(length, startLocation, endLocation, direction));
		}
	}




	public void addToMoveResultMakeTrue(int i) {
		moveResults.set(i, true);
	}

	public void makeMoveResultFalse(int i) {
		moveResults.set(i, false);
	}

	public int getLocations(int i) {
		int l = locations.get(i);
		return l;
	}

}

