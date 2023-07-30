package fvr.model;

import java.util.*;

public class DepthFirstSearch {
	
	public int rows, cols;
	public Cell[][] grid;
	public Stack<Cell> stack;
	public Random rnd;

	public DepthFirstSearch(int rows, int cols) {
		super();
		this.rnd = new Random();
		this.stack = new Stack<>();
		this.rows = rows;
		this.cols = cols;
		
		setup();
		generatePath();
	}
	
	public void setup() {
		//Setting maze size
		grid = new Cell[rows][cols];
		
		//Initializing cells
		for (int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				grid[row][col] = new Cell(row, col);
			}
		}
		
		//Setting starter cell
		grid[0][0].setVisited(true);
		stack.push(grid[0][0]);
		
		//Setting start and end cells walls
		grid[0][0].walls[0] = false;
		grid[rows - 1][cols - 1].walls[1] = false;
		
	}
	
	public void generatePath() {
		
		while(!stack.isEmpty()) {
			
			Cell currentCell = stack.peek();
			ArrayList<Cell> unvisitedNeighbors = findNeighbors(currentCell);
			
			if(!unvisitedNeighbors.isEmpty()) {

				Cell selectedCell = unvisitedNeighbors.get(rnd.nextInt(unvisitedNeighbors.size()));
				removeWalls(currentCell, selectedCell);
				grid[selectedCell.posRow][selectedCell.posCol].setVisited(true);
				stack.push(selectedCell);
			}
			else {
				//Recursive Backtracking
				stack.pop();
			}
			
		}
		//End of the generation
		System.err.println("End of generation. Drawing...");
		
	}

	public ArrayList<Cell> findNeighbors(Cell currentCell) {
		
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<>();
		
		//Top
		if(validateCoords(currentCell.posRow - 1, currentCell.posCol)) {
			unvisitedNeighbors.add(new Cell(currentCell.posRow - 1, currentCell.posCol));
		}
		//Bottom
		if(validateCoords(currentCell.posRow + 1, currentCell.posCol)) {
			unvisitedNeighbors.add(new Cell(currentCell.posRow + 1, currentCell.posCol));
		}
		//Left
		if(validateCoords(currentCell.posRow, currentCell.posCol - 1)) {
			unvisitedNeighbors.add(new Cell(currentCell.posRow, currentCell.posCol - 1));
		}
		//Right
		if(validateCoords(currentCell.posRow, currentCell.posCol + 1)) {
			unvisitedNeighbors.add(new Cell(currentCell.posRow, currentCell.posCol + 1));
		}
		
		return unvisitedNeighbors;
		
	}
	
	public boolean validateCoords(int posRow, int posCol ) {
		
		//Checks if the coordinates exist in grid boundaries
		if(posRow < 0 || posRow >= rows || posCol < 0 || posCol >= cols) {
			return false;
		}
		//Checks if the cell is already visited
		if(grid[posRow][posCol].isVisited()) {
			return false;
		}
		
		return true;
		
	}
	
	public void removeWalls(Cell prevCell, Cell selectedCell) {
		
		//Down
		if(prevCell.posRow < selectedCell.posRow) {
			grid[prevCell.posRow][prevCell.posCol].walls[1] = false;
			grid[selectedCell.posRow][selectedCell.posCol].walls[0] = false;
		}
		//Up
		if(prevCell.posRow > selectedCell.posRow) {
			grid[prevCell.posRow][prevCell.posCol].walls[0] = false;
			grid[selectedCell.posRow][selectedCell.posCol].walls[1] = false;
		}
		//Left
		if(prevCell.posCol > selectedCell.posCol) {
			grid[prevCell.posRow][prevCell.posCol].walls[2] = false;
			grid[selectedCell.posRow][selectedCell.posCol].walls[3] = false;
		}
		//Right
		if(prevCell.posCol < selectedCell.posCol) {
			grid[prevCell.posRow][prevCell.posCol].walls[3] = false;
			grid[selectedCell.posRow][selectedCell.posCol].walls[2] = false;
		}
		
		
	}

	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}
	

}
