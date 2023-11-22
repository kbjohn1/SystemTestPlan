package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Test class for the FailingTestList class.
 * 
 * @author Kevin John
 */
public class FailingTestListTest {

	/**
	 * Tests the constructor FailingTestList
	 */
	@Test
	public void testFailingTestList() {
		FailingTestList ftl = new FailingTestList();
		assertEquals("Failing Tests", ftl.getTestPlanName());
	}

//	/**
//	 * Test method for addTestCase.
//	 */
//	@Test
//	public void testAddTestCase() {
//		FailingTestList ftl = new FailingTestList();
//
//		// Creating a passing test case
//		TestCase passingTestCase = new TestCase("ID1", "Type1", "Description1", "PASS");
//
//		// Creating a failing test case
//		TestCase failingTestCase = new TestCase("ID2", "Type2", "Description2", "FAIL");
//
//		// TODO CONTINUE TESTING HERE
//
//		// Adding the passing test case and ensuring it throws an exception
//		assertThrows(IllegalArgumentException.class, () -> ftl.addTestCase(passingTestCase));
//
//		// Adding the failing test case and checking if it was added correctly
//		ftl.addTestCase(failingTestCase);
//		assertEquals(failingTestCase, ftl.getTestCase(0));
//	}

	/**
	 * Test method for setTestPlanName.
	 */
	@Test
	public void testSetTestPlanName() {
		FailingTestList ftl = new FailingTestList();

		// Setting the test plan name to a new name
		ftl.setTestPlanName("Failing Tests");

		// Checking if the test plan name has been updated
		assertEquals("Failing Tests", ftl.getTestPlanName());
	}

	/**
	 * Test method for getTestCasesAsArray.
	 */
	@Test
	public void testGetTestCasesAsArray() {
		// Creating a new FailingTestList
		FailingTestList ftl = new FailingTestList();

		// Creating a few failing test cases and adding them to the failing test list
		TestCase testCase1 = new TestCase("ID1", "Type1", "Description1", "FAIL");
		TestCase testCase2 = new TestCase("ID2", "Type2", "Description2", "FAIL");
		ftl.addTestCase(testCase1);
		ftl.addTestCase(testCase2);

		// Getting the test cases as a 2D string array
		String[][] testCasesAsArray = ftl.getTestCasesAsArray();

		// Asserting that the retrieved 2D string array is of the expected size and
		// content
		assertEquals(2, testCasesAsArray.length);
//		assertEquals("", testCasesAsArray[0][0]);
//		assertEquals("", testCasesAsArray[0][1]);
//		assertEquals("", testCasesAsArray[0][2]);
//		assertEquals("", testCasesAsArray[1][0]);
//		assertEquals("", testCasesAsArray[1][1]);
//		assertEquals("", testCasesAsArray[1][2]);
	}
	
	/**
	 * Test method for clearTests
	 */
	@Test
	public void testClearTest() {
		FailingTestList ftl = new FailingTestList();

		// Creating a few failing test cases and adding them to the failing test list
		TestCase testCase1 = new TestCase("ID1", "Type1", "Description1", "FAIL");
		TestCase testCase2 = new TestCase("ID2", "Type2", "Description2", "FAIL");
		ftl.addTestCase(testCase1);
		ftl.addTestCase(testCase2);
		
		ftl.clearTests();
		assertEquals(0, ftl.getNumberOfFailingTests());
	}

}
