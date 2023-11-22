package edu.ncsu.csc216.stp.model.tests;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test class for the TestResult class.
 * 
 * @author Kevin John
 */
public class TestResultTest {
	/**
	 * Test method for constructor TestResult.
	 */
	@Test
	public void testTestResultConstructor() {
		// Test with valid parameters
		TestResult testResult = new TestResult(true, "Passing result");
		assertTrue(testResult.isPassing());
		assertEquals("Passing result", testResult.getActualResults());

		// Test with different valid parameters
		testResult = new TestResult(false, "Failing result");
		assertFalse(testResult.isPassing());
		assertEquals("Failing result", testResult.getActualResults());

		// Test with invalid parameters
		try {
			testResult = new TestResult(true, "");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test results.", e.getMessage());
		}

		try {
			testResult = new TestResult(false, null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test results.", e.getMessage()); 
		}
	}

	// coverage for rest of the methods are covered under TestCaseTest
}
