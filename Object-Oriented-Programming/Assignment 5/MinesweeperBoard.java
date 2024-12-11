import java.util.*;
import java.io.*;

public class MinesweeperBoard implements Serializable {
	
	private static final int BOARD_SIZE = 10;
	public int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
	public static double mineProbability = 0.15;
	private static final int MINE = -1;
	private static final int EMPTY = 0;
	private static final int REVEALED = 2;
	private static final int FLAG = 1;

	public MinesweeperBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
		
		
        Random random = new Random();
        for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				double rand = random.nextDouble();
				if (rand <= mineProbability) {
					board[i][j] = MINE;
					System.out.println("Mine at: (" + j + " ," + i + ")");
				}
				else {
					board[i][j] = EMPTY;
				}
			}
		}
		
		//testBoard();
		
    }
	
	public int[][] getBoardArray() {
		return board;
	}
	
	public void testBoard() {
		for (int i = 0; i<board.length; i++) {
			for (int j = 0; j<board[i].length; j++) {
				System.out.println(board[i][j]);
			}
		}
	}
	
	public MinesweeperBoard getBoard() {
		return this;
	}
	

	
}