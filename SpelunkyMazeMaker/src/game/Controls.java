package game;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Handles user input of JavaFX objects
 * 
 * @author kai
 *
 */
public class Controls {
	
	/**
	 * Getters, setters, and instance variables
	 */
	
	private static Controls instance;
	public static Controls getInstance() {return instance;}
	
	private Stage _infoStage;
	public Stage getStage() {return _infoStage;}
	
	private int _roundedSlider;
	
	private boolean _pressed;
	public boolean getPressed() {return _pressed;}
	
	public int getRSlider() {
		return _roundedSlider;
	}
	
	public Controls() throws IOException {
		_pressed = false;
		
		instance = this;
		
		_infoStage = new Stage();
		_roundedSlider = 1;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Info.fxml"));
    	BorderPane root = loader.load();
    	Scene scene = new Scene(root, 700, 700);
    	_infoStage.setTitle("Info");
    	_infoStage.setResizable(false);
    	_infoStage.setScene(scene);
    	
    	
    	
	}
	
	@FXML
	StackPane _bottom, _top, _right, _center;
	
	
	
	@FXML
	VBox _left, _sliderBox;
	
	@FXML
	BorderPane _mainPane;
	
	public BorderPane getMainPane() {
		return _mainPane;
	}
	
	public void setPane(double width, double height) {
		_mainPane.setPrefHeight(height + 150);
		_mainPane.setPrefWidth(width + 250);
	}

	@FXML
	private Slider _slider;
	
	@FXML
	private Button _quit, _resetAllButton, _playButton, _resetEditButton, _resetPlayButton;
	
	@FXML
	private Label _gameLabel1, _gameLabel2, _gameLabel3, _lab1, _lab2, _lab3, _lab4, _lab5, _lab6, _lab7, _lab8, _lab9;
	
	@FXML
	private CheckBox _darkBox;
	
	/**
	 * When the slider is moved, update the roundedSlider instance variable for tile selection
	 */
	@FXML
	private void sliderMoved() {
		int value = (int) Math.round(_slider.getValue());
		_roundedSlider = value;
	}
	
	/**
	 * Exit on quit pressed
	 */
	@FXML
	private void quitClicked() {
		System.exit(0);
	}
	
	/**
	 * Reset all when reset all button pressed
	 * @throws IOException
	 */
	@FXML 
	protected void resetAllClicked() throws IOException{
        Grid.getInstance().resetAll();
    }
	
	/**
	 * On reset and play pressed, reset and play, unless in build mode
	 * @throws IOException
	 */
	@FXML 
	protected void resetPlayClicked() throws IOException{
        if(Grid.getInstance().getPlaying()) {
        	Grid.getInstance().reset();
        	playGameClicked();
        }
        else {
        	updateGameLabel("You cannot reset and play in build mode");
        }
    }
	
	/**
	 * On reset and edit pressed, reset and edit, unless in build mode
	 * @throws IOException
	 */
	@FXML 
	protected void resetEditClicked() throws IOException{
        if(Grid.getInstance().getPlaying()) {
        	Grid.getInstance().reset();
        }
        else {
        	updateGameLabel("You cannot reset and edit in build mode");
        }
    }
	
	/**
	 * On play game pressed, allow user to play game
	 * unless already playing or no player character on screen or game just finished
	 * @throws IOException
	 */
	@FXML 
	protected void playGameClicked() throws IOException{
		if(!Grid.getInstance().getPlaying() && !Grid.getInstance().getDead() &&
				Grid.getInstance().getSpaces().getPlayer().getCol() != -1 && 
				Grid.getInstance().getSpaces().getPlayer().getRow() != -1) {
	        // 
	       //Here I want to invoke gotoRegister
			Grid.getInstance().setPlaying(true);
			clearGameLabel();
			Grid.getInstance().setPlaying(true);
			Grid.getInstance().setDead(false);
			Grid.getInstance().saveMazeArray();
			Grid.getInstance().setDark(_darkBox.isSelected());
			_slider.setVisible(false);
			_lab1.setVisible(false);
			_lab2.setVisible(false);
			_lab3.setVisible(false);
			_lab4.setVisible(false);
			_lab5.setVisible(false);
			_lab6.setVisible(false);
			_lab7.setVisible(false);
			_lab8.setVisible(false);
			_lab9.setVisible(false);
			_darkBox.setVisible(false);
			_darkBox.setDisable(true);
			Grid.getInstance().drawGrid();
		}
		else if(Grid.getInstance().getDead()){
			updateGameLabel("You finished the game, click reset to play again");
		}
		else if(Grid.getInstance().getPlaying()){
			updateGameLabel("You are already playing");
		}
		
		else {
			updateGameLabel("You cannot play without a player character");
		}
    }
	
