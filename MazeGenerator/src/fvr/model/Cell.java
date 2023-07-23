package fvr.model;

public class Cell {

	public int _rowPos, _colPos;
	public boolean[] _walls = {true, true, true, true}; //Top, Bottom, Left, Right

	public Cell() {
	}

	public Cell(int _rowPos, int _colPos) {
		super();
		this._rowPos = _rowPos;
		this._colPos = _colPos;
	}

	public int get_rowPos() {
		return _rowPos;
	}

	public void set_rowPos(int _rowPos) {
		this._rowPos = _rowPos;
	}

	public int get_colPos() {
		return _colPos;
	}

	public void set_colPos(int _colPos) {
		this._colPos = _colPos;
	}

	public boolean[] get_walls() {
		return _walls;
	}

	public void set_walls(boolean[] _walls) {
		this._walls = _walls;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(" + get_rowPos() + ", " + get_colPos() + ")\n");

		return sb.toString();
	}

}
