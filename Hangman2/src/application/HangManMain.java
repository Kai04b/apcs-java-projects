package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/**
 * Starts program
 * 
 * @author kai
 *
 */
public class HangManMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		GUI obj = new GUI();
		obj.run(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}