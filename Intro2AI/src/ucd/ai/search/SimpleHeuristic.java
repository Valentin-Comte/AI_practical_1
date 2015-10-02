package ucd.ai.search;

public class SimpleHeuristic {

	private Board goalBoard;
	
	public SimpleHeuristic(Board goal) {
		super();
		goalBoard = goal;
	}
	
	
	/**
	 * calls one of the methods to get a heuristic score
	 * if it is passed i = 0 it calls the generateMisplacedTileHeuristic method
	 * if not it calls the generateDistanceHeuristic method
	 * @param board
	 * @param i
	 * @return
	 */
	public int getHeuristicScore(Board board, int i){
		if(i == 0)
			return this.generateMisplacedTileHeuristic(board);
		else
			return this.generateDistanceHeuristic(board);
	}
	
	
	/**
	 * this simple heuristic works by calculating how many tiles are 
	 * out of place on the board (i.e. not in the same places as in the goal board) 
	 * @param board
	 */
	public int generateMisplacedTileHeuristic(Board board) {
		int counter = 0;
		for (int x = 0; x < 3; x++) {//x loops through the x coordinates (rows) of the board
			for (int y = 0; y < 3; y++) { //y loops through the y coordinates (cols) of the board
				if (board.getTile(x, y).getValue() != goalBoard.getTile(x, y).getValue())
					counter++;
			}
		}
		return counter;
	}

	/**
	 * here you should try to come up with a better heuristic than the simple
	 * generateMisplacedTileHeuristic above.
	 * HINT: the generateMisplacedTileHeuristic only counts how many tiles are
	 * out of place but does not take into account "how much" a tile is out of place.
	 * How do we measure "how much a tile is out of place".....? how many moves are needed
	 * to get it into the correct place maybe.......
	 * @param board
	 */
	public int generateDistanceHeuristic(Board board) {
		//Complete this heuristic
		return 0;
	}

}
