package automat;

import java.util.ArrayList;

public class Stack {
	private class Node{
		Node pre;
		String content = "";
		
		public Node(Node pre, String content) {
			super();
			this.pre = pre;
			this.content = content;
		}
		
	}
	private Node n;
	public Stack(){
		n = new Node(null,null);
		
	}
	public Stack(String firstElement){
		n = new Node(null,firstElement);
	}
	
	public String getTop(){
		return n.content;
	}
	
	public void push(String elm){
		n= new Node(n,elm);
	}
	
	public void pop() throws Exception{
		if(n.pre!=null)n=n.pre;
		else throw new Exception("Stack is empty!");
	}
	
	public String[] getStack(){
		ArrayList<String> stack = new ArrayList<>();
		Node n = this.n;
		while(n!=null){
			stack.add(n.content);
			n = n.pre;
		}
		String[] stackS = new String[stack.size()];
		for(int i = stack.size()-1; i >= 0; i --){
			stackS[stack.size()-1-i] = stack.get(i);
		}
		return stackS;
	}
}
