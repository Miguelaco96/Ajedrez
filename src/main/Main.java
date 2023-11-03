package main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
    	
    	
        JFrame frame = new JFrame();
        frame.setBackground(Color.black);
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);

        // Crear el tablero (Board)
        Board board = new Board();

        frame.setLayout(new GridBagLayout());

        // Agregar el tablero en la parte central y expandirlo
        frame.add(board);
        
        frame.setVisible(true);
        
    }
}