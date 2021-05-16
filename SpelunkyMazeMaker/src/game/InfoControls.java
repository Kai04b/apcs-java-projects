package game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Handles user input and graphical display of informational pane
 * 
 * @author kai
 *
 */
public class InfoControls {
	
	@FXML
	private Button quitB;
	
	
	/**
	 * When close button pressed, close window
	 */
	@FXML
	public void close() {
		Controls.getInstance().getStage().close();
	}
}
