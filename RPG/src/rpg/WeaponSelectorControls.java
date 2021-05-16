package rpg;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Handles user input on the weapon selector pane
 * 
 * @author kai
 *
 */
public class WeaponSelectorControls {
	
	/**
	 * JavaFX constants for button visual styles
	 */
	
	private static final String GRAY_BACKGROUND = "-fx-background-color: Gray";
	private static final String WHITE_BACKGROUND = "-fx-background-color: White";
	private static final String BLACK_BACKGROUND = "-fx-background-color: Black";
	
	private static final String BLACK_BORDER = "-fx-border-color: Black";
	private static final String WHITE_BORDER = "-fx-border-color: White";
	
	private static final String ROYALBLUE_BORDER = "-fx-border-color: ROYALBLUE";
	private static final String RED_BORDER = "-fx-border-color: CRIMSON";
	private static final String YELLOW_BORDER = "-fx-border-color: GOLD";
	
	private static final String GENERIC_BORDER = "-fx-border-color: ";
	
	private static final String BORDER_WIDTH_5 = "-fx-border-width: 5";
	private static final String BORDER_WIDTH_2 = "-fx-border-width: 2";
	
	/**
	 * Instance variables, moves set to default rock, paper, scissors and PlayerVariety set to blue by default
	 */
	
	private Move move1 = Move.ROCK;
	private Move move2 = Move.SCISSORS;
	private Move move3 = Move.PAPER;
	private PlayerVariety pv = PlayerVariety.BLUE;
	
	@FXML
	private Slider _weaponSelectorCrushing, _weaponSelectorSharp, _weaponSelectorEncumbering;
	
	/**
	 * Change visual style of buttons when pressed and released to provide visual feedback for user
	 */
	
	@FXML
	private void crushMoved() {
		int value = 8 - (int)_weaponSelectorCrushing.getValue();
		move1 = Move.getMove(value * 3);
		
	}
	
	@FXML
	private void sharpMoved() {
		int value = 8 - (int)_weaponSelectorSharp.getValue();
		move2 = Move.getMove(value * 3 + 1);
	}
	
	@FXML
	private void encumberingMoved() {
		int value = 8 - (int)_weaponSelectorEncumbering.getValue();
		move3 = Move.getMove(value * 3 + 2);
	}
	
	@FXML
	private Button _quitB, _playB;
	
	@FXML
	private void quitPressed() {
		_quitB.setStyle(GRAY_BACKGROUND + "; " + WHITE_BORDER);
	}
	@FXML
	private void quitReleased() {
		_quitB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
		System.exit(0);
	}
	@FXML
	private void quitEntered() {
		_quitB.setStyle(BLACK_BACKGROUND + "; " + GENERIC_BORDER + pv.getColorStr());
	}
	@FXML
	private void quitExited() {
		_quitB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
	}
	
	@FXML
	private void playPressed() {
		_playB.setStyle(GRAY_BACKGROUND + "; " + WHITE_BORDER);
	}
	@FXML
	private void playReleased() {
		_playB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
		WeaponSelector.getInstance().getStage().close();
		new Game(new Player("Player", 0, move1, move2, move3, pv));
	}
	@FXML
	private void playEntered() {
		_playB.setStyle(BLACK_BACKGROUND + "; " + GENERIC_BORDER + pv.getColorStr());
	}
	@FXML
	private void playExited() {
		_playB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
	}
	
	@FXML
	private CheckBox _cb1, _cb2, _cb3;
	
	/**
	 * If user attempts to click checkboxes, do not allow more than or less than 1 checkboxes checked, like a radio button
	 */
	
	@FXML
	private void cb1Checked() {
		pv = PlayerVariety.BLUE;
		_center.setStyle(WHITE_BACKGROUND + ";" + BORDER_WIDTH_5 + ";" + GENERIC_BORDER + " " + pv.getColorStr());
		if(!_cb1.isSelected()) {
			_cb1.setSelected(true);
			
		}
		else {
			_cb1.setStyle(GENERIC_BORDER + " ROYALBLUE; " + BORDER_WIDTH_2);
			_cb2.setSelected(false);
			_cb2.setStyle("");
			_cb3.setSelected(false);
			_cb3.setStyle("");
		}
	}
	
	@FXML
	private void cb2Checked() {
		pv = PlayerVariety.RED;
		_center.setStyle(WHITE_BACKGROUND + ";" + BORDER_WIDTH_5 + ";" + GENERIC_BORDER + " " + pv.getColorStr());
		if(!_cb2.isSelected()) {
			_cb2.setSelected(true);
		}
		else {
			_cb2.setStyle(GENERIC_BORDER + " CRIMSON; " + BORDER_WIDTH_2);
			_cb1.setSelected(false);
			_cb1.setStyle("");
			_cb3.setSelected(false);
			_cb3.setStyle("");
		}
	}
	
	@FXML
	private void cb3Checked() {
		pv = PlayerVariety.YELLOW;
		_center.setStyle(WHITE_BACKGROUND + ";" + BORDER_WIDTH_5 + ";" + GENERIC_BORDER + " " + pv.getColorStr());
		if(!_cb3.isSelected()) {
			_cb3.setSelected(true);
		}
		else {
			_cb3.setStyle(GENERIC_BORDER + " GOLD; " + BORDER_WIDTH_2);
			_cb1.setSelected(false);
			_cb1.setStyle("");
			_cb2.setSelected(false);
			_cb2.setStyle("");
		}
	}
	
	@FXML
	private StackPane _center;
	
	/**
	 * Sprite imagviews for displaying player variety sprites
	 */
	
	@FXML
	private ImageView _sprite1, _sprite2, _sprite3;
	
	public ImageView getSprite1() {return _sprite1;}
	public ImageView getSprite2() {return _sprite2;}
	public ImageView getSprite3() {return _sprite3;}
}

