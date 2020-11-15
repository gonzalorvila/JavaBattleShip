package view;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.util.ArrayList;

public class ShipPanel extends JPanel {

	private int width;
	private int height;
	private JPanel shipPanel;
	private ArrayList<ShipButton> shipButtons;

	public ShipPanel(int width, int height) {
		this.setBackground(new Color(53, 101, 77));
		this.width = width;
		this.height = height;
		shipPanel = new JPanel();
		shipPanel.setOpaque(false);
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());
		this.add(shipPanel, BorderLayout.CENTER);
		this.shipButtons = new ArrayList<ShipButton>();
		
	}

	public void addShip(int size, ActionListener actionListener) {
		ShipIcon s = new ShipIcon(size);
		ShipButton ship = new ShipButton(s, size);
		shipButtons.add(ship);
		ship.setBorder(new LineBorder(Color.BLACK));
		ship.addActionListener(actionListener);
		shipPanel.add(ship);
		shipPanel.revalidate();
		shipPanel.repaint();
	}

	public void toggleShipsEnabled(boolean toggle) {
		for (ShipButton ship : shipButtons) {
			ship.setEnabled(toggle);
		}
	}
}
