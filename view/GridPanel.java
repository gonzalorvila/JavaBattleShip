package view;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class GridPanel extends JPanel {

    private int width;
    private int height;
    private JPanel gridPanel;
    
    public GridPanel(String player, int width, int height) {
        this.setBackground(new Color(51, 153, 255));
        this.width = width;
        this.height = height;
        gridPanel = new JPanel();
        gridPanel.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(gridPanel, BorderLayout.CENTER);
        this.setSize(width, height);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        int rows = 10;
        int columns = 10;

        //drawing the rows
        int addRows = height / rows;
        for (int i = 0; i <= rows; i++) {
            g.drawLine(0, i * addRows, width, i * addRows);
        }

        //drawing the columns
        int addColumns = width / columns;
        for (int i = 0; i <= columns; i++) {
            g.drawLine(i * addColumns, 0, i * addColumns, height);
        }

    }

}