package model;

import java.util.ArrayList;
import java.util.*;

public class Ships 
{
	private String shipName;
	private int length;
	private int startRow;
	private int startColumn;
	private int endRow;
	private int endColumn;

	public Ships(int startRow, int startColumn, int endRow, int endColumn, int length) {
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.endRow = endRow;
		this.endColumn = endColumn;
		this.length = length;
	}

	public void setStartColumn(int i) {
		this.startColumn = i;
	}

	public void setStartRow(int i) {
		this.startRow = i;
	}

	public void setEndRow(int r) {
		this.endRow = r;
	}

	public void setEndColumn(int e) {
		this.endColumn = e;
	}

	public int getStartColumn() {
		return this.startColumn;
	}

	public int getStartRow() {
		return this.startRow;
	}

	public int getEndRow() {
		return this.endRow;
	}

	public int getEndColumn() {
		return this.endColumn;
	}

	public ArrayList<Integer> storingColumnsFilled() {
		ArrayList<Integer> columns = new ArrayList<Integer>();
		if (startColumn == endColumn) {
			for (int i = 0; i < this.length; i++){
				columns.add(startColumn);
			}
		} else {
			if (startColumn < endColumn) {
				for (int i = startColumn; i <= endColumn; i++) {
					columns.add(i);	
				}
			} else {
				for (int i = startColumn; i >= endColumn; i--) {
					columns.add(i);
				}
			}
		}
		return columns;
	}
	
	public ArrayList<Integer> storingRowsFilled() {
		ArrayList<Integer> rows = new ArrayList<Integer>();
		if (startRow == endRow) {
			for (int i = 0; i < this.length; i++) {
				rows.add(startRow);
			}
		} else {
			if (startRow < endRow) {
				for(int i = startRow; i <= endRow; i++) {
					rows.add(i);
				}
			} else {
				for (int i = startRow; i >= endRow; i--) {
					rows.add(i);
				}
			}
		}
		return rows;
	}

	public void setShipLength(int i) {
		this.length = i;
	}
	public int getShipLength() {
		return this.length;
	}
}
