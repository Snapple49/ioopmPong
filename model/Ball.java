package model;
import java.awt.Point;

public class Ball extends Point {
	
	public Point velocity;
	
	public Ball(int x, int y, Point velocity) {
		super(x, y);
		this.velocity = velocity;
	}
	
	public void move() {
		this.x = this.x + this.velocity.x;
		this.y = this.y + this.velocity.y;
	}
	public void changeDirection(boolean bar) {
		if (bar) {
			this.x = -(this.x);
		}
		else {
			this.y = -(this.y);
		}
	}
	
	
}
