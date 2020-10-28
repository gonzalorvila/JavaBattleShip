package view;
import javax.swing.*;
import java.awt.Rectangle;
import java.awt.*;
//import java.awt.Color;

public class ShipIcon implements Icon{
	protected int width;
	protected int length;
	protected  Color color;
	//private JPanel shipPanel;
	

	public ShipIcon(int size) {
		//shipPanel.setTitle("Ship example");
		this.width = (500/11)*size;
		this.length = (500/11);
		this.color = Color.gray;
	}

	@Override
	public int getIconWidth() {
		return this.width;
	}

	@Override
	public int getIconHeight() {
		return this.length;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.GRAY);
		g2.fillRect(x,y,this.width,this.length);
		Rectangle r = new Rectangle(this.width, this.length);
		g2.draw(r);
	}
}


