package model;
import java.awt.Point;

public class PointList<X extends Point> {
	private X first;
	private PointList<X> rest;
	
	public PointList(X first, PointList<X> rest) {
		this.first = first;
		this.rest = rest;
	}
	
	public X getFirst() {
		return this.first;
	}
	
	public PointList<X> getRest() {
		return this.rest;
	}
	
	
	
}
