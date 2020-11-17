import java.awt.event.*;

import model.*;
//import controller.*;
import view.*;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.*;

public class Driver
{
    public static void main(String []args)
    {
        
        //Opponent opponent = new Opponent();
        ArrayList<Ships> shipArray = new ArrayList<Ships>();
        UseCases useCases = new UseCases(shipArray);
        GameBoardState gbState = new GameBoardState();

        
        GameBoard gameTable = new GameBoard();
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
                    useCases.onGridSelection(selection, gameTable);
                }
            }
        });

        
        gbState.setDifficulty(1);

        //UseCases useCases = new UseCases();

        //useCases.startNewGame(shipArray, player, opponent, gbState);       
        //useCases.makeMove(shipArray, player, opponent, gbState);
        //useCases.onResult(shipArray, player, opponent, gbState);

    }
}
