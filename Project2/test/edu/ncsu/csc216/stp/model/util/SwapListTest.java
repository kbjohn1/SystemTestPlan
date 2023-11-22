package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the functionality of the SwapList class.
 * 
 * @param <E> type for SwapList
 */
public class SwapListTest<E> {

//	/*
//	 * an array of generic objects called list
//	 */
//	private E[] list;
//
//	/*
//	 * integer field representing the size of the list
//	 */
//	private int size;
//
//	/**
//	 * constant representing the capacity of the course
//	 */
//	private static final int INT_CAPACITY = 10;
//
//	/**
//	 * Sets up the Log object for testing.
//	 */
//	@SuppressWarnings("unchecked")
//	@Before
//	public void setUp() {
//		list = (E[]) new Object[INT_CAPACITY];
//		size = 0;
//	}

	/**
	 * Tests the add method for the SwapList class.
	 * 
	 * Ensures that elements are added to the list as specified by the add method.
	 */
	@Test
	public void testAdd() {
		// adding a valid object to the list
		SwapList<String> list1 = new SwapList<>();
		list1.add("Test");
		assertEquals(1, list1.size());

		// adding a null object to the list
		SwapList<String> list2 = new SwapList<>();
		assertThrows(NullPointerException.class, () -> {
			list2.add(null);
		});

		// testing resize functionality
		SwapList<Integer> list3 = new SwapList<>();
		list3.add(1);
		list3.add(2);
		list3.add(3);
		list3.add(4);
		list3.add(5);
		list3.add(6);
		list3.add(7);
		list3.add(8);
		list3.add(9);
		list3.add(10);
		list3.add(11);
		assertEquals(list3.size(), 11);
	}

