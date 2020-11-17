package view;

import model.*;
import controller.BattleShipUserInterface;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;


public class GameBoard extends JPanel implements BattleShipUserInterface
{
    public static int frameSize = 2000;
    private GridPanel computerGrid;
    private GridPanel userGrid;
    private JFrame mainFrame;
    private ShipPanel ships;
    private ShipButton selectedShip;
    private JLabel message;
    private GridButton firstButton;
    private boolean difficulty;
    /*private ShipPanel Carrier;
    private ShipPanel battleship;
    private ShipPanel cruiser;
    private ShipPanel submarine;
    private ShipPanel destroyer;*/

    public void createGameBoard(ActionListener shipActionListener, ActionListener gridActionListener, boolean difficulty) {
        this.firstButton = new GridButton("dummy");
        // Top level container for the game
        mainFrame = new JFrame("Battleship");
        mainFrame.setPreferredSize(new Dimension(frameSize, frameSize*2/7));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Top level panel representing 
        JPanel battleshipTable = new JPanel();
        battleshipTable.setLayout(new BoxLayout(battleshipTable, BoxLayout.X_AXIS));

        if(difficulty) {
            this.computerGrid = new GridPanel(10,50,50, gridActionListener, false);
            // Panel to show user's grid
            this.userGrid = new GridPanel(10, 50, 50, gridActionListener, true);
        } else {
            // Panel to show the computer's grid
            this.computerGrid = new GridPanel(15,50,50, gridActionListener, false);
            // Panel to show user's grid
            this.userGrid = new GridPanel(15, 50, 50, gridActionListener, true);            
        }

        //Panel to show ships
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1));
        rightPanel.setPreferredSize(new Dimension(400, 200));
        
        this.ships = new ShipPanel(100, 100);
        ships.addShip(5, shipActionListener);
        ships.addShip(4, shipActionListener);
        ships.addShip(3, shipActionListener);
        ships.addShip(3, shipActionListener);
        ships.addShip(2, shipActionListener);
        rightPanel.add(ships);

        JPanel messagePanel = new JPanel();
        messagePanel.setPreferredSize(new Dimension(100, 100));
        this.message = new JLabel("Welcome to BattleShip! Select a ship to begin placement");
        messagePanel.add(message);
        rightPanel.add(messagePanel);

        // Place both grids on the  battleship table
        battleshipTable.add(this.computerGrid);
        battleshipTable.add(this.userGrid);
        battleshipTable.add(rightPanel);        

        // Add and Pack
        mainFrame.add(battleshipTable);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void showValidShipPlacements(GridButton selectedButton) {
        setMessage("Select the selected ship's end location");
        GridButton button[][] = userGrid.button;
        int selectedRow = selectedButton.getRow();
        int selectedColumn = selectedButton.getColumn();
        int shipSize = selectedShip.getShipSize();
        int gridSize = userGrid.getGridSize();
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
        ships.toggleShipsEnabled(false);
        this.firstButton = selectedButton;
    }

    public Ships placeShip(GridButton secondButton) {
        GridButton button[][] = userGrid.button;
        int firstButtonRow = firstButton.getRow();
        int secondButtonRow = secondButton.getRow();
        int firstButtonColumn = firstButton.getColumn();
        int secondButtonColumn= secondButton.getColumn();
        int shipSize = selectedShip.getShipSize();
        
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
        int gridSize = userGrid.getGridSize();
        for (int columns = 0; columns < gridSize; columns++){
            for (int rows = 0; rows < gridSize; rows++) {
                button[rows][columns].setEnabled(false);
            }
        }
        selectedShip.setEnabled(true);
        selectedShip.setBorder(new LineBorder(Color.GREEN));
        selectedShip.setEnabled(false);
        Ships newShip = new Ships(firstButtonRow, firstButtonColumn, secondButtonRow, secondButtonColumn);

        this.enableUserGrid(false);
        return newShip;
    }


    public void enableUserGrid(boolean enabled) {
        userGrid.enableGrid(enabled);
    }

    public void enableComputerGrid(boolean enabled) {
        computerGrid.enableGrid(enabled);
    }

    public void setSelectedShip(ShipButton ship) {
        this.selectedShip = ship;
    }

    public ShipButton getSelectedShip() {
        return this.selectedShip;
    }

    public ShipPanel getShipPanel() {
        return this.ships;
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }
}
