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

	public Ships(int length, int startLocation, int endLocation, boolean direction) {
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.direction = direction;
		this.length = length;
	}

	public int getLength() {
		return this.length;
	}

	public int getStartLocation() {
		return this.startLocation;
	}

	public int getEndLocation() {
		return this.endLocation;
	}

	public boolean getDirection() {
		return this.direction;
	}

}
