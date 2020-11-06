package model;
import java.util.ArrayList;

public class Player {
	private Ships ship;
	private GameBoardState gameBoardState;
	
	public Player() {
		System.out.println("Player Constructor");
	}

	public int setShipLocation(Ships ship) {
		System.out.println("Player:: setShipLocation");
		return 0;
	}
}
