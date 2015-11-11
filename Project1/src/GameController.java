import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends KeyAdapter {
    static boolean leftDirection = false;
	static boolean upDirection = false;
	static boolean downDirection = false;
	static boolean rightDirection = false;
		
		/**
		 * Listener for keyboard input: affects the ball position
		 * forcing the ball to move on either a horizontal or
		 * vertical slope
		 */
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
			
			if ((key == KeyEvent.VK_LEFT) && (!leftDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
                rightDirection = false;
                System.out.println("LEFT KEY");
            }

            if ((key == KeyEvent.VK_RIGHT) && (!rightDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
                leftDirection = false;
                System.out.println("Right KEY");
            }

            if ((key == KeyEvent.VK_UP) && (!upDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
                downDirection = false;
                System.out.println("Up KEY");
            }

            if ((key == KeyEvent.VK_DOWN) && (!downDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
                upDirection = false;
                System.out.println("Down KEY");
            }
        }
    }