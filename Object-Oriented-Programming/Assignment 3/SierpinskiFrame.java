import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SierpinskiFrame extends JFrame {
	
	SierpinskiFrame() {
		setTitle("Sierpinski's Triangle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300,200);
		SierpinskiPanel panel = new SierpinskiPanel();
		add(panel);
		this.addComponentListener(new ComponentAdapter() {  
			public void componentResized(ComponentEvent e) {
            Component c = (Component)e.getSource();
			repaint();
			}
        });
	}
}