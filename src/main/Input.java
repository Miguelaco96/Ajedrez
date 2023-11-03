package main;
import java.awt.event.*;

import piece.Piece;

public class Input extends MouseAdapter{
	
   Board board;
	public Input(Board board) {
		this.board=board;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(board.selectedPiece != null) {
			
			board.selectedPiece.xPos = e.getX() - board.tileSize /2;
			board.selectedPiece.yPos = e.getY() - board.tileSize /2;
			
			board.repaint();			
		}	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int col = e.getX() / board.tileSize;
		
		int row = e.getY() / board.tileSize;
		
		Piece pieceXY = board.getPiece(col, row);
		if(pieceXY != null) {
			board.selectedPiece = pieceXY;
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int col = e.getX() / board.tileSize;
		
		int row = e.getY() / board.tileSize;
		
		if (board.selectedPiece != null) {
			
			Move move =new Move(board, board.selectedPiece, col, row);
			
			if (board.isValidMove(move)) {
				if(move.capture!= null){
					board.capture(move);					
				}
				board.makeMove(move);
				
				
				
			}else {
				
				board.backMove(move);
			}	
		}
		board.selectedPiece = null;
		
	
		board.repaint();
		
	}
}
