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
	private GameBoardState gbs;
	//private int[] shipLengths;
	private ArrayList<Ships> opponentShips;
	private Ships s;
	private int startRow;
	private int endRow;
	private int startColumn;
	private int endColumn;
	private boolean overlaps;
	private int gridSize;

	public Opponent(int gridSize) 
	{
		this.gbs = new GameBoardState(10);
		this.moveResults = new ArrayList<Boolean>();
		this.opponentShips = new ArrayList<Ships>();
		this.gridSize = gridSize;
	}

	public int[] opponentMove()
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
		int[] guess = new int[2];
		guess[0] = this.rowGuess;
		guess[1] = this.columnGuess;
		return guess;
		//We need  to be able to check from here whether or not it was a hit or not when the move so that we can store the results in a boolean array as well as store the rows and columns guess in arrays
		//at the same index so that we can check whether the last move made was a hit and if it should guess around there. and then set up if and if else statements to guess around the box that was hit.
		//Also we only need to make sure the next guess is in near we are not going to account for if two are hit in a row or anything its not going to continue guessing that direction unless if it was the
		//first direction in the if statements
	}

	public boolean[][] setOpponentShips() {
		int[] shipLengths;
		shipLengths = new int[5];
		shipLengths[0] = 5;
		shipLengths[1] = 4;
		shipLengths[2] = 3;
		shipLengths[3] = 3;
		shipLengths[4] = 2;
		for (int i = 0; i < 5; i++) {
			int length = shipLengths[i];
			System.out.println("Ships Length: " + length);
			if (i == 0) {
				Random num = new Random();
				startColumn = num.nextInt(10);
				startRow = num.nextInt(10);
				direction = num.nextBoolean();
				if (direction) {
					endColumn = startColumn;
					if((startRow + length - 1) < 10) {
						endRow = startRow + length -1;
					} else {
						endRow = startRow - length + 1;
					}
				} else {
					endRow = startRow;
					if ((startColumn + length -1 < 10)) {
						endColumn = startColumn + (length -1);
					} else {
						endColumn = startColumn - length + 1;
					}
				}
			} else {
				boolean moveBoolean = true;
				while (moveBoolean) {
					Random num = new Random();
					startColumn = num.nextInt(10);
					startRow = num.nextInt(10);
					direction = num.nextBoolean();
					if (direction) {
						endColumn = startColumn;
						if((startRow + length - 1) < 10) {
							endRow = startRow + length -1;
						} else {
							endRow = startRow - length + 1;
						}
					} else {
						endRow = startRow;
						if ((startColumn + length -1 < 10)) {
							endColumn = startColumn + (length -1);
						} else {
							endColumn = startColumn - length + 1;
						}
					}
					moveBoolean = checkForOverlaps(startRow, startColumn, endRow, endColumn, length);
				}							
			}
			Ships s = new Ships(startRow, startColumn,endRow,endColumn, length);
			s.setShipLength(shipLengths[i]);
			opponentShips.add(s);
		}
		for (Ships s : opponentShips) {
			System.out.println("Start row:" + s.getStartRow() + ", Start Column: " + s.getStartColumn());
		}
		
		boolean oppShipsBoolArray[][] = new boolean[gridSize][gridSize];
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				oppShipsBoolArray[i][j] = false;
			}
		} 
		for (Ships s : opponentShips) {
			ArrayList<Integer> columns = s.storingColumnsFilled();
			ArrayList<Integer> rows = s.storingRowsFilled();
			for (int i = 0; i < s.getShipLength(); i++) {
				oppShipsBoolArray[columns.get(i)][rows.get(i)] = true;
			}
		}
		return oppShipsBoolArray;
	}

	public boolean checkForOverlaps(int startRow, int startColumn, int endRow, int endColumn, int length) {
		Ships newShip = new Ships(startRow, startColumn, endRow, endColumn, length);
		ArrayList<Integer> newRows = new ArrayList<Integer>();
		ArrayList<Integer> newColumns = new ArrayList<Integer>();
		newRows = newShip.storingRowsFilled();
		newColumns = newShip.storingColumnsFilled();
		for (Ships s : opponentShips) {
			ArrayList<Integer> columns = new ArrayList<Integer>();
			ArrayList<Integer> rows = new ArrayList<Integer>();
			columns = s.storingColumnsFilled();
			rows = s.storingRowsFilled();
			System.out.println(s.getShipLength());
			for (int i = 0; i < newShip.getShipLength(); i++) {
				if(columns.get(i) == newColumns.get(i) && rows.get(i) == newRows.get(i)){
					overlaps = true;
					break;
				} else {
					overlaps = false;
				}
			}
			for( int j = 0; i < newShip.getShipLength(); i++) {
				if(columns.get(s.getShipLength() - 1) == newColumns.get(j) && rows.get(s.getShipLength() - 1) == newRows.get(j)){
					overlaps = true;
					break;
				}
		}
		return overlaps;		
	}


	public void addToMoveResultMakeTrue(int i) {
		moveResults.set(i, true);
	}

	public void makeMoveResultFalse(int i) {
		moveResults.set(i, false);
	}

}
