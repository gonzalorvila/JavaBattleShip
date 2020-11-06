import java.awt.event.*;

import model.*;
//import controller.*;
import view.*;
import java.util.ArrayList;

public class Driver
{
    public static void main(String []args)
    {
        //GameBoard gameTable = new GameBoard();

        Ships ship = new Ships();
        ArrayList<Ships> shipArray = new ArrayList<Ships>();
        Player player = new Player();
        Opponent opponent = new Opponent();
        GameBoardState gbState = new GameBoardState(player, opponent, shipArray);
        
        
        gbState.createEmptyGameBoard();
        gbState.setDifficulty(1);

        UseCases useCases = new UseCases();

        useCases.startNewGame(shipArray, player, opponent, gbState);       
        useCases.makeMove(shipArray, player, opponent, gbState);
        useCases.onResult(shipArray, player, opponent, gbState);
    }
}