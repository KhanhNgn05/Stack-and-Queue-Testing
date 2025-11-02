/*
 * Author: Khanh Nguyen
 * Course CSC 210 - Spring 2025
 * File: ArrayQueue.java
 * Purpose: This file implement the ArrayQueue Class that implements QueueInterface. 
 * 			ArrayQueue is a Queue that uses Java Array as the underlying data structure to keep track of the items.
 * Methods: The basic methods for a Queue is implemented, including dequeue(); enqueue(); peek();isEmpty(); size(); clear() and toString();
 * This class also overrides Java equals(Object) method to accurately compare ArrayQueues.
 * Helper method isFull() to check if the underlying Array is full.
 * Helper method hasEmpty() and normalize() helps make element shifting more efficient.
 */
public class ArrayQueue implements QueueInterface {

	private Integer[] items; 
	private int size;
	
	public ArrayQueue() {
		/*
		 * Constructor
		 * Initialize an empty ArrayQueue object. The underlying Array is set to size 100.
		 */
		this.items = new Integer[100];
		this.size=0;
	}
	public ArrayQueue(ArrayQueue old) {
		/*
		 * Deep Copy constructor
		 * takes an ArrayQueue as argument and initialize a new ArrayQueue with the same elements as the argument.
		 * This serves as a method to create a deep copy of an ArrayQueue object.
		 */
		old.normalize();
		this.items= new Integer[old.items().length];
		this.size=0;
		for (int i=0; i<items.length; i++) {
			if (old.items[i]!=null) {
			enqueue(old.items[i]);
			}
		}
	}
	
	@Override
	public void enqueue(int value) {
		/*
		 * This method adds a new element to the end of the Queue.
		 * Argument int value -- is the value of the new element to be added.
		 * If the underlying Array is full, creates a new Array of double the size and copy over all current elements
		 * then adds the new element.
		 */
		if (isEmpty()) {
			items[0]=value;
			size+=1;
			return;
		}
		
		//check if the back of the Queue is full, but there is empty space at the front.
		//Artifact of dequeue method not shifting elements
		
		if (items[items.length-1]!=null) {
			normalize();
		}
		
		if (size == items.length) {
			Integer[] newItems = new Integer[items.length * 2];  //double Array size; copy elements over
			for (int i=0; i<items.length; i++) {
				newItems[i]=items[i];
			}
			this.items=newItems;
		}
		
		for (int i=items.length-1; i>=0; i--) {
			if (items[i]!=null) {
				items[i+1]=value;
				size+=1;
				break;
			}
		}

		

	}

	private void normalize() {
		/*
		 * Helper Function
		 * The way dequeue is implemented for the ArrayQueue class makes it so that everytime dequeue() is called
		 * the First element is removed from the Queue, leaving an empty position at the front.
		 * Instead of inefficiently shifting all the elements after every dequeue, this normalize() method does the shifting
		 * and only gets called when it is necessary to shift:
		 * 	 When the back of the Queue is full, but there is empty space at the front
		 * 	 When a deep copy is needed.
		 * 	 When calling toString()
		 */
		if (!hasEmpty()) {
			return;
		}
		int empty=0;
		int index=0;
		while (empty<size) {
			index+=1;
			if (items[index]!=null) {			// Iterate through the elements until reaches first non-empty index
				items[empty]=items[index];	// Copy over to first available index, starting at 0
				empty+=1;
			}
		
	}
		//removes remaining old elements
		while (items[empty]!=null) {
			items[empty]=null;
			empty+=1;
		}
	}
	@Override
	public int dequeue() {
		/*
		 * This method removes the element at the start of the Queue
		 * Returns the value of the removed element
		 * If empty Queue, returns -1 and do nothing
		 * This method does not shift elements after removing.
		 */
		if(isEmpty()) {
			return -1;
		}
		int ret;
		for (int i=0; i<items.length; i++) {
			if (items[i]!=null) {
				ret=items[i];
				items[i]=null;
				size-=1;
				return ret;
			}
		}
		return -1;
	}

	@Override
	public int peek() {
		/*
		 * This method Returns the value of the top element in the Queue.
		 * If empty Queue, returns -1.
		 * This method does not alter the Queue in any way.
		 * Takes no argument
		 */
		if(isEmpty()) {
			return -1;
		}
		for (int i=0; i<items.length; i++) {
			if (items[i]!=null) {
				return items[i];
			}
		}
		return -1;
	}
	private boolean hasEmpty() {
		/*
		 * Helper function to check if the front of the Queue has empty spots.
		 * is only called by normalize()
		 */
		return items[0]==null;
	}
	
	@Override
	public boolean isEmpty() {
		/*
		 * isEmpty() returns true if the Queue is empty
		 * returns false otherwise
		 * Takes no argument
		 */
		return size==0;
	}

	@Override
	public int size() {
		/*
		 * size() returns the number of elements in the Queue
		 * if empty Queue, returns 0.
		 */
		return size;
	}
	@Override
	public void clear() {
		/*
		 * clear() clears all element from the Queue.
		 * Consequently, also set the field size to 0
		 */
		this.items= new Integer[100];
		this.size=0;
	}
	
	public String toString() {
		/*
		 * This method returns the String representation of the Queue
		 * The format is similar to how an Array is represented in String.
		 */
		normalize();
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
	public boolean equals(Object q2) {
		/*
		 * This method overrides Java equals(Object) method to accurately compares 2 ArrayQueue.
		 * If the argument is not an instance of ArrayQueue, returns false.
		 *
		 * If both Queue has all the same elements in the same order, returns true.
		 * Returns false otherwise
		 */
		if (q2 instanceof ArrayQueue) {
		if (size()!= ((ArrayQueue) q2).size()) {
			return false;
		}
		for (int i=0; i<size(); i++) {
			if (!((ArrayQueue) q2).items()[i].equals(items[i])) {
				return false;
			}
		}
		return true;
		}
		return false;
	}

}
