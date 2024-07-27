package fvr.model;

import java.util.*;

public class HuntKill {

	public int rows, cols;
	public Cell[][] grid;
	public Stack<Cell> stack;
	public Random rnd;

	public HuntKill(int rows, int cols) {
		super();
		this.rnd = new Random();
		this.stack = new Stack<>();
		this.rows = rows;
		this.cols = cols;

		setup();
		generatePath();

	}

	public void setup() {
		// Setting maze size
		grid = new Cell[rows][cols];

		// Initializing cells
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				grid[row][col] = new Cell(row, col);
			}
		}

		// Randomly setting starter cell
		int rndRow = rnd.nextInt(rows);
		int rndCol = rnd.nextInt(cols);

		grid[rndRow][rndCol].setVisited(true);
		stack.push(grid[rndRow][rndCol]);

		// Setting maze start and maze end cells walls. Different from the maze gen
		// start cell.
		grid[0][0].walls[0] = false;
		grid[rows - 1][cols - 1].walls[1] = false;

	}

	public void generatePath() {

		while (!stack.isEmpty()) {

			Cell currentCell = stack.pop();
			ArrayList<Cell> unvisitedNeighbors = findNeighbors(currentCell);

			if (!unvisitedNeighbors.isEmpty()) {
				Cell selectedCell = unvisitedNeighbors.get(rnd.nextInt(unvisitedNeighbors.size()));
				removeWalls(currentCell, selectedCell);
				grid[selectedCell.posRow][selectedCell.posCol].setVisited(true);
				stack.push(selectedCell);
			} else {
				// Hunt
				hunt();
			}

		}

		// End of the generation
		System.err.println("End of generation. Drawing...");

	}

	public void hunt() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				Cell huntCell = grid[row][col];
				if (!huntCell.isVisited()) {
					ArrayList<Cell> visitedNeighbors = findVisitedNeighbors(huntCell);

					if (!visitedNeighbors.isEmpty()) {
						removeWalls(huntCell, visitedNeighbors.get(0));
						grid[huntCell.posRow][huntCell.posCol].setVisited(true);
						stack.push(huntCell);
						return;
					}
				}
			}
		}
	}

	public ArrayList<Cell> findNeighbors(Cell currentCell) {

		ArrayList<Cell> unvisitedNeighbors = new ArrayList<>();

		// Top
		if (validateCoords(currentCell.posRow - 1, currentCell.posCol)) {
			unvisitedNeighbors.add(new Cell(currentCell.posRow - 1, currentCell.posCol));
		}
		// Bottom
		if (validateCoords(currentCell.posRow + 1, currentCell.posCol)) {
			unvisitedNeighbors.add(new Cell(currentCell.posRow + 1, currentCell.posCol));
		}
		// Left
		if (validateCoords(currentCell.posRow, currentCell.posCol - 1)) {
			unvisitedNeighbors.add(new Cell(currentCell.posRow, currentCell.posCol - 1));
		}
		// Right
		if (validateCoords(currentCell.posRow, currentCell.posCol + 1)) {
			unvisitedNeighbors.add(new Cell(currentCell.posRow, currentCell.posCol + 1));
		}

		return unvisitedNeighbors;

	}

	public ArrayList<Cell> findVisitedNeighbors(Cell currentCell) {
		ArrayList<Cell> visitedNeighbors = new ArrayList<>();

		// Top
		if (validateCoordsVisited(currentCell.posRow - 1, currentCell.posCol)) {
			visitedNeighbors.add(new Cell(currentCell.posRow - 1, currentCell.posCol));
		}
		// Bottom
		if (validateCoordsVisited(currentCell.posRow + 1, currentCell.posCol)) {
			visitedNeighbors.add(new Cell(currentCell.posRow + 1, currentCell.posCol));
		}
		// Left
		if (validateCoordsVisited(currentCell.posRow, currentCell.posCol - 1)) {
			visitedNeighbors.add(new Cell(currentCell.posRow, currentCell.posCol - 1));
		}
		// Right
		if (validateCoordsVisited(currentCell.posRow, currentCell.posCol + 1)) {
			visitedNeighbors.add(new Cell(currentCell.posRow, currentCell.posCol + 1));
		}

		return visitedNeighbors;
	}

	public boolean validateCoords(int posRow, int posCol) {

		// Checks if the coordinates exist in grid boundaries
		if (posRow < 0 || posRow >= rows || posCol < 0 || posCol >= cols) {
			return false;
		}
		// Checks if the cell is already visited
		if (grid[posRow][posCol].isVisited()) {
			return false;
		}

		return true;

	}

	public boolean validateCoordsVisited(int posRow, int posCol) {
		// Checks if the coordinates exist in grid boundaries
		if (posRow < 0 || posRow >= rows || posCol < 0 || posCol >= cols) {
			return false;
		}
		// Checks if the cell is already visited
		if (grid[posRow][posCol].isVisited()) {
			return true;
		}

		return false;

	}

	public void removeWalls(Cell prevCell, Cell selectedCell) {

		// Down
		if (prevCell.posRow < selectedCell.posRow) {
			grid[prevCell.posRow][prevCell.posCol].walls[1] = false;
			grid[selectedCell.posRow][selectedCell.posCol].walls[0] = false;
		}
		// Up
		if (prevCell.posRow > selectedCell.posRow) {
			grid[prevCell.posRow][prevCell.posCol].walls[0] = false;
			grid[selectedCell.posRow][selectedCell.posCol].walls[1] = false;
		}
		// Left
		if (prevCell.posCol > selectedCell.posCol) {
			grid[prevCell.posRow][prevCell.posCol].walls[2] = false;
			grid[selectedCell.posRow][selectedCell.posCol].walls[3] = false;
		}
		// Right
		if (prevCell.posCol < selectedCell.posCol) {
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
