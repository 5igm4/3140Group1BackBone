import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Sets up the GUI and starts the game.
 * 
 * @author sdexter72
 *
 */
public class Main{

    public static void main(String[] args) {
    	
        JFrame frame = new JFrame("Let's Play...");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // bare bones: just add a panel where the game objects are drawn
        
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel, BorderLayout.CENTER);

        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setVisible(true);
        
        // the game starts when the gamepanel animation begins
        gamePanel.go();

    }
}