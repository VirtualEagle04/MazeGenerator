package fvr.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMatrixGenerator {

	public int _cols, _rows;
	public int[][] _mazeMatrix;
	public boolean[][] _mazeVisitedCells;
	public Stack<Cell> _cellStack;
	public ArrayList<Cell> _unvisitedNeighbors;
	
	public Cell[] _cells;
	
	public MazeMatrixGenerator(int _cols, int _rows) {
		this._cols = _cols;
		this._rows = _rows;

		mazeSetup();
		//printMaze();
		pushUntilEmpty();
	}

	// DEBUG
	public void printMaze() {
		for (int[] _row : _mazeMatrix) {
			for (int _col : _row) {
				if (_col == 0) {
					System.out.print('∙' + " ");
				}
				if (_col == 1) {
					System.out.print('■' + " ");
				}
			}
			System.out.println();
		}
		System.out.println("----------------------------------------");

	}
	
	public void printMazeWithPlayer(Cell _currentCell, Cell _prevCell) {
		for (int _row = 0; _row < _mazeMatrix.length; _row++) {
			for (int _col = 0; _col < _mazeMatrix[0].length; _col++) {
				
				if(_row == _currentCell._rowPos && _col == _currentCell._colPos) {
					System.out.print("\u001B[31m");
					System.out.print('¶' + " ");
					System.out.print("\u001B[0m");
				}
				
				if(_row == _prevCell._rowPos && _col == _prevCell._colPos) {
					System.out.print("\u001B[33m");
					System.out.print('+' + " ");
					System.out.print("\u001B[0m");
				}
				
				if (_mazeMatrix[_row][_col] == 0 && !(_row == _currentCell._rowPos && _col == _currentCell._colPos) && !(_row == _prevCell._rowPos && _col == _prevCell._colPos)) {
					
					System.out.print('•' + " ");
				}
				
				if (_mazeMatrix[_row][_col] == 1) {
					
					System.out.print('■' + " ");
				}
			}
			System.out.println();
		}
		System.out.println("----------------------------------------");

	}

	public void mazeSetup() {
		_mazeMatrix = new int[_rows][_cols];
		_mazeVisitedCells = new boolean[_rows][_cols];
		
		_cellStack = new Stack<>();
		_unvisitedNeighbors = new ArrayList<>();
		
		_cells = new Cell[_rows * _cols];

		//Initialize all coordinates as walls (1) and marking them as unvisited (false)
		
		for (int _row = 0; _row < _rows; _row++) {

			for (int _col = 0; _col < _cols; _col++) {

				/**
				 * 0 = Path 1 = Wall
				 */

				_mazeMatrix[_row][_col] = 1;
				_mazeVisitedCells[_row][_col] = false;

			}

		}
		
		//Creating all cells with all walls
		for(int i = 0; i < _cells.length; i++) {
			
			_cells[i] = new Cell();
			
		}
		

		_mazeMatrix[0][0] = 0;
		_mazeVisitedCells[0][0] = true;
		_cellStack.push(new Cell(0, 0));


	}

	// Helper function to check if a cell is valid and unvisited
	public boolean validateCell(int _posRow, int _posCol) {

		if (_posRow < 0 || _posCol < 0 || _posRow >= _rows || _posCol >= _cols) {
			return false;
		}
		if(_mazeMatrix[_posRow][_posCol] == 0) {
			return false;
		}

		return _mazeVisitedCells[_posRow][_posCol] == false;

	}
	
	// Helper function to check for valid neighbor cells
	public void validateNeighbors(Cell _cellToVerify) {
		
		// Top
		if (validateCell(_cellToVerify._rowPos - 1, _cellToVerify._colPos)) {
			_unvisitedNeighbors.add(new Cell(_cellToVerify._rowPos - 1, _cellToVerify._colPos));

			System.out.println("(" + _cellToVerify._rowPos + ", " + _cellToVerify._colPos + ") Has Top Neighbor");
		}
		// Bottom
		if (validateCell(_cellToVerify._rowPos + 1, _cellToVerify._colPos)) {
			_unvisitedNeighbors.add(new Cell(_cellToVerify._rowPos + 1, _cellToVerify._colPos));
			
			System.out.println("(" + _cellToVerify._rowPos + ", " + _cellToVerify._colPos + ") Has Bottom Neighbor");
		}
		// Left
		if (validateCell(_cellToVerify._rowPos, _cellToVerify._colPos - 1)) {
			_unvisitedNeighbors.add(new Cell(_cellToVerify._rowPos, _cellToVerify._colPos - 1));
			
			System.out.println("(" + _cellToVerify._rowPos + ", " + _cellToVerify._colPos + ") Has Left Neighbor");
		}
		// Right
		if (validateCell(_cellToVerify._rowPos, _cellToVerify._colPos + 1)) {
			_unvisitedNeighbors.add(new Cell(_cellToVerify._rowPos, _cellToVerify._colPos + 1));

			System.out.println("(" + _cellToVerify._rowPos + ", " + _cellToVerify._colPos + ") Has Right Neighbor");
		}
		
	}
	
	// Function that removes the walls of the previous cell and the selected neighbor cell
	public void removeWallsByPosition(Cell _prev, int _prevIndexList, Cell _selected, int _selectedIndexList) {
		
		if(_prev._rowPos == 0 && _prev._colPos == 0) {
			_cells[_prevIndexList]._walls[0] = false;
		}
		if(_selected._rowPos == (_rows -1) && _selected._colPos == (_cols -1)) {
			_cells[_selectedIndexList]._walls[1] = false;
		}
		
		
		if(_prev._rowPos < _selected._rowPos) {
			_cells[_prevIndexList]._walls[1] = false;
			_cells[_selectedIndexList]._walls[0] = false;
			return;
		}
		if(_prev._rowPos > _selected._rowPos) {
			_cells[_prevIndexList]._walls[0] = false;
			_cells[_selectedIndexList]._walls[1] = false;
			return;
		}
		if(_prev._colPos < _selected._colPos) {
			_cells[_prevIndexList]._walls[3] = false;
			_cells[_selectedIndexList]._walls[2] = false;
			return;
		}
		if(_prev._colPos > _selected._colPos) {
			_cells[_prevIndexList]._walls[2] = false;
			_cells[_selectedIndexList]._walls[3] = false;
			return;
		}

	}

	public void pushUntilEmpty() {
		
		// While the stack is NOT empty
		while (!_cellStack.empty()) {

			Cell _currentCell = _cellStack.peek();
			
			_unvisitedNeighbors.clear();
			
			validateNeighbors(_currentCell);

			if (!(_unvisitedNeighbors.isEmpty())) {
				Random rnd = new Random();
				Cell _selectedNeighbor = _unvisitedNeighbors.get(rnd.nextInt(_unvisitedNeighbors.size()));
				
				/**
				 * 2D Index to 1D Index
				 * https://stackoverflow.com/questions/1730961/convert-a-2d-array-index-into-a-1d-index
				 */
				
				int _prevIndexList = (_currentCell._rowPos * _rows) + _currentCell._colPos;
				int _selectedIndexList = (_selectedNeighbor._rowPos * _rows) + _selectedNeighbor._colPos;
				removeWallsByPosition(_currentCell, _prevIndexList, _selectedNeighbor, _selectedIndexList);

				_mazeMatrix[_selectedNeighbor._rowPos][_selectedNeighbor._colPos] = 0;
				_mazeVisitedCells[_selectedNeighbor._rowPos][_selectedNeighbor._colPos] = true;
				_cellStack.push(new Cell(_selectedNeighbor._rowPos, _selectedNeighbor._colPos));

				printMazeWithPlayer(_selectedNeighbor, _currentCell);
			}
			else {
				popBacktrack();
			}
			
			
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {}
		}
	}
	
	public void popBacktrack() {
		
		_cellStack.pop();
		pushUntilEmpty();
		
	}

	public Cell[] get_cells() {
		return _cells;
	}

	public void set_cells(Cell[] _cells) {
		this._cells = _cells;
	}

	public int[][] get_mazeMatrix() {
		return _mazeMatrix;
	}

	public void set_mazeMatrix(int[][] _mazeMatrix) {
		this._mazeMatrix = _mazeMatrix;
	}
	
	
	
	

}
