package rpg;

import javafx.scene.paint.Color;

/**
 * Three possible weapon categories
 * 
 * @author kai
 *
 */
public enum Type {
	
	/* A beats B 
	 * B beats C
	 * C beats A
	 */
	A("Blunt", Color.DARKBLUE), B("Sharp", Color.DARKRED), C("Encumbering", Color.DARKVIOLET);
	
	private Type(String name, Color color) {
		_name = name;
		_color = color;
	}
	
	//Instance variables, getters, and setters
	
	//Name of type
	private String _name;
	public String getName() {return _name;}
	
	//JavaFX Color object
	private Color _color;
	public Color getColor() {return _color;}
	
	//String representation of Type
	public String toString() {return getName();}
	
	/**
	 * Given two types, find the winner, for example
	 * 
	 * @param one first Type
	 * @param two second Type
	 * @return -1 if one wins, 0 if tie, 1 if two wins, -2 if error
	 */
	public static int findWinner(Type one, Type two) {
		if(one == two) {
			return 0;
		}
		if(		one == A && two == B ||
				one == B && two == C ||
				one == C && two == A) {
			return 1;
		}
		if(one == A && two == C ||
				one == B && two == A ||
				one == C && two == B) {
			return -1;
		}
		return -2;
	}
}