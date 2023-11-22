package edu.ncsu.csc216.stp.model.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the functionality of the Log class.
 * 
 * @param <E> Object being logged
 */
public class LogTest<E> {

//	/** The test Log object */
//	private E[] log;
//
//	/*
//	 * integer field representing the size of the list
//	 */
//	private int size;
//
//	/**
//	 * constant representing the capacity of the list
//	 */
//	private static final int INT_CAPACITY = 10;
//
//	/**
//	 * Sets up the Log object for testing.
//	 */
//	@SuppressWarnings("unchecked")
//	@Before
//	public void setUp() {
//		log = (E[]) new Object[INT_CAPACITY];
//		size = 0;
//	}

	/**
	 * Tests the add method for the Log class.
	 * 
	 * Ensures that elements are added to the end of the list as specified by the
	 * add method.
	 */
	@Test
	public void testAdd() {
		// adding a valid object to the list
		Log<String> log1 = new Log<>();
		log1.add("Test");
		assertEquals(1, log1.size());

		// adding a null object to the list
		Log<String> log2 = new Log<>();
		assertThrows(NullPointerException.class, () -> {
			log2.add(null);
		});
		
		// testing resize functionality 
		Log<Integer> log3 = new Log<>();
		log3.add(1);
		log3.add(2);
		log3.add(3);
		log3.add(4);
		log3.add(5);
		log3.add(6);
		log3.add(7);
		log3.add(8);
		log3.add(9);
		log3.add(10);
		log3.add(11);
		assertEquals(log3.size(), 11);
	}

	/**
	 * Tests the get method for the Log class.
	 * 
	 * Ensures that elements can be retrieved from the list by their index as
	 * specified by the get method.
	 */
	@Test
	public void testGet() {
		// testing index bounds
		Log<Integer> log1 = new Log<>();
		log1.add(1);
		log1.add(2);
		log1.add(3);
		log1.add(4);
		log1.add(5);
		log1.add(6);
		log1.add(7);
		log1.add(8);
		log1.add(9);
		log1.add(10);
		assertThrows(IndexOutOfBoundsException.class, () -> log1.get(10));
		assertThrows(IndexOutOfBoundsException.class, () -> log1.get(11));
		assertThrows(IndexOutOfBoundsException.class, () -> log1.get(-1));
		try {
			log1.get(11);
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Invalid index.", e.getMessage());
		}

		Log<String> logString = new Log<>();
		logString.add("First");
		logString.add("Second");
		logString.add("Third");
		assertEquals("Second", logString.get(1));
	}

	/**
	 * Tests the size method for the Log class.
	 * 
	 * Ensures that the number of elements in the list is computed correctly as
	 * specified by the size method.
	 */
	@Test
	public void testSize() {
		Log<String> log1 = new Log<>();
		assertEquals(0, log1.size());

		Log<String> logString = new Log<>();
		logString.add("First");
		logString.add("Second");
		logString.add("Third");
		assertEquals(3, logString.size());

		Log<String> log3 = new Log<>();
		log3.add("Only");
		log3.add("Two");
		log3.add("Three");
		log3.add("Four");
		log3.add("Five");
		log3.add("Six");
		log3.add("Seven");
		log3.add("Eight");
		log3.add("Nine");
		log3.add("Ten");
		log3.get(0); // Access an element to prevent IndexOutOfBoundsException
		assertEquals(10, log3.size());
	}
}
