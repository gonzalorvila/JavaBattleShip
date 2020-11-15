package view;

import javax.swing.*;

public class GridButton extends JButton {
    private int row;
    private int column;
    private boolean free;

    public GridButton(String string) {
        super(string);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean getFree() {
        return this.free;
    }
}