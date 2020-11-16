package model;

import java.util.ArrayList;
import java.util.*;

public class Ships 
{
	private String shipName;
	private int[] shipLength;
	private int startRow;
	private int startColumn;
	private int endRow;
	private int endColumn;

	public Ships(int startRow, int startColumn, int endRow, int endColumn) {
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.endRow = endRow;
		this.endColumn = endColumn;
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

}
