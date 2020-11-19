package model;
import java.util.ArrayList;

public class GameBoardState
{
    private Opponent opponent;
    private boolean userShipGrid[][];
    private boolean compGuessGrid[][];
    private int userScore;
    private int opponentScore;
    private boolean Difficulty;
    private int gridSize;
    private ArrayList<Integer> rowLocation;
    private ArrayList<Integer> columnLocation;
    private ArrayList<Ships> userShips;

    public GameBoardState(int gridSize)
    {
      this.gridSize = gridSize;
      this.userShips = new ArrayList<Ships>();
      this.rowLocation = new ArrayList<Integer>();
      this.columnLocation = new ArrayList<Integer>();
      this.userShipGrid = new boolean[gridSize][gridSize];
      this.compGuessGrid = new boolean[gridSize][gridSize];
      for (int columns =0; columns < gridSize; columns++){
        for (int rows = 0; rows < gridSize; rows++) {
          userShipGrid[rows][columns] = false;
          compGuessGrid[rows][columns] = false;
        }
      }
    }

    public void setUserGrid(ArrayList<Ships> userShipLocations) {
      userShips = userShipLocations;
      for (Ships s : userShipLocations) {
			  ArrayList<Integer> columns = s.storingColumnsFilled();
        ArrayList<Integer> rows = s.storingRowsFilled();
        rowLocation.add(rows.get(0));
        columnLocation.add(columns.get(0));
        for (int j = 0; j < s.getShipLength(); j++)
        {
          userShipGrid[rows.get(j)][columns.get(j)] = true;
        }
      }
      this.userShipGrid = userShipGrid;
    }

    public boolean[][] getUserGrid() {
      return userShipGrid;
    }

    public void setCompGrid(boolean[][] compGrid) {
      this.compGuessGrid = compGrid;
    }

    public boolean[][] getCompGrid() {
      return compGuessGrid;
    }

    public void setDifficulty(boolean Difficulty)
    {
        this.Difficulty = Difficulty;
	    //we would then use this value of Difficulty to change stuff in the view class and this still needs to be implemented. 
	    //Right now we have two difficulties: 1 is to make all the ships size 3 so it is harder to find it, 2 is making the board bigger.
    }

    public boolean getDifficulty() {
      return Difficulty;
    }

    public void setScore(int userScore, int opponentScore) 
    {
        this.userScore = userScore;
        this.opponentScore = opponentScore;
    }

    public int getUserScore() 
    {
		return userScore; 
    }

    public int getOpponentScore() 
    {
		return opponentScore; 
    }
    
    public boolean onHit(int row, int column, ArrayList<Ships> ships)
    {
      for (Ships s: ships) {
        ArrayList<Integer> columns = s.storingColumnsFilled();
        ArrayList<Integer> rows = s.storingRowsFilled();
        for (int i = 0; i < rows.size(); i++) {
          System.out.println("Guess: " + row + ", " + column);
          System.out.println("Ship location: " + rows.get(i) + ", " + columns.get(i));
          if (row == columns.get(i) && column == rows.get(i)) {
            s.setScore(s.getScore()-1);
            System.out.println(s.getScore());
            if (s.getScore() == 0) {
              isSunk(row, column);
              return false;
            }
          }
        }
      }
      return true;
    }

    public boolean isSunk(int row, int column) 
    {/*
      boolean result = false;
      int shipLength = 0;
      for (int columns =0; columns < gridSize; columns++){
        for (int rows = 0; rows < gridSize; rows++) {
          userShipGrid[rows][columns] = false;
          compGrid[rows][columns] = false;
        }
      }
	    for (int i : locationsArray) {
		    if (i == location) {
          shipLength++;
          if (shipLength == locationsArray.size()) {
            result = true;
          }
		    }
	    }
      return result;*/
      return false;
    }

}
