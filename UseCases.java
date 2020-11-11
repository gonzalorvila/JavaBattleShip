import java.util.ArrayList;
import model.*;
import javax.swing.*;
import view.*;

public class UseCases
{
    
    public static void startNewGame(ArrayList<Ships> ships, Player player, Opponent opponent, GameBoardState gbState)
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
            makeMove(ships, player, opponent, gbState);
        }
        else
        {
            onResult(ships, player, opponent, gbState);
        }
    }

    public static void onGridSelection(JButton selectedButton, GameBoard gameTable) {
        ShipButton selectedShip = gameTable.getSelectedShip();
        if (selectedShip != null) {
            chooseShipLocation(selectedButton, gameTable, selectedShip);
        }
    }

    public static void chooseShipLocation(JButton selectedButton, GameBoard gameTable, ShipButton selectedShip) {
        JButton button[][] = gameTable.getUserGrid().button;
        int gridSize = gameTable.getUserGrid().getGridSize();
        int selectedRow = 0;
        int selectedColumn = 0;
        for (int columns = 0; columns < gridSize; columns++){
            for (int rows = 0; rows < gridSize; rows++) {
                if (button[rows][columns] != selectedButton) {
                    button[rows][columns].setEnabled(false);
                }
                else {
                    selectedRow = rows;
                    selectedColumn = columns;
                }
            }
        }
        int shipSize = selectedShip.getShipSize();
        if (selectedColumn - shipSize + 1 >= 0) {
            button[selectedRow][selectedColumn-shipSize+1].setEnabled(true);
        }
        if (selectedColumn + shipSize - 1 < gridSize) {
            button[selectedRow][selectedColumn+shipSize-1].setEnabled(true);
        }
        if (selectedRow - shipSize + 1 >= 0) {
            button[selectedRow-shipSize+1][selectedColumn].setEnabled(true);
        }
        if (selectedRow + shipSize - 1 < gridSize) {
            button[selectedRow+shipSize-1][selectedColumn].setEnabled(true);
        }
        return;
    }

    public static void makeMove(ArrayList<Ships> ships, Player player, Opponent opponent, GameBoardState gbState)
    {
        boolean moveResult = false; //will be set to true during gameplay
        System.out.println("UseCases:: makeMove");
    
        while(moveResult)
        {
            // Call teminal input class to get location of attack on opponet board
            // This will return int location
            int location = 0;
            boolean valid = gbState.checkMove(location);
            if (valid)
            {
                moveResult = gbState.isHit(location);
                if (moveResult)
                {
                    makeMove(ships, player, opponent, gbState);
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
        int oppLocation = opponent.opponentMove();
        
        while (moveResult)
        {
            boolean valid = gbState.checkMove(oppLocation);
            if (valid)
            {
                moveResult = gbState.isHit(oppLocation);
                if (moveResult)
                {
                    oppLocation = opponent.opponentMove();
                }
            }
        }

        if ((gbState.getUserScore() == 0) || gbState.getOpponentScore() == 0)
        {
            onResult(ships, player, opponent, gbState);
        }
    }

    public static void onResult(ArrayList<Ships> ships, Player player, Opponent opponent, GameBoardState gbState)
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