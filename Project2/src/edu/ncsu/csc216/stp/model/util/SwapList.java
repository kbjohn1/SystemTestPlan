package edu.ncsu.csc216.stp.model.util;

/**
 * Implements the ISwapList interface. Interface for a list that changes the
 * position of elements through swap operations.
 * 
 * @author Kevin John
 * 
 * @param <E> type for the ISwapList
 */
public class SwapList<E> implements ISwapList<E> {

	/**
	 * generic object list
	 */
	private E[] list;

	/**
	 * integer field representing the size of the list
	 */
	private int size;

	/**
	 * constant representing the capacity of the list
	 */
	private static final int INT_CAPACITY = 10;

	/**
	 * SwapList constructor (See Generic ArrayList Lecture slides)
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		// E item = (E) new Object();
		list = (E[]) new Object[INT_CAPACITY];
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
		if (size == list.length) {
			E[] newLog = (E[]) new Object[list.length * 2];
			for (int i = 0; i < size; i++) {
				newLog[i] = list[i];
			}
			list = newLog;
		}

		// Add the element to the end of the list
		list[size] = element;
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
		E value = list[idx];
		for (int i = idx; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return value;
	}

	/**
	 * Moves the element at the given index to index-1. If the element is already at
	 * the front of the list, the list is not changed.
	 * 
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public void moveUp(int idx) {

		// Check if the element is already at the front of the list
		if (idx == 0 && size == 0 || idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		} else if (idx == 0) {
			return; // Element is already at the front, do nothing
		}

		// Swap the element with the one at index-1
		E temp = list[idx];
		list[idx] = list[idx - 1];
		list[idx - 1] = temp;
	}

	/**
	 * Moves the element at the given index to index+1. If the element is already at
	 * the end of the list, the list is not changed.
	 * 
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public void moveDown(int idx) {

		// Check if the element is already at the front of the list
		if (idx == 0 && size == 0 || idx < 0 || idx > size - 1) {
			throw new IndexOutOfBoundsException("Invalid index.");
		} else if (idx == size - 1) {
			return; // Element is already at the end, do nothing
		}

		// Swap the element with the one at index+1
		E temp = list[idx];
		list[idx] = list[idx + 1];
		list[idx + 1] = temp;
	}

	/**
	 * Moves the element at the given index to index 0. If the element is already at
	 * the front of the list, the list is not changed.
	 * 
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public void moveToFront(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E temp = list[idx];
		for (int i = idx; i > 0; i--) {
			list[i] = list[i - 1];
		}
		list[0] = temp;

	}

	/**
	 * Moves the element at the given index to size-1. If the element is already at
	 * the end of the list, the list is not changed.
	 * 
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	@Override
	public void moveToBack(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E temp = list[idx];
		for (int i = idx; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = temp;

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
		return list[idx];
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
