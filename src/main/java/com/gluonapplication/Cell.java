package com.gluonapplication;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Cell {

	private int type;
	protected Position position;
	private Node node;
	
	public Cell(Position position,int type){
		
		this.position = position;
		this.type = type;

	}
	
	public void setType(int type){
		
		this.type = type;
		
	}
	
	public Node getNode(){
		
		if (type == Constants.FOOD){
			this.node = new Circle(position.x+position.width/2,position.y+position.height/2,position.width/8);
			((Circle)node).setFill(Constants.CELL_FOOD_COLOR);
		}
		else if (type == Constants.OBSTACLE){
			
			Constants.BORDER_IMAGE =  new ImageView("border.jpg");
			
			Constants.BORDER_IMAGE.setFitWidth(position.width);
			Constants.BORDER_IMAGE.setFitHeight(position.height);

			Constants.BORDER_IMAGE.setX(position.x+position.width/2 - position.width/2);
			Constants.BORDER_IMAGE.setY(position.y+position.height/2 - position.height/2);
			
			this.node= Constants.BORDER_IMAGE;
						
			//this.node = new Rectangle(position.x,position.y,position.width,position.height);
			//((Rectangle)node).setFill(Constants.CELL_BORDR_COLOR);
		}
		else if (type == Constants.EMPTY){
			this.node = new Rectangle(position.x,position.y,position.width,position.height);
			((Rectangle)node).setFill(Constants.CELL_EMPTY_COLOR);
		}
		
		return node;
		
	}

	public int getType() {
		return type;
	}
	
}
