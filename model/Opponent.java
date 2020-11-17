package model;
import view.*;
import java.util.*;
import java.util.ArrayList;

public class Opponent
{
	private int rowGuess;
	private int columnGuess;
	private ArrayList prevMoves; // = new ArrayList();
	private ArrayList<Boolean> moveResults; // = new ArrayList();
	private int numOfMoves = 0;
	private boolean result;
	private boolean direction; // if this is true then the ship will be placed vertically otherwise it will be horizontal
	private ArrayList<Ships> userShipLocations;
	private GameBoardState gbs;
	private int[] shipLengths;
	private ArrayList<Ships> opponentShips;
	private Ships s;
	private int startRow;
	private int endRow;
	private int startColumn;
	private int endColumn;

	public Opponent(ArrayList<Integer> userShipLocations) 
	{
		this.gbs = new GameBoardState();
		this.userShipLocations = userShipLocations;
		this.moveResults = new ArrayList<Boolean>();
		this.shipLengths = new int[]{5, 4, 3, 3, 2};
		this.opponentShips = new ArrayList<Ships>();
	}

	public void opponentMove()
	{
		Random num = new Random();
		this.rowGuess = num.nextInt(10);
		this.columnGuess = num.nextInt(10);
		boolean[][] checkGuess = this.gbs.getCompGrid();
		boolean validGuess = true;
		while (validGuess) {
			if (checkGuess[rowGuess][columnGuess]) {
				this.rowGuess = num.nextInt(10);
				this.columnGuess = num.nextInt(10);
			} 
			else {
				checkGuess[rowGuess][columnGuess] = true;
				this.gbs.setCompGrid(checkGuess);
				validGuess = false;
			}
		}
	}

	public void setOpponentShips() {
		for (int i = 0; i < 5; i++) {
			int length = shipLengths[i];
			if (i ==0) {
				Random num = new Random();
				this.startColumn = num.nextInt(10);
				this.startRow = num.nextInt(10);
				this.direction = num.nextBoolean();
				if (direction) {
					this.endColumn = startColumn;
					if((startColumn + length - 1) < 10) {
						this.endRow = startRow + length -1;
					} else {
						this.endRow = startRow - length - 1;
					}
				}
			} else {

			}
			opponentShips.add(new Ships(startRow, startColumn,endRow,endColumn));
			
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

