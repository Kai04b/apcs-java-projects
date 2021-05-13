package application;

import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * Handles graphical display of the graph and controls
 * 
 * @author kai
 *
 */
public class Graph {
	
	/**
	 * Getters, setters, and instance variables
	 */
	private static Graph _instance;
	public static Graph getInstance() {return _instance;}
	
	private static final int PANE_WIDTH = 1000;
	private static final int PANE_HEIGHT = 500;
	
	private Stage _primaryStage;
	public Stage getStage() {return _primaryStage;}
	
	private Controller _controller;
	public Controller getController() {return _controller;}
	
	private BorderPane _root;
	public BorderPane getRoot() {return _root;}
	
	private Scene _scene;
	public Scene getScene() {return _scene;}
	
	private StackPane _center;
	public StackPane getCenter() {return _center;}
	
	private LineList _lines;
	public LineList getLines() {return _lines;}
 	
	private PointList _points;
	public PointList getPoints() {return _points;}
	
	private Label _mouseLabel;
	
	private static double RIGHT_BOUND = 10;
	private static double LEFT_BOUND = -10;
	
	private static double TOP_BOUND = 10;
	private static double BOTTOM_BOUND = -10;
	
	private static double X_PIXELS = 400;
	private static double Y_PIXELS = 400;
	
	private static double X_SIZE = RIGHT_BOUND - LEFT_BOUND;
	private static double Y_SIZE = TOP_BOUND - BOTTOM_BOUND;
	
	private static double X_SCALE = X_PIXELS/(X_SIZE);
	private static double Y_SCALE = Y_PIXELS/(Y_SIZE);
	
	private ArrayList<Color> _colors;

	
	public Graph() {
		_instance = this;
		
		initPane();
		
		initColors();
		initPoints();
		initLines();
		initAxis();
		
		drawGraph();
		
	}
	
	/**
	 * Fill list of colors with JavaFX color objects
	 */
	private void initColors() {
		_colors = new ArrayList<Color>();
		_colors.add(Color.PURPLE);
		_colors.add(Color.BLUE);
		_colors.add(Color.RED);
		_colors.add(Color.GREEN);
		_colors.add(Color.AQUA);
		_colors.add(Color.ORANGE);
		_colors.add(Color.DEEPPINK);
		_colors.add(Color.FUCHSIA);
		_colors.add(Color.HOTPINK);
		_colors.add(Color.LIME);
		_colors.add(Color.MAGENTA);
	}
	
