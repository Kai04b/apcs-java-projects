package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Handles graphical display of and user input on the informational pane
 * 
 * @author kai
 *
 */
public class InfoControls {
	
	private static final String GRAY_BACKGROUND = "-fx-background-color: Gray";
	private static final String WHITE_BACKGROUND = "-fx-background-color: White";
	private static final String ORANGE_BACKGROUND = "-fx-background-color: LIGHTBLUE";
	
	@FXML
	private Button quitB;
	
	@FXML
	private Text _helpText;
	
	/**
	 * Close pane when close button pressed
	 */
	@FXML
	public void close() {
		Controller.getInstance().getInfoStage().close();
	}
	
	/**
	 * When the quit button hovered over, hover exited, pressed, and released, change visual style to give user input
	 */
	
	@FXML
	private void quitEntered() {
		quitB.setStyle(ORANGE_BACKGROUND);
	}
	@FXML
	private void quitExited() {
		quitB.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void quitReleased() {
		quitB.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void quitPressed() {
		quitB.setStyle(GRAY_BACKGROUND);
	}
}
