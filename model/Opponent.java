package model;

public class Opponent extends Player
{
    public Opponent()
    {
        System.out.println("Opponent constructor");
    }

    public int opponentMove()
    {
        System.out.println("Opponent:: opponentMove will move for Opponent");
        return 0;
    }
}