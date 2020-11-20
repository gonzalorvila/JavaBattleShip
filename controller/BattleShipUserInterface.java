package controller;
import model.*;
import view.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface BattleShipUserInterface
{
    public void createGameBoard(ActionListener shipActionListener, ActionListener gridActionListener, ActionListener backActionListener, boolean difficulty);
    public void showValidShipPlacements(GridButton selectedButton);
    public Ships placeShip(GridButton secondButton);
    public void enableUserGrid(boolean enabled);
    public void enableComputerGrid(boolean enabled);
    public void setSelectedShip(ShipButton ship);
    public ShipPanel getShipPanel();
    public void setMessage(String message);
    public void updateUserGrid(int[] oppGuess, boolean moveResult);
    public void placeOppShipsOnGrid(boolean[][] oppBoolArray, ArrayList<Ships> ships);
    public void closeFrame();
}