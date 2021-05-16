package game;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.*;  
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Handles graphical display and user input on the main grid of tiles/spaces
 * 
 * @author kai
 *
 */
public class Grid {
	
	
	private static Grid instance;
	// static method to get instance of Grid
	public static Grid getInstance() {
		return instance;
	}
		

	/**
	 * Instance variables, getters, and setters
	 */
	
	private boolean _dark;
	public void setDark(boolean b) {_dark = b;}
	
	private boolean _ePressed;
	public void toggleEPressed() {
		_ePressed = !_ePressed;
	}
	public void setEpressed(boolean b) {_ePressed = b;}
	
	
	private boolean _playing;
	public void setPlaying(boolean b) {_playing = b;}
    public boolean getPlaying() {return _playing;}
    
	private boolean _dead;
	public void setDead(boolean b) {
		_dead = b;
	}
    public boolean getDead() {return _dead;}
	
	private Stage primaryStage;

    private double sceneWidth;
    private double sceneHeight;

    private int m;
    private int n;
    
    public int getM() {return m;}
    public int getN() {return n;}

    double sqWidth;
    double sqHeight;

    private MyNode[][] _playfield;
    
    private MazeArray _savedSpaces;
    private MazeArray _spaces;
    public MazeArray getSpaces() {return _spaces;}
    
    private Controls _controller;
    
    public Controls getController() {return _controller;}

