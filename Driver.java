import java.awt.event.*;

import model.*;
import controller.*;
import view.*;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.*;
import java.io.*;

public class Driver
{
    public static void main(String []args) throws IOException
    {
        
        GameBoard gameTable = new GameBoard();
        BattleShipController controller = new BattleShipController(gameTable);
        GameBoardState gbState = new GameBoardState();

        MainMenuPanel menu = new MainMenuPanel();
        gbState.setDifficulty(true);
        menu.makeMainMenu(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj instanceof JButton){
                    JButton j = (JButton) obj;
                    if (j.getText().equals("Regular Mode")) {
                        gbState.setDifficulty(true);
                    }
                    else if (j.getText().equals("Hard Mode: Bigger Board!")) {
                        gbState.setDifficulty(false);
                    }
                    else if (j.getText().equals("Start Game")) {
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
                                    controller.onGridSelection(selection, gameTable, gbState.getDifficulty());
                                }
                            }
                        },  new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    Object obj = e.getSource();
                                    if (obj instanceof JButton) {
                                        JButton selection = (JButton) obj;
                                        controller.playAgain();
                                    }
                                }
                        }, gbState.getDifficulty(), controller.getUserMoveCount());
                        controller.startNewGame(gbState.getDifficulty());
                    }
                }
            }
        });
    }
}
