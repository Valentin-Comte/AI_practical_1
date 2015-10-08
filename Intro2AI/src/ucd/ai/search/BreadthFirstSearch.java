package ucd.ai.search;

import java.util.LinkedList;
import java.util.ArrayList;
import ucd.ai.search.Node;


// Personal version of BreadthFirstSearch, a bit different from the pseudocode given
public class BreadthFirstSearch {

	private String goal;
	private LinkedList<ArrayList<Node>> queue;
	
	public BreadthFirstSearch(String goal, Node initialNode){
		this.queue = new LinkedList<ArrayList<Node>>();
		this.goal = goal;
		ArrayList<Node> firstPath = new ArrayList<Node>();
		firstPath.add(initialNode);
		this.queue.add(firstPath);
	}
	
	public void process(){
		System.out.println("Looking for the node: " + this.goal);
		System.out.print("Search order: "+ this.queue.get(0).get(0).getName());

		// Checking if the goal state is the start state
		if(this.queue.get(0).get(0).getName().compareTo(this.goal)==0){
			System.out.println("\nSUCCESS");
			System.out.println("The path to goal state is: "+ this.goal);
			return;
		}
		
		// Starting search
		while(this.queue.size()!=0){
			ArrayList<Node> nodeChildren = this.queue.get(0).get(this.queue.get(0).size()-1).getChildren();
			
			for(int i=0; i < nodeChildren.size(); i++){
				if(!this.queue.get(0).contains(nodeChildren.get(i))){
					ArrayList<Node> newPath = new ArrayList<Node>(this.queue.get(0));
					newPath.add(nodeChildren.get(i));
					System.out.print(" " + nodeChildren.get(i).getName());
		
					if(nodeChildren.get(i).getName().compareTo(this.goal)==0){
						System.out.println("\nSUCCESS");
						System.out.print("The path to goal state is: ");
						for(int j=0; j < newPath.size(); j++){
							System.out.print(newPath.get(j).getName() + " ");
						}
						return;
					}
					this.queue.add(newPath);
				}
			}
			
			this.queue.removeFirst();
		}
		System.out.println("FAILED: could not find the goal state");
	}
	
	public static void main(String[] args){
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");
		Node f = new Node("f");
		Node g = new Node("g");
		
		a.addChild(b);
		a.addChild(c);
		b.addChild(d);
		b.addChild(e);
		c.addChild(f);
		c.addChild(g);
		
		BreadthFirstSearch bfs = new BreadthFirstSearch("f", a);
		bfs.process();
	}
	
}
