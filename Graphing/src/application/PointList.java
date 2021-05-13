package application;

import java.util.ArrayList;

/**
 * An editable list of point objects
 * 
 * @author kai
 *
 */
public class PointList {

	/**
	 * Instance variables and getter
	 */
	
	private ArrayList<Point> _pointList;
	public ArrayList<Point> getPointList() {return _pointList;}
	
	public PointList() {
		_pointList = new ArrayList<Point>();
	}
	
	/**
	 * Add a point to the list
	 * @param p Point to be added
	 */
	public void addPoint(Point p) {
		_pointList.add(p);
	}
	
	/**
	 * Remove a point from the list
	 * 
	 * @param p Point to be removed
	 */
	public void removePoint(Point p) {
		for(int i = 0; i < _pointList.size(); i++) {
			if(_pointList.get(i).equals(p)) {
				_pointList.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Check if point exists within list
	 * 
	 * @param p Point to check
	 * @return 	true if Point is in list
	 */
	public boolean pointExists(Point p) {
		for(Point point: _pointList) {
			if(p.equals(point)) {
				return true;
			}
		}
		return false;
	}
}
