package model;

public class Player {
	private Ship ship;
	private GameBoard gameBoard;
	public Player() {
		System.out.println("Player Constructor");
	}

	public void setShipLocation(Ship ship, GameBoard gameBoard) {
		System.out.println("Player:: setShipLocation");
	}

	public void makeMove(GameBoard gameBoard, Ship ship) {
		System.out.println("Player:: makeMove");
	}


}
