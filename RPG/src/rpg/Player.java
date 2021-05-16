package rpg;

public class Player {

	/**
	 * Instance variables, getters, and setters
	 */
	private String _name;
	public String getName() {return _name;}
	
	private double _maxHealth;
	public double getMaxHealth() {return _maxHealth;}
	public void setMaxHealth(double h) { _maxHealth = h;}
	
	private double _charge;
	public double getCharge() {return _charge;}
	public void setCharge(double h) { _charge = h;}
	/**
	 * Decrease charge of player, don't allow below zero
	 * @param d charge to attempt decrease by
	 */
	public void decreaseCharge(double d) {
		double newCharge = _charge - d;
		if(newCharge < 0.0) {
			newCharge = 0.0;
		}
		_charge = newCharge;
		System.out.println("Setting charge to: " + newCharge);
	}
	/**
	 * Increase charge of player, don't allow above 100
	 * @param d charge to attempt increase by
	 */
	public void increaseCharge(double d) {
		double newCharge = _charge + d;
		if(_pv == PlayerVariety.BLUE) {
			newCharge *= 3;
		}
		if(newCharge > 100.0) {
			newCharge = 100.0;
		}
		_charge = newCharge;
	}

	private double _health;
	public double getHealth() {return _health;}
	public void setHealth(double h) { _health = h;}
	/**
	 * Decrease health of player, don't allow below zero
	 * @param d amount of health to attempt decrease by
	 */
	public void decreaseHealth(double d) {
		double newHealth = _health - d;
		if(newHealth < 0.0) {
			newHealth = 0.0;
		}
		_health = newHealth;
	}
	/**
	 * Increase health of player, don't allow above maxHealth
	 * @param d amount of health to attempt increase by
	 */
	public void increaseHealth(double d) {
		double newHealth = _health + d;
		if(newHealth > _maxHealth) {
			newHealth = _maxHealth;
		}
		_health = newHealth;
	}
	
	//Golden, Red, Blue
	private PlayerVariety _pv;
	public PlayerVariety getPlayerVariety() {return _pv;}
	
	//Crushing
	private Move _move1;
	public Move getMove1() {return _move1;}
	
	//Sharp
	private Move _move2;
	public Move getMove2() {return _move2;}
	
	//Encumbering
	private Move _move3;
	public Move getMove3() {return _move3;}

	//Move to be used
	private Move _currentMove;
	public Move getCurrentMove() {return _currentMove;}
	public void setCurrentMove(Move move) {_currentMove = move;}
	
	//Select a move with 1, 2, or 3
	public void setCurrentMove(int moveNum) {
		switch(moveNum) {
			case 1:
				_currentMove = _move1;
				break;
			case 2:
				_currentMove = _move2;
				break;
			case 3:
				_currentMove = _move3;
				break;
		}	
	}
	
	public Player(String name, double health, Move move1, Move move2, Move move3, PlayerVariety pv) {
		_name = name;
		_charge = 0;
		_maxHealth = health;
		_health = health;
		_move1 = move1;
		_move2 = move2;
		_move3 = move3;
		_pv = pv;
		_currentMove = null;
	}
	
	/**
	 * 
	 * @return String representing full win message of player for example "Your rock crushes"
	 */
	public String getFullWinMessage() {
		return "Your " + getCurrentMove().getName().toLowerCase() + " " + getCurrentMove().getWinMsg().toLowerCase();
	}
	
	
}
