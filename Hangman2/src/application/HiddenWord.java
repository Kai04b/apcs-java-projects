package application;

/**
 * Handles the hidden word to be guessed
 * 
 * @author kai
 *
 */
public class HiddenWord {

	private String _hword;
	
	public void setString(String s) {_hword = s;}
	
	/**
	 * Given a word, turns it into a hidden word where letters are asterisks * and spaces are spaces
	 * 
	 * @param w word to be converted
	 */
	public HiddenWord(String w) {
		_hword = "";
		for(int i = 0; i < w.length(); i++) {
			if(w.substring(i, i+1).equals(" ")) {
				_hword += " ";
			}
			else {
				_hword += "*";
			}
		}
	}
	
	public HiddenWord() {
		_hword = "";
	}
	
	/**
	 * 
	 * 
	 * @param w		word to be revealed
	 * @param let	letter guessed
	 * @return		return whether the guess was in the word or not
	 */
	public boolean changeHiddenWord(String w, String let) {
		String newWord = "";
		for(int i = 0; i < w.length(); i++) {
			if(w.substring(i, i+1).equalsIgnoreCase(let)) {
				newWord += let.toLowerCase();
			}
			else {
				newWord += _hword.substring(i, i+1);
			}
		}
		if(_hword.equals(newWord)) {
			_hword = newWord;
			return false;
		}
		else {
			_hword = newWord;
			return true;
		}
		
		
	}
	
	/**
	 * Convert hidden word to a string
	 */
	public String toString() {
		return _hword;
	}
}
