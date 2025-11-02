/*
 * Author: Khanh Nguyen
 * File: ListQueue.java
 * Purpose: This file implement the ListQueue Class that implements QueueInterface. 
 * 			ListQueue is a Queue that uses abstract class LinkedList as the underlying data structure to keep track of the items.
 * Methods: The basic methods for a Queue is implemented, including dequeue(); enqueue(); peek();isEmpty(); size(); clear() and toString();
 * This class also overrides Java equals(Object) method to accurately compare ListQueues.
 */
public class ListQueue implements QueueInterface {

	private LinkedList itemList;

	private class LinkedList {
		private Node head;

		class Node {
			/*
			 * This class implements the individual nodes for the LinkedList class
			 * Only has basic setters and getters.
			 */
			private int value;
			private Node next;

			Node(int val) {
				/*
				 * Initiates new Node with a value
				 * Arguments: val -- is the value of the Node.
				 */
				this.value = val;
				this.next = null;
			}

			void setNext(Node node) {
				this.next = node;
			}

			Node next() {
				return next;
			}

			int value() {
				return this.value;
			}
		}
		LinkedList(){
			this.head=null;
		}
		
		void append(int val) {
			/*
			 * Append adds a new Node to the end of the List
			 * Argument val -- is the value of the new Node
			 */
			if (isEmpty()) {
				this.head = new Node(val);
				return;
			}
			Node newNode = new Node(val);
			Node pointer = this.head;
			while (pointer.next() != null) {
				pointer = pointer.next();
			}
			pointer.setNext(newNode);

		}
		Boolean isEmpty() {
			/*
			 * is Empty returns true of List is empty
			 * returns false otherwise
			 */
			return this.head == null;
		}
		
		Node getHead() {
			/*
			 * getter for the head of the List
			 */
			return this.head;
		}
		void setHead(Node node) {
			/*
			 * Setter to set the head of the List
			 */
			this.head = node;
		}
		int size() {
			/*
			 * returns the number of Nodes in the Linked List
			 * if empty List, returns 0
			 */
			if (isEmpty()) {
				return 0;
			}
			int count=1;
			Node pointer = getHead();
			while (pointer.next()!= null) {
				count+=1;
				pointer=pointer.next();
			}
			return count;
		}
		public String toString() {
			/*
			 * toString() returns the String representation of LinkedList
			 * The format is similar to how Java represents an Array.
			 */
			if (isEmpty()) {
				return "{}";
			}
			Node pointer=this.getHead();
			String ret="{";
			while (pointer!=null) {
				ret+= String.valueOf(pointer.value())+",";
				pointer=pointer.next();
			}
			return ret.substring(0,ret.length()-1)+"}";
		}
		LinkedList copy() {
			/*
			 * This method returns a deep copy of the LinkedList.
			 * The deep copy is created by initiating a new LinkedList
			 * then iterate through the current List, and append the values of every nodes to the new List
			 */
			LinkedList newCopy = new LinkedList();
			Node pointer = this.getHead();
			while (pointer != null) {
				int val = pointer.value();
				newCopy.append(val);
				pointer=pointer.next();
			}
			return newCopy;
		}
		Boolean isEqual(LinkedList list2) {
			/*
			 * This method compares the current LinkedList with the argument LinkedList.
			 * returns true if both List has all the same Nodes in the same position.
			 * returns false otherwise
			 * Argument list2 -- is the Linked list to compare with
			 */
			if (list2.isEmpty() && isEmpty()) {
				return true;
			}
			if (list2.size()!=size()) {
				return false;
			}
			Node pointer1 = getHead();
			Node pointer2 = list2.getHead();
			while (pointer1!=null) {
				if (pointer1.value()!=pointer2.value()) {
					return false;
				}
				pointer1=pointer1.next();
				pointer2=pointer2.next();
			}
			return true;
		}

	}
	public ListQueue() {
		/*
		 * Initialize a new empty ListQueue object
		 */
		this.itemList=new LinkedList();
	}
	public ListQueue(ListQueue old) {
		/*
		 * This constructor creates a deep copy of a ListQueue object
		 * by initializing a new ListQueue, and setting the underlying item LinkedList to
		 * a deep copy of the old Queue's underlying item LinkedList.
		 * The LinkedList deep copy is created by LinkedList.copy() method.
		 */
		this.itemList = old.itemList.copy();
	}
	@Override
	public void enqueue(int value) {
		/*
		 * This method adds a new element to the end of the Queue.
		 * Argument int value -- is the value of the new element to be added.
		 * */
		itemList.append(value);
	}

	@Override
	public int dequeue() {
		/*
		 * This method removes the element at the start of the Queue
		 * Returns the value of the removed element
		 * If empty Queue, returns -1 and do nothing
		 */
		if (isEmpty()) {
			return -1;
		}
		int ret = itemList.getHead().value();
		itemList.setHead(itemList.getHead().next());
		return ret;
	}

	@Override
	public int peek() {
		/*
		 * peek() returns the value of the front element in the Queue.
		 * If empty Queue, returns -1.
		 * This method does not alter the Queue in any way.
		 * Takes no argument
		 */
		if (isEmpty()) {
			return -1;
		}
		int ret = itemList.getHead().value();
		return ret;
	}

	@Override
	public boolean isEmpty() {
		/*
		 * isEmpty() returns true if the Queue is empty
		 * returns false otherwise
		 * Takes no argument
		 */
		return itemList.isEmpty();
	}

	@Override
	public int size() {
		/*
		 * size() returns the number of elements in the Queue
		 * if empty Queue, returns 0.
		 */
		return itemList.size();

	}

	@Override
	public void clear() {
		/*
		 * clear() clears all element from the Queue.
		 * Consequently, also set the field size to 0
		 */
		itemList = new LinkedList();
	}
	
	public String toString() {
		/*
		 * This method returns the String representation of the Queue
		 * The format is similar to how an Array is represented in String.
		 */
		return itemList.toString();
		
	}
	private LinkedList items() {
		/*
		 * Getter method for the underlying LinkedList. 
		 * It is used in the equals() method when type casting.
		 */
		return this.itemList;
	}
	@Override
	public boolean equals(Object  queue2) {
		/*
		 * This method overrides Java equals(Object) method to accurately compares 2 ArrayQueue.
		 * If the argument is not an instance of ArrayQueue, returns false.
		 *
		 * If both Queue has all the same elements in the same order, returns true.
		 * Returns false otherwise
		 * The logic is implemented with LinkedList.isEqual()
		 */
		if (queue2 instanceof ListQueue) {
		return ((ListQueue) queue2).items().isEqual(itemList);
		}
		return false;
	}

}
