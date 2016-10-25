import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Models an 'object' that is rendered in the game display.
 * 
 * Each object has a "bounding box" defined by its top left and bottom right
 * corners. Each object has a speed in the x and y directions, which can be
 * changed the set and accel methods.
 * 
 * Subclasses may add additional behavior as necessary. Subclasses should NOT
 * implement rendering behavior; that is to be implemented by classes that use
 * GameObjects.
 * 
 * @author sdexter72
 * 
 */

public class GameObject {
	protected Point topLeft; // initial coordinates of top left corner of object
	protected Point bottomRight; // initial coordinates of bottom left corner of
									// object
	protected static Random random;
	protected int xSpeed;
	protected int ySpeed;

	protected boolean bounced = false;
	static int speedCounter = 0;

	/**
	 * 
	 * CHANGED Returns true if object1 and object2 have collided.
	 * 
	 * @param object1
	 *            game object
	 * @param object2
	 *            game object
	 * @return The two objects have collided.
	 */

	public static boolean didCollide(GameObject object1, GameObject object2) {
		Rectangle Obj1 = getObjRec(object1);
		Rectangle Obj2 = getObjRec(object2);
		if (Obj1.intersects(Obj2)) {
			return true;
		}
		return false;
	}

	/**
	 * @param obj
	 *            object we're getting the hit box for
	 * @return The rectangle that encompass' the GameObject
	 */
	private static Rectangle getObjRec(GameObject obj) {
		return new Rectangle(obj.topLeft.x, obj.topLeft.y, obj.bottomRight.x
				- obj.topLeft.x, obj.bottomRight.y - obj.topLeft.y);
	}

	/**
	 * Initialize object with top and bottom corners and initial x- and y-speed
	 * 
	 */

	public GameObject(Point topLeft, Point bottomRight, int xSpeed, int ySpeed) {
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;

		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		random = new Random();
	}

	/**
	 * Initialize object with top corner, height, width, and initial x- and
	 * y-speed
	 * 
	 */

	public GameObject(int initX, int initY, int height, int width, int xSpeed,
			int ySpeed) {
		this(new Point(initX, initY), new Point(initX + width, initY + height),
				xSpeed, ySpeed);
	}

	/**
	 * Initialize object with top corner at (0,0), with given height and width,
	 * and initial speed 5 in the x-dimension
	 * 
	 */

	public GameObject(int height, int width) {
		this(0, 0, height, width, 5, 0);
	}

	/**
	 * Set the GameObject's speed in the x dimension
	 * 
	 * @param xSpeed
	 *            The desired x-speed.
	 */

	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	/**
	 * Set the GameObject's speed in the x dimension
	 * 
	 * @param ySpeed
	 *            The desired y-speed.
	 */
	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	/**
	 * Increase the GameObject's speed in the x dimension
	 * 
	 * @param x
	 *            The amount of increase.
	 */

	public void accelX(int x) {
		if (xSpeed > 0)
			xSpeed += x;
		else
			xSpeed -= x;
	}

	/**
	 * Increase the GameObject's speed in the y dimension
	 * 
	 * @param y
	 *            The amount of increase.
	 */

	public void accelY(int y) {
		if (ySpeed > 0)
			ySpeed += y;
		else
			ySpeed -= y;
	}

	/**
	 * 
	 * @return A Point representing the top-left corner of the GameObject's
	 *         bounding box
	 */
	public Point getTopLeft() {
		return topLeft;
	}

	/**
	 * 
	 * @return A Point representing the bottom-right corner of the GameObject's
	 *         bounding box
	 */

	public Point getBottomRight() {
		return bottomRight;
	}

	/**
	 * 
	 * @return The height (in pixels) of the GameObject
	 */
	public int getHeight() {
		return bottomRight.y - topLeft.y;
	}

	/**
	 * 
	 * @return The width (in pixels) of the GameObject
	 */

	public int getWidth() {
		return bottomRight.x - topLeft.x;

	}

	/**
	 * Changes the location of the obstacle object for the next
	 * "animation frame"
	 * 
	 */
	public void step() {
		
		bottomRight.y += ySpeed;
		topLeft.x += xSpeed;
		bottomRight.x += xSpeed;
		topLeft.y += ySpeed;
		// System.out.println("bottomRighty" + bottomRight.y);

	}

	/**
	 * A function to check if the ball needs to bounce and changes the ball's
	 * velocity appropriately
	 * 
	 * @param ball
	 *            The GameObject that should be checked
	 * @param isPlayer
	 *            if the function is called on a player then the game is over.
	 *            we end the game by setting 'bounced' to true, signaling the
	 *            tick to end the game.
	 */
	protected void shouldBounce(boolean isPlayer) {
		if (this.topLeft.x + this.getWidth() * 1.2 > 640) {
			if (isPlayer == true) {
				bounced = true;
				return;
			} else {
				this.bounce(true);
			//	this.bottomRight.x = 640 - this.getWidth()/2;
			//	this.topLeft.x = 640 - this.getWidth()/2;
			}
		} else if (this.topLeft.x < 0) {
			if (isPlayer == true) {
				bounced = true;
				return;
			} else {
				this.bounce(true);
			//	this.bottomRight.x = this.getWidth()/2;
				//this.topLeft.x = this.getWidth()/2;
			}
		}

		if (this.topLeft.y + getWidth() * 2 > 480 ) {
			if (isPlayer == true) {
				bounced = true;
				return;
			} else {
				this.bounce(false);
				//this.bottomRight.y = 480 - this.getHeight()/2;
				//this.topLeft.y = 480 - this.getHeight()/2;
			}
		} else if (this.topLeft.y < 0) {
			if (isPlayer == true) {
				bounced = true;
				return;
			} else {
				this.bounce(false);
				//this.bottomRight.y = this.getHeight()/2;
				//this.topLeft.y = this.getHeight()/2;
			}
		}
	}

	/**
	 * CHANGED Simply negates the axis that collided with the boundary Only used
	 * to move the enemy objects, or end the game if the object is the player
	 * object
	 * 
	 * @param isX
	 *            if true negates the x-axis speed
	 */

	protected void bounce(boolean isX) {
		if (isX == true)
			this.xSpeed = -(xSpeed);
		else
			this.ySpeed = -(ySpeed);
	}

	/**
	 * 
	 * @param i
	 *            The amount by which you wish to increment the speed
	 */
	public void speedup(int i) {
		if (Math.abs(xSpeed) < 30 && Math.abs(ySpeed) < 30) {
			accelX(i);
			// System.out.println("XSpeed = " + xSpeed);
			accelY(i);
			// System.out.println("YSpeed = " + ySpeed);
		}
	}
	
	/**
	 * Speeds up the player object in 500 frame intervals The speed is
	 * calculated by choosing a random value between 1 and 5
	 */
	protected void speedUp() {
		// every 250 frames we speed up the player
		if (speedCounter > 250) {
			int randomInt = random.nextInt(5) + 1;
			this.speedup(randomInt);
			speedCounter = 0;
		}
	}
	
	public void incSpeedCounter() {
		speedCounter++;
	}
}
