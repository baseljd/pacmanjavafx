package com.gluonapplication;

import java.util.ArrayList;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MapGenerator {

	protected static Pane root;
	private static Grid grid;
	static Timeline timeLine;
	static Scene scene;
	static Stage stage;
	
	public static Stage generateMap (Stage stage){
		
        MapGenerator.stage = stage;
		root = new Pane();    	
		root.setStyle("-fx-background-color: black");
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        scene = new Scene(root, Constants.screenWidth, Constants.screenHeight);
        
		Constants.BORDER_IMAGE =  new ImageView("border.jpg");

        
        //Create obstacles
        initObstacles();
        
        //Create Grid
        grid = new Grid();
        
        for (int i =0;i< Constants.COLUMN_CELL_COUNT;i++){
        	
            for (int j =0;j< Constants.ROW_CELL_COUNT;j++){
            	
            	Position position = new Position(i,j);
            	//Random random = new Random();
            	int type = Constants.OBSTACLE;
            	
            	//Check if not boundary
            	if ( i != Constants.COLUMN_CELL_COUNT-1 && j != Constants.ROW_CELL_COUNT-1 && i != 0 && j!= 0){
            		if (i == 1 && j ==1)
            			type = Constants.EMPTY;
            		else if (isObstacle(position))
            			type = Constants.OBSTACLE;
            		else
            			type = Constants.FOOD;
            	}

            		//type = random.nextInt(2 - 0 + 1) + 0;
            	Cell cell = new Cell(position,type);
            	grid.addCell(cell);
            	
                root.getChildren().add(cell.getNode());
            }
        	
        }
        
        
        stage.getIcons().add(new Image(GluonApplication.class.getResourceAsStream("/icon.png")));

        stage.setScene(scene);
        
        
        return stage;
		
	}
	
	public static void redrawMap(){
		
	
		Position.initScreenDimentions();

		
		root.getChildren().clear();
		
		for (int i = 0;i < Constants.ROW_CELL_COUNT;i++)
			for (int j = 0;j < Constants.COLUMN_CELL_COUNT;j++)
                root.getChildren().add(grid.getCell(i, j).getNode());
	
		
	root.getChildren().add(GamePlay.pacman.getNode());

	root.getChildren().add(GamePlay.ghost1.getNode());

	
	Position scorePosition = new Position(1, Constants.COLUMN_CELL_COUNT-2);
	Text text = new Text(scorePosition.x-30,scorePosition.y-10,"SCORE : "+Pacman.Score);
	text.setFill(Constants.PACMAN_COLOR);
	text.setFont(Font.font("IMPACT", Constants.screenWidth/35));
	
	root.getChildren().add(text);

	
	root.requestFocus();
	
	}
	
	
	
	private static boolean isObstacle(Position position){
		
		for (int i =0;i< obstacles.size();i++){
			Position tmpPosition = obstacles.get(i);			
			if (position.row == tmpPosition.row && position.column == tmpPosition.column)
				return true;
		}
		
		return false;
		
	}
	
	private static ArrayList<Position> obstacles = new ArrayList<>();
	
	private static void initObstacles(){
		
		//Generate Left Obstacles
		obstacles.add(new Position(2, 2));
		obstacles.add(new Position(1, 4));
		obstacles.add(new Position(2, 4));
		obstacles.add(new Position(3, 4));
		obstacles.add(new Position(4, 4));

		obstacles.add(new Position(4, 2));
		obstacles.add(new Position(5, 2));
		obstacles.add(new Position(6, 2));

		obstacles.add(new Position(6, 3));

		obstacles.add(new Position(13, 4));
		obstacles.add(new Position(12, 4));
		obstacles.add(new Position(11, 4));
		obstacles.add(new Position(10, 4));
		
		obstacles.add(new Position(12, 2));
		obstacles.add(new Position(8, 2));
		obstacles.add(new Position(9, 2));
		obstacles.add(new Position(11, 2));

		obstacles.add(new Position(3, 6));

		
		//Generate Reflection
		int loopSize = obstacles.size();
		for (int i =0;i< loopSize;i++){
			
			Position tmpPosition = obstacles.get(i);
			Position newPosition = new Position(tmpPosition.row, Constants.COLUMN_CELL_COUNT-1-tmpPosition.column);
			obstacles.add(newPosition);
			
		}
		
		//Generate Center Obstacles
		obstacles.add(new Position(6, 6));
		obstacles.add(new Position(7, 6));
		obstacles.add(new Position(8, 6));
		obstacles.add(new Position(8, 7));
		obstacles.add(new Position(8, 8));

		obstacles.add(new Position(7, 8));
		obstacles.add(new Position(6, 8));

		obstacles.add(new Position(10, 7));
		obstacles.add(new Position(11, 7));
		obstacles.add(new Position(12, 7));

		obstacles.add(new Position(2, 7));
		obstacles.add(new Position(3, 7));
		obstacles.add(new Position(4, 7));


		
	}
	
	public static void addClickListener(){
		
		MapGenerator.root.setOnKeyPressed(event -> {
    		
    		if (event.getCode() == KeyCode.UP)
    			Pacman.moveUp();    			
    		else if (event.getCode() == KeyCode.DOWN)
    			Pacman.moveDown();
    		else if (event.getCode() == KeyCode.LEFT)
    			Pacman.moveLeft();
    		else if (event.getCode() == KeyCode.RIGHT)
    			Pacman.moveRight();
    		
    	});
		
	}
	
	
	public static void invalidateClickListener(){
		
		MapGenerator.root.setOnKeyPressed(event -> {
    		
			if (event.getCode() == KeyCode.SPACE)
			{
				replay();
			}
    		
    	});
		
	}
	
	public static void replay(){
		
		//Generate Map
    	MapGenerator.generateMap(stage);
		
		Pacman.Score = 0;
		//Add CLick istener
    	MapGenerator.addClickListener();
    	
    	//Create Pacman
    	GamePlay.pacman = new Pacman();
    	
    	//Create Ghost
    	Ghost ghost = new Ghost();

    	//Redraw Map   	
    	MapGenerator.redrawMap();
    	
    	//Start Timeline
    	MapGenerator.startTimeline();
		
	}
	
	public static void gameEnded(){
		
	invalidateClickListener();
	timeLine.stop();	
		
	Text text = new Text(" GAME OVER! \n YOUR SCORE \n\t    "+ Pacman.Score);
	text.setFill(Constants.PACMAN_COLOR);
	text.setFont(Font.font("IMPACT", Constants.screenWidth/20));
	text.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(Constants.screenWidth/8.7));
	text.layoutYProperty().bind(scene.heightProperty().divide(2).subtract(Constants.screenHeight/9.7));
	
	Text replayText = new Text("Press Space to Replay");
	replayText.setFill(Constants.PACMAN_COLOR);
	replayText.setFont(Font.font("IMPACT", Constants.screenWidth/40));
	replayText.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(Constants.screenWidth/8.7));
	replayText.layoutYProperty().bind(scene.heightProperty().divide(1.2));

	Rectangle rectangle = new Rectangle(Constants.screenWidth/3, Constants.screenHeight/2.5);
	rectangle.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(rectangle.getWidth()/2));
	rectangle.layoutYProperty().bind(scene.heightProperty().divide(2).subtract(rectangle.getHeight()/2));
	rectangle.setFill(Constants.GAMEOVER_RECTANGLE_COLOR);
	rectangle.setStroke(Constants.GAMEOVER_RECTANGLE_STROKE_COLOR);
	root.getChildren().add(rectangle);
	root.getChildren().add(text);
	root.getChildren().add(replayText);
			
	}
	
	public static void startTimeline(){
    	
    	timeLine = new Timeline(new KeyFrame(Duration.millis(250), event -> {
        	GamePlay.ghost1.moveGhost();
    			}));
    	timeLine.setCycleCount(Timeline.INDEFINITE);
    	timeLine.play();
    	
    }
	
	
}
