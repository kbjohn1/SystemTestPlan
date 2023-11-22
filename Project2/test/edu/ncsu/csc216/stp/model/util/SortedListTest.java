package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionality of the SortedList class.
 * 
 * @param <E> type for SortedList; must implement Comparable
 */
public class SortedListTest<E extends Comparable<E>> {

	/** The test SortedList object */
	private SortedList<E> sortedList;

	/**
	 * Sets up the SortedList object for testing.
	 */
	@Before
	public void setUp() {
		sortedList = new SortedList<E>();
		assertEquals(sortedList.size(), 0);
	}

	/**
	 * Test method for SortedList
	 */
	@Test
	public void testSortedList() {
		SortedList<String> sortedList1 = new SortedList<>();
		sortedList1 = new SortedList<String>();
		assertEquals(0, sortedList1.size());
	}

	/**
	 * Tests the add method for the SortedList class.
	 * 
	 * Ensures that elements are added to the list in sorted order as specified by
	 * the add method.
	 */
	@Test
	public void testAdd() {
		SortedList<String> sortedList1 = new SortedList<>();

		sortedList1.add("C");
		sortedList1.add("A");
		sortedList1.add("B");

		assertEquals(3, sortedList1.size());
		assertEquals("A", sortedList1.get(0));
		assertEquals("B", sortedList1.get(1));
		assertEquals("C", sortedList1.get(2));

		// Test adding elements that are duplicates
		try {
			sortedList1.add("B");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add duplicate element.", e.getMessage());
		}
		assertEquals(3, sortedList1.size()); // Size should not change

		// Test adding null element
		try {
			sortedList1.add(null);
			fail("Expected NullPointerException");
		} catch (NullPointerException e) {
			assertEquals("Cannot add null element.", e.getMessage());
		}
		assertEquals(3, sortedList1.size()); // Size should not change

		SortedList<String> sortedList2 = new SortedList<>();
		assertEquals(0, sortedList2.size());
		sortedList2.add("A");
		assertEquals(1, sortedList2.size());
		sortedList2.add("B");
		assertEquals(2, sortedList2.size());
		sortedList2.add("C");
		assertEquals(3, sortedList2.size());
		sortedList2.add("D");
		assertEquals(4, sortedList2.size());
		sortedList2.add("E");
		assertEquals(5, sortedList2.size());
		sortedList2.add("F");
		assertEquals(6, sortedList2.size());
		assertEquals(sortedList2.get(0), "A");
		assertEquals(sortedList2.get(1), "B");
		assertEquals(sortedList2.get(2), "C");
		assertEquals(sortedList2.get(3), "D");
		assertEquals(sortedList2.get(4), "E");
		assertEquals(sortedList2.get(5), "F");
	}

	/**
	 * Tests the remove method for the SortedList class.
	 * 
	 * Ensures that elements can be removed from the list at a given index as
	 * specified by the remove method.
	 */
	@Test
	public void testRemove() {
		SortedList<String> sortedList1 = new SortedList<>();
		sortedList1.add("C");
		sortedList1.add("A");
		sortedList1.add("B");

		// Test removing from the front of the list
		assertEquals("A", sortedList1.remove(0));
		assertEquals(2, sortedList1.size());

		// Test removing from the middle of the list
		assertEquals("C", sortedList1.remove(1));
		assertEquals(1, sortedList1.size());

		// Test removing from the end of the list
		assertEquals("B", sortedList1.remove(0));
		assertEquals(0, sortedList1.size());
		
		// check index bounds
		assertThrows(IndexOutOfBoundsException.class, () -> sortedList1.remove(-1));
		
		assertThrows(IndexOutOfBoundsException.class, () -> sortedList1.remove(1000));
	}

	/**
	 * Tests the contains method for the SortedList class.
	 * 
	 * Ensures that the presence of an element in the list can be verified as
	 * specified by the contains method.
	 */
	@Test
	public void testContains() {
		SortedList<String> sortedList1 = new SortedList<>();
		sortedList1.add("C");
		sortedList1.add("A");
		sortedList1.add("B");

		// Test containing elements that are present in the list
		assertTrue(sortedList1.contains("A"));
		assertTrue(sortedList1.contains("B"));
		assertTrue(sortedList1.contains("C"));

		// Test containing elements that are not present in the list
		assertFalse(sortedList1.contains("D"));
		assertFalse(sortedList1.contains("E"));
	}

	/**
	 * Tests the get method for the SortedList class.
	 * 
	 * Ensures that elements can be retrieved from the list by their index as
	 * specified by the get method.
	 */
	@Test
	public void testGet() {
		SortedList<String> sortedList1 = new SortedList<>();
		sortedList1.add("C");
		sortedList1.add("A");
		sortedList1.add("B");

		// Test getting elements at various indices
		assertEquals("A", sortedList1.get(0));
		assertEquals("B", sortedList1.get(1));
		assertEquals("C", sortedList1.get(2));

		// Test getting from an empty list
		SortedList<String> emptyList = new SortedList<>();
		try {
			emptyList.get(0);
			fail("Should have thrown IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}

		assertThrows(IndexOutOfBoundsException.class, () -> sortedList1.get(sortedList1.size()));
		assertThrows(IndexOutOfBoundsException.class, () -> sortedList1.get(sortedList1.size() + 1));
		assertThrows(IndexOutOfBoundsException.class, () -> sortedList1.get(-1));
	}

	/**
	 * Tests the size method for the SortedList class.
	 * 
	 * Ensures that the number of elements in the list is computed correctly as
	 * specified by the size method.
	 */
	@Test
	public void testSize() {
		SortedList<String> sortedList1 = new SortedList<>();

		// Test size on an empty list
		assertEquals(0, sortedList1.size());

		// Test size after adding elements
		sortedList1.add("C");
		assertEquals(1, sortedList1.size());

		sortedList1.add("A");
		assertEquals(2, sortedList1.size());

		sortedList1.add("B");
		assertEquals(3, sortedList1.size());

		// Test size after removing elements
		sortedList1.remove(0);
		assertEquals(2, sortedList1.size());

		sortedList1.remove(0);
		assertEquals(1, sortedList1.size());

		sortedList1.remove(0);
		assertEquals(0, sortedList1.size());
	}

}
