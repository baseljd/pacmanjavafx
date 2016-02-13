package com.gluonapplication;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class Position {

	double x;
	double y;
	
	double width;
	double height;
	
	int row;
	int column;
	
	public static void initScreenDimentions(){
		
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        Constants.screenWidth = visualBounds.getWidth();
        Constants.screenHeight = visualBounds.getHeight();

        Constants.screenWidth = 600;
        Constants.screenHeight = 350;
        
	}
	
	public Position(int i, int j){
		
        
		
		this.row = i;
		this.column = j;
		
    	this.x = (Constants.screenWidth/Constants.COLUMN_CELL_COUNT)*j;
		this.y = (Constants.screenHeight/Constants.ROW_CELL_COUNT)*i;
		this.width = Constants.screenWidth/Constants.ROW_CELL_COUNT;
		this.height = Constants.screenHeight/Constants.ROW_CELL_COUNT;
		
	}
	
}
