import java.awt.Point;

public class PlayerGameObject extends GameObject{

	public PlayerGameObject(Point topLeft, Point bottomRight, int xSpeed, int ySpeed) {	
		super(topLeft,bottomRight,xSpeed,ySpeed);
	}

	/**
	 * Initialize object with top corner, height, width, and initial x- and
	 * y-speed
	 * 
	 */

	public PlayerGameObject(int initX, int initY, int height, int width, int xSpeed, int ySpeed) {
		super(new Point(initX, initY), new Point(initX + width, initY + height), xSpeed, ySpeed);
	}

	/**
	 * Initialize object with top corner at (0,0), with given height and width,
	 * and initial speed 5 in the x-dimension
	 * 
	 */

	public PlayerGameObject(int height, int width) {
		super(0, 0, height, width, 5, 0);
	}
	
	public boolean move() {
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
		return bounced;
	}
	
}