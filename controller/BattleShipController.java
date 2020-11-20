package controller;
import java.util.ArrayList;
import model.*;
import javax.swing.*;
import view.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BattleShipController
{
    private GridButton firstButton;
    private boolean secondSelection;
    private ArrayList<Ships> playerShips;
    private ArrayList<Ships> opponentShips;
    private Opponent opponent;
    private boolean[][] oppBoolArray;
    private GameBoardState gbState;
    private BattleShipUserInterface userInterface; 
    private boolean[][] userShipLocations;
    private boolean difficulty;
    private int userMoveCount;

    public BattleShipController(BattleShipUserInterface ui) {
        this.firstButton = new GridButton("dummy");
        this.secondSelection = false;
        this.playerShips = new ArrayList<Ships>();
        this.opponentShips = new ArrayList<Ships>();
        this.opponent = new Opponent();
        this.gbState = new GameBoardState();
        this.userInterface = ui;
        
        try {
            FileInputStream fileIn = new FileInputStream("bestGame.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userMoveCount = (int) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception i) {
            userMoveCount = 0;
        }
    }
    
    public void startNewGame(boolean difficulty)
    {
        this.difficulty = difficulty;
        opponent.setMoveHistory(difficulty);
        opponent.setOpponentShips();
        oppBoolArray = opponent.getOpponentShips();
        userInterface.placeOppShipsOnGrid(oppBoolArray, opponent.getOppShips());
        userMoveCount = 0;
    }

    public void onGridSelection(GridButton selectedButton, GameBoard gameTable, boolean difficulty) {
        ShipButton selectedShip = gameTable.getSelectedShip();
        if (selectedShip != null) {
            if (!secondSelection) {
                this.secondSelection = true;
                gameTable.showValidShipPlacements(selectedButton);
            }
            else {
                if (playerShips.size() < 4) {
                    gameTable.getShipPanel().toggleShipsEnabled(true);
                }
                Ships newShip = gameTable.placeShip(selectedButton);
                this.playerShips.add(newShip);
                if (playerShips.size() == 5) {
                    gbState.setUserGrid(playerShips,difficulty);
                    userShipLocations = gbState.getUserGrid();
                    gameTable.enableComputerGrid(true);
                    gameTable.setSelectedShip(null);
                    gameTable.setMessage("Your move! Choose a spot on the attack grid");
                }
                else {

                    gameTable.setMessage("Select next ship for placement");
                }
                this.secondSelection = false;
                
            }
        }
        else {
            boolean moveResult = true;
            moveResult = evaluateMove(selectedButton, gameTable);
            onResult();
            if (moveResult) {
                return;
            }
            int[] oppGuess = new int[2];
            moveResult = true;
            
            while(moveResult) {
                oppGuess = opponent.opponentMove(playerShips);
                if(userShipLocations[oppGuess[0]][oppGuess[1]] == true) {
                    Ships s = gbState.onHit(oppGuess[0], oppGuess[1], playerShips, false);
                    moveResult = true;
                } else {
                    moveResult = false;
                }
                onResult();
                gameTable.updateUserGrid(oppGuess, moveResult);
            }

        }
    }

    public boolean evaluateMove(GridButton selectedButton, GameBoard gameTable)
    {
        userMoveCount++;
        boolean moveEval = false;
        selectedButton.setEnabled(true);
        if (selectedButton.getFree()) {
            selectedButton.setBackground(Color.GREEN);
            gameTable.setMessage("Miss!");
            moveEval = false;
        }
        else {
            opponentShips = opponent.getOppShips();
            Ships hitResult = gbState.onHit(selectedButton.getRow(), selectedButton.getColumn(), opponentShips, true);
            selectedButton.setBackground(Color.RED);
            if (hitResult == null) {
                gameTable.setMessage("Hit!");
            }
            else {
                gameTable.setMessage("You sunk the " + hitResult.getShipName() + "! Ships remaining: " + gbState.getScore(true));
            }
            moveEval = true;
        }
        selectedButton.setEnabled(false);
        return moveEval;
    }

    public void onResult()
    {
        int userResult = gbState.getScore(true);
        int compResult = gbState.getScore(false);

        if (userResult == 0) {
            int oldUserMoveCount;
            try {
                FileInputStream fileIn = new FileInputStream("bestGame.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                oldUserMoveCount = (int) in.readObject();
                in.close();
                fileIn.close(); 
            } catch(Exception e) {
                oldUserMoveCount = 0;
            }
            if (oldUserMoveCount == 0 || oldUserMoveCount > userMoveCount) {
                try {
                    Path currentRelativePath = Paths.get("");
                    String pathToScore = currentRelativePath.toAbsolutePath().toString() + "bestGame.ser";
                    File oldFile = new File(pathToScore);
                    oldFile.delete();
                    FileOutputStream fileOut = new FileOutputStream("bestGame.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(userMoveCount);
                    out.close();
                    fileOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    userMoveCount = oldUserMoveCount;
                }
            }
            userInterface.setMessage("You won! :)");
            userInterface.enableComputerGrid(false);
            userInterface.enableUserGrid(false);
        }
        else if (compResult == 0) {
            userInterface.setMessage("You lost! :(");
            userInterface.enableComputerGrid(false);
            userInterface.enableUserGrid(false);
        }
    }

    public void playAgain() {
        this.firstButton = new GridButton("dummy");
        this.secondSelection = false;
        this.playerShips = new ArrayList<Ships>();
        this.opponentShips = new ArrayList<Ships>();
        this.opponent = new Opponent();
        this.gbState = new GameBoardState();
        try {
            FileInputStream fileIn = new FileInputStream("bestGame.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userMoveCount = (int) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception i) {
            userMoveCount = 0;
        }
        
        userInterface.closeFrame();
    }

    public int getUserMoveCount() {
        return this.userMoveCount;
    }
}