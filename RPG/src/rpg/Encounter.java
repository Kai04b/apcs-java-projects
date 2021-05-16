package rpg;

import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Handles single encounter between player and enemy, once one dies, encounter ends
 * 
 * @author kai
 *
 */
public class Encounter {
	
	/**
	 * Constants for in-game random chances
	 */
	
	private static final double CRIT_CHANCE = .2;
	private static final double EBUY_CHANCE = .3;
	
	/**
	 * Getters, setters, and instance variables
	 */
	
	private static Encounter _instance;
	public static Encounter getInstance() {return _instance;}
	
	private Game _game;
	public Game getGame() {return _game;}
	
	private Controller _eController;
	public Controller getController() {return _eController;}
	
	private Player _p;
	public Player getPlayer() {return _p;}
	
	private Enemy _e;
	public Enemy getEnemy() {return _e;}
	
	private String _gameText;
	public String getGameText() {return _gameText;}
	public void setGameText(String s) {_gameText = s;}
	public void addToGameText(String s) {_gameText += s;}
	
	private Status _status;
	public Status getStatus() {return _status;}
	
	private int _tutorial;
	
	public Encounter(Player p, Enemy e) {
		
		_instance = this;
		
		_game = Game.getInstance();
		_eController = _game.getController();
		_status = Status.PLAYING;
		_tutorial = 0;
		
		_p = p;
		_e = e;
		
		if(_e.getEnemyVariety() == EnemyVariety.GENERICNPC) {
			tutorial(_tutorial);
		}
		
		updateImage();
		updatePane();
	}
	
	/**
	 * Handles the tutorial of the game
	 * 
	 * @param t Stage of tutorial, 1 for first message, 7 for last message, 8 for skip
	 */
	private void tutorial(int t) {
		_eController.getContinueB().setVisible(true);
		
		if(t == 0) {
			_status = Status.WAITING;
			_gameText = "Hi, I'm the tutorial guy"
					+ "\nA time machine has sent us back in time"
					+ "\nYou'll have to battle your way through the past"
					+ "\nand into the future";
			
		}
		if(t == 1) {
			_status = Status.WAITING;
			_gameText = "Whether it's a primitve club or an alien energy beam"
					+ "\nEvery weapon is either sharp, blunt, or encumbering"
					+ "\nBlunt always beats sharp, "
					+ "\nWhich beats encumbering, "
					+ "\nWhich beats blunt";
		}
		if(t == 2) {
			_status = Status.WAITING;
			_gameText = "It's just one big circle!";
		}
		if(t == 3) {
			_status = Status.WAITING;
			_gameText = "When you get hit, your special meter will charge up"
					+ "\nWhen it's full, you can hit the "
					+ "\nspecial button to deal incredible damage (3x)!";
		}
		if(t == 4) {
			_status = Status.WAITING;
			_gameText = "You can also use your special meter\n"
					+ "To buy health, ranging from 100 to 500 points\n";
		}
		if(t == 5) {
			_status = Status.WAITING;
			_gameText = "Oh and I almost forgot"
					+ "\nThere's a 20% chance you'll land"
					+ "\na critical hit and deal double damage"
					+ "\nBut it will charge up the enemy's\nspecial meter twice as much!";
		}
		if(t == 6) {
			_status = Status.WAITING;
			_gameText = "I'll give you a friendly little spar for practice"
					+ "\nBut once you get past me it'll get a lot harder"
					+ "\nGood luck!";
		}
		if(t == 7) {
			_eController.getContinueB().setVisible(false);
			_eController.getSkipB().setVisible(false);
			_status = Status.PLAYING;
			_gameText = "Press one of the buttons below and I'll fight back!";
		}
		if(t == 8) {
			_eController.getContinueB().setVisible(false);
			_eController.getSkipB().setVisible(false);
			_status = Status.PLAYING;
			_gameText = "";
		}
		
	
		_tutorial++;

		updateImage();
		updatePane();
		
	}

	/**
	 * Set enemy sprite and player sprite to correct images
	 */
	private void updateImage() {	
		_eController.getESpriteIV().setImage(new Image(_e.getEnemyVariety().getImgStr()));
		_eController.getESpriteIV().setPreserveRatio(true);
		_eController.getESpriteIV().setFitHeight(150);
		
		_eController.getPSpriteIV().setImage(new Image(_p.getPlayerVariety().getImgStr()));
		_eController.getESpriteIV().setPreserveRatio(true);
		_eController.getESpriteIV().setFitHeight(150);
	}
	
