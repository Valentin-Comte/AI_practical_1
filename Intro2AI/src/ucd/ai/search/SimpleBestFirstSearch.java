package ucd.ai.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SimpleBestFirstSearch {

	public Board board; //the starting board
	public Board goalBoard; //the goal board
	public SimpleHeuristic simpHeuristic;
	
	public SimpleBestFirstSearch() {
		super();
		board = new Board();
		generateGoalBoard();
		simpHeuristic = new SimpleHeuristic(goalBoard);
	}

	//TO DO - Complete this search 
	//Note: the parameter allows you to switch between heuristics 
	public boolean doSearch(int heuristic){
		LinkedList<Board> open = new LinkedList<Board>();
		open.add(board);
		LinkedList<Board> closed = new LinkedList<Board>();
		int levelCount = 0;
		while (!open.isEmpty()) {
			Board firstElement = open.removeFirst();
			firstElement.outputBoard();
			if(firstElement.equals(goalBoard)){
				System.out.println("SUCCESS: levelCount = " + levelCount);
				return true;
			}
			else{
				ArrayList<Board> children = firstElement.getChildren();
				for(int i=0; i<children.size(); i++){
					if(!boardInList(children.get(i), open) && !boardInList(children.get(i), closed)){
						children.get(i).setHeuristicValue(
								this.simpHeuristic.getHeuristicScore(children.get(i), heuristic));
						children.get(i).setBoardLevel(levelCount+1);
						open.add(children.get(i));
					}
				}
				closed.add(firstElement);
				Collections.sort(open);
				levelCount++;
			}
		}
		System.out.println("FAIL: the board could not be solved");
		return false;
	}
	
	public boolean boardInList(Board board, LinkedList<Board> list){
		for(int i=0; i<list.size(); i++){
			if(list.get(i).equals(board)){
				return true;
			}
		}
		return false;
	}
	
	
	public void generateGoalBoard() {
		goalBoard = new Board();
		goalBoard.addTile(new Tile(1, 0, 0));
		goalBoard.addTile(new Tile(2, 0, 1));
		goalBoard.addTile(new Tile(3, 0, 2));
		goalBoard.addTile(new Tile(8, 1, 0));
		goalBoard.addTile(new Tile(0, 1, 1));
		goalBoard.addTile(new Tile(4, 1, 2));
		goalBoard.addTile(new Tile(7, 2, 0));
		goalBoard.addTile(new Tile(6, 2, 1));
		goalBoard.addTile(new Tile(5, 2, 2));
		
	}

	public void createStartBoardEasy() {
		board.addTile(new Tile(1, 0, 0));
		board.addTile(new Tile(3, 0, 1));
		board.addTile(new Tile(4, 0, 2));
		board.addTile(new Tile(8, 1, 0));
		board.addTile(new Tile(6, 1, 1));
		board.addTile(new Tile(2, 1, 2));
		board.addTile(new Tile(7, 2, 0));
		board.addTile(new Tile(0, 2, 1));
		board.addTile(new Tile(5, 2, 2));
	}
	
	public void createStartBoardMedium() {
		board.addTile(new Tile(2, 0, 0));
		board.addTile(new Tile(8, 0, 1));
		board.addTile(new Tile(1, 0, 2));
		board.addTile(new Tile(0, 1, 0));
		board.addTile(new Tile(4, 1, 1));
		board.addTile(new Tile(3, 1, 2));
		board.addTile(new Tile(7, 2, 0));
		board.addTile(new Tile(6, 2, 1));
		board.addTile(new Tile(5, 2, 2));
	}	
	
	public void createStartBoardHard() {
		board.addTile(new Tile(2, 0, 0));
		board.addTile(new Tile(8, 0, 1));
		board.addTile(new Tile(1, 0, 2));
		board.addTile(new Tile(4, 1, 0));
		board.addTile(new Tile(6, 1, 1));
		board.addTile(new Tile(3, 1, 2));
		board.addTile(new Tile(0, 2, 0));
		board.addTile(new Tile(7, 2, 1));
		board.addTile(new Tile(5, 2, 2));
	}
	
	public void createStartBoardWorst() {
		board.addTile(new Tile(5, 0, 0));
		board.addTile(new Tile(6, 0, 1));
		board.addTile(new Tile(7, 0, 2));
		board.addTile(new Tile(4, 1, 0));
		board.addTile(new Tile(0, 1, 1));
		board.addTile(new Tile(8, 1, 2));
		board.addTile(new Tile(3, 2, 0));
		board.addTile(new Tile(2, 2, 1));
		board.addTile(new Tile(1, 2, 2));
	}
	
	
	public void createRandomStartBoard() {
		Random rand = new Random();
		ArrayList<Integer> array = new ArrayList<Integer>();
		int ran;
		int count = 0;
		while (count < 9) {
			ran = rand.nextInt(9);
			if (!array.contains(new Integer(ran))) {
				array.add(new Integer(ran));
				count++;
			}
		}
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				Tile tile = new Tile(array.get((x * 3) + y).intValue(), x, y);
				board.addTile(tile);
			}
		}
	}	



	public Board getGoalBoard(){
		return goalBoard;
	}
	
	/**
	 * checks if the given list contains the board
	 * @param open
	 * @param board
	 */
	public boolean listContainsBoard(List<Board> open, Board board) {
		for (Board b : open) {
			if (board.equals(b)) {
				return true;
			}
		}
		return false;
	}
}
