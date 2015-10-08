package ucd.ai.search;

import java.util.ArrayList;
import java.util.List;

public class Board implements Comparable<Board>, Cloneable {

	private List<Tile> tiles; //a list of the 9 tiles on the board
	private int heuristicValue; //stores the heuristic score (how close it is to the goal board) for a board
	private int boardLevel; //stores the depth on the tree that the board was located
	
	
	public Board() {
		super();
		tiles = new ArrayList<Tile>();
		heuristicValue = 0;
		boardLevel = 0;
	}
	
	public int getBoardLevel() {
		return boardLevel;
	}
	
	public void setBoardLevel(int boardLevel) {
		this.boardLevel = boardLevel;
	}

	public int getHeuristicValue() {
		return heuristicValue;
	}

	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}

	public void addTile(Tile tile){
		if(tiles.size() <= 9)
			tiles.add(tile);
		else
			System.out.println("Too many tiles added");
	}
	
	/**
	 * gets the x and y coordinates on the board of the tile with the given value
	 * @param value
	 */
	public Tile getTile(int value){
		for(Tile tile : tiles){
			if(tile.getValue() == value)
				return tile;
		}
		return null;
	}
	
	/**
	 * gets the value of a tile given its x and y coordinates on the board
	 */
	public Tile getTile(int x, int y){
		for(Tile tile : tiles){
			if(tile.getX() ==x && tile.getY() == y)
				return tile;
		}
		return null;
	}
	
	
	/**
	 * gets the x coordinate of the blank (space) tile
	 */
	public int getBlankX(){
		for(Tile tile : tiles){
			if(tile.getValue() == 0)
				return tile.getX();
		}
		return -1;
	}
	
	/**
	 * gets the y coordinate of the blank (space) tile
	 */
	
	public int getBlankY(){
		for(Tile tile : tiles){
			if(tile.getValue() == 0)
				return tile.getY();
		}
		return -1;
	}

	/**
	 * the compareTo method is used for sorting.
	 * this method returns either -1, 0, or 1.
	 * if this board has a better (lower) heuristic score it returns -1.
	 * if this board has a worse heuristic score it returns 1
	 * if the 2 heuristic scores are equal it returns 0
	 */
	public int compareTo(Board o) {
		if(this.getHeuristicValue() < o.getHeuristicValue())
			return -1;
		else if(this.getHeuristicValue() > o.getHeuristicValue())
			return 1;
		else if(this.getHeuristicValue() == o.getHeuristicValue()){
			if(this.getBoardLevel() < o.getBoardLevel())
				return -1;
			else if(this.getBoardLevel() > o.getBoardLevel())
				return 1;
		}
		return 0;
	}
	
	/**
	 * determines if two boards are equals by checking the value of the tile at each position
	 * @param b
	 * @return
	 */
	public boolean equals(Board b){
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				if(this.getTile(x,y).getValue() != b.getTile(x,y).getValue())
					return false;
			}
		}
		return true;
	}
	
	/**
	 * gets all possible children of the board
	 * each child board corresponds to the board with one possible move made
	 * if n tiles "can move" (are adjacent but not diagonal to the blank tile) then there
	 * will be n child boards generated
	 */
	public ArrayList<Board> getChildren(){
		ArrayList<Board> children = new ArrayList<Board>(); 
		int x = this.getBlankX();
		int y = this.getBlankY();
		for(Tile tile: tiles){
			if(tile.canMove(x,y)){ //if a tile can move then a new child board can be generated
				Board newB = this.clone();
				newB.getTile(x, y).setValue(tile.getValue());
				newB.getTile(tile.getX(), tile.getY()).setValue(0);
				children.add(newB);
				//System.out.println("CHILD");
				//newB.outputBoard();
			} 
		}
		return children;
	}
	
	
	/**
	 * clones a board
	 */
	public Board clone() {
		
		Board newB = new Board();
		newB.setBoardLevel(this.getBoardLevel());
		newB.setHeuristicValue(this.getHeuristicValue());
		List<Tile> newTiles = new ArrayList<Tile>();
		for (Tile t: this.getTiles()) {
			newTiles.add(t.clone());
		}
		newB.setTiles(newTiles);
		return newB;
	}
	
	
	/**
	 * prints out the tiles of the board
	 *
	 */
	public void outputBoard(){
		System.out.println("H="+this.getHeuristicValue()+" L="+this.getBoardLevel());
		System.out.println("- - -");
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				System.out.print(this.getTile(x,y).getValue()+"|");
			}
			System.out.println();
			System.out.println("- - -");
		}
		System.out.println();
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
	
	public int[] findTileCoordinates(int value){
		int coordinates[] = {-1,-1};
		for (int x = 0; x < 3; x++) {//x loops through the x coordinates of the board
			for (int y = 0; y < 3; y++) { //y loops through the y coordinates of the board
				if (this.getTile(x, y).getValue() == value){
					coordinates[0] = x;
					coordinates[1] = y;
					return coordinates;
				}
			}
		}
		return coordinates;
	}
}
