import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Sets up the GUI and starts the game.
 * 
 * @author sdexter72
 *
 */
@SuppressWarnings("unused")
public class Main{

    public static void main(String[] args) {
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	displayScreen();
            }
        });

    }

	
private static void displayScreen() {

    JFrame frame = new JFrame("Let's Play...");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    
    // bare bones: just add a panel where the game objects are drawn
    
    GamePanel gamePanel = new GamePanel();
    frame.add(gamePanel);

    frame.setSize(640, 480);
    frame.setResizable(false);
    frame.setVisible(true);
    
    // the game starts when the gamepanel animation begins
    gamePanel.go();
    
    

}

}