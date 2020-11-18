package view;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class GridPanel extends JPanel {
    JPanel buttonPanel;
    GridBagConstraints constraints;
    public GridButton button[][];
    private boolean gridName;
    private int rows;
    private int columns;
    private int gridSize;
    private JLabel whichGrid;

    public GridPanel (int gridSize, int height, int width, ActionListener gridActionListener, boolean gridName) {
        this.gridSize = gridSize;  
        this.setPreferredSize(new Dimension(width, height));
        this.button = new GridButton[gridSize][gridSize];
        this.setLayout(new GridBagLayout());
	    this.setBackground(Color.GRAY);
        buttonPanel = new JPanel();
        //buttonPanel.setBackground(new Color(51, 153, 255));
        buttonPanel.setLayout(new GridLayout(gridSize,gridSize));
        this.setBackground(Color.BLUE);
        int squareNum = 1;
        String numString;
        for (columns =0; columns < gridSize; columns++){
            for (rows = 0; rows < gridSize; rows++) {
                numString = Integer.toString(squareNum);
                button[rows][columns] = new GridButton(numString);
                button[rows][columns].setBackground(Color.BLUE);
                button[rows][columns].setOpaque(true);
                //button[rows][columns].setBorderPainted(false);
                button[rows][columns].setPreferredSize(new Dimension(100,100));
                //button[rows][columns].addActionListener(new TilePressed(rows, columns));
                button[rows][columns].setEnabled(false);
                button[rows][columns].addActionListener(gridActionListener);
                button[rows][columns].setRow(rows);
                button[rows][columns].setColumn(columns);
                button[rows][columns].setFree(true);
                buttonPanel.add(button[rows][columns]);
                squareNum++;
            }
        }

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.65;

        this.add(buttonPanel, constraints);
 	    GridBagConstraints lc = new GridBagConstraints();
        lc.anchor = GridBagConstraints.PAGE_END;
        lc.ipady = 30;
        lc.fill = GridBagConstraints.HORIZONTAL;
        lc.gridy = GridBagConstraints.RELATIVE;
        if (gridName == true) {
                whichGrid = new JLabel("User Grid");

        } else {
                whichGrid = new JLabel("Attack Grid");
        }
        this.add(whichGrid, lc);	

    }

    public void enableGrid(boolean enabled) {
        if (whichGrid.getText() == "User Grid") {
            for (columns =0; columns < gridSize; columns++){
                for (rows = 0; rows < gridSize; rows++) {
                    if (button[rows][columns].getFree()) {
                        button[rows][columns].setEnabled(enabled);
                    }
                }
            }
        }
        else {
            for (columns =0; columns < gridSize; columns++){
                for (rows = 0; rows < gridSize; rows++) {
                    button[rows][columns].setEnabled(enabled);
                }
            }
        }
    }

    public int getGridSize() {
        return this.gridSize;
    }

    public void setGrid(GridButton button[][]) {
        this.button = button;
    }
}
