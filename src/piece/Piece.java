package piece;

import javax.imageio.*;

import main.Board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class Piece {
	
	public int col, row;
	public int xPos, yPos;
	public boolean isWhite;
	public String name;
	public int value;
	
	BufferedImage sheet;
	public boolean isFirstMove = true;
	
	{
		try {
			URL imageURL = getClass().getResource("/img/Chess_Pieces_Sprite.png");
//			
			sheet = ImageIO.read(imageURL);
		     
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	protected int sheetScale = sheet.getWidth()/6;
	
	Image sprite;
		
	Board board;
	
	public Piece(Board board) {
		this.board = board;
	}
	public boolean isValidMovement(int col, int row){return true;} 
	public boolean moveWithColliderWhitPiece(int col, int row){return false;} 
	
	public void paint(Graphics2D g2d) {
		
		g2d.drawImage(sprite,xPos,yPos,null);
		
	}
	
}
