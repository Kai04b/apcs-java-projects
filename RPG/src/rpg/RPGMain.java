package rpg;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Start the program
 * 
 * @author kai
 *
 */
public class RPGMain extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		new WeaponSelector();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
