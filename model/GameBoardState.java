package model;
import java.util.ArrayList;

public class GameBoardState
{
    private Opponent opponent;
    protected ArrayList<Integer> userShipLocations;
    protected ArrayList<Integer> compShipLocations;
    private boolean userGrid[][];
    private boolean compGrid[][];
    private int userScore;
    private int opponentScore;
    private int Difficulty;

    public GameBoardState(int gridSize)
    {
	    this.userShipLocations = new ArrayList<Integer>();
      this.compShipLocations = new ArrayList<Integer>();
      this.userGrid = new boolean[10][10];
      this.compGrid = new boolean[10][10];
      for (int columns =0; columns < gridSize; columns++){
        for (int rows = 0; rows < gridSize; rows++) {
          userGrid[rows][columns] = false;
          compGrid[rows][columns] = false;
        }
      }
    }

    public void setUserGrid(boolean[][] userGrid) {
      this.userGrid = userGrid;
    }

    public boolean[][] getUserGrid() {
      return userGrid;
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


    /*public boolean isHit(GridButton button) 

    public boolean isHit(ArrayList<Integer> locationsArray, int location) 
    {
	    boolean result = false;
	    for (int i : locationsArray) {
		    if (i == location) {
			    result = true;
		    }
	    }
      return result;
    }
*/
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
