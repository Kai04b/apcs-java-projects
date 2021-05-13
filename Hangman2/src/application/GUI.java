package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * Handles user input and display of GUI info
 * 
 * @author kai
 *
 */
public class GUI {
	
	private int _roundCnt;
	private boolean _playing;
	private int _loseCnt;
	
	@FXML
	private TextField txtIn;
	
	@FXML
	private Label label;
	
	@FXML
	private Label misses;
	
	@FXML
	private Label rounds;
	
	@FXML
	private Button enterB;
	
	@FXML
	private Button quitB;
	
	@FXML
	private Button resetB;
	
	@FXML
	private Circle head;
	
	@FXML
	private Shape body;
	
	@FXML
	private Shape lArm;
	
	@FXML
	private Shape rArm;
	
	@FXML
	private Shape lLeg;
	
	@FXML
	private Shape rLeg;
	
	@FXML
	private Label guessedLetters;
	
	private int _stage;
	
	@FXML
	public void quitClicked() {
		System.exit(0);
	}
	
	@FXML
	public void resetClicked() {
		reset();
	}
	
	/**
	 * When enter pressed, continue program
	 * 
	 * @param e
	 */
	@FXML
	private void keyPressed(KeyEvent e) {
		_clickL.setText("");
		if(e.getCode().equals(KeyCode.ENTER)) {
			enterClicked(false);
		}
	}
	
	private int _clickCnt;
	
	@FXML
	private Label _clickL;
	
	@FXML
	private void buttonClick() {
		enterClicked(true);
	}
	
	/**
	 * If player clicks the graphical enter button 5 times, tell them they can use the enter button as well
	 */
	private void usingButton() {
		_clickCnt++;
		if(_clickCnt == 5) {
			_clickL.setText("You can also use the enter key on your keyboard");
		}
		else {
			_clickL.setText("");
		}
	}
	
	/**
	 * 
	 * 
	 * @param b true if player is using the on-screen enter button, false if using keyboard enter
	 */
	@FXML
	public void enterClicked(boolean b) {
		if(_playing) {
			if(_stage == 0) {
				
				if(game.askForWord(txtIn.getText())) {
					txtIn.clear();
					_stage++;
					label.setText("How many mistakes to lose the game? (Standard is 6)");
					txtIn.setPromptText("Type an integer");
					guessedLetters.setText("");
					if(b) {
						usingButton();
					}
				}
			}
			else if(_stage == 1) {
				if(Game.isInteger(txtIn.getText())) {
					_loseCnt = Integer.parseInt(txtIn.getText());
					_stage++;
					label.setText(game.getHW().toString());
					txtIn.setPromptText("Type a single letter");
					misses.setText("Misses: " + game.getMissCnt());
					rounds.setText("Rounds: " + _roundCnt);
					guessedLetters.setText("Guessed Letters: " + game.guessedLetters());
					txtIn.clear();
					if(b) {usingButton();}
				}
			}
			else if(_stage == 2){
				if(game.askForLetter(txtIn.getText())) {
					if(b) {usingButton();}
					label.setText(game.getHW().toString());
					txtIn.clear();
					if(!game.modifyHiddenWord() && _playing && label.getText().contains("*")) {
						game.setMissCnt(game.getMissCnt() + 1);
						misses.setText("Misses: " + game.getMissCnt());
						if(game.getMissCnt() >= _loseCnt * 1.0/6) {
							head.setVisible(true);
						}
						if(game.getMissCnt() >= _loseCnt * 2.0/6) {
							body.setVisible(true);
						}
						if(game.getMissCnt() >= _loseCnt * 3.0/6) {
							lArm.setVisible(true);
						}
						if(game.getMissCnt()>= _loseCnt * 4.0/6) {
							rArm.setVisible(true);
						}
						if(game.getMissCnt() >= _loseCnt * 5.0/6) {
							lLeg.setVisible(true);
						}
						if(game.getMissCnt() >= _loseCnt) {
							label.setText("You lose!\nThe phrase was " + game.getWord() + "\nClick reset to play again");
							rLeg.setVisible(true);
							_playing = false;
							System.out.println("lost");
						}
					}
					if(game.getMissCnt() != _loseCnt && _playing) {
						label.setText(game.getHW().toString());
					}
					if(!(label.getText().contains("*")) && _playing) {
						label.setText("You win!\nThe phrase was " + game.getWord() + "\nClick reset to play again");
						_playing = false;
					}
					
					guessedLetters.setText("Guessed Letters: " + game.guessedLetters());
					rounds.setText("Round: " + ++_roundCnt);
				}	
			}
		}
	}
	
	private Game game;
	
	public GUI() {
		game = new Game();
		_stage = 0;
		_roundCnt = 1;
		_playing = true;
		_clickCnt = 0;
	}
	
	/**
	 * Reset GUI elements when reset pressed
	 */
	private void reset() {
		game.reset();
		_stage = 0;
		_roundCnt = 1;
		_playing = true;
		_clickL.setText("");
		rounds.setText("Round: ");
		misses.setText("Misses: ");
		label.setText("Type a word or phrase in the box below to get started");
		txtIn.setPromptText("Type the secret word");
		head.setVisible(false);
		body.setVisible(false);
		lArm.setVisible(false);
		rArm.setVisible(false);
		lLeg.setVisible(false);
		rLeg.setVisible(false);
		guessedLetters.setText("The word must be at least 3 letters long, with no special characters");
		txtIn.clear();
	}
	
	/**
	 * Open and display stage
	 * 
	 * @param primaryStage JavaFX stage to be displayed
	 */
	public void run(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
