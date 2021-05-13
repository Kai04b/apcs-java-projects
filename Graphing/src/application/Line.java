package application;

import javafx.scene.paint.Color;

/**
 * Handles a linear equation
 * 
 * @author kai
 *
 */
public class Line {
	
	/**
	 * Getters, setters, and instance variables
	 */
	
	private double _slope;
	public double getSlope() {return _slope;}
	
	private double _yint;
	public double getYint() {return _yint;}
	
	private double _a;
	public double getA() {return _a;}
	
	private double _b;
	public double getB() {return _b;}
	
	private double _c;
	public double getC() {return _c;}
	
	private double _size;
	public double getSize() {return _size;}
	
	private Color _col;
	public Color getColor() {return _col;}
	
	public Line(double slope, double yint, double size, Color col) {
		_slope = slope;
		_yint = yint;
		
		_a = -_slope;
		_b = 1;
		_c = _yint;
		
		_size = size;
		_col = col;
	}
	
	public Line(double a, double b, double c, double size, Color col) {
		_a = a;
		_b = b;
		_c = c;
		
		_slope = -_a/_b;
		_yint = _c/_b;
		
		_size = size;
		_col = col;
		
	}
	
	/**
	 * Given two lines, find intersection point
	 * 
	 * @param l Line to be checked
	 * @return	Point of intersection, null if no intersection
	 */
	public Point intersection(Line l) {
		double x = (l.getYint() - _yint)/(_slope - l.getSlope());
		double y = function(x);
		if(Double.isNaN(x) || Double.isNaN(y)) {
			return null;
		}
		return new Point(x, y, 5, _col);
	}
	
	/**
	 * Check if two lines are equivalent
	 * 
	 * @param l	Other line to be checked
	 * @return	true if the lines are the same, false if different
	 */
	public boolean equals(Line l) {
		if(_slope == l.getSlope() && _yint == l.getSlope())
			return true;
		else if(_a == l.getA() && _b == l.getB() && _c == l.getC())
			return true;
		else
			return false;
	}
	
	/**
	 * Calculate f(x)
	 * 
	 * @param x x-value to be plugged in
	 * @return corresponding y-value
	 */
	public double function(double x) {
		return _slope * x + _yint;
	}
	
	/**
	 * Modify a line, basically copy it from another
	 * 
	 * @param l Line to copy instance variables from
	 */
	public void replace(Line l) {
		_slope = l.getSlope();
		_yint = l.getYint();
		_a = l.getA();
		_b = l.getB();
		_c = l.getC();
	}
	
	/**
	 * Convert line to string, containging slope intercept, standard form, color, and thickness
	 */
	public String toString() {
		return " / Slope: " + _slope + " / Y-intercept: " + _yint + " / A: " + _a + " / B: " + _b + " / C: " + _c + " / Color: " + _col.toString();
	}
}
