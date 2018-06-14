package de.tu_clausthal.in.bachelorproject2018.ticktacktoe.model;

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class Spiel {

	
	static int[][] board  = new int[3][3];

	public static void main(String[] args) {
		
		int winner = game(board, 1);
		System.out.println(winner);
		
	}
	
	public static int game(int[][] board, int difficulty) {
		
		while(won(board) == 0) {	
			printboard(board);
			
			int x;
			int y;
			int winner;
			do {
				Scanner s1 = new Scanner(System.in);
				x = Integer.parseInt(s1.nextLine());
				Scanner s2 = new Scanner(System.in);
				y = Integer.parseInt(s2.nextLine());
			}while(board[x][y] != 0);
			board[x][y] = 1;
			winner = won(board);
			if(winner > 0) {
				printboard(board);
				return winner;
			}
			
			printboard(board);
			
			board = bot(board, difficulty);
			winner = won(board);
			if(winner > 0) {
				printboard(board);
				return winner;
			}
			
		}
		printboard(board);
		return 3;
	}
	
	public static void printboard(int[][] board) {
		for(int[] line: board) {
			for(int field: line) {
				System.out.print(field + " ");
			}
			System.out.println();
		}
	}
	
	public static int won(int[][] board) {
		
		boolean isDraw = true;
		for(int[] line: board) {
			for(int field: line) {
				if(field == 0) {
					isDraw = false;
				}
			}
		}
		
		if(isDraw == true) {
			return 3;
		}
		else if(board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != 0) {
			return board[1][0];
		}
		else if(board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != 0) {
			return board[2][0];
		}
		else if(board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != 0) {
			return board[0][0];
		}
		else if(board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != 0) {
			return board[0][1];
		}
		else if(board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != 0) {
			return board[0][2];
		}
		else if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != 0) {
			return board[0][0];
		}
		else if(board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] != 0) {
			return board[2][0];
		}
		else if(board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != 0) {
			return board[0][0];
		}
		else {
			return 0;
		}
	}
	
	public static int[][] bot(int[][] board, int difficulty) {

		switch(difficulty) {
		case 0:
			return player(board);
		case 1:
			return randomBot(board);
		case 2:
			int i = ThreadLocalRandom.current().nextInt(0, 11);
			if(i < 4) {
				return randomBot(board);
			}
			else {
				return minimaxBot(board);
			}
		case 3:
			return minimaxBot(board);
		
		}
		return board;
	}
	
	public static int[][] player(int[][] board){
		int x;
		int y;
		do {
			Scanner s1 = new Scanner(System.in);
			x = Integer.parseInt(s1.nextLine());
			Scanner s2 = new Scanner(System.in);
			y = Integer.parseInt(s2.nextLine());
		}while(board[x][y] != 0);
		board[x][y] = 2;
		return board;
	}
	
	public static int[][] randomBot(int[][] board){
		int x;
		int y;
		do {
			x = ThreadLocalRandom.current().nextInt(0, 3);
			y = ThreadLocalRandom.current().nextInt(0, 3);
		}while(board[x][y] != 0);
		board[x][y] = 2;
		return board;
	}
	
	public static int[][] minimaxBot(int[][] board){
		
		return board;
	}

}

