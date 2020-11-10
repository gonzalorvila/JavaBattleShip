package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;

//import model.*;

public class GameBoard extends JPanel 
{
    public static int frameSize = 1500;

    //private GridPanel computerGrid;
    private GridPanel computerGrid;
    private GridPanel userGrid;
    private JFrame mainFrame;
    private ShipPanel ships;
    /*private ShipPanel Carrier;
    private ShipPanel battleship;
    private ShipPanel cruiser;
    private ShipPanel submarine;
    private ShipPanel destroyer;*/


    public GameBoard()
    {
        // Top level container for the game
        mainFrame = new JFrame("Battleship");
        mainFrame.setPreferredSize(new Dimension(frameSize, frameSize*2/7));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Top level panel representing 
        JPanel battleshipTable = new JPanel();
        battleshipTable.setLayout(new BoxLayout(battleshipTable, BoxLayout.X_AXIS));

        // Panel to show the computer's grid
        this.computerGrid = new GridPanel(10,50,50);

        // Panel to show user's grid
        this.userGrid = new GridPanel(10, 50, 50);

        // Extra panel for padding
        JPanel padding = new JPanel();
        padding.setPreferredSize(new Dimension(75, frameSize*2/7));
        padding.setBackground(new Color(0, 0, 255));


        //Panel to show ships
        this.ships = new ShipPanel(100, 200);
        ships.addShip(5);
        ships.addShip(4);
        ships.addShip(3);
        ships.addShip(3);
        ships.addShip(2);
        

        // Place both grids on the  battleship table
        battleshipTable.add(this.computerGrid);
        battleshipTable.add(padding);
        battleshipTable.add(this.userGrid);
        battleshipTable.add(this.ships);


        // Add buttons here (if necessary)
        

        // Add and Pack
        mainFrame.add(battleshipTable);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
