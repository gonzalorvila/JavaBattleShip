package model;

import java.util.ArrayList;
import java.util.*;

public class Ships 
{
	private String shipName;
	private int length;
	private int startLocation;
	private int endLocation;
	private boolean direction;
	private int[] shipCoordinates;

	private int location;
	private int startRow;
	private int startColumn;
	private int endRow;
	private int endColumn;

	public Ships(int length, int startLocation, int endLocation, boolean direction) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.direction = direction;
		this.length = length;
	}

	public Ships(int startRow, int startColumn, int endRow, int endColumn) {
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.endRow = endRow;
		this.endColumn = endColumn;
	}

	public boolean getDirection() {
		return this.direction;
	}

 	public int[] getShipLocations() {
		this.shipCoordinates[0] = startLocation;
		this.shipCoordinates[length - 1] = endLocation;
		if (this.direction) {
			int i = 1;
			for (int l = startLocation + 1; l < endLocation;) {
				if (startLocation < endLocation) {
					this.shipCoordinates[i] = l;
					l++;
				} else {
					this.shipCoordinates[i] = l;
					l--;
				}
				i++;
			}
		} else {
			int i = 1;
			for(int l = startLocation; l < endLocation;) {
				if (startLocation < endLocation) {
					this.shipCoordinates[i] = l;
					l += 10;
				} else {
					this.shipCoordinates[i] = l;
					l -= 10;
				}
				i++;
			}
		}
		return shipCoordinates;
	}


	public void setLength(int i) {
		this.length = i;
	}

	public void setDirection(boolean d) {
		this.direction = d;
	}

	public void setStartLocation(int l) {
		this.startLocation = l;
	}

	public void setEndLocation(int e) {
		this.endLocation = e;
	}


 	public void storeLocations(ArrayList<Integer> locationsArray, int[] location)
    {
        for(int i = 0; i < location.length; i++) {
			locationsArray.add(location[i]);
		}
    }

}
