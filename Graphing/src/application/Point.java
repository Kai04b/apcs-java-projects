package application;

import javafx.scene.paint.Color;


/**
 * x,y point of data
 * 
 * @author kai
 *
 */
public class Point {
	
	/**
	 * Getters, setters, and instance variables
	 */
	
	private double _x;
	public double getX() {return _x;}
	
	private double _y;
	public double getY() {return _y;}
	
	private double _size;
	public double getSize() {return _size;}
	
	private Color _col;
	public Color getColor() {return _col;}
	
	public Point(double x, double y, double size, Color c) {
		_x = x;
		_y = y;
		_size = size;
		_col = c;
	}
	
	/**
	 * Check if two points are equivalent
	 * 
	 * @param p other point to compare to
	 * @return true if equivalent (x = x and y = y), false otherwise
	 */
	public boolean equals(Point p) {
		if(_x == p.getX() &&_y == p.getY()) 
			return true;
		else
			return false;
	}
	
	/**
	 * Replace this point with instance variable of another, copy
	 * 
	 * @param p Point to copy
	 */
	public void replace(Point p) {
		_x = p.getX();
		_y = p.getY();
	}
	
	/**
	 * Convert Point object to String, containg x-pos, y-pos, and color info
	 */
	public String toString() {
		return " / X-pos: " + _x + " / Y-pos: " + _y + " / Color: " + _col;
	}
	
	
}
