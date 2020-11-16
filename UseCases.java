import java.util.ArrayList;
import model.*;
import javax.swing.*;
import view.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class UseCases
{
    private GridButton firstButton;
    private boolean secondSelection;
    private ArrayList<Ships> playerShips;
    private ArrayList<Ships> opponentShips;

    public UseCases() {
        this.firstButton = new GridButton("dummy");
        this.secondSelection = false;
        this.playerShips = new ArrayList<Ships>();
        this.opponentShips = new ArrayList<Ships>();
    }
    
    public void startNewGame(ArrayList<Ships> ships, Opponent opponent, GameBoardState gbState)
    {
        for (Ships s : ships)
        {
            // int location = player.setShipLocation(s);
            // gbState.storeLocations(player, location);
            // location = opponent.setShipLocation(s);
            // gbState.storeLocations(opponent, location);
        }

        gbState.setScore(5, 5);

        if ((gbState.getUserScore() != 0) && (gbState.getOpponentScore() != 0))
        {
            //makeMove(ships, opponent, gbState);
        }
        else
        {
            onResult(ships, opponent, gbState);
        }
    }

    public void onGridSelection(GridButton selectedButton, GameBoard gameTable) {
        ShipButton selectedShip = gameTable.getSelectedShip();
        if (selectedShip != null) {
            if (!secondSelection) {
                chooseShipStart(selectedButton, gameTable, selectedShip);
            }
            else {
                chooseShipEnd(selectedButton, gameTable, selectedShip);
            }
        }
        else {
            evaluateMove(selectedButton, gameTable);
        }
    }

    public void chooseShipStart(GridButton selectedButton, GameBoard gameTable, ShipButton selectedShip) {
        gameTable.setMessage("Select the selected ship´s end location");
        GridButton button[][] = gameTable.getUserGrid().button;
        int gridSize = gameTable.getUserGrid().getGridSize();
        int selectedRow = selectedButton.getRow();
        int selectedColumn = selectedButton.getColumn();
        int shipSize = selectedShip.getShipSize();
        for (int columns = 0; columns < gridSize; columns++){
            for (int rows = 0; rows < gridSize; rows++) {
                if (button[selectedRow][selectedColumn].getFree()) {
                    button[rows][columns].setEnabled(false);
                }
            }
        }
        boolean allFree = true;
        if (selectedColumn - shipSize + 1 >= 0 && button[selectedRow][selectedColumn-shipSize+1].getFree()) {
            for (int i=selectedColumn-shipSize+1; i<= selectedColumn; i++) {
                if (!button[selectedRow][i].getFree()) {
                    allFree = false;
                }
            }
            if (allFree) {
                button[selectedRow][selectedColumn-shipSize+1].setEnabled(true);
            }
            allFree = true;
        }
        if (selectedColumn + shipSize - 1 < gridSize && button[selectedRow][selectedColumn+shipSize-1].getFree()) {
            for (int i=selectedColumn; i<= selectedColumn+shipSize-1; i++) {
                if (!button[selectedRow][i].getFree()) {
                    allFree = false;
                }
            }
            if (allFree) {
                button[selectedRow][selectedColumn+shipSize-1].setEnabled(true);
            }
            allFree = true;
        }
        if (selectedRow - shipSize + 1 >= 0 && button[selectedRow-shipSize+1][selectedColumn].getFree()) {
            for (int i=selectedRow-shipSize+1; i<= selectedRow; i++) {
                if (!button[i][selectedColumn].getFree()) {
                    allFree = false;
                }
            }
            if (allFree) {
                button[selectedRow-shipSize+1][selectedColumn].setEnabled(true);
            }
            allFree = true;
        }
        if (selectedRow + shipSize - 1 < gridSize && button[selectedRow+shipSize-1][selectedColumn].getFree()) {
            for (int i=selectedRow; i<= selectedRow+shipSize-1; i++) {
                if (!button[i][selectedColumn].getFree()) {
                    allFree = false;
                }
            }
            if (allFree) {
                button[selectedRow+shipSize-1][selectedColumn].setEnabled(true);
            }
            allFree = true;
            
        }
        gameTable.getShipPanel().toggleShipsEnabled(false);
        this.firstButton = selectedButton;
        this.secondSelection = true;
        return;
    }

    public void chooseShipEnd(GridButton secondButton, GameBoard gameTable, ShipButton selectedShip) {
        GridButton button[][] = gameTable.getUserGrid().button;
        int firstButtonRow = firstButton.getRow();
        int secondButtonRow = secondButton.getRow();
        int firstButtonColumn = firstButton.getColumn();
        int secondButtonColumn= secondButton.getColumn();
        int shipSize = selectedShip.getShipSize();
        if (playerShips.size() < 4) {
            gameTable.getShipPanel().toggleShipsEnabled(true);
        }
        if (secondButtonRow == firstButtonRow) {
            if (firstButtonColumn > secondButtonColumn) {
                for (int i=secondButtonColumn; i<= firstButtonColumn; i++) {
                    button[firstButtonRow][i].setBackground(Color.GRAY);
                    button[firstButtonRow][i].setEnabled(false);
                    button[firstButtonRow][i].setFree(false);
                }
            }
            else {
                for (int i=firstButtonColumn; i<= secondButtonColumn; i++) {
                    button[firstButtonRow][i].setBackground(Color.GRAY);
                    button[firstButtonRow][i].setEnabled(false);
                    button[firstButtonRow][i].setFree(false);
                }
            }
        }
        else {
            if (firstButtonRow > secondButtonRow) {
                for (int i=secondButtonRow; i<= firstButtonRow; i++) {
                    button[i][firstButtonColumn].setBackground(Color.GRAY);
                    button[i][firstButtonColumn].setEnabled(false);
                    button[i][firstButtonColumn].setFree(false);
                }
            }
            else {
                for (int i=firstButtonRow; i<= secondButtonRow; i++) {
                    button[i][firstButtonColumn].setBackground(Color.GRAY);
                    button[i][firstButtonColumn].setEnabled(false);
                    button[i][firstButtonColumn].setFree(false);
                }
            }            
        }
        int gridSize = gameTable.getUserGrid().getGridSize();
        for (int columns = 0; columns < gridSize; columns++){
            for (int rows = 0; rows < gridSize; rows++) {
                button[rows][columns].setEnabled(false);
            }
        }
        selectedShip.setEnabled(true);
        selectedShip.setBorder(new LineBorder(Color.GREEN));
        selectedShip.setEnabled(false);
        Ships newShip = new Ships(firstButtonRow, firstButtonColumn, secondButtonRow, secondButtonColumn);
        this.playerShips.add(newShip);
        this.secondSelection = false;

        gameTable.enableUserGrid(false);
        if (playerShips.size() == 5) {
            gameTable.enableComputerGrid(true);
            gameTable.setSelectedShip(null);
            gameTable.setMessage("Your move! Choose a spot on the attack grid");
        }
        else {
            gameTable.setMessage("Select next ship for placement");
        }
    }

    public void evaluateMove(GridButton selectedButton, GameBoard gameTable)
    {
        selectedButton.setEnabled(true);
        if (selectedButton.getFree()) {
            selectedButton.setBackground(Color.BLUE);
            gameTable.setMessage("Miss!");
        }
        else {
            selectedButton.setBackground(Color.RED);
            gameTable.setMessage("Hit!");
        }
        selectedButton.setEnabled(false);
    }



    /*public static void makeMove(ArrayList<Ships> ships, Opponent opponent, GameBoardState gbState)
    {
        boolean moveResult = false; //will be set to true during gameplay
        System.out.println("UseCases:: makeMove");
    
        while(moveResult)
        {
            // Call teminal input class to get location of attack on opponet board
            // This will return int location
            int location = 0;
            if (valid)
            {
                moveResult = gbState.isHit(location);
                if (moveResult)
                {
                    makeMove(ships, opponent, gbState);
                }
            }
        }

        if (gbState.getOpponentScore() == 0)
        {
            moveResult = false;
        }
        else
        {
            moveResult = false; //will be set to true during gameplay
        }
        opponent.opponentMove();
        
        while (moveResult)
        {
            boolean valid = gbState.checkMove(oppLocation);
            if (valid)
            {
                moveResult = gbState.isHit(oppLocation);
                if (moveResult)
                {
                    opponent.opponentMove();
                }
            }
        }

        if ((gbState.getUserScore() == 0) || gbState.getOpponentScore() == 0)
        {
            onResult(ships, opponent, gbState);
        }
    }*/

    public void onResult(ArrayList<Ships> ships, Opponent opponent, GameBoardState gbState)
    {
        if (gbState.getUserScore() == 0)
        {
            System.out.println("Opponent Wins!");
            // Final product will not print to terminal.
        }
        else
        {
            System.out.println("User Wins!");
        }
    }
}