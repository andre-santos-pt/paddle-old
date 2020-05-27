package pt.iscte.paddle.interpreter.impl;

import java.util.ArrayList;

public class Stack<E> {
	private ArrayList<E> stack = new ArrayList<>();
	
	public E peek() {
		if(stack.isEmpty())
			throw new IllegalStateException("empty stack");
		
		return stack.get(stack.size()-1);
	}
	
	public E pop() {
		if(stack.isEmpty())
			throw new IllegalStateException("empty stack");
		
		E e = peek();
		stack.remove(stack.size()-1);
		return e;
	}
	
	public void push(E e) {
		stack.add(e);
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public int size() {
		return stack.size();
	}
	
	@Override
	public String toString() {
		return stack.toString();
	}
}
