import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SierpinskiPanel extends JPanel {
	
	private int length;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int minDimension = Math.min(getWidth(), getHeight());
		length = minDimension;
		drawSierpinski(g, 0, 0, length);
		}
		
	
	private void drawSierpinski(Graphics g, int x, int y, int length) {
		//System.out.println(length);
        if (length == 1) {
			//System.out.println("x: " + x +"\ny: " + y);
			g.drawRect(x, y, 1, 1);
			g.fillRect(x, y, 1, 1);
			//System.out.println("base case reached");
			return;
		}
		else {
			int newLength = length;
			//left
			drawSierpinski(g, x, y + newLength, newLength / 2);
			//Right
			drawSierpinski(g, x + newLength, y + newLength, newLength / 2);
			//Top
			drawSierpinski(g, x + newLength / 2, y, newLength / 2);
		}
	}
}
