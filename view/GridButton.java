package view;

import javax.swing.*;

public class GridButton extends JButton {
    private int row;
    private int column;
    private boolean free;
    private int startRow;
    private int startColumn;

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

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getStartColumn() {
        return startColumn;
    }
}