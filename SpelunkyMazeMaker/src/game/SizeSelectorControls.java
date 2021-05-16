package game;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * Handles user input of size selection pane
 * 
 * @author kai
 *
 */
public class SizeSelectorControls {
	
	private int rows = 10;
	public int getX() {return rows;}
	private int cols = 10;
	public int getY() {return cols;}
	
	@FXML
	Slider hSelect, vSelect;
	
	@FXML
	Label selectLabel;
	
	/**
	 * On slider moved, change rows and cols to reflect slide and update label to provide user feedback
	 */
	@FXML
	public void sliderMoved() {
		cols = (int)hSelect.getValue();
		rows = 50 - (int)vSelect.getValue() + 1;
		selectLabel.setText(rows + " rows by " + cols + " columns");
	}
	
	/**
	 * On start pressed, close the size selector window and show the grid
	 * @throws IOException
	 */
	@FXML
	public void startPressed() throws IOException {
		SizeSelector.getInstance().getStage().close();
		new Grid(rows, cols);
	}
	
	/**
	 * On quit pressed, exit program
	 */
	@FXML
	public void quit() {
		System.exit(0);
	}
}
