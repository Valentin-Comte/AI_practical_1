package ucd.ai.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch {
	private String goal;
	private Stack<Node> queue;
	
	public DepthFirstSearch(String goal, Node initialNode){
		this.queue = new Stack<Node>();
		this.goal = goal;
		this.queue.push(initialNode);
	}
	
	public boolean expandChildren(Stack<Node> path){
		ArrayList<Node> children = path.peek().getChildren();
		for(int i=0; i<children.size(); i++){
			path.push(children.get(i));
			System.out.print(" "+children.get(i).getName());
			if(children.get(i).getName().compareTo(this.goal)==0){
				System.out.println("\nSUCCESS");
				System.out.print("The path to goal state is: ");
				for(int j=0; j < path.size(); j++){
					System.out.print(path.get(j).getName() + " ");
				}
				return true;
			}
			boolean result = expandChildren(path);
			if(result){
				return true;
			}
			path.pop();
		}
		return false;
		
	}
	
	public void process(){
		System.out.println("Looking for the node: " + this.goal);
		System.out.print("Search order: "+ this.queue.get(0).getName());
	
		// Checking if the goal state is the start state
		if(this.queue.get(0).getName().compareTo(this.goal)==0){
			System.out.println("\nSUCCESS");
			System.out.println("The path to goal state is: "+ this.goal);
			return;
		}

		boolean result = expandChildren(this.queue);
		if(!result){
			System.out.println("\nFAILED: could not find the goal state");
		}
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
		
		DepthFirstSearch dfs = new DepthFirstSearch("f", a);
		dfs.process();
	}
}
