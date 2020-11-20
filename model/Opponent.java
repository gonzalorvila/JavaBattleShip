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
	private boolean[][] compGuessGrid;

	public Opponent() 
	{
		this.gbs = new GameBoardState();
		this.opponentShips = new ArrayList<Ships>();
		this.userShipLocations = new ArrayList<Ships>();
	}

	public int[] opponentMove(ArrayList<Ships> playerShips, boolean difficulty)
	{
		if (difficulty) {
			gridSize = 10;
			this.moveResults = new boolean[100];
			this.prevColumnGuess = new int[100];
			this.prevRowGuess = new int[100];
			for(int z = 0; z < 100; z++) {
				moveResults[z] = false;
				prevColumnGuess[z] = 0;
				prevRowGuess[z] = 0;
			}
			this.compGuessGrid = new boolean[gridSize][gridSize];
			for (int columns =0; columns < gridSize; columns++){
				for (int rows = 0; rows < gridSize; rows++) {
					compGuessGrid[rows][columns] = false;
				}
			}
			this.gbs.setCompGrid(compGuessGrid);
		} else {
			gridSize = 15;
			this.moveResults = new boolean[225];
			this.prevColumnGuess = new int[225];
			this.prevRowGuess = new int[225];
			for(int z = 0; z < 225; z++) {
				moveResults[z] = false;
				prevColumnGuess[z] = 0;
				prevRowGuess[z] = 0;
			}
			this.compGuessGrid = new boolean[gridSize][gridSize];
			for (int columns =0; columns < gridSize; columns++){
				for (int rows = 0; rows < gridSize; rows++) {
					compGuessGrid[rows][columns] = false;
				}
			}
			this.gbs.setCompGrid(compGuessGrid);
		}
		int[] guess = new int[2];
		if (numOfMoves == 0) {
			Random num = new Random();
			this.rowGuess = num.nextInt(gridSize);
			this.columnGuess = num.nextInt(gridSize);
			boolean[][] checkGuess = this.gbs.getCompGrid();
			boolean validGuess = true;
			while (validGuess) {
				if (checkGuess[rowGuess][columnGuess]) {
					this.rowGuess = num.nextInt(gridSize);
					this.columnGuess = num.nextInt(gridSize);
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
				if (numOfMoves > 5) {
					if (moveResults[numOfMoves -1] == true && prevRowGuess[numOfMoves -1] != (gridSize - 1) && !checkGuess[prevRowGuess[numOfMoves -1] + 1][prevColumnGuess[numOfMoves -1]]) {
						guess[1] = prevColumnGuess[numOfMoves -1];
						guess[0] = prevRowGuess[numOfMoves -1] + 1;
					}
					else if (moveResults[numOfMoves - 2] == true && prevRowGuess[numOfMoves - 2] != 0 && !checkGuess[prevRowGuess[numOfMoves - 2] - 1][prevColumnGuess[numOfMoves - 2]] ) {
						guess[1] = prevColumnGuess[numOfMoves - 2];
						guess[0] = prevRowGuess[numOfMoves - 2] - 1;
					}
					else if (moveResults[numOfMoves - 3] == true && prevColumnGuess[numOfMoves - 3] != (gridSize - 1) && !checkGuess[prevRowGuess[numOfMoves - 3]][prevColumnGuess[numOfMoves - 3] + 1] ) {
						guess[1] = prevColumnGuess[numOfMoves - 3] + 1;
						guess[0] = prevRowGuess[numOfMoves - 3];
					}
					else if (moveResults[numOfMoves - 4] == true && prevColumnGuess[numOfMoves - 4] != 0 && !checkGuess[prevRowGuess[numOfMoves - 4]][prevColumnGuess[numOfMoves - 4] - 1] ) {
						guess[1] = prevColumnGuess[numOfMoves - 4] - 1;
						guess[0] = prevRowGuess[numOfMoves - 4];
					} else {
						Random num = new Random();
						this.rowGuess = num.nextInt(gridSize);
						this.columnGuess = num.nextInt(gridSize);
						checkGuess = this.gbs.getCompGrid();
						validGuess = true;
						while (validGuess) {
							if (checkGuess[rowGuess][columnGuess]) {
								this.rowGuess = num.nextInt(gridSize);
								this.columnGuess = num.nextInt(gridSize);
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
					this.rowGuess = num.nextInt(gridSize);
					this.columnGuess = num.nextInt(gridSize);
					checkGuess = this.gbs.getCompGrid();
					validGuess = true;
					while (validGuess) {
						if (checkGuess[rowGuess][columnGuess]) {
							this.rowGuess = num.nextInt(gridSize);
							this.columnGuess = num.nextInt(gridSize);
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
		userGridForEval = new boolean[gridSize][gridSize];
		gbs.setUserGrid(playerShips, difficulty);
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

	public void setOpponentShips(boolean difficulty) {
		if (difficulty) {
			gridSize = 10;
			this.moveResults = new boolean[100];
			this.prevColumnGuess = new int[100];
			this.prevRowGuess = new int[100];
		} else {
			gridSize = 15;
			this.moveResults = new boolean[225];
			this.prevColumnGuess = new int[225];
			this.prevRowGuess = new int[225];
		}
		int[] shipLengths;
		shipLengths = new int[5];
		shipLengths[0] = 5;
		shipLengths[1] = 4;
		shipLengths[2] = 3;
		shipLengths[3] = 3;
		shipLengths[4] = 2;
		for (int i = 0; i < 5; i++) {
			int length = shipLengths[i];
			if (i == 0) {
				Random num = new Random();
				startColumn = num.nextInt(gridSize);
				startRow = num.nextInt(gridSize);
				direction = num.nextBoolean();
				if (direction) {
					endColumn = startColumn;
					if((startRow + length - 1) < gridSize) {
						endRow = startRow + length -1;
					} else {
						endRow = startRow - length + 1;
					}
				} else {
					endRow = startRow;
					if ((startColumn + length -1 < gridSize)) {
						endColumn = startColumn + (length -1);
					} else {
						endColumn = startColumn - length + 1;
					}
				}
			} else {
				boolean moveBoolean = true;
				while (moveBoolean) {
					Random num = new Random();
					startColumn = num.nextInt(gridSize);
					startRow = num.nextInt(gridSize);
					direction = num.nextBoolean();
					if (direction) {
						endColumn = startColumn;
						if((startRow + length - 1) < gridSize) {
							endRow = startRow + length -1;
						} else {
							endRow = startRow - length + 1;
						}
					} else {
						endRow = startRow;
						if ((startColumn + length -1 < gridSize)) {
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

	public ArrayList<Ships> getOppShips() {
		return opponentShips;
	}

}