	/**
	 * Update visuals, change name labels, button text, health labels, and health bars according to encounter information
	 */
	private void updatePane() {	
		_eController.getCurrEncounterL().setText(String.valueOf("Encounter: " + Game.getInstance().getCurrentEncounter()));
		
		_eController.getPHealthL().setText(" " + String.valueOf((int)_p.getHealth()) + " ");
		_eController.getPHealthR().setWidth((_p.getHealth()/_p.getMaxHealth()) * 200.0);
		_eController.getPChargeR().setWidth((_p.getCharge()/100.0) * 200.0);
		
		_eController.getMoveB1().setText(_p.getMove1().getName());
		_eController.getMoveB2().setText(_p.getMove2().getName());
		_eController.getMoveB3().setText(_p.getMove3().getName());
		
		_eController.getMoveL1().setText(_p.getMove1().getType().toString());
		_eController.getMoveL2().setText(_p.getMove2().getType().toString());
		_eController.getMoveL3().setText(_p.getMove3().getType().toString());
		_eController.getMoveL1().setTextFill(_p.getMove1().getType().getColor());
		_eController.getMoveL2().setTextFill(_p.getMove2().getType().getColor());
		_eController.getMoveL3().setTextFill(_p.getMove3().getType().getColor());
		
		_eController.getEnemyNameL().setText(_e.getEnemyVariety().getName());
		
		_eController.getESpriteIV().setOpacity((_e.getHealth()/_e.getMaxHealth())/2 + .5);
		_eController.getPSpriteIV().setOpacity((_p.getHealth()/_p.getMaxHealth())/2 + .5);
		
		_eController.getEHealthL().setText(" " + String.valueOf((int)_e.getHealth()) + " ");
		_eController.getEHealthR().setWidth((_e.getHealth()/_e.getMaxHealth()) * 200.0);
		_eController.getEChargeR().setWidth((_e.getCharge()/100.0) * 200.0);
		
		_eController.getEnemyB1().setText(_e.getEnemyVariety().getMove1().getName());
		_eController.getEnemyB2().setText(_e.getEnemyVariety().getMove2().getName());
		_eController.getEnemyB3().setText(_e.getEnemyVariety().getMove3().getName());
		
		_eController.getEnemyL1().setText(_e.getEnemyVariety().getMove1().getType().toString());
		_eController.getEnemyL2().setText(_e.getEnemyVariety().getMove2().getType().toString());
		_eController.getEnemyL3().setText(_e.getEnemyVariety().getMove3().getType().toString());
		_eController.getEnemyL1().setTextFill(_e.getEnemyVariety().getMove1().getType().getColor());
		_eController.getEnemyL2().setTextFill(_e.getEnemyVariety().getMove2().getType().getColor());
		_eController.getEnemyL3().setTextFill(_e.getEnemyVariety().getMove3().getType().getColor());
		
		_eController.getGameText().setText(_gameText);
		_eController.getGameText().setFill((_p.getPlayerVariety().getColor()));
	}

	/**
	 * On player button press, play a round
	 * Set enemy move to random
	 * If enemy has enough charge, do special attack or buy health
	 * Find winner based on moves and take health from loser
	 * Update pane to reflect changes visually
	 */
	public void playRound() {
		_gameText = "";
		_e.setRandomMove();
		System.out.println("Playing round");
		System.out.println("Player played: " + _p.getCurrentMove());
		System.out.println("Computer played: " + _e.getCurrentMove());
		if(!checkESpecial()) {}
		else if(!checkEBuyHealth()) {}
		else {
			findWinner();
		}
			checkDeath();
			updatePane();
		
	}
	/**
	 * Given a player move and computer move, find who won and update
	 */
	private void findWinner() {
		int winner = Type.findWinner(_p.getCurrentMove().getType(), _e.getCurrentMove().getType());
		switch(winner) {
			case -1:
				
				comWin();
				break;
			case 0:
				System.out.println("Tie");
				tie();
				break;
			case 1:
				System.out.println("Player wins");
				plyWin();
				break;
			case 2:
				System.out.println("uhhhh idk what happened man");
				break;
		}
	}
	/**
	 * On computer win, update game text to inform player and subtract player health
	 */
	private void comWin() {
		_gameText += _e.getFullWinMessage();
		double rand = Math.random();
		if(rand < CRIT_CHANCE) {
			_p.decreaseHealth(200);
			_p.increaseCharge(40);
			_gameText += "\nRandom critical hit! Double damage done!";
		}
		else {
			_p.decreaseHealth(100);
			_p.increaseCharge(20);
		}
		
		updatePane();
	}
	/**
	 * On player win, update game text to inform player and subtract computer health
	 */
	private void plyWin() {
		_gameText = _p.getFullWinMessage() + " the " + _e.getEnemyVariety().getName().toLowerCase();
		double rand = Math.random();
		
		double health = 100.0;
		double charge = 20.0;
		if(rand < CRIT_CHANCE) {
			health *= 2;
			charge *= 2;
			_gameText += "\nRandom critical hit! Double damage done!";
		}
		if(_p.getPlayerVariety() == PlayerVariety.YELLOW) {
			health *= 1.5;
		}
		_e.decreaseHealth(health);
		_e.increaseCharge(charge);
		
		updatePane();
	}
	/**
	 * On tie, update game text to inform player and do not change health
	 */
	private void tie() {
		_gameText += "Your " + _p.getCurrentMove().getName().toLowerCase() + " and the " + _e.getEnemyVariety().getName().toLowerCase() + "'s " + _e.getCurrentMove().getName().toLowerCase() + "\nare the same type (" + _e.getCurrentMove().getType().getName().toLowerCase() + ")\nand therefore have no effect";
		updateImage();
		updatePane();
	}
	
