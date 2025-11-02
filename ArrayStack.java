/*
 * Author: Khanh Nguyen
 * File: ArrayStack.java
 * Purpose: This file implement the ArrayStack Class that implements StackInterface. 
 * 			ArrayStack is a Stack that uses Java Array as the underlying data structure to keep track of the items.
 * Methods: The basic methods for a Stack is implemented, including pop(); push(); peek();isEmpty(); size(); clear() and toString();
 * This class also overrides Java equals(Object) method to accurately compare ArrayStacks.
 * Helper method isFull() to check if the underlying Array is full.
 */
public class ArrayStack implements StackInterface{

	private Integer[] items;
	
	public ArrayStack() {
		/*
		 * Constructor
		 * Initialize an empty ArrayStack object. The underlying Array is set to size 100.
		 */
		this.items=new Integer[100];
	}
	public ArrayStack(ArrayStack old) {
		/*
		 * Deep Copy constructor
		 * takes an ArraySTack as argument and initialize a new ArrayStack with the same elements as the argument.
		 * This serves as a method to create a deep copy of an ArrayStack object.
		 */
		this.items= new Integer[old.items().length];
		for (int i=0; i<items.length; i++) {
			if (old.items[i]!=null) {
			push(old.items[i]);
			}
		}
	}
	
	public Boolean isFull() {
		/*
		 * Helper method
		 * returns true if the underlying Array is full, return false otherwise. 
		 */
		for (Integer i : items) {
			if (i == null) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void push(int value) {
		/*
		 * push() push a new element to the Stack
		 * Argument: int value -- is the value of the new element to push to the stack.
		 * if the underlying Array is full, initialize a new Array with double the size, copy all the current elements 
		 * over, and push the new element to the Stack.
		 * returns nothing.
		 */
		if (isFull()) {
			Integer[] newItems = new Integer[items.length * 2];
			for (int i=0; i<items.length; i++) {
				newItems[i]=items[i];
			}
			this.items=newItems;
		}
		
		for (int i=0; i<items.length; i++) {
			if (items[i]==null) {
				items[i] = value;
				return;
			}
		}
	}

	@Override
	public int pop() {
		/*
		 * pop() removes the element on top of the Stack
		 * Returns the value of the removed element.
		 * If empty Stack, returns -1 and do nothing.
		 * Takes no argument
		 */
		if (isEmpty()) {
			return -1;
		}
		//iterate through the array until reaches an empty spot, the previous position will be the top
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				int ret = items[i - 1];
				items[i - 1] = null;
				return ret;
			}
		}
		// if Stack is full, there is no empty spot. Removes element at last index
		int ret = items [items.length -1];
		items [ items.length -1]= null;
		return ret;
	}
	
	@Override
	public int peek() {
		/*
		 * peek() Returns the value of the top element in the Stack.
		 * If empty Stack, returns -1.
		 * This method does not alter the Stack in any way.
		 * Takes no argument
		 */
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return -1;
		}
		
		//Same logic as pop()
		
		for (int i = 0; i < size(); i++) {
			if (items[i] == null) {
				int ret = items[i - 1];
				return ret;
			}
		}
		int ret = items [size() -1];
		return ret;
	}

	@Override
	public boolean isEmpty() {
		/*
		 * isEmpty() returns true if the Stack is empty
		 * returns false otherwise
		 * Takes no argument
		 */
		if (items[0]==null) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		/*
		 * size() returns the number of elements in the Stack
		 * if empty Stack, returns 0.
		 */
		if(isEmpty()) {
			return 0;
		}
		for (int i=0; i< items.length; i++) {
			if (items[i]==null) {
				return i;
			}
		}
		return items.length;
	}

	@Override
	public void clear() {
		/*
		 * clear() clears all element from the Stack.
		 */
		// TODO Auto-generated method stub
		this.items = new Integer[100];
	}
	
	public String toString() {
		/*
		 * toString() returns the String representation of the Stack
		 * The format is similar to how an Array is represented in String.
		 */
		if (isEmpty()) {
			return "{}";
		}
		String ret="{";
		for (int i=0; i<size(); i++) {
			ret+= String.valueOf(items[i]) + ",";
		}
		ret=ret.substring(0,ret.length()-1);
		ret+="}";
		return ret;
	}
	private Integer[] items() {
		/*
		 * Getter method for the underlying Array. 
		 * It is used in the equals method when type casting.
		 */
		return items;
	}
	@Override
	public boolean equals(Object stack2) {
		/*
		 * equals() overrides Java equals(Object) method to accurately compares 2 ArrayStack.
		 * If the argument is not an instance of ArrayStack, returns false.
		 *
		 * If both Stack has all the same elements in the same order, returns true.
		 * Returns false otherwise
		 */
		if (stack2 instanceof ArrayStack) {
		// check if size matches
		if (size()!= ((ArrayStack) stack2).size()) {
			return false;
		}
		//check if elements matches
		for (int i=0; i<size(); i++) {
			if (!((ArrayStack) stack2).items()[i].equals(items[i])) {
				return false;
			}
		}
		return true;
		}
		return false;
	}

}
