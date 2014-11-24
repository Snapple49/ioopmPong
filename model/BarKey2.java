package model;
import java.awt.Point;

public class BarKey2 extends Point{
	public int length;
	
	public BarKey2(int x, int y, int length) {
		super(x, y);
		this.length = length;
		
		
		
	}
	
	public void move(boolean up) {
		if (up) {
			this.y++;
		}
		else {
			this.y--;
		}
	}
	
	
}
