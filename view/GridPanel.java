package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridPanel extends JPanel {

    private int length;
    private int height;
    private JPanel grid;
    
    public GridPanel(String player, int length, int height) {
        this.setBackground(Color.BLUE);
        this.length = length;
        this.height = height;
        grid = new JPanel();
        grid.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(grid, BorderLayout.CENTER);
        this.setSize(length, height);
        this.setVisible(true);
        
    }

    public void Gridpaint(Graphics g) {

        int rows = 11;
        int columns = 11;
        int width = getSize().width;
        int height = getSize().height;

        //drawing the rows
        int addRows = height / rows;
        for (int i = 0; i < rows; i++) {
            g.drawLine(0, i * addRows, width, i * addRows);
        }

        //drawing the columns
        int addColumns = width / columns;
        for (int i = 0; i < columns; i++) {
            g.drawLine(i * addColumns, 0, i * addColumns, height);
        }
    }

}