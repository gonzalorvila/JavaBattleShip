package model;
import view.*;
import java.util.*;
import java.util.ArrayList;

public class Opponent
{
	private ArrayList<Integer> locations;
	private GameBoardState gbs;
	private int[] shipLengths;
	private ArrayList<Ships> opponentShips;
	private Ships s;
	private int startRow;
	private int endRow;
	private int startColumn;
	private int endColumn;
	private boolean overlaps;

	public Opponent(ArrayList<Ships> userShipLocations) 
	{
		this.gbs = new GameBoardState(10);
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
		//this.result = gbs.isHit(GameBoard.userGrid.button[rowGuess][columnGuess]);
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
				boolean result = true;
				while (!result) {
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
					result = checkForOverlaps(startRow, startColumn, endRow, endColumn);
				}							
			}
			opponentShips.add(new Ships(startRow, startColumn,endRow,endColumn));
		}
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


