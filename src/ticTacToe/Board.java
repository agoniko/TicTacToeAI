package ticTacToe;

import java.util.ArrayList;

public class Board {

	public char[][] data;
	public int depth;

	public Board() {
		depth = 0;
		this.data = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				data[i][j] = ' ';
			}
		}
	}

	public Board(char[][] data, Board root, ArrayList<Board> children, int depth) {
		this.data = data;
		this.depth = depth;
	}

	public String toString() {
		int cont = 1;
		String result = new String(" ----------- \n");
		for (int i = 0; i < 3; i++) {
			result += "| ";
			for (int j = 0; j < 3; j++) {
				result += data[i][j] + " | ";
				cont++;
			}
			result += "\n ----------- \n";
		}
		return result;
	}

	public String Example() {
		int cont = 1;
		String result = new String(" ----------- \n");
		for (int i = 0; i < 3; i++) {
			result += "| ";
			for (int j = 0; j < 3; j++) {
				result += cont + " | ";
				cont++;
			}
			result += "\n ----------- \n";
		}
		return result;
	}
}
