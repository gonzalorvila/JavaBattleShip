package view;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GridPanel extends JPanel {
    JPanel buttonPanel;
    GridBagConstraints constraints;
    public static JButton button[][] = new JButton[10][10];

    private int rows;
    private int columns;
    private int gridSize;

    public GridPanel (int gridSize, int height, int width) {
        this.gridSize = gridSize;  
        this.setPreferredSize(new Dimension(width, height));

        this.setLayout(new GridBagLayout());
        buttonPanel = new JPanel();
        //buttonPanel.setBackground(new Color(51, 153, 255));
        buttonPanel.setLayout(new GridLayout(10,10));
        int squareNum = 1;
        String numString;
        for (columns =0; columns < gridSize; columns++){
            for (rows = 0; rows < gridSize; rows++) {
                numString = Integer.toString(squareNum);
                button[rows][columns] = new JButton(numString);
                button[rows][columns].setBackground(Color.BLUE);
                button[rows][columns].setOpaque(true);
                //button[rows][columns].setBorderPainted(false);
                button[rows][columns].setPreferredSize(new Dimension(100,100));
                //button[rows][columns].addActionListener(new TilePressed(rows, columns));
                buttonPanel.add(button[rows][columns]);
                squareNum++;
            }
        }

        button[1][1].setBorderPainted(false);
        button[1][1].setForeground(Color.BLUE);
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.65;

        this.add(buttonPanel, constraints);

    }
}
