import javax.swing.*;

public class MinesweeperFrame extends JFrame {
	
	private MinesweeperPanel panel;
	
	public MinesweeperFrame() {
		panel = new MinesweeperPanel(this);
		add(panel);
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650,650);
		setVisible(true);
	}
	
	
}