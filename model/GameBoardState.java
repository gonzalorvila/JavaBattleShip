package model;
import java.util.ArrayList;

public class GameBoardState
{
    private Player player;
    private Opponent opponent;
    private ArrayList<Ships> userShips;
    private ArrayList<Ships> opponentShips;

    public GameBoardState(Player player, Opponent opponent, ArrayList<Ships> ships)
    {
        this.player = player;
        this.opponent = opponent;
        this.userShips = ships;
        this.opponentShips = ships;
        System.out.println("GameBoardState constructor");
    }

    public void createEmptyGameBoard()
    {
        System.out.println("GameBoardState:: createEmptyGameBoard at the start of the game");
    }

    public void setDifficulty(int d)
    {
        System.out.println("GameBoardState:: setDifficulty to level d");
    }

    public void storeLocations(Player player, int location)
    {
        System.out.println("GameBoardState:: storeLocations to create ArrayList of ship locations");
    }

    public void setScore(int userScore, int opponentScore) 
    {
		System.out.println("GameBoardState:: setScore");
	}

	public int getUserScore() {
		System.out.println("GameBoardState:: getUserScore");
		return 0; 
	}

    public int getOpponentScore() {
		System.out.println("GameBoardState:: getOpponentScore");
		return 0; 
	}

	public boolean checkMove(int location) {
		System.out.println("GameBoardState:: Checks if the move made is a valid move");
		return true;
    }

    public boolean isHit(int location) {
		System.out.println("GameBoardState:: isHit will check if boat is hit and substract length of it until sunk");
        return true;
	}

}