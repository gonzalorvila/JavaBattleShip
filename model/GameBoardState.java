package model;

public class GameBoardState
{
    public GameBoardState()
    {
        System.out.println("This is constructor");
    }

    public void createEmptyGameBoard()
    {
        System.out.println("GameBoardState:: createEmptyGameBoard at the start of the game");
    }

    public void setDifficulty(int d)
    {
        System.out.println("GameBoardState:: setDifficulty to level d");
    }
}