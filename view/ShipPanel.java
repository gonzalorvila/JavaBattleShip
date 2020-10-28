package view;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class ShipPanel extends JPanel {

	private int width;
	private int height;
	private JPanel shipPanel;

	public ShipPanel(int width, int height) {
		this.setBackground(new Color(53, 101, 77));
		this.width = width;
		this.height = height;
		shipPanel = new JPanel();
		shipPanel.setOpaque(false);
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());
		this.add(shipPanel, BorderLayout.CENTER);
		
	}

	public void addShip(int size) {
		ShipIcon s = new ShipIcon(size);
		JLabel ship = new JLabel(s);
		shipPanel.add(ship);
		shipPanel.revalidate();
		shipPanel.repaint();
	}
}