    public Grid(int wide, int high) throws IOException {
    	m = wide;
    	n = high;
    	System.out.println("constructing" + wide + high);
    	_playfield = new MyNode[m][n];
    	
    	sceneHeight = 550;
    	sceneWidth = sceneHeight;
    	System.out.println("scenewidth " + sceneWidth + " sceneheight " + sceneHeight);
    	sqWidth = sceneWidth/n;
        sqHeight = sceneHeight/m;
        System.out.println("sqwidth " + sqWidth + "sqheight" + sqHeight);
    	
    	
    	
    	 _spaces = new MazeArray(m, n);
    	instance = this;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
    	BorderPane root = loader.load();
    	_controller = loader.getController();
    	_controller.setPane(sceneWidth, sceneHeight);
    	
    	_controller.setSliderBoxSpacing(sceneHeight / 9.0 - 25);
    	
    	_playing = false;
    	_dead = false;
    	
    	primaryStage = new Stage();
    	
        Group grid = new Group();

        // initialize playfield
        for( int i=0; i < m; i++) {
            for( int j=0; j < n; j++) {
            	System.out.println("constructing: " + i + " " + j);
                // create node
               // MyNode node = new MyNode(i + "-" + j, j * sqWidth, i * sqHeight, sqWidth, sqHeight, i, j);
            	MyNode node = new MyNode("", j * sqWidth + 1, i * sqHeight, sqWidth, sqHeight, i, j);

                node.setOnMouseEntered(e -> {
					try {
						createSquare(node, true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
                node.setOnMouseClicked(e -> {
					try {
						createSquare(node, false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
                node.setOnMouseDragEntered(e -> {
					try {
						createSquare(node, false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
                

                // add node to group
                grid.getChildren().add(node);

                // add to playfield for further reference using an array
                _playfield[i][j] = node;
                
            }
        }
        
       
        root.setCenter(grid);
        
        Scene scene = new Scene(root, sceneWidth + 300, sceneHeight + 150);

        primaryStage.setTitle("Maze Maker");
        
        
        primaryStage.setScene( scene);
        
        double currentWidth = sceneWidth + 300;
        double currentHeight = sceneHeight + 165;
        primaryStage.setMinWidth(currentWidth);
        primaryStage.setMinHeight(currentHeight);
        System.out.println("min width " + primaryStage.getMinWidth());
        System.out.println("min height " + primaryStage.getMinHeight());
        primaryStage.setResizable(true);
        primaryStage.show();
        drawGrid();
    }
    
    public void resize() {
    	sceneWidth = _controller.getMainPane().getWidth() - 300;
		sceneHeight = _controller.getMainPane().getHeight() - 150;
		
		sqWidth = sceneWidth/n;
        sqHeight = sceneHeight/m;
    	for(int r = 0; r < _playfield.length; r++) {
    		for(int c = 0; c < _playfield[0].length; c++) {
    			
    			_controller.setSliderBoxSpacing(sceneHeight / 9.0 - 25);
    			
    	        _playfield[r][c].setSqWidth(sqWidth);
    			_playfield[r][c].setSqHeight(sqHeight);
    			
    			_playfield[r][c].setTranslateX((c * sqWidth) + 1);
    			_playfield[r][c].setTranslateY((r * sqHeight));
    	
    			
    		}
    	}
    	drawGrid();
    }
    
    public void drawGrid() {
    	if(_dark) {
    		drawGridDark();
    	}
    	else {
    		drawGridLight();
    	}
    }
    
    public void drawGridLight() {
    	//_spaces.printSpaces();
    	for(int r = 0; r < _playfield.length; r++) {
    		for(int c = 0; c < _playfield[0].length; c++) {
    			Rectangle rec = _playfield[r][c].getRectangle();
    			Space s = (Space)(_spaces.getSpace(r, c));
    			ImageView iv = _playfield[r][c].getImageView();
    			
    			if(_spaces.getMinotaurList().isMinotaurPosition(r, c)) {
    				s = Space.ENEMY;
    			}
    			else if(_spaces.isPlayerPosition(r, c)) {
    				s = Space.PLAYER;
    			}
    			rec.setFill(s.getFill());
    			rec.setStroke(s.getBorder());
    			if(s.getImage() != null) {
    				iv.setImage(s.getImage());
    			}
    			if(_spaces.isPlayerPosition(r, c) && _spaces.getPlayer().hasSword()) {
    				iv.setImage(Sprite.MANROCK.getImage());
    			}
    			if(_spaces.isPlayerPosition(r, c) && _spaces.getSpace(r, c) == Space.EXIT) {
    				iv.setImage(Sprite.MANDOOR.getImage());
    			}
    			
    			
//    			if(s == Space.AIR) {
//    	    		lab.setText(_playfield[r][c].getX() + "-"  + _playfield[r][c].getY());
//    			}
    			
    			
//    			else if(s == Space.WALL) {
//    	  
//    	    		
//    	    		lab.setTextFill(Color.WHITE);
//    	    		lab.setText("Wall");
//    			}
//    			else if(s == Space.PLAYER) {
//    				
//    				
//    	    		lab.setTextFill(Color.WHITE);
//    	    		lab.setText("Player");
//    			}
//    			else if(s == Space.EXIT) {
//    				
//    				
//    	    		lab.setTextFill(Color.WHITE);
//    	    		lab.setText("Exit");
//    			}
    			//_playfield[r][c].setRectangle(rec);
    			//_playfield[3][9].setRectangle(rec);
    			iv.setOpacity(1);
    		}
    	}
    	
    }
    
    public void drawGridDark() {
    	//_spaces.printSpaces();
    	for(int r = 0; r < _playfield.length; r++) {
    		for(int c = 0; c < _playfield[0].length; c++) {
    			Rectangle rec = _playfield[r][c].getRectangle();
    			Space s = (Space)(_spaces.getSpace(r, c));
    			ImageView iv = _playfield[r][c].getImageView();
    			
    			if(_spaces.getMinotaurList().isMinotaurPosition(r, c)) {
    				s = Space.ENEMY;
    			}
    			else if(_spaces.isPlayerPosition(r, c)) {
    				s = Space.PLAYER;
    			}
    			rec.setFill(s.getFill());
    			rec.setStroke(s.getBorder());
    			if(s.getImage() != null) {
    				iv.setImage(s.getImage());
    			}
    			if(_spaces.isPlayerPosition(r, c) && _spaces.getPlayer().hasSword()) {
    				iv.setImage(Sprite.MANROCK.getImage());
    			}
    			if(_spaces.isPlayerPosition(r, c) && _spaces.getSpace(r, c) == Space.EXIT) {
    				iv.setImage(Sprite.MANDOOR.getImage());
    			}
    			
    			double distance = Math.sqrt(Math.pow(_spaces.getPlayer().getRow() - r, 2) + Math.pow(_spaces.getPlayer().getCol() - c, 2));
    			distance = modifyDistance(distance);
    			iv.setOpacity(1 - distance);
    		}
    	}
    	
    }
    
    private double modifyDistance(double d) {
    	if(_spaces.getPlayer().hasBoots()) {
    		return d /= (m+n)/2;
    	}
    	else {
    		return d/= (m+n)/6;
    	}
    }
    
    public void createSquare(MyNode node, boolean b) throws Exception {
        	if(b && !_playing && !_dead && _ePressed) {
        		try {
					node.mouseClicked(_controller.getRSlider());
					drawGrid();
				} 
        		catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
        	}
        	else if(!b && !_playing && !_dead) {
        		node.mouseClicked(_controller.getRSlider());
        		drawGrid();
        	}
    }
    
    public void resetAll() {
    	primaryStage.close();
    	try {
			new SizeSelector();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void reset() {
    	_spaces = new MazeArray(_savedSpaces);
    	_spaces.printSpaces();
    	System.out.println("reset");
    	_controller.stopPlaying();
    	drawGrid();
    }
    
    public void saveMazeArray() {
    	_savedSpaces = new MazeArray(_spaces);
    	_savedSpaces.printSpaces();
    	System.out.println("saved");
    }
    
    public void playRound(Direction d) {
    	_spaces.playRound(d);
    	drawGrid();
    	System.out.println(_dark);
    }
    
    /**
     * Handles graphical representation of a space/tile
     * 
     * @author kai
     *
     */
    public class MyNode extends StackPane {
    	
    	/**
    	 * Instance variables, getters, and setters
    	 */
    	
    	private Rectangle _r;
    	private ImageView _iv;
    	private Space _space;
    	
    	private double _width;
    	public double getSqWidth() {return _width;}
    	public void setSqWidth(double d) {
	    	_width = d;
			_r.setWidth(d);
			_iv.setFitWidth(d + 2);
    	;}
    	
    	
    	
    	private double _height;
    	public double getSqHeight() {return _width;}
    	public void setSqHeight(double d) {
    		_height = d;
    		_r.setHeight(d);
    		_iv.setFitHeight(d + 2);
    	}
    	
    	private int _m;
    	
    	
    	private int _n;
    	
    	public void setRectangle(Rectangle r) {
    		_r = r;
    	}
    	
    	public Rectangle getRectangle() {
    		return _r;
    	}
    	
    	public ImageView getImageView() {
    		return _iv;
    	}

        public MyNode(String name, double x, double y, double width, double height, int m, int n) {
            // create rectangle
            _r = new Rectangle(width, height);
            
            _m = m;
            _n = n;
          
            _iv = new ImageView(Space.AIR.getImage());
            _iv.setFitHeight(height + 2);
            _iv.setFitWidth(width + 2);
            _iv.toFront();

            // create label
            

            // set position
            setTranslateX( x);
            setTranslateY( y);

            getChildren().addAll(_r, _iv);

        }
        
        
        /**
         * On mouse clicked, set node to corresponding Space enum
         * 
         * @param n	Index corresponding to Space index
         * @throws Exception
         */
        public void mouseClicked(int n) throws Exception{
        	System.out.println(n);
        	if(n == Space.AIR.getNum()) {
        		airMaker();
        	}
        	else if(n == Space.WALL.getNum()) {
        		wallMaker();
        	}
        	else if(n == Space.PLAYER.getNum()) {
        		playerMaker();
        	}
        	else if(n == Space.EXIT.getNum()) {
        		exitMaker();
        	}
        	else if(n == Space.ENEMY.getNum()) {
        		minotaurMaker();
        	}
        	else if(n == Space.WEAPON.getNum()) {
        		swordMaker();
        	}
        	else if(n == Space.KEY.getNum()) {
        		keyMaker();
        	}
        	else if(n == Space.DOOR.getNum()) {
        		doorMaker();
        	}
        	else if(n == Space.TORCH.getNum()) {
        		torchMaker();
        	}
        }
        
        /**
         * Special methods for each Space enum
         * If Space already exists at coordinates, fill with empty air
         */
        
        public void airMaker() {
        	if(getSpaces().getMinotaurList().isMinotaurPosition(_m, _n)) {
        		deleteMinotaur();
        	}
        	else if(getSpaces().isPlayerPosition(_m, _n)) {
        		deletePlayer();
        	}
        	else {
        		fillAir();
        	}
        }
        
        public void wallMaker() {
        	if(getSpaces().getSpace(_m, _n) == Space.WALL) {
        		fillAir();
        	}
        	else if(!getSpaces().isPlayerPosition(_m, _n) && !getSpaces().getMinotaurList().isMinotaurPosition(_m, _n)){
        		fillWall();
        	}
        }
        
        public void playerMaker() {
        	if(getSpaces().isPlayerPosition(_m, _n)) {
        		deletePlayer();
        	}
        	else if(getSpaces().getSpace(_m, _n) == Space.AIR) {
        		fillPlayer();
        	}
        }
        
        public void exitMaker() {
        	if(getSpaces().getSpace(_m, _n) == Space.EXIT) {
	        	fillAir();
        	}
        	else if(!getSpaces().isPlayerPosition(_m, _n) && !getSpaces().getMinotaurList().isMinotaurPosition(_m, _n)) {
        		fillExit();
        	}
        }
        
        public void swordMaker() {
        	if(getSpaces().getSpace(_m, _n) == Space.WEAPON) {
	        	fillAir();
        	}
        	else if(!getSpaces().isPlayerPosition(_m, _n) && !getSpaces().getMinotaurList().isMinotaurPosition(_m, _n)) {
        		fillSword();
        	}
        }
        
        public void minotaurMaker() {
        	if(getSpaces().getMinotaurList().isMinotaurPosition(_m, _n)) {
        		deleteMinotaur();
        	}
        	else if(getSpaces().getSpace(_m, _n) == Space.AIR) {
        		fillMinotaur();
        	}
        }
        public void keyMaker() {
        	if(getSpaces().getSpace(_m, _n) == Space.KEY) {
	        	fillAir();
        	}
        	else if(!getSpaces().isPlayerPosition(_m, _n) && !getSpaces().getMinotaurList().isMinotaurPosition(_m, _n)) {
        		fillKey();
        	}
        }
        public void doorMaker() {
        	if(getSpaces().getSpace(_m, _n) == Space.DOOR) {
	        	fillAir();
        	}
        	else if(!getSpaces().isPlayerPosition(_m, _n) && !getSpaces().getMinotaurList().isMinotaurPosition(_m, _n)) {
        		fillDoor();
        	}
        }
        public void torchMaker() {
        	if(getSpaces().getSpace(_m, _n) == Space.TORCH) {
	        	fillAir();
        	}
        	else if(!getSpaces().isPlayerPosition(_m, _n) && !getSpaces().getMinotaurList().isMinotaurPosition(_m, _n)) {
        		fillTorch();
        	}
        }
        
        /**
         * Fill MazeArray object spaces with Space enum at correct coordinates
         */
        
        private void fillWall() {
        	//_space = Space.WALL;
        	getSpaces().setSpace(Space.WALL, _m, _n);
        }
        private void fillAir() {
        	//_space = Space.AIR;
        	getSpaces().setSpace(Space.AIR, _m, _n);
        }
        private void fillPlayer() {
        	getSpaces().setPlayerPos(_m, _n);
        }
        private void deletePlayer() {
        	getSpaces().setPlayerPos(-1, -1);
        }
        private void fillExit() {
        	//_space = Space.EXIT;
        	getSpaces().setSpace(Space.EXIT, _m, _n);
        }
        private void fillKey() {
        	getSpaces().setSpace(Space.KEY, _m, _n);
        }
        private void fillSword() {
        	//_space = Space.SWORD;
        	getSpaces().setSpace(Space.WEAPON, _m, _n);
        }
        private void fillDoor() {
        	//_space = Space.SWORD;
        	getSpaces().setSpace(Space.DOOR, _m, _n);
        }
        private void fillTorch() {
        	//_space = Space.SWORD;
        	getSpaces().setSpace(Space.TORCH, _m, _n);
        }
        private void fillMinotaur() {
        	getSpaces().getMinotaurList().addMinotaur(_m, _n);
        }
        private void deleteMinotaur() {
        	getSpaces().getMinotaurList().removeAtPosition(_m, _n);
        }
      
        

    }

}