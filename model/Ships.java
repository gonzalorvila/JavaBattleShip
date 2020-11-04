package model;

import java.util.ArrayList;

public class Ships {
	private Ship ship;
	private GameBoardState gameBoardState;
	private String shipName;
	private ArrayList<Ship> shipLocations;

	public Ships(Ship ship, GameBoardState gameBoardState) {
		System.out.println("Constructor: Ships");
	}

	public void setHit(Player player) {
		System.out.println("Ships:: setHit");
	}
}
