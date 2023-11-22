package edu.ncsu.csc216.stp.model.util;


/**
 * Implements the ISortedList interface. Interface for a list that keeps objects
 * in sorted order as defined by the Comparable interface.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 * 
 * @param <E> type for ISortedList; must implement Comparable
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {

	/**
	 * integer field representing the size of the list
	 */
	private int size;

	/**
	 * Stores the front reference from ListNode
	 */
	private ListNode front;

	/**
	 * Private ListNode class for LinkedList implementation
	 */
	private class ListNode {
		/** data of the current node*/
		public E data;
		/** transition from one node to the next*/
		public ListNode next;

//		public ListNode(E data) {
//			this(data, null);
//		}

		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * Constructs a SortedList with an initial size of 0.
	 */
	public SortedList() {
		this.size = 0;
	}

	/**
	 * Adds the element to the list in sorted order.
	 * 
	 * @param element element to add
	 * @throws NullPointerException     if element is null
	 * @throws IllegalArgumentException if element cannot be added
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		// Check for duplicate elements
		ListNode current = front;
		while (current != null) {
			if (current.data.equals(element)) {
				throw new IllegalArgumentException("Cannot add duplicate element.");
			}
			current = current.next;
		}

		// Add to front of a list, including an empty list
		if (front == null || element.compareTo(front.data) < 0) {
			front = new ListNode(element, front);
		} else { // insert into middle or end of list
			current = front;
			while (current.next != null && current.next.data.compareTo(element) < 0) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		size++;
	}

	/**
	 * Returns the element from the given index. The element is removed from the
	 * list.
	 * 
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E value = null;
		if (idx == 0) { // Special Case: front of list
			value = front.data;
			front = front.next;
		} else {
			// removing from elsewhere in the list
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			value = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return value;
	}

	/**
	 * Returns true if the element is in the list.
	 * 
	 * @param element element to search for
	 * @return true if element is found
	 */
	@Override
	public boolean contains(E element) {
		ListNode current = front;
		while (current != null) {
			if (current.data.equals(element)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Returns the element at the given index.
	 * 
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		ListNode current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

}
