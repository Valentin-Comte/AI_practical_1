package ucd.ai.search;

import java.util.ArrayList;

public class Node {

	private ArrayList<Node> children;
	private String name;
	
	public Node(String name){
		children = new ArrayList<Node>();
		this.name = name;
	}
	
	public ArrayList<Node> getChildren(){
		return new ArrayList<Node>(children);
	}
	
	public Node addChild(Node child){
		children.add(child);
		return child;
	}
	
	public void removeChild(Node child){
		children.remove(child);
	}

	public String getName() {
		return name;
	}

	public boolean equals(Node other){
		return (this.getName().compareTo(other.getName())==0);
	}
}