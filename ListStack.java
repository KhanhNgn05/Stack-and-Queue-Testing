/*
 * Author: Khanh Nguyen
 * File: ArrayStack.java
 * Purpose: This file implement the ListStack Class that implements StackInterface. 
 * 			ListStack is a Stack that uses abstract class LinkedList as the underlying data structure to keep track of the items.
 * Methods: The basic methods for a Stack is implemented, including pop(); push(); peek();isEmpty(); size(); clear() and toString();
 * This class also overrides Java equals(Object) method to accurately compare ListStacks.
 */
public class ListStack implements StackInterface {

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

		LinkedList() {
			this.head = null;
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
			int count = 1;
			Node pointer = getHead();
			while (pointer.next() != null) {
				count += 1;
				pointer = pointer.next();
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
			Node pointer = this.getHead();
			String ret = "{";
			while (pointer != null) {
				ret += String.valueOf(pointer.value()) + ",";
				pointer = pointer.next();
			}
			return ret.substring(0, ret.length() - 1) + "}";
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

		int removeLast() {
			/*
			 * removeLast() removes the last element in the Linked List;
			 * returns the value of the element removed
			 * if empty List, return -1 and do nothing.
			 */
			if (isEmpty()) {
				return -1;
			}
			Node pointer = getHead();
			int ret;
			if (size() == 1) {
				ret = this.getHead().value();
				this.setHead(null);
				return ret;
			}
			while (pointer.next().next() != null) {
				pointer = pointer.next();
			}
			ret = pointer.next().value();
			pointer.setNext(null);
			return ret;

		}

		int peekLast() {
			/*
			 * This method returns the value of the last element of the LinkedList
			 * if empty list, returns -1
			 */
			if (isEmpty()) {
				return -1;
			}
			Node pointer = getHead();
			while (pointer.next() != null) {
				pointer = pointer.next();
			}
			return pointer.value();
		}
		Boolean isEqual(LinkedList list2) {
			/*
			 * This method compares the current LinkedList with the argument LinkedList.
			 * returns true if both List has all the same Nodes in the same position.
			 * returns false otherwise
			 * Argument list2 -- is the Linked list to compare with
			 */
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

	public ListStack() {
		/*
		 * Initialize a new empty ListStack object
		 */
		this.itemList = new LinkedList();
	}

	@Override
	public void push(int value) {
		/*
		 * push() adds a new element to the top of the stack
		 * Argument int value -- is the value of the new element to be added
		 */
		itemList.append(value);
	}

	@Override
	public int pop() {
		/*
		 * pop() removes the first element of the Stack.
		 * returns the value of the element removed.
		 * If empty Stack, returns -1 and do nothing
		 */
		int ret = itemList.removeLast();
		return ret;
	}

	@Override
	public int peek() {
		/*
		 * peek() returns the value of the element on top of the Stack
		 * if empty Stack, returns -1.
		 * takes no argument.
		 */
		return itemList.peekLast();
	}

	@Override
	public boolean isEmpty() {
		/*
		 * isEmpty() returns true if the Stack is empty
		 * returns false otherwise
		 * Takes no argument
		 */
		return itemList.isEmpty();
	}

	@Override
	public int size() {
		/*
		 * size() returns the number of elements in the Stack
		 * if empty Stack, returns 0.
		 */
		return itemList.size();
	}

	@Override
	public void clear() {
		/*
		 * clear() clears all element from the Stack.
		 * Consequently, also set the field size to 0
		 */
		itemList = new LinkedList();
	}

	public String toString() {
		/*
		 * This method returns the String representation of the Stack
		 * The format is similar to how an Array is represented in String.
		 */
		return itemList.toString();
	}

	public ListStack(ListStack old) {
		/*
		 * This constructor creates a deep copy of a ListStack object
		 * by initializing a new ListStack, and setting the underlying item LinkedList to
		 * a deep copy of the old Stack's underlying item LinkedList.
		 * The LinkedList deep copy is created by LinkedList.copy() method.
		 */
		this.itemList = old.itemList.copy();
	}

private LinkedList items() {
	/*
	 * Getter method for the underlying LinkedList. 
	 * It is used in the equals method when type casting.
	 */
	return this.itemList;
}
@Override
public boolean equals(Object  stack2) {
	/*
	 * This method overrides Java equals(Object) method to accurately compares 2 ArrayStack.
	 * If the argument is not an instance of ArrayStack, returns false.
	 *
	 * If both Stack has all the same elements in the same order, returns true.
	 * Returns false otherwise
	 * The logic is implemented with LinkedList.isEqual()
	 */
	if (stack2 instanceof ListStack) {
	return ((ListStack) stack2).items().isEqual(itemList);
	}
	return false;
}

}
