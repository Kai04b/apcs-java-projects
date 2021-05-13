package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Handles user inputs
 * 
 * @author kai
 *
 */
public class Controller {
	
	private Stage _infoStage;
	public Stage getInfoStage() {return _infoStage;}
	
	private static Controller _instance;
	public static Controller getInstance() {return _instance;}
	
	public Controller() throws IOException {
		
		_instance = this;
		
		_infoStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Info.fxml"));
    	BorderPane root = loader.load();
    	Scene scene = new Scene(root, 800, 400);
    	_infoStage.setTitle("Info");
    	_infoStage.setResizable(false);
    	_infoStage.setScene(scene);
    	
    	
    	
	}

	
	@FXML
	private StackPane _graph;
	public StackPane getGraph() {return _graph;}
	
	@FXML
	private TextField _input;
	
	/**
	 * Take user string input, convert it to a point, and add it to the point list
	 */
	@FXML
	private void createPoint() {
		
		Point p = stringToPoint();
		if(p != null) {
			Graph.getInstance().getPoints().addPoint(p);
			Graph.getInstance().drawGraph();
			
			_input.clear();
			_infoText.setText("");
		}
	}
	
	/**
	 * Take user string input, convert it to a line in standard form, and add it to the line list
	 */
	@FXML
	private void createLineStandard() {
		Line l = stringToLine();
		if(l != null) {
			Graph.getInstance().getLines().addLine(l);
			Graph.getInstance().drawGraph();
			
			_input.clear();
			_infoText.setText("");
		}
	}
	/**
	 * Take user string input, convert it to a line in slope intercept form, and add it to the line list
	 */
	@FXML
	private void createLineSlope() {
		Line l = stringToLine2();
		if(l != null) {
			Graph.getInstance().getLines().addLine(l);
			Graph.getInstance().drawGraph();
			
			_input.clear();
			_infoText.setText("");
		}
	}
	/**
	 * Take user input and remove corresponding point, either from index or (x,y) input
	 */
	@FXML
	private void removePoint() {
		if(isInteger(_input.getText())) {
			 int num = stringToInt(_input.getText()) - 1;
			 if(num < Graph.getInstance().getPoints().getPointList().size()) {
				 Graph.getInstance().getPoints().getPointList().remove(num);
				 Graph.getInstance().drawGraph();
				 _input.clear();
				_infoText.setText("");
			 }
			 else {
				 _infoText.setText("Out of bounds");
			 }
		}
		
		else {
			Point p = stringToPoint();
			if(p != null) {
				Graph.getInstance().getPoints().removePoint(p);
				Graph.getInstance().drawGraph();
				
				_input.clear();
				_infoText.setText("");
			}
		}
	}
	/**
	 * Given user input of line in standard form, remove line from linelist
	 */
	@FXML
	private void removeLineStandard() {
		if(isInteger(_input.getText())) {
			 int num = stringToInt(_input.getText()) - 1;
			 if(num < Graph.getInstance().getLines().getLineList().size()) {
				 Graph.getInstance().getLines().getLineList().remove(num);
				 Graph.getInstance().drawGraph();
				 _input.clear();
				_infoText.setText("");
			 }
			 else {
				 _infoText.setText("Out of bounds");
			 }
		}
		else {
			Line l = stringToLine();
			if(l != null) {
				Graph.getInstance().getLines().removeLine(l);
				Graph.getInstance().drawGraph();
				
				_input.clear();
				_infoText.setText("");
			}
		}
	}
	/**
	 * Given user input of line in slope intercept form, remove line from lineList
	 */
	@FXML
	private void removeLineSlope() {
		if(isInteger(_input.getText())) {
			 int num = stringToInt(_input.getText()) - 1;
			 if(num < Graph.getInstance().getLines().getLineList().size()) {
				 Graph.getInstance().getLines().getLineList().remove(num);
				 Graph.getInstance().drawGraph();
				 _input.clear();
				_infoText.setText("");
			 }
			 else {
				 _infoText.setText("Out of bounds");
			 }
		}
		else {
			Line l = stringToLine2();
			if(l != null) {
				Graph.getInstance().getLines().removeLine(l);
				Graph.getInstance().drawGraph();
				
				_input.clear();
				_infoText.setText("");
			}
		}
	}
	/**
	 * Given user input as text, replace point with user-defined point
	 */
	@FXML
	private void replacePoint() {
		String input = _input.getText();
		int colon = input.indexOf(":");
		
		String indexstr = input.substring(0, colon);
				
		_input.setText(input.substring(colon+1));
		
		int index = (int)stringToDouble(indexstr) - 1;
		
		
		
		Point p = stringToPoint();
		if(p != null && index < Graph.getInstance().getPoints().getPointList().size()) {
			Graph.getInstance().getPoints().getPointList().get(index).replace(p);
			Graph.getInstance().drawGraph();
			
			_input.clear();
			_infoText.setText("");
		}
	}
	/**
	 * Given user input as text, replace line with user-defined line in standard form
	 */
	@FXML
	private void replaceLineStandard() {
		String input = _input.getText();
		int colon = input.indexOf(":");
		
		String indexstr = input.substring(0, colon);
				
		_input.setText(input.substring(colon+1));
		
		int index = (int)stringToDouble(indexstr) - 1;
		
		
		
		Line l = stringToLine();
		if(l != null && index < Graph.getInstance().getLines().getLineList().size()) {
			Graph.getInstance().getLines().getLineList().get(index).replace(l);
			Graph.getInstance().drawGraph();
			
			_input.clear();
			_infoText.setText("");
		}
	}
	/**
	 * Given user input as text, replace line with user-defined line in slope intercept form
	 */
	@FXML
	private void replaceLineSlope() {
		String input = _input.getText();
		int colon = input.indexOf(":");
		
		String indexstr = input.substring(0, colon);
				
		_input.setText(input.substring(colon+1));
		
		int index = (int)stringToDouble(indexstr) - 1;
		
		
		
		Line l = stringToLine2();
		if(l != null && index < Graph.getInstance().getLines().getLineList().size()) {
			Graph.getInstance().getLines().getLineList().get(index).replace(l);
			Graph.getInstance().drawGraph();
			
			_input.clear();
			_infoText.setText("");
		}
	}
	
