package rpg;

/**
 * Enemy to player, with health, ability to deal damage and randomly perform special attacks when enough charge
 * 
 * @author kai
 *
 */
public class Enemy {

	/**
	 * Getters, setters, and instance variables
	 */
	
	private EnemyVariety _enemyVariety;
	public EnemyVariety getEnemyVariety() {return _enemyVariety;}
	
	private double _maxHealth;
	public double getMaxHealth() {return _maxHealth;}
	
	private double _health;	
	public double getHealth() {return _health;}
	public void setHealth(double h) { _health = h;}
	
	/**
	 * Decrease health of enemy, don't allow health to go below zero
	 * @param d attempted health to be decreased by
	 */
	public void decreaseHealth(double d) {
		double newHealth = _health - d;
		if(newHealth < 0.0) {
			newHealth = 0.0;
		}
		_health = newHealth;
	}
	/**
	 * Increase health of enemy, don't allow health to go above max health
	 * @param d attempted health to be increased by
	 */
	public void increaseHealth(double d) {
		double newHealth = _health + d;
		if(newHealth > _maxHealth) {
			newHealth = _maxHealth;
		}
		_health = newHealth;
	}
	
	private double _charge;
	public double getCharge() {return _charge;}
	public void setCharge(double h) { _charge = h;}
	
	/**
	 * Decrease charge of enemy, don't allow charge to go below zero
	 * @param d attempted change in charge
	 */
	public void decreaseCharge(double d) {
		double newCharge = _charge - d;
		if(newCharge < 0.0) {
			newCharge = 0.0;
		}
		_charge = newCharge;
	}
	/**
	 * Increase charge of enemy, don't allow charge to go above 100
	 * @param d attempted change in charge
	 */
	public void increaseCharge(double d) {
		double newCharge = _charge + d;
		if(newCharge > 100.0) {
			newCharge = 100.0;
		}
		_charge = newCharge;
	}

	private Move _currentMove;
	public Move getCurrentMove() {return _currentMove;}
	public void setCurrentMove(Move move) {_currentMove = move;}
	
	public Enemy(double health, int round) {
		_health = health;
		_maxHealth = health;
		initVariety(round);
		setRandomMove();
	}
	
	/**
	 * Set enemy variety based on current round
	 * More difficult varieties in later rounds
	 * Example:
	 * Round 1 -> caveman
	 * 
	 * @param round Current round
	 */
	public void initVariety(int round) {
		if(round - 1 < EnemyVariety.getAllEnemyVarieties().size() - 1) {
			_enemyVariety = EnemyVariety.getEnemyVariety(round - 1);
		}
		else {
			_enemyVariety = EnemyVariety.WARPED;
		}
	}
	
	/**
	 * Set enemy move to random of three, encumbering, sharp, crushing
	 */
	public void setRandomMove() {
		double number = Math.random();
		
		if(number < (1.0/3)) {
			setCurrentMove(getEnemyVariety().getMove1());
		}
		else if(number < (2.0/3)) {
			setCurrentMove(getEnemyVariety().getMove2());
		}
		else {
			setCurrentMove(getEnemyVariety().getMove3());
		}
	}
	
	/**
	 * Set enemy move to random of three, encumbering, sharp, or crushing
	 */
	public String getFullWinMessage() {
		return "The " + getEnemyVariety().getName().toLowerCase() + 
		"'s " + getCurrentMove().getName().toLowerCase() + " " + getCurrentMove().getWinMsg().toLowerCase() + " you";
	}

}
