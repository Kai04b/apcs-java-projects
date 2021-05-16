package game;


/**
 * Player with position and ability to pick up various objects
 * 
 * @author kai
 *
 */
public class Player {
	
	/**
	 * Instance variables, getters, and setters
	 */
	
	private int _row, _col;
	private boolean _isAlive;
	private boolean _sword;
	private boolean _boots;
	private boolean _rune;
	private boolean _key;
	private int _steps;
	
	public int getRow() { return _row; }
	public int getCol() { return _col; }
	public void setPos(int r, int c) { _row = r; _col = c; }
	
	
	public Player(int r, int c) {
		_row = r;
		_col = c;
		_isAlive = true;
		_steps = 0;
		_sword = false;
	}
	
	/**
	 * Construct player based on copy of another player
	 * 
	 * @param p2 Other player to be copied
	 */
	public Player(Player p2) {
		_row = p2.getRow();
		_col = p2.getCol();
		_isAlive = p2.isAlive();
		_steps = p2.getSteps();
		_sword = p2.hasSword();
	}
	
	public Player() {
		_row = -1;
		_col = -1;
		_isAlive = true;
		_steps = 0;
		_sword = false;
	}
	

	/**
	 * Move enemy in different directions, don't allow to move into wall or border
	 * If attempting move into door, only allow if has key
	 * 
	 * @return
	 */
	
	public boolean moveUp() {
		int row = getRow();
		int col = getCol();
		if(row - 1 >= 0 && MazeArray.getInstance().getSpace(row - 1, col) != Space.WALL && MazeArray.getInstance().getSpace(row - 1, col) != Space.DOOR) {
			setPos(row - 1, col);
			return true;
		}
		if(row - 1 >= 0 && MazeArray.getInstance().getSpace(row - 1, col) != Space.WALL && MazeArray.getInstance().getPlayer().hasKey()) {
			setPos(row - 1, col);
			return true;
		}
		return false;
	}
	public boolean moveDown() {
		int row = getRow();
		int col = getCol();
		if(row + 1 < Grid.getInstance().getM() && MazeArray.getInstance().getSpace(row + 1, col) != Space.WALL && MazeArray.getInstance().getSpace(row + 1, col) != Space.DOOR) {
			setPos(row + 1, col);
			return true;
		}
		if(row + 1 < Grid.getInstance().getM() && MazeArray.getInstance().getSpace(row + 1, col) != Space.WALL && MazeArray.getInstance().getPlayer().hasKey()) {
			setPos(row + 1, col);
			return true;
		}
		return false;
	}
	public boolean moveLeft() {
		int row = getRow();
		int col = getCol();
		if(col - 1 >= 0 && MazeArray.getInstance().getSpace(row, col - 1) != Space.WALL && MazeArray.getInstance().getSpace(row, col - 1) != Space.DOOR) {
			setPos(row, col - 1);
			return true;
		}
		if(col - 1 >= 0 && MazeArray.getInstance().getSpace(row, col - 1) != Space.WALL && MazeArray.getInstance().getPlayer().hasKey()) {
			setPos(row, col - 1);
			return true;
		}
		return false;
	}
	public boolean moveRight() {
		int row = getRow();
		int col = getCol();
		if(col + 1 < Grid.getInstance().getN() && MazeArray.getInstance().getSpace(row, col + 1) != Space.WALL && MazeArray.getInstance().getSpace(row, col + 1) != Space.DOOR) {
			setPos(row, col + 1);
			return true;
		}
		if(col + 1 < Grid.getInstance().getN() && MazeArray.getInstance().getSpace(row, col + 1) != Space.WALL && MazeArray.getInstance().getPlayer().hasKey()) {
			System.out.println(Grid.getInstance().getM());
			setPos(row, col + 1);
			return true;
		}
		return false;
	}
	
	/**
	 * More getters and setters
	 */
	
	public boolean isAlive() { return _isAlive; }
	public void kill() { _isAlive = false; }
	public boolean hasSword() {return _sword;}
	public void giveSword() {_sword = true;}
	public void takeSword() {_sword = false;}
	public void addStep() {_steps++;}
	public int getSteps() {return _steps;}
	public boolean hasBoots() {return _boots;}
	public void giveBoots() {_boots = true;}
	public boolean hasRune() {return _rune;}
	public void giveRune() {_rune = true;}
	public void takeRune() {_rune = false;}
	public boolean hasKey() {return _key; }
	public void giveKey() {_key = true; }
}
