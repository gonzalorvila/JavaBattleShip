import java.util.ArrayList;
import model.*;

public class UseCases
{
    
    public static void startNewGame(ArrayList<Ships> ships, Player player, Opponent opponent, GameBoardState gbState)
    {
        for (Ships s : ships)
        {
            int location = player.setShipLocation(s);
            gbState.storeLocations(player, location);
            location = opponent.setShipLocation(s);
            gbState.storeLocations(opponent, location);
        }

        gbState.setScore(5, 5);

        if ((gbState.getUserScore() != 0) && (gbState.getOpponentScore() != 0))
        {
            makeMove(ships, player, opponent, gbState);
        }
        else
        {
            onResult(ships, player, opponent, gbState);
        }
    }

    public static void makeMove(ArrayList<Ships> ships, Player player, Opponent opponent, GameBoardState gbState)
    {
        boolean moveResult = true;
        
    
        while(moveResult)
        {
            // Call teminal input class to get location of attack on opponet board
            // This will return int location
            int location = 0;
            boolean valid = gbState.checkMove(location);
            if (valid)
            {
                moveResult = gbState.isHit(location);
                if (moveResult)
                {
                    makeMove(ships, player, opponent, gbState);
                }
            }
        }

        if (gbState.getOpponentScore() == 0)
        {
            moveResult = false;
        }
        else
        {
            moveResult = true;
        }
        int oppLocation = opponent.opponentMove();
        
        while (moveResult)
        {
            boolean valid = gbState.checkMove(oppLocation);
            if (valid)
            {
                moveResult = gbState.isHit(oppLocation);
                if (moveResult)
                {
                    oppLocation = opponent.opponentMove();
                }
            }
        }

        if ((gbState.getUserScore() == 0) || gbState.getOpponentScore() == 0)
        {
            onResult(ships, player, opponent, gbState);
        }
    }

    public static void onResult(ArrayList<Ships> ships, Player player, Opponent opponent, GameBoardState gbState)
    {
        if (gbState.getUserScore() == 0)
        {
            System.out.println("Opponent Wins!");
            // Final product will not print to terminal.
        }
        else
        {
            System.out.println("User Wins!");
        }
    }
}