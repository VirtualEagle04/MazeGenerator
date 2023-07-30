package fvr.model;

public class Cell {
	
	public int posCol, posRow;
	public boolean visited = false;
	public boolean[] walls = {true, true, true, true}; //top, down, left, right
	
	public Cell() {}
	
	//ONLY POSITION
	public Cell(int posRow, int posCol) {
		super();
		this.posCol = posCol;
		this.posRow = posRow;
	}
	
	//POSITION & VISITED
	public Cell(int posRow, int posCol, boolean visited) {
		super();
		this.posCol = posCol;
		this.posRow = posRow;
		this.visited = visited;
	}

	public int getPosCol() {
		return posCol;
	}

	public void setPosCol(int posCol) {
		this.posCol = posCol;
	}

	public int getPosRow() {
		return posRow;
	}

	public void setPosRow(int posRow) {
		this.posRow = posRow;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean[] getWalls() {
		return walls;
	}

	public void setWalls(boolean[] walls) {
		this.walls = walls;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(" + posRow + ", " + posCol + ")");
		
		return sb.toString();
	}
	
	
	
	
}
