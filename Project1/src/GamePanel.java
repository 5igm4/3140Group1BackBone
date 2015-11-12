import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


/**
 * Animates a simple graphical game.
 * 
 * Uses a Swing Timer to advance the animation; keeps track of, and renders, all GameObjects. Handles all relevant game events.
 * 
 * @author sdexter72
 *
 */

public class GamePanel extends JPanel implements ActionListener {
	
   	static final int FRAME_RATE = 30; // animation proceeds at 30 frames per second
	private int speedCounter = 0;
    private int SCORE = 0;
	Random random = new Random();
   	Timer t;	// animation timer
	
	GameObject ball, ball2; // bare-bones animation: just a simple object that slides across the panel
	
	/**
	 * Sets up panel background, creates game Timer, creates initial GameObjects
	 * 
	 */
	
	GamePanel () {
		addKeyListener(new GameController());
		setFocusable(true);
		setBackground(Color.WHITE);
        t = new Timer(1000/FRAME_RATE, this);	
		ball = new GameObject(0,250,35,35,5,4);
		ball2 = new GameObject(250-35,250-35,35,35,3,3);
		
	}
	
	/**
	 * How to render one "frame" of the animation
	 */
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.blue);
		g.fillOval(ball.topLeft.x, ball.topLeft.y, ball.getWidth(), ball.getHeight());
		g.fillOval(ball2.topLeft.x, ball2.topLeft.y, ball2.getWidth(), ball2.getHeight());
        
		drawScore(g);
	}
	
	private void drawScore(Graphics g){
		String drawScore;
        g.setFont(new Font("Helvetica", Font.BOLD, 14));
        g.setColor(Color.BLACK);
		drawScore = "Score: " + SCORE;
        g.drawString(drawScore, 500 / 2 + 112, 450 + 16);
	}

	/**
	 * Responds to all actionPerformed events. In bare-bones implementation, these are just 'ticks' from the timer.
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// if this is an event from the Timer, call the method that advances the animation
		if (e.getSource() == t) {
			tick();
			this.speedCounter++;
			this.SCORE += 2;
		}
	}

	/**
	 * Make sure all GameObjects are in the right place (do any need to be removed? do we need to create any new ones?), then redraw game
	 */
	
	private void tick() {
//		shouldBounce(ball);
//		shouldBounce(ball2);
//		ball.step();
		if(speedCounter > 200) {
			int randomInt = random.nextInt(5) + 1;
			ball2.speedup(randomInt);
			speedCounter = 0;
		}
		ball2.move();// we just have the ball right now, so this is easy
		ball.step();
		if(GameObject.didCollide(ball2,ball)){
			System.out.println("COLLISION");
		}
		repaint();		// ask to have the game redrawn (this will invoke paintComponent() when the system says the time is right)
	}
	
	/**
	 * Start the Timer: this will cause events to be fired, and thus the animation to begin
	 */
	
	void go() {
		t.start();
	}

}
