package model;
import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class Ball represents the ball in Pong. It provides methods for moving the ball using the ball's velocity and placing the ball somewhere on the field. 
 */
public class Ball extends Point {

	/**
	 * The Class InvalidVelocityException is an exception that indicates that the ball velocity is to high. 
	 */
	public class InvalidVelocityException extends IllegalArgumentException{
		
		/**
		 * Instantiates a new invalid velocity exception.
		 *
		 * @param errormsg the errormsg
		 */
		public InvalidVelocityException(String errormsg){
			System.err.println(errormsg);
		}
	}
	
	/** The velocity. */
	public Point velocity;

	/**
	 * Instantiates a new ball.
	 *
	 * @param pos the ball's position
	 * @param velocity the ball's velocity
	 */
	public Ball(Point pos, Point velocity) {
		super(pos.x, pos.y);
		this.velocity = velocity;
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
		}else throw new InvalidVelocityException("The velocity of the ball was set too low or high! Keep within -50 and 50.");
	}

	/**
	 * Moves the ball using the ball's velocity.
	 */
	public void move() {
		this.x = this.x + this.velocity.x;
		this.y = this.y + this.velocity.y;
	}
	
	/**
	 * Bounces the ball on a horizontal or vertical object, depending on horizontal.
	 *
	 * @param horizontal true if the ball will bounce on an horizontal object. 
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
