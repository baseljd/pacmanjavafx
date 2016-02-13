package com.gluonapplication;


public class Grid {
	
	protected static Cell [] [] grid;
	
	public Grid(){
		
		grid = new Cell [Constants.ROW_CELL_COUNT] [Constants.COLUMN_CELL_COUNT];
		
	}
	
	public static void addCell(Cell cell){
		
		grid [cell.position.row][cell.position.column] = cell;
		
	}
	
	public static Cell getCell(int row, int column){
		
		return grid [row][column];
		
	}
	
	
	

	
}
