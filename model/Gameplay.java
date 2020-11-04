package model;

public class Gameplay {

	private GameBoardState gameBoardState;
	private int userScore;
	private int compScore;

	public Gameplay(GameBoardState gameBoardState) {
		System.out.println("Constructor for gameplay");
	}

	public void setScore(GameBoardState gameBoardState) {
		System.out.println("Gameplay:: setScore");
	}

	public int getScore() {
		System.out.println("Gameplay:: getScore");
		return 0; 
	}

	public boolean checkMove(GameBoardState gameBoardState) {
		System.out.println("Gameplay:: Checks if the move made is a valid move");
		return true;
	}
}

