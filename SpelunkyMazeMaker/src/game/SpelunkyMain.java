package game;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Starts program
 * 
 * @author kai
 *
 */
public class SpelunkyMain extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		new SizeSelector();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
