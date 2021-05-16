package rpg;

/**
 * Handles logic of overall game, tutorial and moving between encounters
 * 
 */
import java.awt.Toolkit;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game {
	
	/**
	 * Instance variables, getters, setters
	 */
	
	private Controller _controller;
    public Controller getController() {return _controller;}
    
    private Stage _primaryStage;
    public Stage getPrimaryStage() {return _primaryStage;}

	private static Game _instance;
	public static Game getInstance() {return _instance;}
	
	private Player _gPlayer;
	public Player getPlayer() {return _gPlayer;}
	
	private int _currEncounter;
	public int getCurrentEncounter() {return _currEncounter;}
	public void setCurrentEncounter(int e) {_currEncounter = e;}
	
	public Game(Player p) {
		_instance = this;
		
		_currEncounter = 1;
		_gPlayer = p;
		
		initPane();
		generateEncounter();
	}
	
	/**
	 * Initialize all the graphical elements of the main JavaFX pane and backend controls of it
	 */
	public void initPane() {
		try {
			_primaryStage = new Stage();
			
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
			BorderPane root = loader.load();
	    	_controller = loader.getController();
	    	
//	    	double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	    	double sceneHeight = 500;
	    	double sceneWidth = 1000;

	    	Scene scene = new Scene(root, sceneWidth, sceneHeight);
	    	
	    	_controller.getEHealthIV().setImage(new Image("images/pixelheart.png"));
	    	_controller.getPHealthIV().setImage(new Image("images/pixelheart.png"));
	    	_controller.getESpecialIV().setImage(new Image("images/special.png"));
	    	_controller.getPSpecialIV().setImage(new Image("images/special.png"));
	    	
	    	_controller.getCenter().setStyle("-fx-background-color: White; -fx-border-width: 10; -fx-border-color: " + _gPlayer.getPlayerVariety().getColorStr());
	    	
	    	_primaryStage.setTitle("Rock Paper Scissors Through History");
	    	_primaryStage.setMinWidth(sceneWidth);
	    	_primaryStage.setMinHeight(sceneHeight + 30);
	    	_primaryStage.setScene(scene);
	        _primaryStage.setResizable(true);
	        _primaryStage.setOnCloseRequest(e -> System.exit(0));
	        _primaryStage.show();
    	} 
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generate a single enemy encounter between player and enemy
	 */
	public void generateEncounter() {
		_gPlayer.setHealth(generatePlayerHealth());
		_gPlayer.setMaxHealth(generatePlayerHealth());
		new Encounter(_gPlayer, new Enemy(generateEnemyHealth(), _currEncounter));
	}
	
	
	/**
	 * Generate new player health based on round according to linear equation 200(x-1)+300
	 * 25% more health for red player
	 * 
	 * @return new health of player
	 */
	public int generatePlayerHealth() {
		int result = (int)(200 * (_currEncounter - 1) + 300);
		if(_gPlayer.getPlayerVariety() == PlayerVariety.RED) {
			result *= 1.25;
		}
		return result;
	}
	/**
	 * Generate new enemy health based on round according to linear equation 250(x-1)+100
	 * 
	 * @return new health of enemy
	 */
	public int generateEnemyHealth() {
		return (int)(250 * (_currEncounter - 1) + 100);
	}
}
