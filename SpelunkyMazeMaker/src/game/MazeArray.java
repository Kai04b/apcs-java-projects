package game;

import java.io.IOException;


/**
 * Handles grid of tiles, player position, and enemy positions
 * 
 * @author kai
 *
 */
public class MazeArray {
	
	private static MazeArray instance;
	public static MazeArray getInstance() {
        return instance;
	}
	public void updateInstance() {
		instance = this;
	}
	
	private Space[][] _spaces;
	
	
	private Player _p;
	public Player getPlayer() {return _p;}
	public void setPlayerPos(int m, int n) {
		System.out.println("Player pos: " + m + " " + n);
		_p.setPos(m, n);
	}
	public boolean isPlayerPosition(int row, int col) {
		return _p.getRow() == row && _p.getCol() == col;
	}
	
	private MinotaurList _minoList;
	public MinotaurList getMinotaurList() {return _minoList;}
	
	
	
	
	
	public int getWidth() {return _spaces.length;}
	public int getHeight() {return _spaces[0].length;}
	
	
			
	public MazeArray(int rows, int cols) throws IOException {
		System.out.println("constructed MazeArray " + rows + " " + cols);
		_minoList = new MinotaurList();
		_p = new Player();
		_spaces = new Space[rows][cols];
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				_spaces[r][c] = Space.AIR;
			}
		}
	}
	
	public MazeArray(MazeArray m) {
		instance = this;
		_p = new Player(m.getPlayer());
		_minoList = new MinotaurList(m.getMinotaurList());
		_spaces = new Space[m.getWidth()][m.getHeight()];
		for(int r = 0; r < m.getWidth(); r++) {
			for(int c = 0; c < m.getHeight(); c++) {
				_spaces[r][c] = m.getSpace(r, c);
			}
		}
	}
	
	/**
	 * Set a given index in the array to a specific Space enum
	 * 
	 * @param e		Space enum to change the index
	 * @param row 	Row of space to change
	 * @param col 	Column of space to change
	 */
	public void setSpace(Space e, int row, int col) {
		System.out.println("Set space " + row + ", " + col + " to " + e.getName());
		_spaces[row][col] = e;
	}
	
	/**
	 * Get Space enum at given index
	 * 
	 * @param row 	Row to access
	 * @param col 	Column to access
	 * @return		Space enum of element at row, column
	 */
	public Space getSpace(int row, int col) {return _spaces[row][col];}
	
	
	public void printSpaces() {
		for(int r = 0; r < _spaces.length; r++) {
			for(int c = 0; c < _spaces[0].length; c++) {
				System.out.print(((Space) _spaces[r][c]).getName() + "\t");
			}
			System.out.println();
		}
	}
	
	
	/**
	 * Play a full round given a player input of direction,
	 * move player, then minotaur
	 * 
	 * @param d Direction enum of player movement
	 */
	public void playRound(Direction d) {
			if(movePlayer(d)) {
				checkCollisions();
				if(!Grid.getInstance().getDead()) {
					_minoList.moveMinotaurs();
					checkMinotaurs();
				}
			}
	}
	
	/**
	 * Check all possible collisions between player and other objects
	 */
	private void checkCollisions() {
		checkExit();
		checkSword();
		checkTorch();
		checkKey();
		checkDoor();
		checkMinotaurs();
	}
	
	/**
	 * Check if any minotaurs are on the same space as the player
	 * If so, kill player if no sword, kill minotaur if sword, and 50% chance of sword break on killing minotaur
	 */
	private void checkMinotaurs() {
		if(_minoList.checkMinotaurList()) {
			if(_p.hasSword()) {
				killMinotaur();
				if(Math.random() > .5) {
					breakSword();
				}
			}
			else {
				playMinotaurDeath();
			}
		}
	}
	
	/**
	 * On sword break, update player's state and notify the user
	 */
	private void breakSword() {
		_p.takeSword();
		Grid.getInstance().getController().updateGameLabel("The rock cracks in two, you're left empty-handed");
	}
	
	/**
	 * If player on the same space as the exit, player wins, notify user and deny any more input (dead)
	 */
	private void checkExit() {
		if(getSpace(_p.getRow(), _p.getCol()) == Space.EXIT) {
			Grid.getInstance().getController().updateGameLabel("You clamber through the opening in the wall, free from the grim caverns");
			Grid.getInstance().setDead(true);
		}
	}
	
	/**
	 * If player is on same space as a sword, give them a sword, unless they already have one
	 */
	private void checkSword() {
		if(getSpace(_p.getRow(), _p.getCol()) == Space.WEAPON && !_p.hasSword()) {
			_p.giveSword();
			Grid.getInstance().getController().updateGameLabel("You pick up the " + Space.WEAPON.getName().toLowerCase() + ", it feels nice in the hands");
			setSpace(Space.AIR, _p.getRow(), _p.getCol());
		}
		else if(getSpace(_p.getRow(), _p.getCol()) == Space.WEAPON && _p.hasSword()) {
			Grid.getInstance().getController().updateGameLabel(Space.WEAPON.getName() + "s are too heavy to carry two of at once");
		}
	}
	
	/**
	 * If player is on the same space as a key, give them a key, unless they already have one
	 */
	private void checkKey() {
		if(getSpace(_p.getRow(), _p.getCol()) == Space.KEY && !_p.hasKey()) {
			_p.giveKey();
			Grid.getInstance().getController().updateGameLabel("You pick up the ornate looking " + Space.KEY.getName().toLowerCase());
			setSpace(Space.AIR, _p.getRow(), _p.getCol());
		}
		else if(getSpace(_p.getRow(), _p.getCol()) == Space.KEY && _p.hasKey()) {
			Grid.getInstance().getController().updateGameLabel("You already have a " + Space.KEY.getName().toLowerCase());
		}
	}
	/**
	 * If player is on the same space as a torch, give them one, unless they already have
	 */
	private void checkTorch() {
		if(getSpace(_p.getRow(), _p.getCol()) == Space.TORCH && !_p.hasBoots()) {
			_p.giveBoots();
			Grid.getInstance().getController().updateGameLabel("You pick up the bright, fiery " + Space.TORCH.getName().toLowerCase());
			setSpace(Space.AIR, _p.getRow(), _p.getCol());
		}
		else if(getSpace(_p.getRow(), _p.getCol()) == Space.TORCH && _p.hasBoots()) {
			Grid.getInstance().getController().updateGameLabel("You already have a " + Space.TORCH.getName().toLowerCase());
		}
	}
	
	/**
	 * If player attempts to move to same space as door, open door and notify user
	 */
	private void checkDoor() {
		if(getSpace(_p.getRow(), _p.getCol()) == Space.DOOR) {
			setSpace(Space.AIR, _p.getRow(), _p.getCol());
			Grid.getInstance().getController().updateGameLabel("The heavy door creaks open");
		}
	}
	
	/**
	 * Kill the minotaur on the same space as the player and notify user
	 */
	private void killMinotaur() {
		_minoList.removeAtPosition(_p.getRow(), _p.getCol());
		Grid.getInstance().getController().updateGameLabel("You bash in the " + Space.ENEMY.getName().toLowerCase() + "'s skull with a rock, it lies dead on the floor");
	}
	
	/**
	 * Notify user on minotaur killing player
	 */
	private void playMinotaurDeath() {
		Grid.getInstance().setDead(true);
		Grid.getInstance().getController().updateGameLabel("A " + Space.ENEMY.getName().toLowerCase() + " strikes at your leg, fatally wounding you");
	}
	
	/**
	 * Move player character based on user input
	 * @param d Direction enum to move
	 * @return true if move successful, false otherwise
	 */
	private boolean movePlayer(Direction d) {
		updateInstance();
		if(d == Direction.UP) {
			return _p.moveUp();
		}
		else if(d == Direction.DOWN) {
			return _p.moveDown();
		}
		else if(d == Direction.LEFT) {
			return _p.moveLeft();
		}
		else if(d == Direction.RIGHT) {
			return _p.moveRight();
		}
		return false;
	}
	
	
	
}
