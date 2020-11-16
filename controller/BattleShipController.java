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

    public BattleShipController() {
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
            evaluateMove(selectedButton, gameTable);
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
        System.out.println("BattleShipController:: makeMove");
    
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