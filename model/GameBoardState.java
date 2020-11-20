package model;
import java.util.ArrayList;

public class GameBoardState
{
  private boolean userShipGrid[][];
  private boolean compGuessGrid[][];
  private int opponentScore;
  private boolean Difficulty;
  private int gridSize;
  private ArrayList<Integer> rowLocation;
  private ArrayList<Integer> columnLocation;
  private ArrayList<Ships> userShips;
  private int userScore;
  private int compScore;

  public GameBoardState()
  {
    this.userScore = 5;
    this.compScore = 5;
    this.userShips = new ArrayList<Ships>();
    this.rowLocation = new ArrayList<Integer>();
    this.columnLocation = new ArrayList<Integer>();
  }

  public void setUserGrid(ArrayList<Ships> userShipLocations, boolean Difficulty) {
    if (Difficulty) {
      this.userShipGrid = new boolean[10][10];
    } else {
      this.userShipGrid = new boolean[15][15];
    }
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
    if(Difficulty) {
      this.gridSize = 10;
      this.userShipGrid = new boolean[gridSize][gridSize];
      this.compGuessGrid = new boolean[gridSize][gridSize];
      for (int columns =0; columns < gridSize; columns++){
        for (int rows = 0; rows < gridSize; rows++) {
          userShipGrid[rows][columns] = false;
          compGuessGrid[rows][columns] = false;
        }
      }
    } else {
      this.gridSize = 15;
      this.userShipGrid = new boolean[gridSize][gridSize];
      this.compGuessGrid = new boolean[gridSize][gridSize];
      for (int columns =0; columns < gridSize; columns++){
        for (int rows = 0; rows < gridSize; rows++) {
          userShipGrid[rows][columns] = false;
          compGuessGrid[rows][columns] = false;
        }
      }
    }
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

  public int getScore(boolean userName) {
    if (userName) {
      return this.userScore;
    } else {
      return this.compScore;
    }
  }
  
  public Ships onHit(int row, int column, ArrayList<Ships> ships, boolean userName)
  {
    for (Ships s: ships) {
      ArrayList<Integer> columns = s.storingColumnsFilled();
      ArrayList<Integer> rows = s.storingRowsFilled();
      for (int i = 0; i < rows.size(); i++) {
        if ((userName && (row == columns.get(i) && column == rows.get(i))) || (!userName && (row == rows.get(i) && column == columns.get(i)))) {
          s.setScore(s.getScore()-1);
          if (s.getScore() == 0) {
            if (userName) {
              userScore--;
            } else {
              compScore--;
            }
            return s;
          }
        }
      }
    }
    return null;
  }

}
