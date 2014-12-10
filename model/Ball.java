package model;
import java.awt.Point;

/**
 * The Class Ball represents the ball in pong. It provides methods for moving the ball and setting the ball. 
 */
public class Ball extends Point {
	
	/** The velocity, where x i the velocity in the x-axis and y is the velocity in the y-axis. */
	public Point velocity;

	/**
	 * Instantiates a new ball at position pos and velocity vel.
	 *
	 * @param pos the ball's position
	 * @param vel the ball's velocity
	 */
	public Ball(Point pos, Point vel) {
		super(pos.x, pos.y);
		this.velocity = vel;
	}


	
	/**
	 * Sets the ball's position and velocity. 
	 *
	 * @param pos the ball's new position.
	 * @param vel the ball's new velocity.
	 */
	public void setBall(Point pos, Point vel){
		this.setLocation(pos);
		if (vel.x <= 50 && vel.x >= -50 && vel.y <= 50 && vel.y >= -50){
			this.velocity = vel;
		}
	}

	/**
	 * Changes the ball position using the ball's velocity.
	 */
	public void move() {
		this.x = this.x + this.velocity.x;
		this.y = this.y + this.velocity.y;
	}
	
	/**
	 * Bounces the ball horizontally or vertically, depending on horizontal flag.
	 *
	 * @param horizontal true if the ball will bounce horizontally. 
	 */
	public void bounce(boolean horizontal) {
		if (horizontal) {
			this.velocity.x = -(this.velocity.x);
		}
		else {
			this.velocity.y = -(this.velocity.y);
		}
	}


}
