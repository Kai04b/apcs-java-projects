package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Starts the graphing application
 * 
 * @author kai
 *
 */
public class GraphMain extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		new Graph();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}