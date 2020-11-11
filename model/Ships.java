package model;

import java.util.ArrayList;
import java.util.*;

public class Ships 
{
	private String shipName;
	private int length;
	private int location;
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

 	public void storeLocations(ArrayList<Integer> locationsArray, int[] location)
    {
        for(int i = 0; i < location.length; i++) {
			locationsArray.add(location[i]);
		}
    }

}