	/**
	 * Initialize main pane
	 */
	private void initPane() {
		try {
			_mouseLabel = new Label();
			
			_primaryStage = new Stage();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Graph.fxml"));
			_root = loader.load();
	    	_controller = loader.getController();
	    	_center = (StackPane)_root.getCenter();
	    	
	    	StackPane bottom = (StackPane)_root.getBottom();
	    	bottom.getChildren().add(_mouseLabel);
			_mouseLabel.setFont(Font.font("Helvetica", 16.0));
			_mouseLabel.setTextFill(Color.WHITE);
			
			_scene = new Scene(_root, PANE_WIDTH, PANE_HEIGHT);
			
			_root.setOnMouseMoved(e -> {resize();});
			
			_center.setOnScroll(e -> scroll(e));
			
			_primaryStage.setResizable(false);
			_primaryStage.setScene(_scene);
			_primaryStage.show();
			_primaryStage.setMinHeight(PANE_HEIGHT);
			_primaryStage.setMinWidth(PANE_WIDTH);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Initialize x and y axis on graph
	 */
	private void initAxis() {
		
		drawAxis();
	
		
		Rectangle xaxis = new Rectangle();
		xaxis.setWidth(X_PIXELS);
		xaxis.setHeight(4);
		
		xaxis.setTranslateY(-((Y_PIXELS/2)+Y_SCALE*-TOP_BOUND));
		
		xaxis.setFill(Color.BLACK);
		
		
		
		Rectangle yaxis = new Rectangle();
		yaxis.setWidth(4);
		yaxis.setHeight(Y_PIXELS);
		
		yaxis.setTranslateX((X_PIXELS/2)+X_SCALE*-RIGHT_BOUND);
		
		yaxis.setFill(Color.BLACK);
		yaxis.toFront();
		
		_center.getChildren().addAll(xaxis, yaxis);
		
		
		
	}
	
	/**
	 * Initialize LineList object
	 */
	private void initLines() {
		_lines = new LineList();
		
	}
	/**
	 * Loop through line list to find all intersection points
	 */
	private void findIntersections() {
		
		for(int i = 0; i < _lines.getLineList().size() - 1; i++) {
			for(int j = i + 1; j < _lines.getLineList().size(); j++) {
				Point p = _lines.getLineList().get(i).intersection(_lines.getLineList().get(j));
				if(p != null && !_points.pointExists(p)) {
					_points.addPoint(p);
					
				}
			}
		}
		
	}
	
	/**
	 * Initalize PointList object
	 */
	private void initPoints() {
		_points = new PointList();
	}
	
	/**
	 * Update graphical display of graph
	 */
	public void drawGraph() {
		_center.getChildren().clear();
		
		initAxis();
		findIntersections();
		drawLines();
		drawPoints();
		fillListViews();
	}
	/**
	 * Draw the x and y-axis, with thin lines at equal intervals to serve as number lines
	 */
	private void drawAxis() {
	
		//System.out.println("X_SIZE:" + X_SIZE + "Y_SIZE:" + Y_SIZE + "X_SCALE:" + X_SCALE + "Y_SCALE" + Y_SCALE);
		for(int i = (int)BOTTOM_BOUND; i <= TOP_BOUND; i++) {
			Rectangle line = new Rectangle();
			line.setWidth(X_PIXELS);
			line.setHeight(1);
			line.setTranslateY(-((i * Y_SCALE) + ((Y_PIXELS/2)+Y_SCALE*-TOP_BOUND)));
			
			line.setFill(Color.LIGHTGRAY);
			line.toBack();
			_center.getChildren().add(line);
		}
		for(int i = (int)LEFT_BOUND; i <= RIGHT_BOUND; i++) {
			Rectangle line = new Rectangle();
			line.setWidth(1);
			line.setHeight(Y_PIXELS);
			line.setTranslateX((i * X_SCALE) + ((X_PIXELS/2)+X_SCALE*-RIGHT_BOUND));
			System.out.println(i);
			line.setFill(Color.LIGHTGRAY);
			line.toBack();
			_center.getChildren().add(line);
		}
	}
	/**
	 * Draw all lines from LineList on graph
	 */
	public void drawLines() {
		for(Line l: _lines.getLineList()) {
			drawLine(l);
		}
	}
	/**
	 * Draw a single Line object on graph
	 * 
	 * @param l Line object to draw
	 */
	public void drawLine(Line l) {
		
		for(double x = LEFT_BOUND; x <= RIGHT_BOUND; x += .01) {
			drawPoint(new Point(x, l.function(x), l.getSize(), l.getColor()));
		}
	}
	
	/**
	 * Draw all points from PointList on graph
	 */
	public void drawPoints() {
		for(Point p: _points.getPointList()) {
			drawPoint(p, true);
		}
	}
	/**
	 * Draw a single Point object on graph, but overloaded so label only shows on mouse over
	 * 
	 * @param p Point object to be drawn
	 */
	public void drawPoint(Point p) {
		Circle pt = new Circle();
		pt.setRadius(p.getSize());
		
		pt.setTranslateX((p.getX() * X_SCALE) + ((X_PIXELS/2)+X_SCALE*-RIGHT_BOUND));
		pt.setTranslateY(-((p.getY() * Y_SCALE) + ((Y_PIXELS/2)+Y_SCALE*-TOP_BOUND)));
		
		if(outOfBounds(p)) {
			pt.setVisible(false);
		}
		pt.setOnMouseEntered(e -> showLabel(e, p));
		pt.setOnMouseExited(e -> hideLabel());
		
		pt.setFill(p.getColor());

		_center.getChildren().add(pt);

	}
	
	/**
	 * 
	 * Draw Point object, but overloaded to draw with label showing constantly
	 *
	 * 
	 * @param p Point to be drawn
	 * @param b boolean, not used, just for overloading purposes
	 */
	private void drawPoint(Point p, boolean b) {
		if(!outOfBounds(p)) { 
		
			Circle pt = new Circle();
			pt.setRadius(p.getSize());
			
			pt.setTranslateX((p.getX() * X_SCALE) + ((X_PIXELS/2)+X_SCALE*-RIGHT_BOUND));
			pt.setTranslateY(-((p.getY() * Y_SCALE) + ((Y_PIXELS/2)+Y_SCALE*-TOP_BOUND)));
			
			pt.setFill(p.getColor());
			
			generateLabel(p);
		
		

			_center.getChildren().add(pt);

		}
	}

	/**
	 * Generate and draw a label for a point object, should read "(x,y)"
	 * @param p Point to generate label for
	 */
	private void generateLabel(Point p) {
		Label label = new Label();
		label.setText("(" + Math.round(p.getX() * 100.0) / 100.0 + ", " + Math.round(p.getY() * 100.0) / 100.0 + ")");
		label.setTranslateX((p.getX() * X_SCALE) + ((X_PIXELS/2)+X_SCALE*-RIGHT_BOUND) + 20);
		label.setTranslateY(-((p.getY() * Y_SCALE) + ((Y_PIXELS/2)+Y_SCALE*-TOP_BOUND)) + 15);
		
		label.setFont(Font.font("Helvetica", 16.0));
		
		_center.getChildren().add(label);
	}
	/**
	 * When user hovers over line, show label (x,y)
	 * 
	 * @param e MouseEvent of mouse hovering over line
	 * @param p Point to show label for mouse hover
	 */
	private void showLabel(MouseEvent e, Point p) {
		_mouseLabel.setText("(" + Math.round(p.getX() * 100.0) / 100.0 + ", " + Math.round(p.getY() * 100.0) / 100.0 + ")");
		_mouseLabel.setVisible(true);
		_mouseLabel.toFront();
	}
	/**
	 * Make label invisible
	 */
	private void hideLabel() {
		_mouseLabel.setVisible(false);
	}
	
	/**
	 * Check if Point object is within current window boundaries
	 * 
	 * @param p Point to be checked
	 * @return	true if out of bounds, false otherwise
	 */
	private boolean outOfBounds(Point p) {
		double x =((p.getX() * X_SCALE) + ((X_PIXELS/2)+X_SCALE*-RIGHT_BOUND));
		double y =(-((p.getY() * Y_SCALE) + ((Y_PIXELS/2)+Y_SCALE*-TOP_BOUND)));
		if(y > Y_PIXELS/2) {
			return true;
		}
		if(y < -Y_PIXELS/2) {
			return true;
		}
		if(x > X_PIXELS/2) {
			return true;
			
		}
		if(x < -X_PIXELS/2) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Return and remove random color from the color list
	 * If color list is empty, reset it
	 * 
	 * @return random color from list
	 */
	public Color randomColor() {
		if(_colors.size() == 0) {
			initColors();
		}
		int rand = (int)(Math.random() * _colors.size());
		return _colors.remove(rand);
	}
	
	/**
	 * On JavaFX pane size change, stretch graph to reflect changes
	 */
	private void resize() {
		X_PIXELS = _center.getWidth();
		Y_PIXELS = _center.getHeight();
		
		recalculate();
		
		drawGraph();
	}
	
	/**
	 * Change the dimensions of the graph give new bounds
	 * 
	 * @param left	Left bound
	 * @param right	Right bound
	 * @param bot	Lower bound
	 * @param top	Upper bound
	 */
	public void changeDimensions(double left, double right, double bot, double top) {
		RIGHT_BOUND = right;
		LEFT_BOUND = left;
		
		TOP_BOUND = top;
		BOTTOM_BOUND = bot;
		
		recalculate();
		
		resize();
	}
	
	/**
	 * Expand or shrink graph size on user scroll, like zooming in and out
	 * 
	 * @param e JavaFX ScrollEvent to be translated into graph size change
	 */
	private void scroll(ScrollEvent e) {
		
		
		
		double factor = e.getDeltaY();
		double newSize = exponent(factor);
		
		
		RIGHT_BOUND *= newSize;
		LEFT_BOUND *= newSize;
		BOTTOM_BOUND *= newSize;
		TOP_BOUND *= newSize;
		
		if(RIGHT_BOUND > 25) {
			RIGHT_BOUND = 25;
		}
		if(LEFT_BOUND < -25) {
			LEFT_BOUND = -25;
		}
		if(BOTTOM_BOUND < -25) {
			BOTTOM_BOUND = -25;
		}
		if(TOP_BOUND > 25) {
			TOP_BOUND = 25;
		}
		
		System.out.println(RIGHT_BOUND +" " + LEFT_BOUND+" "+ TOP_BOUND);
		
		
		recalculate();
		resize();
	}
	
	/**
	 * Used for scoll() method to exponentially change size of graph
	 * 
	 * @param in 	exponent to be used in calculation
	 * @return		result of 1.01^in
	 */
	private double exponent(double in) {
		return Math.pow(1.01, in);
	}
	
	/**
	 * Clear list of all lines and points to wipe graph
	 */
	public void clear() {
		 _lines = new LineList();
		 _points = new PointList();
		 drawGraph();
	}
	
	/**
	 * Recalculate the scale when window size changed
	 */
	private void recalculate() {
		X_SIZE = RIGHT_BOUND - LEFT_BOUND;
		Y_SIZE = TOP_BOUND - BOTTOM_BOUND;
		
		X_SCALE = X_PIXELS/(X_SIZE);
		Y_SCALE = Y_PIXELS/(Y_SIZE);
	}
	
	/**
	 * Fill JavaFX ListView objects with text describing lines and points
	 */
	private void fillListViews() {
		_controller.getLineLV().getItems().clear();
		_controller.getPointLV().getItems().clear();
		for(int i = 0; i < _lines.getLineList().size(); i++) {
			 _controller.getLineLV().getItems().add("Line " + (i+1) + _lines.getLineList().get(i).toString());
		}
		
		for(int i = 0; i < _points.getPointList().size(); i++) {
			 _controller.getPointLV().getItems().add("Point " + (i+1) + _points.getPointList().get(i).toString());
		}
	}
	
}
