package com.gluonapplication;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Constants {

	public static int ROW_CELL_COUNT = 		15;
	public static int COLUMN_CELL_COUNT = 	15;

	public static int EMPTY = 		0;
	public static int OBSTACLE =	1;
	public static int FOOD = 		2;
	public static int ENEMY = 		3;
	
	public static Color CELL_BORDR_COLOR = 	Color.BLUE;
	public static Color CELL_OBSTACLE_COLOR = 	Color.YELLOW;
	public static Color CELL_EMPTY_COLOR = 	Color.BLACK;
	public static Color CELL_FOOD_COLOR = 	Color.YELLOW;
	public static Color PACMAN_COLOR = 	Color.RED;
	public static Color GHOST1_COLOR = 	Color.GREEN;
	public static Color GAMEOVER_RECTANGLE_COLOR = 	Color.DARKGRAY;
	public static Color GAMEOVER_RECTANGLE_STROKE_COLOR = 	Color.RED;


	public static ImageView PACMAN_IMAGE;
	public static ImageView GHOST_IMAGE;
	public static ImageView BORDER_IMAGE;


	public static double screenWidth;
	public static double screenHeight;



	
}
