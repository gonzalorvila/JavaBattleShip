package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;

//import model.*;

public class GameBoard 
{
    public static int frameSize = 1000;

    private GridPanel computerGrid;
    private GridPanel userGrid;
    private JFrame mainFrame;

    public GameBoard()
    {
        // Top level container for the game
        mainFrame = new JFrame("Battleship");
        mainFrame.setPreferredSize(new Dimension(frameSize, frameSize*3/2));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Top level panel representing 
        JPanel battleshipTable = new JPanel();
        battleshipTable.setLayout(new BoxLayout(battleshipTable, BoxLayout.Y_AXIS));

        // Panel to show the computer's grid
        this.computerGrid = new GridPanel(500, 500);

        // Panel to show user's grid
        this.userGrid = new GridPanel(500, 500);

        // Place both grids on the  battleship table
        battleshipTable.add(this.computerGrid);
        battleshipTable.add(this.userGrid);


        // Add buttons here (if necessary)


        // Add and Pack
        mainFrame.add(battleshipTable);
        mainFrame.pack();
        mainFrame.setVisible(true);
}
}