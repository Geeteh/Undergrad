import javax.swing.*;
import java.awt.*;
public class MessageFrame extends JFrame {
	public MessageFrame() {
		setTitle("Message in a Bottle");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new MessagePanel();
		add(panel);
		setVisible(true);
	}
}