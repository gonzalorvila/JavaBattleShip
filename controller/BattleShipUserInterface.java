package controller;
import model.*;
import view.*;
import java.awt.event.ActionListener;

public interface BattleShipUserInterface
{
    public void createGameBoard(ActionListener shipActionListener, ActionListener gridActionListener, boolean difficulty);
    public void showValidShipPlacements(GridButton selectedButton);
    public Ships placeShip(GridButton secondButton);
    public void enableUserGrid(boolean enabled);
    public void enableComputerGrid(boolean enabled);
    public void setSelectedShip(ShipButton ship);
    public ShipPanel getShipPanel();
    public void setMessage(String message);
}