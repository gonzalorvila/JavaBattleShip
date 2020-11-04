package model;

public class Gameplay {

	private GameBoard gameBoard;
	private int userScore;
	private int compScore;

	public Gameplay(GameBoard gameBoard) {
		System.out.println("Constructor for gameplay");
	}

	public void setScore(GameBoard gameBoard) {
		System.out.println("Gameplay:: setScore")
	}

	public int getScore() {
		System.out.println("Gameplay:: getScore");
	}

	public boolean checkMove(GameBoard gameBoard) {
		System.out.println("Checks if the move made is a valid move");
	}
}

