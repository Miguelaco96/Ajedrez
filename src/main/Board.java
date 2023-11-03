package main;

import java.awt.*;

import javax.swing.*;


import java.util.ArrayList;

import piece.*;


public class Board extends JPanel{

	
	private static final long serialVersionUID = 1L;

	public int tileSize=85;
	int cols=8;
	int rows=8;
	
	ArrayList <Piece> pieceList = new ArrayList<>();
	ArrayList <Piece> whitePieceList = new ArrayList<>();
	ArrayList <Piece> blackPieceList = new ArrayList<>();
	Input input = new Input(this);
	public CheckScanner checkScanner = new CheckScanner(this);
	public Piece selectedPiece;
	
	public Board() {
		
		this.setPreferredSize(new Dimension(cols*tileSize, rows*tileSize));
		addPiece();
		this.addMouseListener(input);
		this.addMouseMotionListener(input);
	
	}
	public void addPiece() {
		
		//caballos
		pieceList.add(new Knight(this,1,0,false));
		pieceList.add(new Knight(this,6,0,false));
		pieceList.add(new Knight(this,1,7,true));
		pieceList.add(new Knight(this,6,7,true));
		
		// Peones blancos
		pieceList.add(new Pawn(this, 0, 6, true));
		pieceList.add(new Pawn(this, 1, 6, true));
		pieceList.add(new Pawn(this, 2, 6, true));
		pieceList.add(new Pawn(this, 3, 6, true));
		pieceList.add(new Pawn(this, 4, 6, true));
		pieceList.add(new Pawn(this, 5, 6, true));
		pieceList.add(new Pawn(this, 6, 6, true));
		pieceList.add(new Pawn(this, 7, 6, true));

		// Peones negros
		pieceList.add(new Pawn(this, 0, 1, false));
		pieceList.add(new Pawn(this, 1, 1, false));
		pieceList.add(new Pawn(this, 2, 1, false));
		pieceList.add(new Pawn(this, 3, 1, false));
		pieceList.add(new Pawn(this, 4, 1, false));
		pieceList.add(new Pawn(this, 5, 1, false));
		pieceList.add(new Pawn(this, 6, 1, false));
		pieceList.add(new Pawn(this, 7, 1, false));
		
		// Reyes blancos
		pieceList.add(new King(this, 4, 7, true));

		// Reinas blancas
		pieceList.add(new Queen(this, 3, 7, true));

		// Alfiles blancos
		pieceList.add(new Bishop(this, 2, 7, true));
		pieceList.add(new Bishop(this, 5, 7, true));

		// Torres blancas
		pieceList.add(new Rook(this, 0, 7, true));
		pieceList.add(new Rook(this, 7, 7, true));

		// Reyes negros
		pieceList.add(new King(this, 4, 0, false));

		// Reinas negras
		pieceList.add(new Queen(this, 3, 0, false));

		// Alfiles negros
		pieceList.add(new Bishop(this, 2, 0, false));
		pieceList.add(new Bishop(this, 5, 0, false));

		// Torres negras
		pieceList.add(new Rook(this, 0, 0, false));
		pieceList.add(new Rook(this, 7, 0, false));
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d =(Graphics2D)g;
		
		for (int r=0; r<rows; r++) {
			for(int c=0; c<cols;c++) {
				g2d.setColor((c+r)%2==0 ? Color.white : Color.darkGray);
				g2d.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
			}
		}
		if(selectedPiece!=null)
		for (int r=0 ; r<rows ; r++) {
			for(int c=0; c< cols ; c++) {
				
				if(isValidMove(new Move(this , selectedPiece , c , r ))){
					g2d.setColor(new Color (70,180,60,190));
					g2d.fillRect(c*tileSize, r* tileSize, tileSize, tileSize);
				
				}if(checkScanner.isKingChecked(new Move(this , findKing(true) , findKing(true).col , findKing(true).row))) {
					g2d.setColor(new Color (255,0,0,190));
					g2d.fillRect(findKing(true).col*tileSize,findKing(true).row*tileSize, tileSize, tileSize);
					
					
				
				}if(checkScanner.isKingChecked(new Move(this , findKing(false) , findKing(false).col , findKing(false).row))) {
					g2d.setColor(new Color (255,0,0,190));
					g2d.fillRect(findKing(false).col*tileSize,findKing(false).row*tileSize, tileSize, tileSize);
						
				}
					
				}	
		}
		for(Piece piece: pieceList) {
			piece.paint(g2d);
			}
		}
	
