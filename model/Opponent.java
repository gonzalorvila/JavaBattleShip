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
	private int[] shipLengths;
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
		this.shipLengths = new int[]{5, 4, 3, 3, 2};
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
	}

	public boolean[][] setOpponentShips() {
		for (int i = 0; i < 5; i++) {
			int length = shipLengths[i];
			if (i ==0) {
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
				overlaps = true;
				while (!overlaps) {
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
					overlaps = checkForOverlaps(startRow, startColumn, endRow, endColumn);
				}							
			}
			Ships s = new Ships(startRow, startColumn,endRow,endColumn);
			s.setShipLength(shipLengths[i]);
			opponentShips.add(s);
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

	public boolean checkForOverlaps(int startRow, int startColumn, int endRow, int endColumn) {
		Ships newShip = new Ships(startRow, startColumn, endRow, endColumn);
		ArrayList<Integer> newRows = new ArrayList<Integer>();
		ArrayList<Integer> newColumns = new ArrayList<Integer>();
		newRows = newShip.storingRowsFilled();
		newColumns = newShip.storingColumnsFilled();
		for (Ships s : opponentShips) {
			ArrayList<Integer> columns = new ArrayList<Integer>();
			ArrayList<Integer> rows = new ArrayList<Integer>();
			columns = s.storingColumnsFilled();
			rows = s.storingRowsFilled();
			for (int i : columns) {
				for (int j : rows) {
					for(int c : newColumns) {
						for (int r : newRows) {
							if (r == j && c == i) {
								overlaps = true;
								break;
							} else {
								overlaps = false;
							}
						}
					}
				}
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
