package model;

import java.util.ArrayList;

public class Ships {
	private Ship ship;
	private GameBoard gameBoard;
	private String shipName;
	private ArrayList shipLocations;

	public Ships(Ship ship, GameBoard gameBoard) {
		System.out.println("Constructor: Ships");
	}

	public void setHit(Player player) {
		System.out.println("Ships:: setHit");
	}
}
