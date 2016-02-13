package com.gluonapplication;

import java.util.Random;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Ghost {

	protected Position position;
	private Node node;
	
	public Ghost(){
		
		this.position = new Position(Constants.ROW_CELL_COUNT/2, Constants.COLUMN_CELL_COUNT/2);
		
		Constants.GHOST_IMAGE =  new ImageView("ghost.png");

		
	}
	
	public void moveGhost(){
		
		Random rand = new Random();
	    int randomNum = rand.nextInt((3 - 0) + 1) + 0;
		
	    if (Pacman.position.row > this.position.row){
	    	
	    	 if (Pacman.position.column > this.position.column){
	 	    	
	    		 randomNum = rand.nextInt((1 - 0) + 1) + 0;
	    		 
	    		 if (randomNum == 0)
	    			 moveDown();
	    		 else
	    			 moveRight();
	 	    	
	 	    }
	    	 
	    	 if (Pacman.position.column <= this.position.column){
		 	    	
	    		 randomNum = rand.nextInt((1 - 0) + 1) + 0;
	    		 
	    		 if (randomNum == 0)
	    			 moveDown();
	    		 else
	    			 moveLeft();
	 	    	
	 	    }
	    	
	    }else if (Pacman.position.row <= this.position.row){
	    	
	    	 if (Pacman.position.column >= this.position.column){
	 	    	
	    		 randomNum = rand.nextInt((1 - 0) + 1) + 0;
	    		 
	    		 if (randomNum == 0)
	    			 moveUp();
	    		 else
	    			 moveRight();
	 	    	
	 	    }
	    	 
	    	 if (Pacman.position.column < this.position.column){
		 	    	
	    		 randomNum = rand.nextInt((1 - 0) + 1) + 0;
	    		 
	    		 if (randomNum == 0)
	    			 moveUp();
	    		 else
	    			 moveLeft();
	 	    	
	 	    }
	    	
	    }
	    
	    if (position.row == Pacman.position.row && position.column == Pacman.position.column ){
	    	MapGenerator.gameEnded();		
		}else{
    		MapGenerator.redrawMap();
		}
	    
	    
	}
	
	public  boolean moveUp(){
		
		if (Grid.getCell(position.row-1, position.column).getType() == Constants.OBSTACLE)
			return false;
		
		position = new Position(position.row-1, position.column);
		MapGenerator.redrawMap();
    	
    	return true;

	}
	
	public  boolean moveDown(){

		if (Grid.getCell(position.row+1, position.column).getType() == Constants.OBSTACLE)
			return false;
		
		position = new Position(position.row+1, position.column);
		
    	MapGenerator.redrawMap();
    	
    	return true;

	}

	public  boolean moveLeft(){
		
		if (Grid.getCell(position.row, position.column-1).getType() == Constants.OBSTACLE)
			return false;
		
		position = new Position(position.row, position.column-1);
		   	MapGenerator.redrawMap();

    	return true;

    	
	}
	
	public  boolean moveRight(){
		
		if (Grid.getCell(position.row, position.column+1).getType() == Constants.OBSTACLE)
			return false;
		
		position = new Position(position.row, position.column+1);
		MapGenerator.redrawMap();

    	return true;

	}
	
	public  Node getNode(){
		
			//ode = new Circle(position.x+position.width/2,position.y+position.height/2,position.width/5);
			//((Circle)node).setFill(Constants.GHOST1_COLOR);
		
		double min = position.height;
		if (position.width < position.height)
			min = position.width;
		
		Constants.GHOST_IMAGE.setFitWidth(min);
		Constants.GHOST_IMAGE.setFitHeight(min);

		Constants.GHOST_IMAGE.setX(position.x+position.width/2 - min/2);
		Constants.GHOST_IMAGE.setY(position.y+position.height/2 - min/2);
		
		node= Constants.GHOST_IMAGE;
		
		return node;
		
	}
	
}
