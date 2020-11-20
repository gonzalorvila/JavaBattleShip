package controller;
import java.util.ArrayList;
import model.*;
import javax.swing.*;
import view.*;
import java.awt.*;
import javax.swing.border.LineBorder;

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

    public BattleShipController(BattleShipUserInterface ui) {
        this.firstButton = new GridButton("dummy");
        this.secondSelection = false;
        this.playerShips = new ArrayList<Ships>();
        this.opponentShips = new ArrayList<Ships>();
        this.opponent = new Opponent();
        this.gbState = new GameBoardState();
        this.userInterface = ui; 
    }
    
    public void startNewGame(boolean difficulty)
    {
        this.difficulty = difficulty;
        opponent.setMoveHistory(difficulty);
        opponent.setOpponentShips();
        oppBoolArray = opponent.getOpponentShips();
        userInterface.placeOppShipsOnGrid(oppBoolArray, opponent.getOppShips());

        /*for (Ships s : ships)
        {
            // int location = player.setShipLocation(s);
            // gbState.storeLocations(player, location);
            // location = opponent.setShipLocation(s);
            // gbState.storeLocations(opponent, location);
        }*/

        gbState.setScore(5, 5);

        if ((gbState.getUserScore() != 0) && (gbState.getOpponentScore() != 0))
        {
            //makeMove(ships, opponent, gbState);
        }
        else
        {
            //onResult(ships, opponent, gbState);
        }
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
            if (moveResult) {
                return;
            }
            int[] oppGuess = new int[2];
            moveResult = true;
            
            while(moveResult) {
                oppGuess = opponent.opponentMove(playerShips);
                if(userShipLocations[oppGuess[0]][oppGuess[1]] == true) {
                    Ships s = gbState.onHit(selectedButton.getRow(), selectedButton.getColumn(), playerShips, false);
                    moveResult = true;
                } else {
                    moveResult = false;
                }
                gameTable.updateUserGrid(oppGuess, moveResult);
            }

        }
    }

    public boolean evaluateMove(GridButton selectedButton, GameBoard gameTable)
    {
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
            userInterface.setMessage("You won! :)");
        }
        else if (compResult == 0) {
            userInterface.setMessage("You lost! :(");
        }
    }

    public void playAgain() {
        this.firstButton = new GridButton("dummy");
        this.secondSelection = false;
        this.playerShips = new ArrayList<Ships>();
        this.opponentShips = new ArrayList<Ships>();
        this.opponent = new Opponent();
        this.gbState = new GameBoardState();
        
        userInterface.closeFrame();
    }
}