package game;

import java.util.ArrayList;

/**
 * List of all game enemies
 * 
 * @author kai
 *
 */
public class MinotaurList {
	
	
	/**
	 * Instance variables, getters, and setters
	 */
	
	private ArrayList<Minotaur> _mino;
	public ArrayList<Minotaur> getList() {return _mino;}
	
	public MinotaurList() {
		_mino = new ArrayList<Minotaur>();
	}
	
	/**
	 * Construct with copy
	 * @param listObj Other MinotaurList to copy
	 */
	public MinotaurList(MinotaurList listObj) {
		_mino = new ArrayList<Minotaur>();
		if(listObj.getList().size() > 0) {
			for(int i = 0; i < listObj.getList().size(); i++) {
				_mino.add(i, new Minotaur(listObj.getList().get(i)));
			}
		}
	}
	
	/**
	 * Add a enemy to the list
	 * @param row Row to construct enemy at
	 * @param col Column to construct enemy at
	 */
	public void addMinotaur(int row, int col) {
		_mino.add(new Minotaur(row, col));
	}
	/**
	 * Check if there is an enemy at given position
	 * @param row Row index to check
	 * @param col Column index to check
	 * @return true if enemy at position, false if no enemy at position
	 */
	public boolean isMinotaurPosition(int row, int col) {
		if(_mino.size() > 0) {
			for(Minotaur m : _mino) {
				if(m.getRow() == row && m.getCol() == col)
					return true;
			}
		}
		return false;
	}
	/**
	 * Remove enemy at given position
	 * 
	 * @param row Row to remove enemy at
	 * @param col Column to remove enemy at
	 */
	public void removeAtPosition(int row, int col) {
		for(int i = 0; i < _mino.size(); i++) {
			if(_mino.get(i).getRow() == row && _mino.get(i).getCol() == col) {
				_mino.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Move all enemies
	 */
	public void moveMinotaurs() {
		for(int i = 0; i < _mino.size(); i++) {
			_mino.get(i).autoMove();
		}
	}
	
	/**
	 * Check if any enemies on same space as player
	 * 
	 * @return true if any enemies on player
	 */
	public boolean checkMinotaurList() {
		for(int i = 0; i < _mino.size(); i++) {
			if(_mino.get(i).checkMinotaur()) {
				return true;
			}
		}
		return false;
	}
}
