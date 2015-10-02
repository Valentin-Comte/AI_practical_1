package ucd.ai.search;

public class RunPuzzle {

	public static void main(String[] args) {
		SimpleBestFirstSearch puzzle = new SimpleBestFirstSearch();
		// puzzle.createRandomStart();
		puzzle.createStartBoardMedium();
		//change the 0 below to select different heuristic
		puzzle.doSearch(0);

	}

}
