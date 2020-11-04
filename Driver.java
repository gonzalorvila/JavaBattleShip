import java.awt.event.*;

import model.*;
//import controller.*;
import view.*;

public class Driver
{
    public static void main(String []args)
    {
        //GameBoard gameTable = new GameBoard();

        GameBoardState gbState = new GameBoardState();
        gbState.createEmptyGameBoard();
        gbState.setDifficulty(1);

        Gameplay gameplay = new Gameplay(gbState);
        gameplay.setScore(gbState);
        gameplay.getScore();
        gameplay.checkMove(gbState);

        Ships ship = new Ships(gbState);
        Player player = new Player();
        
        ship.setHit(player);
        player.setShipLocation(ship, gbState);
        player.makeMove(gbState);

        Opponent opponent = new Opponent();
        opponent.opponentMove();
    }
}