	public boolean isCheckMate(ArrayList<Piece> pieceList){
		for(Piece p: pieceList) {

			for (int r=0 ; r<rows ; r++) {
				for(int c=0; c< cols ; c++) {
					
					if(isValidMove(new Move(this , p , c , r ))){
						
						return false;												
					}					
				}
			}
		}
		
		return true;
	}
	
	public boolean isValidMove(Move move) {
		
		if(checkScanner.isKingChecked(move)) {
			System.out.println("falso por cherk fallo");
			System.out.println(isCheckMate(getPieceList()));
			return false;
		}
		if(sameTeam(move.piece, move.capture)){
			
			return false;
		}
		if(!move.piece.isValidMovement(move.newCol, move.newRow)) {
			return false;
		}
		if(move.piece.moveWithColliderWhitPiece(move.newCol, move.newRow)) {
			return false;
		}
		
		if(move.getTurn()%2 == 0 && !move.piece.isWhite) {
			return false;
		}
		if(move.getTurn()%2 != 0 && move.piece.isWhite) {
			return false;
		}
			
	 	
	 return true;
	 
	}
	

	public Piece getPiece(int col, int row) {
		
		for(Piece piece: pieceList) {
			if(piece.col == col && piece.row == row) {
				return piece;			
			}		
		}
		return null;
	}
	
	
	
	public boolean sameTeam(Piece p1, Piece p2) {
		if(p1==null||p2==null) {
			return false;
			
		}return p1.isWhite == p2.isWhite;
	}

	
	public Piece findKing(boolean isWhite) {
		for(Piece piece : pieceList) {
			if(isWhite == piece.isWhite && piece.name.equals("King")) {
				return piece;
			}
		}
		return null;
	}
	
	public void capture(Move move) {
		
		pieceList.remove(move.capture);
	}

	public void backMove(Move move) {
		
		move.piece.col = move.oldCol;
		move.piece.row = move.oldRow;
		move.piece.xPos = move.oldCol * tileSize;
		move.piece.yPos = move.oldRow * tileSize;
		
	}
	
	public void makeMove(Move move) {
		
		
		if(move.piece.name.equals("Pawn")) {
			movePawn(move);
			
		}else if (move.piece.name.equals("King")){
			moveKing(move);
			
		}
			move.piece.col = move.newCol;
			move.piece.row = move.newRow;
			move.piece.xPos = move.newCol * tileSize;
			move.piece.yPos = move.newRow * tileSize;
			
			move.piece.isFirstMove = false;
			move.turn();
			
			
			capture(move);
	}
	
		
	
	public int enPassantTile = -1;
	
	public int getTileNum(int col, int row) {
		return row * rows + col;
	}
	
	private void moveKing(Move move ){
		
		if(Math.abs(move.piece.col - move.newCol)==2) {
			Piece rook;
			if(move.piece.col<move.newCol) {
				rook =getPiece(7,move.piece.row);
				rook.col=5;
			}else {
				rook = getPiece(0,move.piece.row);
				rook.col = 3;
			}
			rook.xPos = rook.col * tileSize;
		}
		
	}
	
	
	private void movePawn(Move move) {
		
		int colorIndex = move.piece.isWhite ? 1: -1;
		
		if(getTileNum(move.newCol, move.newRow) == enPassantTile) {
			move.capture = getPiece(move.newCol,move.newRow + colorIndex);
			
		}
		
		if(Math.abs(move.piece.row - move.newRow)==2) {
			enPassantTile = getTileNum(move.newCol, move.newRow + colorIndex);
			
		}else {			
			
			enPassantTile = -1;
			
		}
		
        int colorProm = move.piece.isWhite ? 0: 7;
		if(move.newRow == colorProm) {
			promotePawn(move);
		}
		
		move.piece.col = move.newCol;
		move.piece.row = move.newRow;
		move.piece.xPos = move.newCol * tileSize;
		move.piece.yPos = move.newRow * tileSize;
		move.piece.isFirstMove = false;
		
		
		capture(move);
	}
	private void promotePawn(Move move) {
		pieceList.remove(move.piece);
		pieceList.add(new Queen (this, move.newCol, move.newRow, move.piece.isWhite));
	}
	public String getName(Piece piece) {
		
		
	return piece.name;
		
	}
	
	public ArrayList<Piece> getPieceList(){
		
		
		 return pieceList;
	}
	
}
