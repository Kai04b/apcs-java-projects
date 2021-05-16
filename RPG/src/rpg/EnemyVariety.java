package rpg;

import java.util.EnumSet;

/**
 * Enumerators of possible enemy types, each containing three different weapons
 * 
 * @author kai
 *
 */
public enum EnemyVariety {
	
	/**
	 * All enemy varieties
	 */
	GENERICNPC(0, "Tutorial Guy", Move.ROCK, Move.SCISSORS, Move.PAPER, "images/genericguy.gif"),
	CAVEMAN(1, "Caveman", Move.CLUB, Move.SPEAR, Move.FUR, "images/caveman2.gif"),
	GLADIATOR(2, "Gladiator", Move.SHIELD, Move.GLADIUS, Move.NET, "images/gladiator3.gif"),
	SAMURAI(3, "Samurai", Move.NUNCHUCK, Move.KATANA, Move.SMOKEBOMB, "images/samurai2.gif"),
	PIRATE(4, "Pirate", Move.CANNON, Move.CUTLASS, Move.RIGGING, "images/pirate2.gif"),
	COWBOY(5, "Cowboy", Move.BOOT, Move.PISTOL, Move.LASSO, "images/cowboy.gif"),
	SOLDIER(6, "Solider", Move.SHOVEL, Move.RIFLE, Move.FLASH, "images/soldier.gif"),
	ROBOT(7, "Robot", Move.CRUSHER, Move.DRILL, Move.ELECTRODE, "images/robot2.gif"),
	ALIEN(8, "Alien", Move.FORCEGUN, Move.RAYGUN, Move.BEAM, "images/alien.gif"),
	WARPED(9, "Void", Move.WARPEDA, Move.WARPEDB, Move.WARPEDC, "images/static.gif"),
	
	;
	
	
	private EnemyVariety(int num, String name, Move move1, Move move2, Move move3, String imgStr) {
		_num = num;
		_name = name;
		_move1 = move1;
		_move2 = move2;
		_move3 = move3;
		_imgStr = imgStr;
	}
	
	/**
	 * Set of enemy varieties for looping purposes, getter method to use in other classes
	 */
	private static EnumSet<EnemyVariety> _allEnemyVarieties = EnumSet.allOf(EnemyVariety.class);
	public static EnumSet<EnemyVariety > getAllEnemyVarieties() {return _allEnemyVarieties;}
	
	/**
	 * Getters and instance variables
	 */
	
	//Index for accessing by index
	private int _num;
	public int getNum() {return _num;}
	
	//Name to be displayed in-game
	private String _name;
	public String getName() {return _name;}
	
	//Crushing
	private Move _move1;
	public Move getMove1() {return _move1;}
	
	//Sharp
	private Move _move2;
	public Move getMove2() {return _move2;}
	
	//Encumbering
	private Move _move3;
	public Move getMove3() {return _move3;}
	
	//String filepath to sprite image for displaying
	private String _imgStr;
	public String getImgStr() {return _imgStr;}
	
	/**
	 * Convert enemy variety for string, only for testing, never used in game
	 */
	public String toString() {
		return _name + " has\n"
				+ _move1 + "\n"
				+ _move2 + "\n"
				+ _move3 + "\n";
	}
	
	/**
	 * Get enemy variety by index
	 * 
	 * @param num Index to access
	 * @return enemyVariety at index, null if no variety at index
	 */
	public static EnemyVariety getEnemyVariety(int num) {
		for(EnemyVariety enemyVariety: _allEnemyVarieties) {
			if(num == enemyVariety.getNum()) {
				return enemyVariety;
			}
		}
		return null;
	}
	
	/**
	 * Get a random EnemyVariety other than variety at given index
	 * Used after first 10 rounds to generate random enemyVariety
	 * But don't want two of same enemyVariety in a row
	 * 
	 * @param num Index of EnemyVariety not allowed to returns
	 * @return random EnemyVariety other than the one at num index
	 */
	public static EnemyVariety getRandomEnemyVariety(int num) {
		int index = num;
		while(index == num) {
			index = (int)(Math.random() * _allEnemyVarieties.size());
		}
		return getEnemyVariety(index);
	}
	
	/**
	 * Get completely random EnemyVariety
	 * 
	 * @return random EnemyVariety
	 */
	public static EnemyVariety getRandomEnemyVariety() {
		return getEnemyVariety((int)(Math.random() * _allEnemyVarieties.size()));
	}
	
	
}
