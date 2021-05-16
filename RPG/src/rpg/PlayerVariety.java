package rpg;

import javafx.scene.paint.Color;
/**
 * Three sprites for choosable three player categories
 * 
 * @author kai
 *
 */
public enum PlayerVariety {

	//3 Types of player to choose from
	BLUE("images/playersprite11.png", "ROYALBLUE", Color.NAVY),
	RED("images/playersprite2.png", "CRIMSON", Color.FIREBRICK),
	YELLOW("images/playersprite3.png", "GOLD", Color.DARKORANGE);
	
	private PlayerVariety(String img, String colStr, Color col) {
		_imgStr = img;
		_colorStr = colStr;
		_col = col;
	}
	
	/**
	 * Getters, setters, and instance variables
	 */
	
	//Filepath to sprite for player variety
	private String _imgStr;
	public String getImgStr() {return _imgStr;}
	
	//String for FXML color selection
	public String _colorStr;
	public String getColorStr() {return _colorStr;}
	
	//JavaFX Color object
	public Color _col;
	public Color getColor() {return _col;}
}
