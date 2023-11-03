//package main;
//
//import javax.swing.JLabel;
//
//public class CheckMate extends JLabel{
//
//	
//    victoryImageLabel = new JLabel(new ImageIcon("victory_image.png"));
//    victoryImageLabel.setVisible(false); // La hace invisible inicialmente
//    frame.add(victoryImageLabel);
//
//    // Agrega un ActionListener que muestra la imagen de victoria cuando ocurra un jaque mate
//    JButton checkmateButton = new JButton("Comprobar mate");
//    checkmateButton.addActionListener(new ActionListener() {
//    	
//    	
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            
//            boolean checkmate = isCheckmate(); 
//
//            if (checkmate) {
//                victoryImageLabel.setVisible(true);
//            }
//        }
//    });
//    add(checkmateButton);
//}
//}
