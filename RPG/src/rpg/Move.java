package rpg;

import java.util.EnumSet;


/**
 * Enumerator of all weapons
 * 
 * @author kai
 *
 */
public enum Move {
	
	/*ideas for verbs
	 * A : 
	 * B :
	 * C : ensnares, 
	 */
	
	
	//guy
	ROCK(0, Type.A, "Rock", "crushes"),
	SCISSORS(1, Type.B, "Scissors", "cuts"), 
	PAPER(2, Type.C, "Paper", "covers"),
	
	//caveman
	CLUB(3, Type.A, "Club", "bashes"),
	SPEAR(4, Type.B, "Stone Spear", "stabs"),
	FUR(5, Type.C, "Fur", "traps"),
	
	//gladiator (roman)
	SHIELD(6, Type.A, "Shield", "bludgeons"),
	GLADIUS(7, Type.B, "Gladius", "slices"),
	NET(8, Type.C, "Net", "entangles"),
	
	//Pirate
	CANNON(9, Type.A, "Cannon", "blasts"),
	CUTLASS(10, Type.B, "Cutlass", "slashes"),
	RIGGING(11, Type.C, "Rigging", "ensnares"),
	
	//Ninja
	NUNCHUCK(12, Type.A, "Nunchaku", "beats"),
	KATANA(13, Type.B, "Katana", "slices"),
	SMOKEBOMB(14, Type.C, "Smoke Bomb", "blinds"),
	
	//Cowboy
	BOOT(15, Type.A, "Boot", "kicks"),
	PISTOL(16, Type.B, "Six-Shooter", "shoots"),
	LASSO(17, Type.C, "Lasso", "catches"),
	
	//Solider
	SHOVEL(18, Type.A, "Trench Shovel", "flattens"),
	RIFLE(19, Type.B, "Rifle", "shoots"),
	FLASH(20, Type.C, "Flash Grenade", "stuns"),
	
	//Robot
	CRUSHER(21, Type.A, "Crusher", "compresses"),
	DRILL(22, Type.B, "Drill", "impales"),
	ELECTRODE(23, Type.C, "Electrode", "shocks"),
	
	//Alien
	FORCEGUN(24, Type.A, "Force Gun", "crumples"),
	RAYGUN(25, Type.B, "Ray Gun", "singes"),
	BEAM(26, Type.C, "Beam", "dazes"),
	
	//Warped
	WARPEDA(27, Type.A, "œ∑¨π˜µ", "©œµ≈†∞£™"),
	WARPEDB(28, Type.B, "“˚˜ƒøˆœ", "µ≤œπç"),
	WARPEDC(29, Type.C, "¡ª˜√µΩ«", "¢√≥ˆ˚ƒ˜ç"),
	
	;
	
	private Move(int index, Type type, String name, String winMsg) {
		_index = index;
		_type = type;
		_name = name;
		_winMsg = winMsg;
	}
	
	//Set of all moves for looping purposes
	private static EnumSet<Move> _allMoves = EnumSet.allOf(Move.class);
	
	//Index for accessing
	private int _index;
	private int getIndex() {return _index;}
	
	//Type enum of move, either crushing, sharp, or encumbering
	private Type _type;
	public Type getType() {return _type;}
	
	//Name of the move to display
	private String _name;
	public String getName() {return _name;}
	
	//Verb of move when it wins, example: rock "crushes"
	private String _winMsg;
	public String getWinMsg() {return _winMsg;}
	
	//Convert move to string, name, type, then verb
	public String toString() {
		return getName() + " (" + getType() + ") which " + getWinMsg();
	}
	
	/**
	 * Get move based on index
	 * 
	 * @param index to access
	 * @return	Move at index, null if none
	 */
	public static Move getMove(int index) {
		for(Move move: _allMoves) {
			if(index == move.getIndex()) {
				return move;
			}
		}
		return null;
	}
	
	
	
	
}
