package game;

import java.util.EnumSet;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Handles possible state of spaces
 * 
 * @author kai
 *
 */
public enum Space {
	
	/**
	 * All possible spaces
	 */
	AIR("Path", 0, Color.BLACK, Color.BLACK, Color.BLACK, "game/air.png"),
	WALL("Wall", 1, Color.BLACK, Color.BLACK, Color.WHITE, "game/wall.png"),
	PLAYER("Player", 2, Color.BLACK, Color.BLACK, Color.WHITE, "game/man.png"),
	EXIT("Exit", 3, Color.BLACK, Color.BLACK, Color.WHITE, "game/door.png"),
	ENEMY("Snake", 4, Color.BLACK, Color.BLACK, Color.WHITE, "game/snake.png"),
	WEAPON("Rock", 5, Color.BLACK, Color.BLACK, Color.WHITE, "game/rock.png"),
	KEY("Key", 6,Color.BLACK, Color.BLACK, Color.YELLOW, "game/key.png"),
	DOOR("Locked Door", 7, Color.BLACK, Color.BLACK, Color.BROWN, "game/lockeddoor.png"),
	TORCH("Torch", 8, Color.BLACK, Color.BLACK, Color.BROWN, "game/torch.png");
	
	/**
	 * Set of all Space enums for looping over
	 */
	private static EnumSet<Space> _allSpaces = EnumSet.allOf(Space.class);
	
	/**
	 * Instance variables
	 */
	private String _name;
	private int _num;
	private Color _fill;
	private Color _border;
	private Color _textFill;
	private Image _img;
	
	private Space(String n, int num, Color f, Color b, Color tf, String str) {
		_name = n;
		_num = num;
		_fill = Color.BLACK;
		_border = Color.BLACK;
		_textFill = Color.BLACK;
		_img = new Image(str);
	}
	/**
	 * Getters
	 */
	
	public String getName() {return _name;}
	public int getNum() {return _num;}
	public Color getFill() {return _fill;}
	public Color getBorder() {return _border;}
	public Color getTextFill() {return _textFill;}
	public Image getImage() {return _img;}
	
	/**
	 * Access and return Space enum at given index
	 * 
	 * @param num index to access
	 * @return Space at num index, null if none
	 */
	public static Space getSpace(int num) {
		for(Space s : _allSpaces) {
			if(s.getNum() == num) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Convert Space enum to String
	 * @return name
	 */
	public String toString() {
		return _name;
	}
	
	
}
