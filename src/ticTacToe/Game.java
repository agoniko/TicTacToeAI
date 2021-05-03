package ticTacToe;

public class Game {

	public static int ramiPotati = 0;

	class Move {
		int row, col;
	};

	char player = 'X', opponent = 'O';

//This function returns true if there are moves
//remaining on the board. It returns false if
//there are no moves left to play.
	static Boolean isMovesLeft(char board[][]) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] == ' ')
					return true;
		return false;
	}

//This is the evaluation function as discussed
//in the previous article ( http://goo.gl/sJgv68 )
	int evaluate(char b[][]) {
		// Checking for Rows for X or O victory.
		for (int row = 0; row < 3; row++) {
			if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
				if (b[row][0] == player)
					return +10;
				else if (b[row][0] == opponent)
					return -10;
			}
		}

		// Checking for Columns for X or O victory.
		for (int col = 0; col < 3; col++) {
			if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
				if (b[0][col] == player)
					return +10;

				else if (b[0][col] == opponent)
					return -10;
			}
		}

		// Checking for Diagonals for X or O victory.
		if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
			if (b[0][0] == player)
				return +10;
			else if (b[0][0] == opponent)
				return -10;
		}

		if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
			if (b[0][2] == player)
				return +10;
			else if (b[0][2] == opponent)
				return -10;
		}

		// Else if none of them have won then return 0
		return 0;
	}

//This is the minimax function. It considers all
//the possible ways the game can go and returns
//the value of the board

	public int minVal(char board[][], int alpha, int beta, int depth) {
		int score = evaluate(board);

		// If Maximizer has won the game
		// return his/her evaluated score
		if (score == 10)
			return score;

		// If Minimizer has won the game
		// return his/her evaluated score
		if (score == -10)
			return score;

		// If there are no more moves and
		// no winner then it is a tie
		if (isMovesLeft(board) == false)
			return 0;
		int best = Integer.MAX_VALUE;

		// Traverse all cells
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// Check if cell is empty
				if (board[i][j] == ' ') {
					// Make the move
					board[i][j] = opponent;

					// Call minimax recursively and choose
					// the minimum value
					best = Math.min(best, maxVal(board, alpha, beta, depth + 1)) + depth;

					// Undo the move
					board[i][j] = ' ';

					if (best <= alpha) {
						ramiPotati++;
						return best;
					}
					beta = Math.min(best, beta);
				}
			}
		}
		return best;
	}

	public int maxVal(char board[][], int alpha, int beta, int depth) {

		int score = evaluate(board);

		// If Maximizer has won the game
		// return his/her evaluated score
		if (score == 10)
			return score;

		// If Minimizer has won the game
		// return his/her evaluated score
		if (score == -10)
			return score;

		// If there are no more moves and
		// no winner then it is a tie
		if (isMovesLeft(board) == false)
			return 0;
		int best = Integer.MIN_VALUE;

		// Traverse all cells
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// Check if cell is empty
				if (board[i][j] == ' ') {
					// Make the move
					board[i][j] = player;

					// Call minimax recursively and choose
					// the maximum value
					best = Math.max(best, minVal(board, alpha, beta, depth + 1)) - depth;

					// Undo the move
					board[i][j] = ' ';

					if (best >= beta) {
						ramiPotati++;
						return best;
					}
					alpha = Math.max(alpha, best);
				}
			}
		}
		return best;
	}

//This will return the best possible
//move for the player
	Move findBestMove(char board[][]) {
		int bestVal = Integer.MIN_VALUE;
		Move bestMove = new Move();
		bestMove.row = -1;
		bestMove.col = -1;

		// Traverse all cells, evaluate minimax function
		// for all empty cells. And return the cell
		// with optimal value.
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// Check if cell is empty
				if (board[i][j] == ' ') {
					// Make the move
					board[i][j] = player;

					// compute evaluation function for this
					// move.
					int moveVal = minVal(board, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

					// Undo the move
					board[i][j] = ' ';

					// If the value of the current move is
					// more than the best value, then update
					// best
					if (moveVal > bestVal) {
						bestMove.row = i;
						bestMove.col = j;
						bestVal = moveVal;
					}
				}
			}
		}

		System.out.printf("The value of the best Move " + "is : %d\n\n", bestVal);

		return bestMove;
	}

	int gameEnded(char b[][]) {
		// Checking for Rows for X or O victory.
		for (int row = 0; row < 3; row++) {
			if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
				if (b[row][0] == player)
					return +10;
				else if (b[row][0] == opponent)
					return -10;
			}
		}

		// Checking for Columns for X or O victory.
		for (int col = 0; col < 3; col++) {
			if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
				if (b[0][col] == player)
					return +10;

				else if (b[0][col] == opponent)
					return -10;
			}
		}

		// Checking for Diagonals for X or O victory.
		if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
			if (b[0][0] == player)
				return +10;
			else if (b[0][0] == opponent)
				return -10;
		}

		if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
			if (b[0][2] == player)
				return +10;
			else if (b[0][2] == opponent)
				return -10;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (b[i][j] == ' ') {
					return 0;
				}
			}
		}
		return -1;

	}
}
