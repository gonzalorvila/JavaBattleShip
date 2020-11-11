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

	public Opponent(ArrayList<Integer> shipLocations) {
		this.gbs = new GameBoardState();
		this.locations = shipLocations;
	}
	public void opponentMove()
	{
		if (prevMoves.isEmpty()) 
		{
			Random num = new Random();
			oppGuess = num.nextInt(101);
			prevMoves.add(oppGuess);
			result = checkOppMove(oppGuess);
			moveResults.add(result);
		}
		else 
		{
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
				prevMoves.add(oppGuess);
				result = checkOppMove(oppGuess);
				moveResults.add(result);
			}
		}
		numOfMoves++;
	}

	public boolean checkOppMove(int oppMove) {
	boolean result = gbs.isHit(locations, oppMove);
	return result;
	}
}