	/**
	 * When key board pressed, move player in direction of WASD key press, or if E pressed, enter multi-build mode
	 * @param e
	 */
	@FXML
	private void keyPressed(KeyEvent e) {
		if(Grid.getInstance().getPlaying() && !Grid.getInstance().getDead()) {
			KeyCode k = e.getCode();
			if(k == KeyCode.W || k == KeyCode.UP) {
				Grid.getInstance().playRound(Direction.UP);
			}
			else if(k == KeyCode.S || k == KeyCode.DOWN) {
				Grid.getInstance().playRound(Direction.DOWN);
			}
			else if(k == KeyCode.A || k == KeyCode.LEFT) {
				Grid.getInstance().playRound(Direction.LEFT);
			}
			else if(k == KeyCode.D || k == KeyCode.RIGHT) {
				Grid.getInstance().playRound(Direction.RIGHT);
			}
			
		}
		if(e.getCode() == KeyCode.E) {
			Grid.getInstance().setEpressed(true);
		}
	}
	
	/**
	 * On e released, exit multi-build mode
	 * @param e
	 */
	@FXML
	private void eReleased(KeyEvent e) {
		if(e.getCode() == KeyCode.E) {
			Grid.getInstance().setEpressed(false);
		}
	}
	
	/**
	 * Show info stage if ont already showing and info button pressed
	 * @throws IOException
	 */
	@FXML
	public void showInfo() throws IOException {
		if(_infoStage.isShowing()) {
			_infoStage.close();
		}
		else {
			_infoStage.show();
		}
	}
	
	/**
	 * On exit of playing, clear JavaFX elements and enter build mode
	 */
	public void stopPlaying() {
		Grid.getInstance().setDark(false);
		Grid.getInstance().drawGrid();
		Grid.getInstance().setDead(false);
		Grid.getInstance().setPlaying(false);
		clearGameLabel();
		
		_slider.setVisible(true);
		_lab1.setVisible(true);
		_lab2.setVisible(true);
		_lab3.setVisible(true);
		_lab4.setVisible(true);
		_lab5.setVisible(true);
		_lab6.setVisible(true);
		_lab7.setVisible(true);
		_lab8.setVisible(true);
		_lab9.setVisible(true);
		
		_darkBox.setVisible(true);
		_darkBox.setDisable(false);
	}
	
	/**
	 * Cascading text update to game text
	 * @param s New text to be displayed on the bottom label
	 */
	public void updateGameLabel(String s) {
		_gameLabel3.setText(_gameLabel2.getText());
		_gameLabel2.setText(_gameLabel1.getText());
		_gameLabel1.setText(s);
	}
	
	/**
	 * Completely eras all 3 game labels
	 */
	public void clearGameLabel() {
		_gameLabel3.setText("");
		_gameLabel2.setText("");
		_gameLabel1.setText("");
	}
	
	/**
	 * When window resized, respace VBox containing labels to fit new window size
	 * 
	 * @param space
	 */
	public void setSliderBoxSpacing(double space) {
		_sliderBox.setSpacing(space);
	}
	
	/**
	 * On window size changed, resize Grid
	 */
	@FXML
	public void resize() {
		Grid.getInstance().resize();
	}
	

}
