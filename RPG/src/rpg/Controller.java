package rpg;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Handles user input of buttons and other JavaFX elements
 * 
 * @author kai
 *
 */
public class Controller {
	
	private static final String GRAY_BACKGROUND = "-fx-background-color: Gray";
	private static final String WHITE_BACKGROUND = "-fx-background-color: White";
	private static final String BLACK_BACKGROUND = "-fx-background-color: Black";
	
	private static final String BLACK_BORDER = "-fx-border-color: Black";
	private static final String WHITE_BORDER = "-fx-border-color: White";
	
	private static final String GENERIC_BORDER = "-fx-border-color: ";
	
	
	
	//BUTTONS
	@FXML
	private Button _moveB1, _moveB2, _moveB3, 
	_enemyB1, _enemyB2, _enemyB3,
	_continueB, _specialB, _quitB, _resetB, _skipB, _buyB;

	/**
	 * Getters for JavaFX Buttons for other classes to manipulate visual style
	 */
	
	public Button getMoveB1() {return _moveB1;}
	public Button getMoveB2() {return _moveB2;}
	public Button getMoveB3() {return _moveB3;}
	public Button getEnemyB1() {return _enemyB1;}
	public Button getEnemyB2() {return _enemyB2;}
	public Button getEnemyB3() {return _enemyB3;}
	public Button getContinueB() {return _continueB;}
	public Button getSpecialB() {return _specialB;}
	public Button getSkipB() {return _skipB;}
	public Button getBuyB() {return _buyB;}
	
	
	/**
	 * When buttons pressed, released, hovered over, and hover exited change visual style to provide user feedback
	 */
	
