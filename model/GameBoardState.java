package model;
import java.util.ArrayList;

public class GameBoardState
{
    private Opponent opponent;
    private boolean userShipGrid[][];
    private boolean compGrid[][];
    private int userScore;
    private int opponentScore;
    private int Difficulty;

    public GameBoardState(int gridSize)
    {
      this.userShipGrid = new boolean[gridSize][gridSize];
      this.compGrid = new boolean[gridSize][gridSize];
      for (int columns =0; columns < gridSize; columns++){
        for (int rows = 0; rows < gridSize; rows++) {
          userShipGrid[rows][columns] = false;
          compGrid[rows][columns] = false;
        }
      }
    }

    public boolean[][] setUserGrid(ArrayList<Ships> userShipLocations) {
      for (Ships s : userShipLocations) {
			  ArrayList<Integer> columns = s.storingColumnsFilled();
			  ArrayList<Integer> rows = s.storingRowsFilled();
        for (int j = 0; j < s.getShipLength(); j++)
        {
          if (columns.size() > 1) { //ship is horizontal
            userShipGrid[rows.get(0)][columns.get(j)] = true;
            System.out.println(rows.get(0) + " " + columns.get(j));
          }
          else {
            userShipGrid[rows.get(j)][columns.get(0)] = true;
            System.out.println(rows.get(j) + " " + columns.get(0));
          }
        }
		  }
      return userShipGrid;  
    }

    public void setCompGrid(boolean[][] compGrid) {
      this.compGrid = compGrid;
    }

    public boolean[][] getCompGrid() {
      return compGrid;
    }

    public void setDifficulty(int Difficulty)
    {
        this.Difficulty = Difficulty;
	    //we would then use this value of Difficulty to change stuff in the view class and this still needs to be implemented. 
	    //Right now we have two difficulties: 1 is to make all the ships size 3 so it is harder to find it, 2 is making the board bigger.
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
    
    public boolean isSunk(ArrayList<Integer> locationsArray, int location) 
    {
      boolean result = false;
      int shipLength = 0;
	    for (int i : locationsArray) {
		    if (i == location) {
          shipLength++;
          if (shipLength == locationsArray.size()) {
            result = true;
          }
		    }
	    }
      return result;
    }

}
