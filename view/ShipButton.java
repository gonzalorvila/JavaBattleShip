package view;

import javax.swing.*;

public class ShipButton extends JButton {
    private int size;

    public ShipButton(Icon ship, int size) {
        super(ship);
        this.size = size;
    }

    public int getShipSize() {
        return this.size;
    }
}