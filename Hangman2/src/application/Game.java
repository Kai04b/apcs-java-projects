package application;

import java.util.ArrayList;

import javafx.stage.Stage;

/**
 * Controls flow of game
 * Asks for word and then guess letters, slowly revealing word
 * 
 * @author kai
 *
 */
public class Game {
	
	private String _word;
	private String _let;
	private boolean _playing;
	private HiddenWord _hw;
	private int _roundCnt;
	private int _missCnt;
	private ArrayList<String> _guessed;
	private boolean _quitting;
	private int _loseNum;
	
	public HiddenWord getHW() {return _hw;}
	public int getMissCnt() {return _missCnt;}
	public void setMissCnt(int u) {_missCnt = u;}
	
	public String getWord() {return _word;}
	
	public Game() {
		_word = "";
		_let = "";
		_playing = true;
		_hw = new HiddenWord(_word);
		_roundCnt = 1;
		_guessed = new ArrayList<String>();
		_quitting = false;
		_loseNum = 6;
	}
	
	/**
	 * Reset game so that another word can be played
	 */
	public void reset() {
		_word = "";
		_let = "";
		_playing = true;
		_hw = new HiddenWord();
		_roundCnt = 1;
		_guessed = new ArrayList<String>();
		_quitting = false;
		_loseNum = 6;
		_missCnt = 0;
	}
	
	/**
	 * Ask player 1 for the secret word
	 * 
	 * @param possWord 	Player 1's word
	 * @return 			true if word is valid (no numbers or special characters), false otherwise
	 */
	public boolean askForWord(String possWord) {
		if(possWord == null || possWord.equals("")) {
			return false;
		}
		else if(possWord.matches(".*[1234567890!@#$%^&*(){}\"\\';:,.<>/?].*")) {
			return false;
		}
		else if(possWord.length() <= 2) {
			return false;
		}
		else {
			_word = possWord;
			_hw = new HiddenWord(_word);
			return true;
		}
	}
	
	/**
	 * Test if text can be converted to int
	 * 
	 * @param str String to be converted to integer
	 * @return true if string can be converted to integer, false otherwise
	 */
	public static boolean isInteger(String str) {
	    try {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}
	
	/**
	 * Ask player two for a single letter to guess
	 * 
	 * @param possLet	The letter guessed
	 * @return			True if letter is valid (single character and no special characters or numbers), false otherwise
	 */
	public boolean askForLetter(String possLet) {
		if(possLet == null) {
			return false;
		}
		else if(possLet.equalsIgnoreCase(_word)) {
			_playing = false;
			_hw.setString(_word);
			return true;
		}
		else if(possLet.matches(".*[1234567890!@#$%^&*(){}\"\\';:,.<>/? ].*")) {
			return false;
		}
		else if(possLet.length() != 1) {
			return false;
		}
		else if(checkGuessed(possLet)) {
			return false;
		}
		else {
			_guessed.add(possLet.toLowerCase());
			_let = possLet;
			System.out.println(possLet);
			return true;
		}
	}
	
	/**
	 * Check if letter has already been guessed
	 * 
	 * @param let Letter to be checked
	 * @return	  true if letter has already been guessed, false otherwise
	 */
	public boolean checkGuessed(String let) {
		for(String s: _guessed) {
			if(let.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Reveal hidden word
	 * 
	 * @return true if letter was in word, false otherwise
	 */
	public boolean modifyHiddenWord() {
		System.out.println("original word:\t" + _word);
		System.out.println("letter:\t\t" + _let);
		System.out.println("hidden word:\t" + _hw);
		return _hw.changeHiddenWord(_word, _let);
	}
	
	/**
	 * Return a string of previously guessed letters
	 * 
	 * @return a string of single letters separated by spaces like "a c m n"
	 */
	public String guessedLetters() {
		String line = "";
		for(String s: _guessed) {
			if(!_word.contains(s)) {
				line += s;
				line += " ";
			}
		}
		return line;
	}
}
