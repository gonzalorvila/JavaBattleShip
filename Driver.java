import java.awt.event.*;

import model.*;
import controller.*;
import view.*;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.*;

public class Driver
{
    public static void main(String []args)
    {
        
        GameBoard gameTable = new GameBoard();
        BattleShipController controller = new BattleShipController(gameTable);
        GameBoardState gbState = new GameBoardState(10);

        gameTable.createGameBoard(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj instanceof ShipButton) {
                    ShipButton ship = (ShipButton) obj;
                    gameTable.setSelectedShip(ship);
                    ship.setBorder(new LineBorder(Color.RED));
                    gameTable.enableUserGrid(true);
                }
            }
        }, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj instanceof GridButton) {
                    GridButton selection = (GridButton) obj;
                    controller.onGridSelection(selection, gameTable);
                }
            }
        }, true);

        
        gbState.setDifficulty(1);
        controller.startNewGame();
        //UseCases useCases = new UseCases();

        //useCases.startNewGame(shipArray, player, opponent, gbState);       
        //useCases.makeMove(shipArray, player, opponent, gbState);
        //useCases.onResult(shipArray, player, opponent, gbState);
    }
}
