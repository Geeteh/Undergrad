import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MinesweeperPanel extends JPanel {
	
	private Toolkit tk;
	public static MinesweeperFrame frame;
	public static MinesweeperFrame loadFrame;
	public static int[][] saveGrid = new int[10][10];
	public static int[][] loadGrid = new int[10][10];
	public static MinesweeperBoard board = new MinesweeperBoard();
	private JButton[][] square = new JButton[10][10];
	private static final int LAYOUT_SIZE = 10;
	public static HashMap<JButton, Integer> map = new HashMap<>();
	
	public MinesweeperPanel(MinesweeperFrame f) {
		frame = f;
		tk = Toolkit.getDefaultToolkit();
		int[][] mineBoard = board.getBoardArray();
		setLayout(new GridLayout(10,10));
		saveGrid = mineBoard;
		//board.testBoard();
		setLayout(new GridLayout(LAYOUT_SIZE,LAYOUT_SIZE));
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
	
	public MinesweeperPanel(MinesweeperFrame loadFrame, int[][] loadGrid) {
		loadFrame.getContentPane().removeAll();
		//this.loadGrid = loadGrid;
		
		setLayout(new GridLayout(LAYOUT_SIZE,LAYOUT_SIZE));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				square[i][j] = new JButton();
				System.out.println(loadGrid[i][j]);
				if (loadGrid[i][j] == 0) square[i][j].setText("?");
				else if (loadGrid[i][j] == 1) square[i][j].setText("F");
				else if (loadGrid[i][j] == 2) { square[i][j].setText("X"); square[i][j].setBackground(Color.RED); System.out.println("Mine was stepped on at: (" + j + " ," + i + ")"); }
				else if (loadGrid[i][j] != 0 && loadGrid[i][j] != 1 && loadGrid[i][j] != 2 && loadGrid[i][j] != -1 && loadGrid[i][j] != 10) { square[i][j].setText(Integer.toString(loadGrid[i][j] / 10)); }
				else if (loadGrid[i][j] == -1) { square[i][j].setText("?"); System.out.println("Mine at: (" + j + " ," + i + ")"); }
				else if (loadGrid[i][j] == 10) { square[i][j].setText("0");  }
				add(square[i][j]);
				square[i][j].addMouseListener(new MinesweeperMouseListener());
				map.put(square[i][j], loadGrid[i][j]);
				
			}
		}
		loadFrame.revalidate();
	}
	
	private class MinesweeperMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
			JButton clickedButton = (JButton)e.getSource();
			if (e.getButton() == MouseEvent.BUTTON1) {
				//System.out.println(map.get(clickedButton));
				if (map.get(clickedButton) == -1) {
					map.put(clickedButton, 2);
					clickedButton.setText("X");
					clickedButton.setBackground(Color.RED);
					System.out.println("Kaboom Game Over");
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							if (square[i][j] == clickedButton) {
								saveGrid[i][j] = 2;
								break;
							}
						}
					}
					
				}
					
				else if (map.get(clickedButton) == 0) {
					map.put(clickedButton, 3);
					int[][] mineBoard = board.getBoardArray();
					int counter = 0;
					int row = -1;
					int col = -1;
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
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							if (square[i][j] == clickedButton) {
								if (counter == 0) saveGrid[i][j] = counter + 10;
								else if (counter != 0) saveGrid[i][j] = counter*10;
								break;
							}
						}
					}
					
					
	
					
				}
			}
			
			
            else if (e.getButton() == MouseEvent.BUTTON3) {
				if (map.get(clickedButton) == 0) {
					map.put(clickedButton, 1);
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							if (square[i][j] == clickedButton) {
								saveGrid[i][j] = 1;
								break;
							}
						}
					}
					
					clickedButton.setText("F");
				}
				else if (map.get(clickedButton) == 1) {
					map.put(clickedButton, 0);
					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							if (square[i][j] == clickedButton) {
								saveGrid[i][j] = 0;
								break;
							}
						}
					}
					clickedButton.setText("?");
				}
			}
        }
    }
	
	private int[][] normalizeBoardArray(int[][] grid) {
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 2) grid[i][j] = -1;
				
			}
		}
		return grid;
	}

	private String getAdjacentMineString(int i, int j, int[][] grid) {
		int counter = 0;
		int row = i;
		int col = j;
		grid = new int[10][10];
		
		if (row > 0 && grid[row-1][col] == -1) {
			counter++;
		}
		if (row < grid.length-1 && grid[row+1][col] == -1) {
			counter++;
		}
		if (col > 0 && grid[row][col-1] == -1) {
			counter++;
		}
		if (col < grid[0].length-1 && grid[row][col+1] == -1) {
			counter++;
		}
		if (row > 0 && col > 0 && grid[row-1][col-1] == -1) {
			counter++;
		}
		if (row > 0 && col < grid[0].length-1 && grid[row-1][col+1] == -1) {
			counter++;
		}
		if (row < grid.length-1 && col > 0 && grid[row+1][col-1] == -1) {
			counter++;
		}
		if (row < grid.length-1 && col < grid[0].length-1 && grid[row+1][col+1] == -1) {
			counter++;
		}
		//clickedButton.setText(Integer.toString(counter));
		return Integer.toString(counter);
		
						
		
	}
	
	
}