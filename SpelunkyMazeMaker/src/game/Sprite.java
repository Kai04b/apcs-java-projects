package game;

import javafx.scene.image.Image;

/**
 * Handles urls of special sprite images
 * 
 * @author kai
 *
 */

public enum Sprite {

	/**
	 * Two special sprites not associated with specific space
	 */
	
	//When player is in front of door
	MANDOOR("game/mandoor.png"),
	//When player has rock
	MANROCK("game/manrock.png");
	
	private Image _i;
	
	private Sprite(String s) {
		_i = new Image(s);
	}
	
	/**
	 * Get sprite Image object associated with Space
	 * 
	 * @return JavaFX image object
	 */
	public Image getImage() {
		return _i;
	}
	
}
