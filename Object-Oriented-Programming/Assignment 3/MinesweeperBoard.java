import java.util.*;
public class MinesweeperBoard {
	
	private static final int BOARD_SIZE = 10;
	private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
	private static final double MINE_PROBABILITY = 0.125;
	private static final int MINE = -1;
	private static final int EMPTY = 0;

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
				if (rand <= MINE_PROBABILITY) {
					board[i][j] = MINE;
					System.out.println("Mine at: (" + i + " ," + j + ")");
				}
				else {
					board[i][j] = EMPTY;
				}
			}
		}
		
		//testBoard();
		
    }
	
	public int[][] getBoardArray() {
		int[][] newBoard = board;
		return newBoard;
	}
	
	public void testBoard() {
		for (int i = 0; i<board.length; i++) {
			for (int j = 0; j<board[i].length; j++) {
				System.out.println(board[i][j]);
			}
		}
	}
	
	
}