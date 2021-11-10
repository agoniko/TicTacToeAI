package ticTacToe;

import java.util.Scanner;

import ticTacToe.Game.Move;

//Java program to find the 
//next optimal move for a player
class main {

	public static void main(String[] args) {
		Game game = new Game();
		Scanner sc = new Scanner(System.in);
		Board board = new Board();
		Boolean turn = true; // inizia la X
		int gameStatus = 0;
		System.out.println("Se vuoi iniziare inserisci O altrimenti inizia il PC");
		String giocatore = sc.nextLine();
		if (giocatore.equals("O")) {
			turn = false;
			System.out.println(board.Example());
			System.out.println(board.toString());
		}

		do {
			if (turn) {
				Move nextMove = game.findBestMove(board.data);
				board.data[nextMove.row][nextMove.col] = game.player;
				turn = !turn;
			} else {
				int nCella = -1;
				do {
					System.out.println("inserisci numero cella valido:"+'\n');
					nCella = sc.nextInt();
				} while (!isValid(nCella, board.data));
				String rowAndCol = rowAndcolByCellNumber(nCella);
				int row = Integer.parseInt(rowAndCol.split(",")[0]);
				int col = Integer.parseInt(rowAndCol.split(",")[1]);
				board.data[row][col] = game.opponent;
				turn = !turn;
			}
			gameStatus = game.gameEnded(board.data);
			System.out.println(board.Example());
			System.out.println(board.toString());
		} while (gameStatus == 0);
		switch (gameStatus) {
		case -1:
			System.out.println("Pareggio");
			break;
		case 10:
			System.out.println("Hai perso!");
			break;
		case -10:
			System.out.println("Hai vinto!");
			break;
		}
		System.out.println("In totale sono stati potati " + Game.ramiPotati + " rami dall'albero di gioco");
	}

	public static String rowAndcolByCellNumber(int cella) {
		int row = -1, col = -1;
		switch (cella) {
		case 1:
			row = 0;
			col = 0;
			break;
		case 2:
			row = 0;
			col = 1;
			break;
		case 3:
			row = 0;
			col = 2;
			break;
		case 4:
			row = 1;
			col = 0;
			break;
		case 5:
			row = 1;
			col = 1;
			break;
		case 6:
			row = 1;
			col = 2;
			break;
		case 7:
			row = 2;
			col = 0;
			break;
		case 8:
			row = 2;
			col = 1;
			break;
		case 9:
			row = 2;
			col = 2;
			break;
		}
		return row + "," + col;
	}

	public static boolean isValid(int cella, char[][] board) {
		String rowAndCol = rowAndcolByCellNumber(cella);
		int row = Integer.parseInt(rowAndCol.split(",")[0]);
		int col = Integer.parseInt(rowAndCol.split(",")[1]);
		if (board[row][col] == ' ' && row > -1 && row < 4 && col > -1 && col < 4) {
			return true;
		} else {
			return false;
		}
	}
}