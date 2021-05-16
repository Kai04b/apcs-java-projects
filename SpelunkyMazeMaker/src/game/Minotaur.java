package game;

/**
 * Game enemy that chases player, can kill or be killed
 * 
 * @author kai
 *
 */
public class Minotaur {

	/**
	 * Instance variables, getters, and setters
	 */
	
	private int _row, _col;
	private boolean _isAlive;
	private int _steps;
	
	public int getRow() { return _row; }
	public int getCol() { return _col; }
	public void setPos(int r, int c) { _row = r; _col = c; }
	
	public Minotaur(int r, int c) {
		_row = r;
		_col = c;
		_isAlive = true;
		_steps = 0;
	}
	
	public Minotaur() {
		_row = -1;
		_col = -1;
		_isAlive = true;
		_steps = 0;
	}
	public Minotaur(Minotaur m2) {
		_row = m2.getRow();
		_col = m2.getCol();
		_isAlive = m2.isAlive();
		_steps = m2.getSteps();
	}
	
	
	public boolean isAlive() { return _isAlive; }
	public void kill() {_isAlive = false;};
	public void addStep() {_steps++;}
	public int getSteps() {return _steps;}
	
	/**
	 * If player is diagonal from enemies, 
	 * randomly prioritize moving veritcally toward player
	 * vs moving horizontally toward player
	 */
	public void autoMove() {
		if(Math.random() < .5) {
			prioritizeVertical();
		}
		else {
			prioritizeHorizontal();
		}
		
	}
	
	/**
	 * Move up or down, then left or right toward player
	 * don't allow moving into wall or border
	 */
	private void prioritizeVertical() {
		int pRow = MazeArray.getInstance().getPlayer().getRow();
		int pCol = MazeArray.getInstance().getPlayer().getCol();
		
		int mRow = getRow();
		int mCol = getCol();
		
		if(pRow < mRow && MazeArray.getInstance().getSpace(mRow - 1, mCol) != Space.WALL && MazeArray.getInstance().getSpace(mRow - 1, mCol) != Space.DOOR) {
			moveUp();
		}
		else if(pRow > mRow && MazeArray.getInstance().getSpace(mRow + 1, mCol) != Space.WALL && MazeArray.getInstance().getSpace(mRow + 1, mCol) != Space.DOOR) {
			moveDown();
		}
		else if(pCol < mCol && MazeArray.getInstance().getSpace(mRow, mCol - 1) != Space.WALL && MazeArray.getInstance().getSpace(mRow, mCol - 1) != Space.DOOR) {
			moveLeft();
		}
		else if(pCol > mCol && MazeArray.getInstance().getSpace(mRow, mCol + 1) != Space.WALL && MazeArray.getInstance().getSpace(mRow, mCol + 1) != Space.DOOR) {
			moveRight();
		}
	}
	
	/**
	 * Move left or right, then up or down toward player
	 * don't allow moving into wall or border
	 */
	private void prioritizeHorizontal() {
		int pRow = MazeArray.getInstance().getPlayer().getRow();
		int pCol = MazeArray.getInstance().getPlayer().getCol();
		
		int mRow = getRow();
		int mCol = getCol();
		
		
		if(pCol < mCol && MazeArray.getInstance().getSpace(mRow, mCol - 1) != Space.WALL && MazeArray.getInstance().getSpace(mRow, mCol - 1) != Space.DOOR) {
			moveLeft();
		}
		else if(pCol > mCol && MazeArray.getInstance().getSpace(mRow, mCol + 1) != Space.WALL && MazeArray.getInstance().getSpace(mRow, mCol + 1) != Space.DOOR) {
			moveRight();
		}
		else if(pRow < mRow && MazeArray.getInstance().getSpace(mRow - 1, mCol) != Space.WALL && MazeArray.getInstance().getSpace(mRow - 1, mCol) != Space.DOOR) {
			moveUp();
		}
		else if(pRow > mRow && MazeArray.getInstance().getSpace(mRow + 1, mCol) != Space.WALL && MazeArray.getInstance().getSpace(mRow + 1, mCol) != Space.DOOR) {
			moveDown();
		}
	}
	
	/**
	 * Check if enemy is on the same space as the player
	 * 
	 * @return true if enemy on player, false otherwise
	 */
	public boolean checkMinotaur() {
		int pRow = MazeArray.getInstance().getPlayer().getRow();
		int pCol = MazeArray.getInstance().getPlayer().getCol();
		
		int mRow = getRow();
		int mCol = getCol();
		if(pRow == mRow && pCol == mCol) {
			return true;
		}
		return false;
	}
	
	/**
	 * Move enemy in each direction, unless there is a wall
	 */
	
	public void moveUp() {
		int row = getRow();
		int col = getCol();
		if(row - 1 >= 0 && MazeArray.getInstance().getSpace(row - 1, col) != Space.WALL) {
			setPos(row - 1, col);
		}
	}
	public void moveDown() {
		int row = getRow();
		int col = getCol();
		if(row + 1 < Grid.getInstance().getM() && MazeArray.getInstance().getSpace(row + 1, col) != Space.WALL) {
			setPos(row + 1, col);
		}
	}
	public void moveLeft() {
		int row = getRow();
		int col = getCol();
		if(col - 1 >= 0 && MazeArray.getInstance().getSpace(row, col - 1) != Space.WALL) {
			setPos(row, col - 1);
		}
	}
	public void moveRight() {
		int row = getRow();
		int col = getCol();
		if(col + 1 < Grid.getInstance().getN() && MazeArray.getInstance().getSpace(row, col + 1) != Space.WALL) {
			setPos(row, col + 1);
		}
	}
}
