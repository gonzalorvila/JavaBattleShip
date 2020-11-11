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
        UseCases useCases = new UseCases();
        //Opponent opponent = new Opponent();
        ArrayList<Ships> shipArray = new ArrayList<Ships>();
        for (int i = 0; i <= 4; i++) {
            shipArray.add(new Ships());
        }
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
                if (obj instanceof JButton) {
                    JButton selection = (JButton) obj;
                    useCases.onGridSelection(selection, gameTable);
                }
            }
        });

        
        gbState.setDifficulty(1);

        

        //useCases.startNewGame(shipArray, opponent, gbState);       
        //useCases.makeMove(shipArray, opponent, gbState);
        //useCases.onResult(shipArray, opponent, gbState);
    }
}