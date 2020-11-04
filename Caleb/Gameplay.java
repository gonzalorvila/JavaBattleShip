package model;

public class Score {

	private GameBoard gameBoard;
	private int userScore;
	private int compScore;

	public Score(GameBoard gameBoard) {
		System.out.println("Constructor for score");
	}

	public void setScore(GameBoard gameBoard) {
		System.out.println("this method sets the score of the game based on number of ships left")
	}

	public void getScore() {
		System.out.println("Gets score of the game");
	}

	public checkMove(GameBoard gameBoard) {
		System.out.println("Checks if the move made is a valid move");
	}
}

