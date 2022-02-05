import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.animation.*;
import javafx.util.*;
import java.util.*;

/**
 * Animation class that is an application with 4 shapes with different functionalities.
 * When shapes are clicked, an animation can be selected to perform.
 * JavaFX 16
 * 
 * @author Ricky Kuang
 */
public class Animation extends Application
{
	private String animationSelection;
	private ArrayList<Color> colorList;
	/**
	 * Overridden start method from Application class
	 * Creates the overall look of the application
	 */
    @Override
    public void start(Stage stage) {
    	// Create border pane, grid will go in center, animation buttons in top, left, right, and bottom.
    	BorderPane borderLayout = new BorderPane();
        GridPane grid = new GridPane();
        animationSelection = "";
        
        // Create list of colors to choose from
        colorList = new ArrayList<Color>();
        colorList.add(Color.RED);
        colorList.add(Color.BLUE);
        colorList.add(Color.GREEN);
        colorList.add(Color.PINK);
        colorList.add(Color.PURPLE);
        colorList.add(Color.YELLOW);
        
        // Create cylinder
        Cylinder cylinder = new Cylinder();
        cylinder.setRadius(75.0);
        cylinder.setHeight(150.0);
        PhongMaterial pm1 = new PhongMaterial();
        pm1.setDiffuseColor(colorList.get(new Random().nextInt(colorList.size())));
        cylinder.setMaterial(pm1);
        cylinder.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	/**
        	 * Performs the animation depending on which animation was selected.
        	 * Four different animations: rotate, scale, sequential, and change color (only when invalid input)
        	 */
        	@Override
        	public void handle(MouseEvent event) {
        		if (animationSelection.equals("Rotate Transition"))
        			rotateShape(cylinder).play();
        		else if (animationSelection.equals("Scale Transition"))
        			scaleShape(cylinder).play();
        		else if (animationSelection.equals("Sequential Transition"))
        			sequentialShape(cylinder).play();
        		else if (animationSelection.equals("") || animationSelection.equals("Fade Transition")){
        			pm1.setDiffuseColor(colorList.get(new Random().nextInt(colorList.size())));
        			cylinder.setMaterial(pm1);
        		}
        	}
        });
        
        // Create sphere
        Sphere sphere = new Sphere();
        sphere.setRadius(85.0);
        PhongMaterial pm2 = new PhongMaterial();
        pm2.setDiffuseColor(colorList.get(new Random().nextInt(colorList.size())));
        sphere.setMaterial(pm2);
        sphere.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	/**
        	 * Performs the animation depending on which animation was selected.
        	 * Four different animations: rotate, scale, sequential, and change color (only when invalid input)
        	 */
        	@Override
        	public void handle(MouseEvent event) {
        		if (animationSelection.equals("Rotate Transition"))
        			rotateShape(sphere).play();
        		else if (animationSelection.equals("Scale Transition"))
        			scaleShape(sphere).play();
        		else if (animationSelection.equals("Sequential Transition"))
        			sequentialShape(sphere).play();
        		else if (animationSelection.equals("") || animationSelection.equals("Fade Transition")){
        			pm2.setDiffuseColor(colorList.get(new Random().nextInt(colorList.size())));
        			sphere.setMaterial(pm2);
        		}
        	}
        });
        
        // Create box
        Box box = new Box();
        box.setWidth(150.0);
        box.setHeight(150.0);
        box.setDepth(150.0);
        PhongMaterial pm3 = new PhongMaterial();
        pm3.setDiffuseColor(colorList.get(new Random().nextInt(colorList.size())));
        box.setMaterial(pm3);
        box.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	/**
        	 * Performs the animation depending on which animation was selected.
        	 * Four different animations: rotate, scale, sequential, and change color (only when invalid input)
        	 */
        	@Override
        	public void handle(MouseEvent event) {
        		if (animationSelection.equals("Rotate Transition"))
        			rotateShape(box).play();
        		else if (animationSelection.equals("Scale Transition"))
        			scaleShape(box).play();
        		else if (animationSelection.equals("Sequential Transition"))
        			sequentialShape(box).play();
        		else if (animationSelection.equals("") || animationSelection.equals("Fade Transition")){
        			pm3.setDiffuseColor(colorList.get(new Random().nextInt(colorList.size())));
        			box.setMaterial(pm3);
        		}
        	}
        });
        
        // Create polygon
        Polygon hexagon = new Polygon();
        hexagon.getPoints().addAll(new Double[] {
        		100.0, 20.0, 
        		200.0, 20.0, 
        		250.0, 120.0, 
        		200.0, 220.0, 
        		100.0, 220.0, 
        		50.0, 120.0
        });
        hexagon.setFill(colorList.get(new Random().nextInt(colorList.size())));
        hexagon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	/**
        	 * Performs the animation depending on which animation was selected.
        	 * Five different animations: rotate, scale, sequential, fade, and change color (only when invalid input)
        	 */
        	@Override
        	public void handle(MouseEvent event) {
        		if (animationSelection.equals("Rotate Transition"))
        			rotateShape(hexagon).play();
        		else if (animationSelection.equals("Scale Transition"))
        			scaleShape(hexagon).play();
        		else if (animationSelection.equals("Sequential Transition"))
        			sequentialShape(hexagon).play();
        		else if (animationSelection.equals("Fade Transition"))
        			fadeShape(hexagon).play();
        		else if (animationSelection.equals("")){
        			hexagon.setFill((colorList.get(new Random().nextInt(colorList.size()))));
        		}
        	}
        });
        
        // Create button for Rotate Transition
        Button rotate = new Button("Rotate Transition");
        rotate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	/**
        	 * Changes the selection of animation to RotateTransition
        	 */
        	@Override
        	public void handle(MouseEvent event) {
        		animationSelection = rotate.getText();
        	}
        });
        
        // Create button for Scale Transition
        Button scale = new Button("Scale Transition");
        scale.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	/**
        	 * Changes animation selection to ScaleTransition
        	 */
        	@Override
        	public void handle(MouseEvent event) {
        		animationSelection = scale.getText();
        	}
        });
        
        // Create button for Sequential Transition
        Button sequential = new Button("Sequential Transition");
        sequential.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	/**
        	 * Changes animation selection to SequentialTransition
        	 */
        	@Override
        	public void handle(MouseEvent event) {
        		animationSelection = sequential.getText();
        	}
        });
        
        // Create button for Fade Transition
        Button fade = new Button("Fade Transition");
        fade.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        	/**
        	 * Changes animation selection to FadeTransition
        	 */
        	@Override
        	public void handle(MouseEvent event) {
        		animationSelection = fade.getText();
        	}
        });
        
        // add shapes to 2x2 grid
        grid.setAlignment(Pos.CENTER);
        grid.addRow(0, cylinder, sphere);
        grid.addRow(1, box, hexagon);
        grid.setGridLinesVisible(true);
        
        // add grid and buttons to border pane
        borderLayout.setCenter(grid);
        BorderPane.setAlignment(rotate, Pos.CENTER);
        borderLayout.setTop(rotate);
        BorderPane.setAlignment(scale, Pos.CENTER);
        borderLayout.setLeft(scale);
        BorderPane.setAlignment(sequential, Pos.CENTER);
        borderLayout.setRight(sequential);
        BorderPane.setAlignment(fade, Pos.CENTER);
        borderLayout.setBottom(fade);
       
        Scene scene = new Scene(borderLayout);
        stage.setScene(scene);
        
        stage.setX(500);
        stage.setY(200);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setTitle("Animation");
        stage.show();
    }
    
    /**
     * Method that implements RotateTransition for shape
     * Takes in a Node since it has to accept both Shape3D and Shape.
     * 
     * @param shape Either a Shape3D or Shape for the purposes of this application.
     * @return A RotateTransition object
     */
    public RotateTransition rotateShape(Node shape) {	
    	RotateTransition rotator = new RotateTransition();
    	rotator.setDuration(Duration.millis(2000));
    	rotator.setNode(shape);
    	rotator.setAxis(Rotate.X_AXIS);
    	rotator.setByAngle(180);
    	rotator.setCycleCount(1);
    	rotator.setAutoReverse(true);
    	return rotator;
    }
    
    /**
     * Method that implements ScaleTransition for shape
     * 
     * @param shape Either Shape3D or Shape for purposes of this application
     * @return A ScaleTransition object
     */
    public ScaleTransition scaleShape(Node shape) {
    	ScaleTransition scaler = new ScaleTransition();
    	scaler.setDuration(Duration.millis(1000));
    	scaler.setNode(shape);
    	scaler.setByY(1);
    	scaler.setByX(1);
    	scaler.setCycleCount(2);
    	scaler.setAutoReverse(true);
    	return scaler;
    }
    
    /**
     * Method that implements SequentialTransition for shape
     * 
     * @param shape Either Shape3D or Shape object for purposes of this application
     * @return A SequentialTransition object
     */
    public SequentialTransition sequentialShape(Node shape) {
    	SequentialTransition sequencer = new SequentialTransition(rotateShape(shape), scaleShape(shape));
    	return sequencer;
    }
    
    /**
     * Method that implements FadeTransition for 2D shape
     * This only works for the polygon
     * 
     * @param polyShape A Polygon object
     * @return A FadeTransition object
     */
    public FadeTransition fadeShape(Polygon polyShape) {
    	FadeTransition fader = new FadeTransition();
    	fader.setNode(polyShape);
    	fader.setDuration(Duration.seconds(1));
    	fader.setFromValue(1.0);
    	fader.setToValue(0.0);
    	fader.setCycleCount(2);
    	fader.setAutoReverse(true);
    	return fader;
    }
    
    /**
     * Main method to run the Animation application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}