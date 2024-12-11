import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MinesweeperPanel extends JPanel {
	
	private Toolkit tk;
	private MinesweeperFrame frame;
	private MinesweeperBoard board = new MinesweeperBoard();
	private JButton[][] square = new JButton[10][10];
	private static final int LAYOUT_SIZE = 10;
	private HashMap<JButton, Integer> map = new HashMap<>();
	
	public MinesweeperPanel(MinesweeperFrame f) {
		frame = f;
		tk = Toolkit.getDefaultToolkit();
		int[][] mineBoard = board.getBoardArray();
		setLayout(new GridLayout(LAYOUT_SIZE,LAYOUT_SIZE));
		//board.testBoard();
		for (int i=0; i<square.length; i++) {
			for (int j=0; j<square[i].length; j++) {
				//square[i][j] = new JButton("("+i+" ,"+j+")");
				//System.out.println(mineBoard[i][j] + ", " + square[i][j]);
				square[i][j] = new JButton("?");
				add(square[i][j]);
				square[i][j].addMouseListener(new MinesweeperMouseListener());
				map.put(square[i][j], mineBoard[i][j]);
			}
		}
		
		
	}
	
	private class MinesweeperMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
			JButton clickedButton = (JButton)e.getSource();
			if (e.getButton() == MouseEvent.BUTTON1) {
	
				if (map.get(clickedButton) == -1) {
					map.put(clickedButton, 2);
					clickedButton.setText("X");
					clickedButton.setBackground(Color.RED);
					System.out.println("Kaboom Game Over");
				}
					
				else if (map.get(clickedButton) == 0) {
					map.put(clickedButton, 2);
					int[][] mineBoard = board.getBoardArray();
					int counter = 0;
					int row = -1;
					int col = -1;;
					for (int i = 0; i < square.length; i++) {
						for (int j = 0; j < square[i].length; j++) {
							if (square[i][j] == clickedButton) {
								row = i;
								col = j;
							}
						}
					}
					if (row > 0 && mineBoard[row-1][col] == -1) {
						counter++;
					}
					if (row < square.length-1 && mineBoard[row+1][col] == -1) {
						counter++;
					}
					if (col > 0 && mineBoard[row][col-1] == -1) {
						counter++;
					}
					if (col < square[0].length-1 && mineBoard[row][col+1] == -1) {
						counter++;
					}
					if (row > 0 && col > 0 && mineBoard[row-1][col-1] == -1) {
						counter++;
					}
					if (row > 0 && col < square[0].length-1 && mineBoard[row-1][col+1] == -1) {
						counter++;
					}
					if (row < square.length-1 && col > 0 && mineBoard[row+1][col-1] == -1) {
						counter++;
					}
					if (row < square.length-1 && col < square[0].length-1 && mineBoard[row+1][col+1] == -1) {
						counter++;
					}
					clickedButton.setText(Integer.toString(counter));
	
					
				}
			}
			
			
            else if (e.getButton() == MouseEvent.BUTTON3) {
				if (map.get(clickedButton) == 0) {
					map.put(clickedButton, 1);
					clickedButton.setText("F");
				}
				else if (map.get(clickedButton) == 1) {
					map.put(clickedButton, 0);
					clickedButton.setText("?");
				}
			}
        }
    }
	
	

	
}