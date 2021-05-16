package rpg;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * Displays of weapon selection pane
 * 
 * @author kai
 *
 */
public class WeaponSelector {
	
	/**
	 * Getters, setters, and instance variables
	 */
	
	private static WeaponSelector _instance;
	public static WeaponSelector getInstance() {
		return _instance;
	}

	private Stage _weaponSelectorStage;
	public Stage getStage() {
		return _weaponSelectorStage;
	}
	
	private WeaponSelectorControls _weaponSelectorController;
	
	private AnchorPane _root;
	
	private Scene _weaponSelectorScene;
	
	public WeaponSelector() throws IOException {
		_instance = this;
		
		_weaponSelectorStage = new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("WeaponSelector.fxml"));
		_root = loader.load();
    	_weaponSelectorController = loader.getController();
    	
    	double sceneHeight = 500;
    	double sceneWidth = 900;
    	
    	createLabels();

    	_weaponSelectorScene = new Scene(_root, sceneWidth, sceneHeight);
    	
    	_weaponSelectorController.getSprite1().setImage(new Image("images/playersprite11.png"));
    	_weaponSelectorController.getSprite2().setImage(new Image("images/playersprite2.png"));
    	_weaponSelectorController.getSprite3().setImage(new Image("images/playersprite3.png"));
    	
    	_weaponSelectorStage.setTitle("Weapon Selection");
    	_weaponSelectorStage.setMinWidth(sceneWidth);
    	_weaponSelectorStage.setMinHeight(sceneHeight);
    	_weaponSelectorStage.setScene(_weaponSelectorScene);
        _weaponSelectorStage.setResizable(false);
        _weaponSelectorStage.setOnCloseRequest(e -> System.exit(0));
        
        _weaponSelectorStage.show();
	}
	
	/**
	 * Create the labels for the weapon selection pane, three columns of labels spaced out, each label containing a weapon name
	 */
	public void createLabels() {
		for(int i = 0; i < 9; i++) {
			Label weaponLabel = new Label();
			weaponLabel.setAlignment(Pos.TOP_LEFT);
			weaponLabel.setText(Move.getMove(i * 3).getName());
			weaponLabel.setTranslateX(110);
			weaponLabel.setTranslateY(i * 40 + 80);
			weaponLabel.setFont(Font.font("Verdana", 13.0));
			weaponLabel.setTextFill(Color.BLACK);
			_root.getChildren().add(weaponLabel);
		}
		for(int i = 0; i < 9; i++) {
			Label weaponLabel = new Label();
			weaponLabel.setAlignment(Pos.TOP_LEFT);
			weaponLabel.setText(Move.getMove(i * 3 + 1).getName());
			weaponLabel.setTranslateX(290);
			weaponLabel.setTranslateY(i * 40 + 80);
			weaponLabel.setFont(Font.font("Verdana", 13.0));
			weaponLabel.setTextFill(Color.BLACK);
			_root.getChildren().add(weaponLabel);
		}
		for(int i = 0; i < 9; i++) {
			Label weaponLabel = new Label();
			weaponLabel.setAlignment(Pos.TOP_LEFT);
			weaponLabel.setText(Move.getMove(i * 3 + 2).getName());
			weaponLabel.setTranslateX(460);
			weaponLabel.setTranslateY(i * 40 + 80);
			weaponLabel.setFont(Font.font("Verdana", 13.0));
			weaponLabel.setTextFill(Color.BLACK);
			_root.getChildren().add(weaponLabel);
		}
	}
}
