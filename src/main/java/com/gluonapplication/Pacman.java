package com.gluonapplication;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Pacman {

	protected static Position position;
	private static Node node;
	protected static int Score;
	
	public Pacman(){
		
		Score =0;
		Pacman.position = new Position(1, 1);
		
		Constants.PACMAN_IMAGE =  new ImageView("pacman.png");
		
	}
	
	public static boolean moveUp(){
		
		if (Grid.getCell(position.row-1, position.column).getType() == Constants.OBSTACLE)
			return false;
		
		Constants.PACMAN_IMAGE.setRotate(90+180);
		
		position = new Position(position.row-1, position.column);
		if (Grid.getCell(position.row, position.column).getType() == Constants.FOOD){
			Pacman.Score++;
			Grid.getCell(position.row, position.column).setType(Constants.EMPTY);
		}    	MapGenerator.redrawMap();
    	
    	return true;

	}
	
	public static boolean moveDown(){

		
		if (Grid.getCell(position.row+1, position.column).getType() == Constants.OBSTACLE)
			return false;
		
		Constants.PACMAN_IMAGE.setRotate(90);

		
		position = new Position(position.row+1, position.column);
		if (Grid.getCell(position.row, position.column).getType() == Constants.FOOD){
			Pacman.Score++;
			Grid.getCell(position.row, position.column).setType(Constants.EMPTY);
		}
    	MapGenerator.redrawMap();
    	
    	return true;

	}

	public static boolean moveLeft(){
		
		
		if (Grid.getCell(position.row, position.column-1).getType() == Constants.OBSTACLE)
			return false;
		
		Constants.PACMAN_IMAGE.setRotate(-180);

		
		position = new Position(position.row, position.column-1);
		if (Grid.getCell(position.row, position.column).getType() == Constants.FOOD){
			Pacman.Score++;
			Grid.getCell(position.row, position.column).setType(Constants.EMPTY);
		}    	MapGenerator.redrawMap();

    	return true;

    	
	}
	
	public static boolean moveRight(){
		
		if (Grid.getCell(position.row, position.column+1).getType() == Constants.OBSTACLE)
			return false;
		
		Constants.PACMAN_IMAGE.setRotate(0);
		
		position = new Position(position.row, position.column+1);
		if (Grid.getCell(position.row, position.column).getType() == Constants.FOOD){
			Pacman.Score++;
			Grid.getCell(position.row, position.column).setType(Constants.EMPTY);
		}    	MapGenerator.redrawMap();

    	return true;

	}
	
	public Node getNode(){

			
			double min = position.height;
			if (position.width < position.height)
				min = position.width;
			
			Constants.PACMAN_IMAGE.setFitWidth(min);
			Constants.PACMAN_IMAGE.setFitHeight(min);

			Constants.PACMAN_IMAGE.setX(position.x+position.width/2 - min/2);
			Constants.PACMAN_IMAGE.setY(position.y+position.height/2 - min/2);
			
			node= Constants.PACMAN_IMAGE;
			
		return node;
		
	}
	


	
}
