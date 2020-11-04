package model;

import java.util.ArrayList;

public class Ships {
	private Location location;
	private Ship ship;
	private GameBoard gameBoard;
	private String shipName;
	private ArrayList shipLocations;

	public Ships(Location location, Ship ship, GameBoard gameBoard) {
		System.out.println("Constructor: Ships");
	}

	public void setHit(Player player) {
		System.out.println("sets hit on the ship");
	}
}
