package model;

import java.util.ArrayList;

public class Ships {
	private GameBoardState gameBoardState;
	private String shipName;

	public Ships(GameBoardState gameBoardState) {
		System.out.println("Constructor: Ships");
	}

	public void setHit(Player player) {
		System.out.println("Ships:: setHit");
	}
}
