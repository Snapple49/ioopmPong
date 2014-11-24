package model;
import java.awt.Point;

public class Ball extends Point {

	protected class InvalidVelocityException extends IllegalArgumentException{
		public InvalidVelocityException(String errormsg){
			System.err.println(errormsg);
		}
	}
	
	public Point velocity;

	public Ball(Point pos, Point velocity) {
		super(pos.x, pos.y);
		this.velocity = velocity;
	}


	//TODO - add defensive programminge
	public void setBall(Point pos, Point vel){
		this.setLocation(pos);
		if (this.velocity.x <= 50 && this.velocity.x >= -50 && this.velocity.y <= 50 && this.velocity.y >= -50){
			this.velocity = vel;
		}else throw new InvalidVelocityException("The velocity of the ball was set too low or high! Keep withing -50 and 50.");
	}

	public void move() {
		this.x = this.x + this.velocity.x;
		this.y = this.y + this.velocity.y;
	}
	public void changeDirection(boolean bar) {
		if (bar) {
			this.velocity.x = -(this.velocity.x);
		}
		else {
			this.velocity.y = -(this.velocity.y);
		}
	}


}
