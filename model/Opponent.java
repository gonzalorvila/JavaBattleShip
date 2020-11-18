package model;
import view.*;
import java.util.*;
import java.util.ArrayList;

public class Opponent
{
	private int rowGuess;
	private int columnGuess;
	private int[] prevRowGuess;
	private int[] prevColumnGuess;
	private boolean[] moveResults;
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
	private ArrayList<Ships> userShipLocations;
	private boolean oppShipsBoolArray[][];

	public Opponent(int gridSize) 
	{
		this.gbs = new GameBoardState(10);
		this.moveResults = new boolean[100];
		this.prevColumnGuess = new int[100];
		this.prevRowGuess = new int[100];
		for(int z = 0; z < 100; z++) {
			moveResults[z] = false;
			prevColumnGuess[z] = 0;
			prevRowGuess[z] = 0;
		}
		this.opponentShips = new ArrayList<Ships>();
		this.gridSize = gridSize;
		this.userShipLocations = new ArrayList<Ships>();
	}

	public int[] opponentMove(ArrayList<Ships> playerShips)
	{
		int[] guess = new int[2];
		if (numOfMoves == 0) {
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
			guess[0] = this.rowGuess;
			guess[1] = this.columnGuess;
		} else {
			boolean[][] checkGuess = this.gbs.getCompGrid();
			boolean validGuess = true;
			while (validGuess) {
				if (numOfMoves > 4) {
					if (moveResults[numOfMoves -1] == true && prevRowGuess[numOfMoves -1] != 9 && !checkGuess[prevRowGuess[numOfMoves -1] + 1][prevColumnGuess[numOfMoves -1]]) {
						guess[1] = prevColumnGuess[numOfMoves -1];
						guess[0] = prevRowGuess[numOfMoves -1] + 1;
					}
					else if (moveResults[numOfMoves - 2] == true && prevRowGuess[numOfMoves - 2] != 0 && !checkGuess[prevRowGuess[numOfMoves - 2] - 1][prevColumnGuess[numOfMoves - 2]] ) {
						guess[1] = prevColumnGuess[numOfMoves - 2];
						guess[0] = prevRowGuess[numOfMoves - 2] - 1;
					}
					else if (moveResults[numOfMoves - 3] == true && prevColumnGuess[numOfMoves - 3] != 9 && !checkGuess[prevRowGuess[numOfMoves - 3]][prevColumnGuess[numOfMoves - 3] + 1] ) {
						guess[1] = prevColumnGuess[numOfMoves - 3] + 1;
						guess[0] = prevRowGuess[numOfMoves - 3];
					}
					else if (moveResults[numOfMoves - 4] == true && prevColumnGuess[numOfMoves - 4] != 0 && !checkGuess[prevRowGuess[numOfMoves - 3]][prevColumnGuess[numOfMoves - 3] - 1] ) {
						guess[1] = prevColumnGuess[numOfMoves - 4] - 1;
						guess[0] = prevRowGuess[numOfMoves - 4];
					} else {
						Random num = new Random();
						this.rowGuess = num.nextInt(10);
						this.columnGuess = num.nextInt(10);
						checkGuess = this.gbs.getCompGrid();
						validGuess = true;
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
						guess[0] = this.rowGuess;
						guess[1] = this.columnGuess;
					}
					checkGuess[rowGuess][columnGuess] = true;
					this.gbs.setCompGrid(checkGuess);
					validGuess = false;
				} 
				else {
					Random num = new Random();	
					this.rowGuess = num.nextInt(10);
					this.columnGuess = num.nextInt(10);
					checkGuess = this.gbs.getCompGrid();
					validGuess = true;
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
					guess[0] = this.rowGuess;
					guess[1] = this.columnGuess;
				}
				checkGuess[guess[0]][guess[1]] = true;
				this.gbs.setCompGrid(checkGuess);
				validGuess = false;
			}	
		}

		boolean[][] userGridForEval;
		userGridForEval = new boolean[10][10];
		gbs.setUserGrid(playerShips);
		userGridForEval = gbs.getUserGrid();
		if (userGridForEval[guess[0]][guess[1]] == true) {
			moveResults[numOfMoves] = true;
		} else {
			moveResults[numOfMoves] = false;
		}
		prevRowGuess[numOfMoves] = guess[0];
		prevColumnGuess[numOfMoves] = guess[1];
		numOfMoves++;
		return guess;
	}

	public void setOpponentShips() {
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
		this.oppShipsBoolArray = oppShipsBoolArray;
	}

	public boolean[][] getOpponentShips() {
		return oppShipsBoolArray;
	}

	public boolean checkForOverlaps(int startRow, int startColumn, int endRow, int endColumn, int length) {
		Ships newShip = new Ships(startRow, startColumn, endRow, endColumn, length);
		ArrayList<Integer> newRows = new ArrayList<Integer>();
		ArrayList<Integer> newColumns = new ArrayList<Integer>();
		newRows = newShip.storingRowsFilled();
		newColumns = newShip.storingColumnsFilled();
		/*for (int t = 0; t < newColumns.size(); t++) {
			System.out.println("new  column " + t + ": " + newColumns.get(t));
			System.out.println("new row " + t + ": " + newRows.get(t));
		}*/
		System.out.println(newRows.size());
		System.out.println(newColumns.size() == newShip.getShipLength());

		for (Ships s : opponentShips) {
			ArrayList<Integer> columns = new ArrayList<Integer>();
			ArrayList<Integer> rows = new ArrayList<Integer>();
			columns = s.storingColumnsFilled();
			rows = s.storingRowsFilled();
			for (int i = 0; i < newRows.size(); i++) {
				for (int j = 0; j < rows.size(); j++) {
					if(columns.get(j) == newColumns.get(i) && rows.get(j) == newRows.get(i)){
						overlaps = true;
						return overlaps;
					} else {
						overlaps = false;
					}
				}
			}
		}
		return overlaps;		
	}

}
