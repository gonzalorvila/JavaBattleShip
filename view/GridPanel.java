package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridPanel extends JPanel {

    private int length;
    private int height;
    
    public GridPanel(int length, int height) {
        this.length = length;
        this.height = height;
        this.setSize(length, height);
        this.setVisible(true);
    }

    public void paintGrid(Component c, Graphics g, int x, int y) {

        Graphics2D g2 = (Graphics2D)g;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                //g2.fillRect(x, y, this.width, this.height);
                g2.drawRect(x, y, this.length, this.height);
            }
        }
    }
}