package application;

import java.util.ArrayList;

/**
 * Editable list of line objects
 * 
 * @author kai
 *
 */
public class LineList {

	/**
	 * Getters, setters, and instance variables
	 */
	
	private ArrayList<Line> _lineList;
	public ArrayList<Line> getLineList() {return _lineList;}
	
	public LineList() {
		_lineList = new ArrayList<Line>();
	}
	
	/**
	 * Add a Line object to the list of lines
	 * 
	 * @param l Line object to be added
	 */
	public void addLine(Line l) {
		_lineList.add(l);
	}
	
	/**
	 * Remove a line from the list of lines
	 * 
	 * @param l Line to be removed
	 */
	public void removeLine(Line l) {
		for(int i = 0; i < _lineList.size(); i++) {
			if(_lineList.get(i).equals(l)) {
				_lineList.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Check if a given line object exists within the list
	 * 
	 * @param l Line to check
	 * @return true if line exists in list, false otherwise
	 */
	public boolean lineExists(Line l) {
		for(Line line: _lineList) {
			if(line.equals(l)) {
				return true;
			}
		}
		return false;
	}
}
