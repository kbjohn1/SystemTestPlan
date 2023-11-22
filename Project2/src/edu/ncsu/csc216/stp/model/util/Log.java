package edu.ncsu.csc216.stp.model.util;

/**
 * Implements the ILog interface. Interface for a list that maintains a log of
 * elements where items are added to the end and cannot be removed.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 * @param <E> Object being logged
 */
public class Log<E> implements ILog<E> {

//	/**
//	 * The UML class diagram suggests that you should implement Log using an
//	 * array-based list, SortedList using a linked list, andSwapList using an
//	 * array-based list. However, you may choose to implement the lists using the
//	 * other type of data storage if you would like. YOU MUST IMPLEMENT AT LEAST ONE
//	 * LINKED LIST AND AT LEAST ONE ARRAY-BASED LIST. If you choose to implement
//	 * SwapList as a linked list, you should consider making the list doubly linked
//	 * (both a prev and next reference in the ListNode) to simplify the
//	 * implementation of moveUp() and moveDown().
//	 */

	/**
	 * an array of generic objects called log
	 */
	private E[] log;

	/**
	 * integer field representing the size of the list
	 */
	private int size;

	/**
	 * constant representing the capacity of the log
	 */
	private static final int INT_CAPACITY = 10;

	/**
	 * log list constructor (See Generic ArrayList Lecture slides)
	 */
	@SuppressWarnings("unchecked")
	public Log() {
		// E item = (E) new Object();
		log = (E[]) new Object[INT_CAPACITY];
		size = 0;
	}

	/**
	 * Adds the element to the end of the list.
	 * 
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		// Check if the array is full, and resize if needed
		if (size == log.length) {
			E[] newLog = (E[]) new Object[log.length * 2];
			for (int i = 0; i < size; i++) {
				newLog[i] = log[i];
			}
			log = newLog;
		}

		// Add the element to the end of the list
		log[size] = element;
		size++;

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
		// setting bounds as size - 1
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return log[idx];
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
