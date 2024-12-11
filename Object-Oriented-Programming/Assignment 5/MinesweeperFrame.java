import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MinesweeperFrame extends JFrame implements ActionListener, Serializable {
	
	private MinesweeperPanel panel;
	private MinesweeperPanel loadPanel;
	private static final int LAYOUT_SIZE = 10;
	
	public MinesweeperFrame() {
		panel = new MinesweeperPanel(this);
		add(panel);
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650,650);
		setVisible(true);
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem loadMenuItem = new JMenuItem("Load");
		JMenuItem quitMenuItem = new JMenuItem("Quit");
		newMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);
		loadMenuItem.addActionListener(this);
		quitMenuItem.addActionListener(this);
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(loadMenuItem);
		fileMenu.add(quitMenuItem);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("New")) {
			
			JDialog dialog = new JDialog(this);
			dialog.setTitle("New Game");
			dialog.setLayout(new GridLayout(0, 1));
			ButtonGroup buttonGroup = new ButtonGroup();
			JRadioButton easyRadioButton = new JRadioButton("Easy", true);
			JRadioButton mediumRadioButton = new JRadioButton("Medium");
			JRadioButton hardRadioButton = new JRadioButton("Hard");
			buttonGroup.add(easyRadioButton);
			buttonGroup.add(mediumRadioButton);
			buttonGroup.add(hardRadioButton);
			dialog.add(easyRadioButton);
			dialog.add(mediumRadioButton);
			dialog.add(hardRadioButton);
			JButton okButton = new JButton("OK");
			dialog.add(okButton);
			dialog.pack();
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (easyRadioButton.isSelected()) {
						System.out.println("Easy game started: Probability of mine is 10%");
						MinesweeperBoard.mineProbability = 0.1;
						MinesweeperPanel.board = new MinesweeperBoard();
						dispose();
						panel = new MinesweeperPanel(new MinesweeperFrame());
					}
					else if (mediumRadioButton.isSelected()) {
						System.out.println("Medium game started: Probability of mine is 15%");
						MinesweeperBoard.mineProbability = 0.15;
						MinesweeperPanel.board = new MinesweeperBoard();
						dispose();
						panel = new MinesweeperPanel(new MinesweeperFrame());
					}
					else if (hardRadioButton.isSelected()) {
						System.out.println("Hard game started: Probability of mine is 20%");
						MinesweeperBoard.mineProbability = 0.2;
						MinesweeperPanel.board = new MinesweeperBoard();
						dispose();
						panel = new MinesweeperPanel(new MinesweeperFrame());
					}
					dialog.dispose();
				}
			});
			
			
		}
		
		else if (command.equals("Save")) {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
					out.writeObject(panel.saveGrid);
					System.out.println("Game saved to: " + file);
					out.close();
				}
				
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		
		else if (command.equals("Load")) {
			int[][] loadGrid = new int[10][10];
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
					loadGrid = (int[][])in.readObject();
					System.out.println("Game loaded!");
					dispose();
					panel = new MinesweeperPanel(new MinesweeperFrame(), loadGrid);
					MinesweeperPanel.frame.add(panel);
					in.close();
				}
				catch (IOException exc) {
					System.out.println("Error reading in Minesweeper_Game");
				}
				catch (ClassNotFoundException exc) {
					System.out.println("Minesweeper_Game does not contain MinesweeperBoard object");
				}
			}
		}
		
		else if (command.equals("Quit")) {
			System.out.println("Exited smoothly!");
			System.exit(0);
		}
	
	}
	
	
}