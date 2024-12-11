import javax.swing.*;
import java.awt.*;
public class MessagePanel extends JPanel {
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		g.drawOval(235,10,30,10);
		g.drawLine(265,15,265,115);
		g.drawLine(235,15,235,115);
		g.drawLine(235,115,205,190);
		g.drawLine(265,115,295,190);
		g.drawLine(295,190,295,390);
		g.drawLine(205,190,205,390);
		g.drawLine(205,390,295,390);
		g.drawString("Please",235,275);
		g.drawString("give",235,285);
		g.drawString("me",235,295);
		g.drawString("a",235,305);
		g.drawString("100",235,315);
	}
}