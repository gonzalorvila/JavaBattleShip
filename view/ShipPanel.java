import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;

public class ShipPanel extends JPanel {

	private int width;
	private JPanel shipPanel;

	public ShipPanel(int width) {
		this.setBackground(new Color(53, 101, 77));
		this.width = width;
		shipPanel = new JPanel();
		shipPanel.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.add(shipPanel, BorderLayout.SOUTH);
	}

	public void addShip(int size) {
		ShipIcon s = new ShipIcon(size);
		JLabel ship = new JLabel(s);
		shipPanel.add(ship);
		shipPanel.revalidate();
		shipPanel.repaint();
	}
}
