import java.awt.Point;
import java.awt.Rectangle;

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

	protected int xSpeed;
	protected int ySpeed;

	/**
	 * 
	 * CHANGED Returns true if object1 and object2 have collided.
	 * 
	 * @return The two objects have collided.
	 */

	public static boolean didCollide(GameObject object1, GameObject object2) {
		Rectangle Obj1 = getObjRec(object1);
		Rectangle Obj2 = getObjRec(object2);
		if(Obj1.intersects(Obj2)){
			return true;
		}
		return false;
	}
    
	/**
	 * 
	 * @return The rectangle that encompass' the GameObject
	 */
	private static Rectangle getObjRec(GameObject obj) {
        return new Rectangle(obj.topLeft.x, obj.topLeft.y, obj.bottomRight.x-obj.topLeft.x, obj.bottomRight.y-obj.topLeft.y);
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
	}

	/**
	 * Initialize object with top corner, height, width, and initial x- and
	 * y-speed
	 * 
	 */

	public GameObject(int initX, int initY, int height, int width, int xSpeed, int ySpeed) {
		this(new Point(initX, initY), new Point(initX + width, initY + height), xSpeed, ySpeed);
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
		if(xSpeed > 0)
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
		if(ySpeed > 0)
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
	 * Changes the location of the player object for the next "animation frame"
	 * 
	 */

	public void move() {
		if(GameController.upDirection == true){
			topLeft.y -= ySpeed;
			bottomRight.y -= ySpeed;
		}
		if(GameController.downDirection == true){
			topLeft.y += ySpeed;
			bottomRight.y += ySpeed;
		}
		if(GameController.rightDirection == true){
			topLeft.x += xSpeed;
			bottomRight.x += xSpeed;
		}
		if(GameController.leftDirection == true){
			topLeft.x -= xSpeed;
			bottomRight.x -= xSpeed;
		}
		shouldBounce(this, true);
	}
	public void step() {
	topLeft.x += xSpeed;
//	System.out.println("topLeftx" + topLeft.x);
	bottomRight.x += xSpeed;
//	System.out.println("bottomRightx" + bottomRight.x);

	topLeft.y += ySpeed;
//	System.out.println("topLefty" + topLeft.y);
	bottomRight.y += ySpeed;
//	System.out.println("bottomRighty" + bottomRight.y);
	shouldBounce(this,false);
	}
	
	/**
	 * A function to check if the ball needs to bounce
	 * and changes the ball's velocity appropriately
	 * @param ball The GameObject that should be checked
	 */
	private void shouldBounce(GameObject ball, boolean isPlayer){
		if(ball.getBottomRight().x >=  500) {
			ball.bounce(true);
			if(isPlayer == true)
				ball.move();
			else
				ball.step();
			}
		if(ball.topLeft.x < -5) {
			ball.bounce(true);
			if(isPlayer == true)
				ball.move();
			else
				ball.step();
			}
		if(ball.getBottomRight().y + (ball.getHeight()/2) >= 500) {
			ball.bounce(false);
			if(isPlayer == true)
				ball.move();
			else
				ball.step();
			}
		if(ball.topLeft.y < -5) {
			ball.bounce(false);
			if(isPlayer == true)
				ball.move();
			else
				ball.step();
			}
	}
	/**
	 * IMPLEMENT THIS: What should an object do if it reaches the boundary of
	 * the game space?
	 * 
	 * (Of course, you can override this implementation if your subclasses
	 * should behave differently)
	 */

	public void bounce(boolean isX) {
		if(isX == true){
			this.xSpeed = -xSpeed;
		} else {
			this.ySpeed = -ySpeed;
		}
	}

	public void speedup(int i) {
		if(Math.abs(xSpeed) < 30 && Math.abs(ySpeed) < 30){
			accelX(i);
			System.out.println("XSpeed = " + xSpeed);
			accelY(i);
			System.out.println("YSpeed = " + ySpeed);
		}
	}
}
