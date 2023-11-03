package main;

import piece.Piece;

public class Move {
	
	int oldCol, oldRow,newCol, newRow;
	static int turn = 2;
	
	Piece piece;
	Piece capture;
	
	
	public Move(Board board, Piece piece, int newCol, int newRow) {
		 
		
		this.oldCol = piece.col;
		this.oldRow = piece.row;
		this.newCol = newCol;
		this.newRow = newRow;
		this.piece = piece;
		this.capture = board.getPiece(newCol, newRow);
		
		
	}

	public void turn() {
		turn++;
	}
	public int getTurn() {
		return turn;
	}
}
