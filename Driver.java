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

        mainMenuPanel menu = new mainMenuPanel();
        menu.makeMainMenu(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if (obj instanceof JButton){
                    JButton j = (JButton) obj;
                    if (j.getName() == "Regular Mode") {
                        gbState.setDifficulty(true);
                    }
                    else if (j.getName() == "Hard Mode: Bigger Board!") {
                        gbState.setDifficulty(false);
                    }
                    else if (j.getName() == "Start Game") {
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
                        }, gbstate.getDifficulty);
                    }
                }
            }
        });
        
        gbState.setDifficulty(1);
        controller.startNewGame();
        //UseCases useCases = new UseCases();

        //useCases.startNewGame(shipArray, player, opponent, gbState);       
        //useCases.makeMove(shipArray, player, opponent, gbState);
        //useCases.onResult(shipArray, player, opponent, gbState);
    }
}