	@FXML
	private void moveB1Pressed() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB1.setStyle(GRAY_BACKGROUND + "; " + BLACK_BORDER);
		}
	}
	@FXML
	private void moveB1Released() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB1.setStyle(WHITE_BACKGROUND + "; " + BLACK_BORDER);
			Encounter.getInstance().getPlayer().setCurrentMove(1);
			Encounter.getInstance().playRound();
		}
	}
	@FXML
	private void moveB1Entered() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB1.setStyle(WHITE_BACKGROUND + "; " + GENERIC_BORDER + Encounter.getInstance().getPlayer().getPlayerVariety().getColorStr());
		}
	}
	@FXML
	private void moveB1Exited() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB1.setStyle(WHITE_BACKGROUND + "; " + BLACK_BORDER);
		}
	}
	
	@FXML
	private void moveB2Pressed() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB2.setStyle(GRAY_BACKGROUND + "; " + BLACK_BORDER);
		}
	}
	@FXML
	private void moveB2Released() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB2.setStyle(WHITE_BACKGROUND + "; " + BLACK_BORDER);
			Encounter.getInstance().getPlayer().setCurrentMove(2);
			Encounter.getInstance().playRound();
		}
	}
	@FXML
	private void moveB2Entered() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB2.setStyle(WHITE_BACKGROUND + "; " + GENERIC_BORDER + Encounter.getInstance().getPlayer().getPlayerVariety().getColorStr());
		}
	}
	@FXML
	private void moveB2Exited() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB2.setStyle(WHITE_BACKGROUND + "; " + BLACK_BORDER);
		}
	}
	
	@FXML
	private void moveB3Pressed() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB3.setStyle(GRAY_BACKGROUND + "; " + BLACK_BORDER);
		}
		
	}
	@FXML
	private void moveB3Released() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB3.setStyle(WHITE_BACKGROUND + "; " + BLACK_BORDER);
			Encounter.getInstance().getPlayer().setCurrentMove(3);
			Encounter.getInstance().playRound();
		}
	}
	
	@FXML
	private void moveB3Entered() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB3.setStyle(WHITE_BACKGROUND + "; " + GENERIC_BORDER + Encounter.getInstance().getPlayer().getPlayerVariety().getColorStr());
		}
	}
	@FXML
	private void moveB3Exited() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_moveB3.setStyle(WHITE_BACKGROUND + "; " + BLACK_BORDER);
		}
	}
	@FXML
	private void continuePressed() {
		if(Encounter.getInstance().getStatus() == Status.WAITING) {
			_continueB.setStyle(GRAY_BACKGROUND + "; " + WHITE_BORDER);
		}
	}
	@FXML
	private void continueReleased() {
		if(Encounter.getInstance().getStatus() == Status.WAITING) {
			_continueB.setVisible(false);
			_continueB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
			Encounter.getInstance().continueB();
			
		}
	}
	@FXML
	private void continueEntered() {
		if(Encounter.getInstance().getStatus() == Status.WAITING) {
			_continueB.setStyle(BLACK_BACKGROUND + "; " + GENERIC_BORDER + Encounter.getInstance().getPlayer().getPlayerVariety().getColorStr());
		}
	}
	@FXML
	private void continueExited() {
		if(Encounter.getInstance().getStatus() == Status.WAITING) {
			_continueB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
		}
	}
	
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
			_quitB.setStyle(BLACK_BACKGROUND + "; " + GENERIC_BORDER + Encounter.getInstance().getPlayer().getPlayerVariety().getColorStr());
	}
	@FXML
	private void quitExited() {
			_quitB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
	}
	
	@FXML
	private void specialPressed() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_specialB.setStyle(GRAY_BACKGROUND + "; " + WHITE_BORDER);
		}
	}
	@FXML
	private void specialReleased() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_specialB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
			Encounter.getInstance().special();
		}
	}
	@FXML
	private void specialEntered() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_specialB.setStyle(BLACK_BACKGROUND + "; " + GENERIC_BORDER + Encounter.getInstance().getPlayer().getPlayerVariety().getColorStr());
		}
	}
	@FXML
	private void specialExited() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_specialB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
		}
	}
	@FXML
	private void buyPressed() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_buyB.setStyle(GRAY_BACKGROUND + "; " + WHITE_BORDER);
		}
	}
	@FXML
	private void buyReleased() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_buyB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
			Encounter.getInstance().buyHealth();
		}
	}
	@FXML
	private void buyEntered() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_buyB.setStyle(BLACK_BACKGROUND + "; " + GENERIC_BORDER + Encounter.getInstance().getPlayer().getPlayerVariety().getColorStr());
		}
	}
	@FXML
	private void buyExited() {
		if(Encounter.getInstance().getStatus() == Status.PLAYING) {
			_buyB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
		}
	}
	@FXML
	private void resetPressed() {
		_resetB.setStyle(GRAY_BACKGROUND + "; " + WHITE_BORDER);
	}
	@FXML
	private void resetReleased() {
		_resetB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
		Encounter.getInstance().reset();
	}
	@FXML
	private void resetEntered() {
		_resetB.setStyle(BLACK_BACKGROUND + "; " + GENERIC_BORDER + Encounter.getInstance().getPlayer().getPlayerVariety().getColorStr());
	}
	@FXML
	private void resetExited() {
		_resetB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
	}
	@FXML
	private void skipPressed() {
			_skipB.setStyle(GRAY_BACKGROUND + "; " + WHITE_BORDER);
	}
	@FXML
	private void skipReleased() {
		_skipB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
		Encounter.getInstance().skip();
	}
	@FXML
	private void skipEntered() {
		_skipB.setStyle(BLACK_BACKGROUND + "; " + GENERIC_BORDER + Encounter.getInstance().getPlayer().getPlayerVariety().getColorStr());
	}
	@FXML
	private void skipExited() {
		_skipB.setStyle(BLACK_BACKGROUND + "; " + WHITE_BORDER);
	}
	
	//LABELS
	
	@FXML
	private Label _moveL1, _moveL2, _moveL3, 
	_enemyL1, _enemyL2, _enemyL3,
	_enemyNameL,
	_pHealthL, _eHealthL,
	_currEncounterL;
	
	/**
	 * Getters for JavaFX Labels for other classes to manipulate visual style
	 */
	
	public Label getMoveL1() {return _moveL1;}
	public Label getMoveL2() {return _moveL2;}
	public Label getMoveL3() {return _moveL3;}
	public Label getEnemyL1() {return _enemyL1;}
	public Label getEnemyL2() {return _enemyL2;}
	public Label getEnemyL3(){return _enemyL3;}
	public Label getEnemyNameL(){return _enemyNameL;}
	public Label getPHealthL() {return _pHealthL;}
	public Label getEHealthL() {return _eHealthL;}
	public Label getCurrEncounterL() {return _currEncounterL;}
	
	
	//RECTANGLES
	@FXML
	private Rectangle _pHealthR, _eHealthR, _pChargeR, _eChargeR;
	
	/**
	 * Getters for JavaFX Rectangles for other classes to manipulate visual style
	 */
	
	public Rectangle getPHealthR() {return _pHealthR;}
	public Rectangle getEHealthR() {return _eHealthR;}
	public Rectangle getPChargeR() {return _pChargeR;}
	public Rectangle getEChargeR() {return _eChargeR;}
	
	@FXML
	private Text _gameText;
	
	/**
	 * Getters for JavaFX Text for other classes to manipulate visual style
	 */
	public Text getGameText() {return _gameText;}
	
	//ImageViews
	@FXML
	private ImageView _eSpriteIV, _pSpriteIV, _eHealthIV, _pHealthIV, _eSpecialIV, _pSpecialIV;
	
	/**
	 * Getters for JavaFX ImageViews for other classes to change image display from other classes
	 */
	public ImageView getESpriteIV() {return _eSpriteIV;}
	public ImageView getPSpriteIV() {return _pSpriteIV;}
	public ImageView getEHealthIV() {return _eHealthIV;}
	public ImageView getPHealthIV() {return _pHealthIV;}
	public ImageView getESpecialIV() {return _eSpecialIV;}
	public ImageView getPSpecialIV() {return _pSpecialIV;}
	
	
	@FXML
	private StackPane _center;
	
	/**
	 * Getters for central StackPane to add and remove elements from other classes
	 */
	public StackPane getCenter() {return _center;}
	
}
