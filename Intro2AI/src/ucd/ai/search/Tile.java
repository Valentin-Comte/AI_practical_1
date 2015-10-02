package ucd.ai.search;

public class Tile implements Cloneable {

	private int value; //the value (number) on the face of the tile
	private int x; //the x coordinate of the tile on the board
	private int y;//the y coordinate of the tile on the board

	public Tile() {
		
		super();
	}
	
	public Tile(int value, int x, int y) {
		super();
		this.value = value;
		this.x = x;
		this.y = y;
	}

	/**
	 * checks if a tile is able to move.
	 * a tile can move if it is adjacent (but not diagonal) to the blank tile
	 * @param blankX
	 * @param blankY
	 * @return
	 */
	public boolean canMove(int blankX, int blankY) {
		int xMove = Math.abs(this.getX() - blankX);
		int yMove = Math.abs(this.getY() - blankY);
		if ((xMove + yMove) == 1)
			return true;
		else
			return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int xVal) {
		x = xVal;
	}

	public int getY() {
		return y;
	}

	public void setY(int yVal) {
		y = yVal;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int v) {
		value = v;
	}
	
	public Tile clone () {
		
		Tile newT = new Tile();
		newT.setX(this.getX());
		newT.setY(this.getY());
		newT.setValue(this.getValue());
		return newT;
	}

}
