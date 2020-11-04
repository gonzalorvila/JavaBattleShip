package model;

public class Player {
	private Ship ship;
	private GameBoardState gameBoardState;
	
	public Player() {
		System.out.println("Player Constructor");
	}

	public void setShipLocation(Ship ship, GameBoardState gameBoardState) {
		System.out.println("Player:: setShipLocation");
	}

	public void makeMove(GameBoardState gameBoardState) {
		System.out.println("Player:: makeMove");
	}


}
