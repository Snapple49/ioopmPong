package model;

public class PointList<Point> {
	private Point first;
	private PointList<Point> rest;
	
	public PointList(Point first, PointList<Point> rest) {
		this.first = first;
		this.rest = rest;
	}
	
	public Point getFirst() {
		return this.first;
	}
	
	public PointList<Point> getRest() {
		return this.rest;
	}
	
	
	
}