	/**
	 * On player attempt special, if player has enough charge, decrease enemy health by 300
	 * If player is yellow type, do more damage
	 */
	public void special() {
		if(_p.getCharge() == 100.0) {
			if(_p.getPlayerVariety() == PlayerVariety.YELLOW) {
				_e.decreaseHealth(450);
			}
			else {
				_e.decreaseHealth(300);
			}
			_p.setCharge(0);
			_gameText = "Your special move does triple damage!";
			_e.increaseCharge(60);
			checkDeath();
			updatePane();
		}
		else {
			_gameText = "You don't have enough charge to perform a special move";
			updatePane();
		}
	}
	/**
	 * On player attempt to buy health, if player has enough charge, increase their health by a third of gap
	 * Intentional design choice to make buying health more advantageous at lower healths, like last stand
	 */
	public void buyHealth() {
		if(_p.getCharge() >= 50) {
			double newHealth = (_p.getMaxHealth() - _p.getHealth())/3;
			if(newHealth < 100) {
				newHealth = 100;
			}
			if(newHealth > 500) {
				newHealth = 500;
			}
			_p.increaseHealth(newHealth);
			_gameText = "You feel revitalized";
			_p.decreaseCharge(50);
			checkDeath();
			updatePane();
		}
		else {
			_gameText = "You don't have enough charge to buy health";
			updatePane();
		}
	}
	/**
	 * Check if enemy buys health
	 * Only if enemy has enough charge and Math.random() < .3 (30% chance dice roll)
	 * 
	 * @return true if enemy buys health, false otherwise
	 */
	public boolean checkEBuyHealth() {
		double rand = Math.random();
		if(_e.getCharge() >= 50 && rand < EBUY_CHANCE) {
			double newHealth = (_e.getMaxHealth() - _e.getHealth())/3;
			if(newHealth < 100) {
				newHealth = 100;
			}
			if(newHealth > 500) {
				newHealth = 500;
			}
			_e.increaseHealth(newHealth);
			_gameText = "The " + _e.getEnemyVariety().getName() + " drinks a health potion";
			_e.decreaseCharge(50);
			checkDeath();
			updatePane();
			return false;
		}
		return true;
	}
	/**
	 * Check if enemy buys special
	 * Only if enemy has enough charge
	 * 
	 * @return true if enemy buys special attack, false otherwise
	 */
	private boolean checkESpecial() {
		if(_e.getCharge() == 100) {
			_p.decreaseHealth(300);
			_gameText = "The " + _e.getEnemyVariety().getName() + " landed a special attack\n"
					+ "It does triple damage!\n"
					+ "Your " + _p.getCurrentMove().getName().toLowerCase() + " misses";
			_e.setCharge(0);
			_p.increaseCharge(60);
			checkDeath();
			updatePane();
			return false;
		}
		return true;
	}
	/**
	 * Check if enemy buys special
	 * Only if enemy has enough charge
	 * 
	 * @return true if enemy buys special attack, false otherwise
	 */
	private void checkDeath() {
		if(_p.getHealth() <= 0.0) {
			_status = Status.WAITING;
			_gameText += "\nThe " + _e.getEnemyVariety().getName().toLowerCase() + " killed you";
			_eController.getContinueB().setVisible(true);
			
		}
		if(_e.getHealth() <= 0.0) {
			_status = Status.WAITING;
			_gameText += "\nYou killed the " + _e.getEnemyVariety().getName().toLowerCase();
			_eController.getContinueB().setVisible(true);
		}
	}
	/**
	 * On player death, reopen game
	 */
	public void pDie() {
		Game.getInstance().getPrimaryStage().close();
		_p.setCharge(0.0);
		_p.setHealth(0);
		_p.setMaxHealth(0);
		new Game(_p);
	}
	/**
	 * On enemy death, generate new encounter
	 * 
	 */
	private void eDie() {
		_eController.getContinueB().setVisible(false);
		Game.getInstance().setCurrentEncounter(Game.getInstance().getCurrentEncounter() + 1);
		Game.getInstance().generateEncounter();
	}
	/**
	 * On continue button pressed:
	 * If in tutorial, increment tutorial
	 * If not in tutorial, kill whichever player has 0 health
	 */
	public void continueB() {
		if(_tutorial != -1) {
			tutorial(_tutorial);
		}
		if(_p.getHealth() <= 0.0) {
			pDie();
			
		}
		if(_e.getHealth() <= 0.0) {
			eDie();
			
		}
	}
	/**
	 * Fully reset game, show weapon selection screen and start from zero encounter
	 */
	public void reset() {
		Game.getInstance().getPrimaryStage().close();
		try {
			new WeaponSelector();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Skip tutorial sequence, just go to fighting
	 */
	public void skip() {
		_tutorial = 8;
		continueB();
	}
	
}
