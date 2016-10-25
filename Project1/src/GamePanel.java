import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Animates a simple graphical game.
 * 
 * Uses a Swing Timer to advance the animation; keeps track of, and renders, all
 * GameObjects. Handles all relevant game events.
 * 
 * @author sdexter72,5igm4
 * 
 */

public class GamePanel extends JPanel implements ActionListener {

	static final int FRAME_RATE = 30; // animation proceeds at 30 frames per
										// second
	private int SCORE = 0;
	private boolean isGameOver = false;
	Random random = new Random();
	Timer t; // animation timer
	JButton b3;
	int randomInt = random.nextInt(7) + 1;
	PlayerGameObject player; // bare-bones animation: just a simple object that
								// slides across the panel
	GameObject[] obstacleArr = {
			(new GameObject(0, 250, 35, 35, random.nextInt(7) + 1,
					random.nextInt(7) + 1)),
			(new GameObject(239, 300, 35, 35, random.nextInt(7) + 1,
					random.nextInt(7) + 1)),
			(new GameObject(260, 20, 35, 35, random.nextInt(7) + 1,
					random.nextInt(7) + 1)) };

	/**
	 * Sets up panel background, creates game Timer, creates initial GameObjects
	 * 
	 */

	GamePanel() {
		addKeyListener(new GameController());
		setFocusable(true);
		setBackground(Color.WHITE);
		t = new Timer(1000 / FRAME_RATE, this);
		player = new PlayerGameObject(250 - 35, 250 - 35, 35, 35, 5, 5);

	}

	/**
	 * How to render one "frame" of the animation
	 */

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawPlayer(g, player);
		drawObstacles(g);
		drawString(g, 362, 466, 14, ("Score: " + SCORE), Color.BLACK);
		if (isGameOver == true) {
			drawString(g, 100, 250, 50, "GAME OVER", Color.WHITE);
			drawString(g, 100, 300, 20, ("Final Score: " + SCORE), Color.WHITE);
		}
	}

	/**
	 * Prints strings on the gamepanel
	 * 
	 * @param g
	 *            graphics
	 * @param x
	 *            x position
	 * @param y
	 *            y position
	 * @param size
	 *            Size of font
	 * @param draw
	 *            String to printout
	 * @param col
	 *            color of the font
	 */
	private void drawString(Graphics g, int x, int y, int size, String draw,
			Color col) {
		g.setFont(new Font("Helvetica", Font.BOLD, size));
		g.setColor(col);
		g.drawString(draw, x, y);
	}

	/**
	 * Draws the player gameObject
	 * 
	 * @param g
	 *            passed by paintComponent
	 * 
	 */
	private void drawPlayer(Graphics g, GameObject player) {
		g.setColor(Color.BLUE);
		g.fillOval(player.topLeft.x, player.topLeft.y, player.getWidth(),
				player.getHeight());
	}

	/**
	 * Draws the obstacles on the screen iterating through an array
	 * 
	 * @param g
	 *            passed by paintComponent
	 */
	private void drawObstacles(Graphics g) {
		g.setColor(Color.RED);
		for (GameObject obj : obstacleArr) {
			g.fillOval(obj.topLeft.x, obj.topLeft.y, obj.getWidth(),
					obj.getHeight());
		}
	}

	/**
	 * Responds to all actionPerformed events. In bare-bones implementation,
	 * these are just 'ticks' from the timer.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {

		// if this is an event from the Timer, call the method that advances the
		// animation
		if (e.getSource() == t) {
			tick();
			this.SCORE += 2;
		}
	}

	/**
	 * Make sure all GameObjects are in the right place (do any need to be
	 * removed? do we need to create any new ones?), then redraw game
	 */

	private void tick() {
		if (player.move()) // move checks if the player bounces, which
			doGameOver(); // ends the game

		isGameOver(obstacleArr, player);
		
		player.incSpeedCounter();
		player.speedUp();

		repaint();

		// ask to have the game redrawn (this will invoke paintComponent()
		// when the system says the time is right)

	}

	/**
	 * Start the Timer: this will cause events to be fired, and thus the
	 * animation to begin
	 */

	void go() {
		t.start();
	}

	/**
	 * Move the obstacle objects and check if they collided with the player if a
	 * collision occurred then the game is over
	 * 
	 * @param obstacleArr
	 *            array of obstacle objects
	 * @param player
	 *            the player game object
	 */
	public void isGameOver(GameObject[] obstacleArr, GameObject player) {
		for (GameObject obj : obstacleArr) {
			if (GameObject.didCollide(player, obj)) {
				System.out.println("Collision end");
				doGameOver();
			}
			obj.incSpeedCounter();
			obj.speedUp();
			obj.step();
			obj.shouldBounce(false);
		}
	}

	public void doGameOver() {
		isGameOver = true;
		setFocusable(false);
		t.stop();
		setBackground(Color.BLACK);
	}

	Thread isGameEndThread = new Thread() {
		public void run() {
			new GamePanel().isGameOver(obstacleArr, player);
		}
	};
}
