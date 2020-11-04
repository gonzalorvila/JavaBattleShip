package model;

public class Player {
	private Ship ship;
	private GameBoard gameBoard;
	private Location location
	public Player() {
		System.out.println("Player Constructor");
	}

	public void setShipLocation(Ship ship, GameBoard gameBoard) {
		System.out.println("Player sets where his/her ships are");
	}

	public void makeMove(GameBoard gameBoard, Ship ship, Location location) {
		System.out.println("this is where the logic for making a move will occur");
	}


}