	/**
	 * Tests the remove method for the SwapList class.
	 * 
	 * Ensures that elements can be removed from the list at a given index as
	 * specified by the remove method.
	 */
	@Test
	public void testRemove() {
		SwapList<String> list1 = new SwapList<>();

		// Add some elements to the list
		list1.add("Element 1");
		list1.add("Element 2");
		list1.add("Element 3");

		// Remove an element at index 1
		String removedElement = list1.remove(1);
		assertEquals("Element 2", removedElement);
		assertEquals(2, list1.size());

		// Ensure that the list does not contain the removed element
		assertFalse(list1.get(1).equals("Element 2"));

		// Attempt to remove an element at an invalid index
		try {
			list1.remove(5);
			fail("Expected IndexOutOfBoundsException to be thrown");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}

		assertThrows(IndexOutOfBoundsException.class, () -> list1.remove(list1.size()));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.remove(list1.size() + 1));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.remove(-1));
	}

	/**
	 * Tests the moveUp method for the SwapList class.
	 * 
	 * Ensures that elements can be moved up in the list through the moveUp method.
	 */
	@Test
	public void testMoveUp() {
		SwapList<String> list1 = new SwapList<>();

		list1.add("Element 1");
		list1.add("Element 2");
		list1.add("Element 3");

		// Move an element up at index 1
		list1.moveUp(1);

		// Verify that the elements have been moved correctly
		assertEquals("Element 2", list1.get(0));
		assertEquals("Element 1", list1.get(1));
		assertEquals("Element 3", list1.get(2));

		// Attempt to move an element up from an invalid index
		try {
			list1.moveUp(-1);
			fail("Expected IndexOutOfBoundsException to be thrown");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}

		SwapList<String> emptyList = new SwapList<>();
		assertThrows(IndexOutOfBoundsException.class, () -> emptyList.moveUp(0));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveUp(list1.size()));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveUp(list1.size() + 1));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveUp(-1));
		
		SwapList<String> list2 = new SwapList<>();
		list2.add("Element 1");

	    // Attempt to move up the only element in the list
	    try {
	    	list2.moveUp(0);
	        // Since there's only one element, moving it up should do nothing
	        assertEquals("Element 1", list2.get(0));
	    } catch (IndexOutOfBoundsException e) {
	        fail("Unexpected IndexOutOfBoundsException: " + e.getMessage());
	    }
		
	}

	/**
	 * Tests the moveDown method for the SwapList class.
	 * 
	 * Ensures that elements can be moved down in the list through the moveDown
	 * method.
	 */
	@Test
	public void testMoveDown() {
		SwapList<String> list1 = new SwapList<>();
		list1.add("First");
		list1.add("Second");
		list1.add("Third");
		list1.add("Fourth");

		// Move the element at index 1 down
		list1.moveDown(1);
		assertEquals("Second", list1.get(2));
		assertEquals("Third", list1.get(1));

		// Try moving the last element down
		try {
			list1.moveDown(list1.size());
			fail("Expected IndexOutOfBoundsException to be thrown");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}

		// Try moving an element at an invalid index
		try {
			list1.moveDown(5);
			fail("Expected IndexOutOfBoundsException to be thrown");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}

		SwapList<String> emptyList = new SwapList<>();
		assertThrows(IndexOutOfBoundsException.class, () -> emptyList.moveDown(0));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveDown(list1.size()));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveDown(list1.size() + 1));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveDown(-1));
		
		SwapList<String> list2 = new SwapList<>();
	    list2.add("Element 1");

	    // Attempt to move down the only element in the list
	    try {
	        list2.moveDown(0);
	        // Since there's only one element, moving it down should do nothing
	        assertEquals("Element 1", list2.get(0));
	    } catch (IndexOutOfBoundsException e) {
	        fail("Unexpected IndexOutOfBoundsException: " + e.getMessage());
	    }

	}

	/**
	 * Tests the moveToFront method for the SwapList class.
	 * 
	 * Ensures that elements can be moved to the front of the list through the
	 * moveToFront method.
	 */
	@Test
	public void testMoveToFront() {
		SwapList<String> list1 = new SwapList<>();
		list1.add("Element 1");
		list1.add("Element 2");
		list1.add("Element 3");

		// Move an element to the front at index 1
		list1.moveToFront(1);

		// Verify that the elements have been moved correctly
		assertEquals("Element 2", list1.get(0));
		assertEquals("Element 1", list1.get(1));
		assertEquals("Element 3", list1.get(2));

		// Attempt to move an element to the front from an invalid index
		try {
			list1.moveToFront(-1);
			fail("Expected IndexOutOfBoundsException to be thrown");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}
		
		try {
			list1.moveToFront(100);
			fail("Expected IndexOutOfBoundsException to be thrown");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}

		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveToFront(list1.size()));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveToFront(list1.size() + 1));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveToFront(-1));

	}

	/**
	 * Tests the moveToBack method for the SwapList class.
	 * 
	 * Ensures that elements can be moved to the back of the list through the
	 * moveToBack method.
	 */
	@Test
	public void testMoveToBack() {
		SwapList<String> list1 = new SwapList<>();
		list1.add("First");
		list1.add("Second");
		list1.add("Third");
		list1.add("Fourth");

		// Move the element at index 1 to the back
		list1.moveToBack(1);
		assertEquals("First", list1.get(0));
		assertEquals("Third", list1.get(1));
		assertEquals("Fourth", list1.get(2));
		assertEquals("Second", list1.get(3));

		// Try moving the last element to the back
		try {
			list1.moveToBack(-1);
			fail("Expected IndexOutOfBoundsException to be thrown");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		} 

		// Try moving an element at an invalid index
		try {
			list1.moveToBack(5);
			fail("Expected IndexOutOfBoundsException to be thrown");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}

		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveToBack(list1.size()));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveToBack(list1.size() + 1));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.moveToBack(-1));
	}

	/**
	 * Tests the get method for the SwapList class.
	 * 
	 * Ensures that elements can be retrieved from the list by their index as
	 * specified by the get method.
	 */
	@Test
	public void testGet() {
		// testing index bounds
		SwapList<Integer> list1 = new SwapList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		list1.add(5);
		list1.add(6);
		list1.add(7);
		list1.add(8);
		list1.add(9);
		list1.add(10);
		assertThrows(IndexOutOfBoundsException.class, () -> list1.get(10));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.get(11));
		assertThrows(IndexOutOfBoundsException.class, () -> list1.get(-1));
		try {
			list1.get(11);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}

		SwapList<String> listString = new SwapList<>();
		listString.add("First");
		listString.add("Second");
		listString.add("Third");
		assertEquals("Second", listString.get(1));
	}

	/**
	 * Tests the size method for the SwapList class.
	 * 
	 * Ensures that the number of elements in the list is computed correctly as
	 * specified by the size method.
	 */
	@Test
	public void testSize() {
		SwapList<String> list1 = new SwapList<>();
		assertEquals(0, list1.size());

		SwapList<String> listString = new SwapList<>();
		listString.add("First");
		listString.add("Second");
		listString.add("Third");
		assertEquals(3, listString.size());

		SwapList<String> list3 = new SwapList<>();
		list3.add("Only");
		list3.add("Two");
		list3.add("Three");
		list3.add("Four");
		list3.add("Five");
		list3.add("Six");
		list3.add("Seven");
		list3.add("Eight");
		list3.add("Nine");
		list3.add("Ten");
		list3.get(0); // Access an element to prevent IndexOutOfBoundsException
		assertEquals(10, list3.size());
	}
}
