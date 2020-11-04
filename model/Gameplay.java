package model;

public class Gameplay {

	private GameBoard gameBoard;
	private int userScore;
	private int compScore;

	public Score(GameBoard gameBoard) {
		System.out.println("Constructor for score");
	}

	public void setScore(GameBoard gameBoard) {
		System.out.println("Score:: setScore")
	}

	public void getScore() {
		System.out.println("Score:: getScore");
	}

	public boolean checkMove(GameBoard gameBoard) {
		System.out.println("Checks if the move made is a valid move");
	}
}