	/**
	 * Take user input and change the window dimensions of the graph
	 */
	@FXML
	private void changeDimensions() {
		String input = _input.getText();
		int comma1 = input.indexOf(",");
		int comma2 = input.lastIndexOf(",");
		
		int x = input.indexOf("x");
		
	
		String strleft = input.substring(0, comma1);
		String strright = input.substring(comma1 + 1, x);
		String strbot = input.substring(x + 1, comma2);
		String strtop = input.substring(comma2 + 1);
		
		
		if(isDouble(strleft) && isDouble(strright) && isDouble(strbot) && isDouble(strtop)) {
			
			double l = stringToDouble(strleft);
			double r = stringToDouble(strright);
			double b = stringToDouble(strbot);
			double t = stringToDouble(strtop);
			
			if(l > 0 || r < 0 || t < 0 || b > 0) {
				_infoText.setText("The origin must be inside the graph's bounds");
			}
			else {
				Graph.getInstance().changeDimensions(l, r, b, t);
				_input.clear();
			}
		}
	}
	
	/**
	 * Clear graph when clear pressed
	 */
	@FXML
	private void clear() {
		Graph.getInstance().clear();
	}
	
	@FXML
	private Text _infoText;
	
	/**
	 * Check if string can be converted to double
	 * 
	 * @param str	String to be checked
	 * @return		true if String can be double, false otherwise
	 */
	public boolean isDouble(String str) { 
		 try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    _infoText.setText("Invalid Value (Don't use spaces)");
		    return false;
		  }
		
	}
	
	/**
	 * Check if string can be converted to integer
	 * 
	 * @param str 	String to be checked
	 * @return 		true if String can be converted to integer, false otherwise
	 */
	public boolean isInteger(String str) { 
		 try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    _infoText.setText("Invalid Value (Don't use spaces)");
		    return false;
		  }
		
	}
	
	/**
	 * Exit program when quit button pressed
	 */
	@FXML
	private void quit() {
		System.exit(0);
	}
	
	/**
	 * Convert String to a Point object
	 * 
	 * @return Point object, null if String cannot be converted
	 */
	private Point stringToPoint() {
		try {
			String input = _input.getText();
			int comma = input.indexOf(",");
			
			int open = input.indexOf("(");
			int close = input.indexOf(")");
			
			
			if(close == - 1) {
				close = input.length();
			}
			if(comma == -1) {
				comma = 0;
			}
			
			String strx = input.substring(open + 1, comma);
			String stry = input.substring(comma + 1, close);
			
			System.out.println(strx +", " + stry);
			
			if(isDouble(strx) && isDouble(stry)) {
			
				double x = stringToDouble(strx);
				double y = stringToDouble(stry);
				return new Point(x, y, 4, randomColor());
			}
			return null;
		}
		catch(Exception e) {
			_infoText.setText("Invalid Value");
			return null;
		}
	}
	
	/**
	 * Convert String in standard form to a Line object
	 * 
	 * @return Line object, null if String cannot be converted
	 */
	private Line stringToLine() {
		try {
			String input = _input.getText();
			int x = input.indexOf("x");
			
			int y = input.indexOf("y");
			int equals = input.indexOf("=");
			
			
			String stra = input.substring(0, x);
			String strb = input.substring(x +1, y);
			String strc = input.substring(equals + 1);
			
			if(stra.equals("")) {
				stra = "1";
			}
			if(strb.equals("+")) {
				strb = "1";
			}
			if(strb.equals("-")) {
				strb = "-1";
			}
			
			System.out.println(stra +"." + strb + "." + strc);
			
			if(isDouble(stra) && isDouble(strb) && isDouble(strc)) {
			
				double a = stringToDouble(stra);
				double b = stringToDouble(strb);
				double c = stringToDouble(strc);
				
				return new Line(a, b, c, 1.5, randomColor());
			}
			return null;
		}
		catch(Exception e) {
			_infoText.setText("Invalid Value");
			return null;
		}
	}
	
	/**
	 * Convert String in slope intercept form to a Line object
	 * 
	 * @return Line object, null if String cannot be converted
	 */
	private Line stringToLine2() {
		try {
			String input = _input.getText();
			
			int y = input.indexOf("y");
			int equals = input.indexOf("=");
			int x = input.indexOf("x");
			int plus = input.indexOf("+");
			int minus = input.lastIndexOf("-");
			
			if(isDouble(input.substring(0, y))) {
				_infoText.setText("Do not put a coefficient before y");
			}
			else {
		
			
				String strm = input.substring(equals + 1, x);
				String strb = "";
				
				if(plus == -1 && minus == -1) {
					strb = "0";
				}
				else if(plus == -1) {
					strb = input.substring(minus);
				}
				else {
					strb = input.substring(plus);
				}
				
				if(strm.equals("")) {
					strm = "1";
				}
				
				System.out.println(strm +"." + strb);
				
				if(isDouble(strm) && isDouble(strb)) {
				
					double m = stringToDouble(strm);
					double b = stringToDouble(strb);
					
					return new Line(m, b, 1.5, randomColor());
				}
			}
			
		}
		catch(Exception e) {
			_infoText.setText("Invalid Value");
		
		}
		return null;
	}
	
	/**
	 * Convert String to a double
	 * 
	 * @return correpsonding value as a double, MAX_VALUE if cannot be converted
	 */
	private double stringToDouble(String str) {
		double foo = Integer.MAX_VALUE;
		try {
		   foo = Double.parseDouble(str);
		}
		catch (NumberFormatException e)
		{
		   
		}
		return foo;
		
	}
	/**
	 * Convert String to a integer
	 * 
	 * @return correpsonding value as an integer, MAX_VALUE if cannot be converted
	 */
	private Integer stringToInt(String str) {
		try {
		   return Integer.parseInt(str);
		}
		catch (NumberFormatException e)
		{
		   return null;
		}
		
	}
	
	/**
	 * Get a random color
	 * @return	A random JavaFX color object
	 */
	private Color randomColor() {
		return Graph.getInstance().randomColor();
	}
	
	@FXML
	private ListView _lineLV, _pointLV;
	
	public ListView getLineLV() {return _lineLV;}
	public ListView getPointLV() {return _pointLV;}
	
	/**
	 * Show infoPane when button pressed
	 * 
	 * @throws IOException
	 */
	@FXML
	public void showInfo() throws IOException {
		if(_infoStage.isShowing()) {
			_infoStage.close();
		}
		else {
			_infoStage.show();
		}
	}
	
	private static final String GRAY_BACKGROUND = "-fx-background-color: Gray";
	private static final String WHITE_BACKGROUND = "-fx-background-color: White";
	private static final String ORANGE_BACKGROUND = "-fx-background-color: LIGHTBLUE";
	
	@FXML
	private Button quitB, helpB;
	
	@FXML
	private MenuButton add, remove, edit, options;
	
	/**
	 * When buttons pressed or hovered over, change visual style to provide user feedback
	 */
	
	@FXML
	private void quitEntered() {
		quitB.setStyle(ORANGE_BACKGROUND);
	}
	@FXML
	private void quitExited() {
		quitB.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void quitReleased() {
		quitB.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void quitPressed() {
		quitB.setStyle(GRAY_BACKGROUND);
	}
	
	@FXML
	private void addEntered() {
		add.setStyle(ORANGE_BACKGROUND);
	}
	@FXML
	private void addExited() {
		add.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void addReleased() {
		add.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void addPressed() {
		add.setStyle(GRAY_BACKGROUND);
	}
	
	@FXML
	private void removeEntered() {
		remove.setStyle(ORANGE_BACKGROUND);
	}
	@FXML
	private void removeExited() {
		remove.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void removeReleased() {
		remove.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void removePressed() {
		remove.setStyle(GRAY_BACKGROUND);
	}
	@FXML
	private void editEntered() {
		edit.setStyle(ORANGE_BACKGROUND);
	}
	@FXML
	private void editExited() {
		edit.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void editReleased() {
		edit.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void editPressed() {
		edit.setStyle(GRAY_BACKGROUND);
	}
	
	@FXML
	private void optionsEntered() {
		options.setStyle(ORANGE_BACKGROUND);
	}
	@FXML
	private void optionsExited() {
		options.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void optionsReleased() {
		options.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void optionsPressed() {
		options.setStyle(GRAY_BACKGROUND);
	}
	
	@FXML
	private void helpEntered() {
		helpB.setStyle(ORANGE_BACKGROUND);
	}
	@FXML
	private void helpExited() {
		helpB.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void helpReleased() {
		helpB.setStyle(WHITE_BACKGROUND);
	}
	@FXML
	private void helpPressed() {
		helpB.setStyle(GRAY_BACKGROUND);
	}
}
