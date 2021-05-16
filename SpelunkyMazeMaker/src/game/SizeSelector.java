package game;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Displays of grid size selection pane
 * 
 * @author kai
 *
 */
public class SizeSelector {
	
	private static SizeSelector instance;
	public static SizeSelector getInstance() {return instance;}
	
	private Stage _sizeSelectorStage;
	public Stage getStage() {return _sizeSelectorStage;}
	
	
	public SizeSelector() throws IOException {
		instance = this;
		_sizeSelectorStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SizeSelector.fxml"));
    	StackPane root = loader.load();
    	Scene scene = new Scene(root, 500, 500);
    	_sizeSelectorStage.setTitle("Select Your Size");
    	_sizeSelectorStage.setScene(scene);
    	_sizeSelectorStage.setResizable(false);
    	_sizeSelectorStage.show();
	}
}
