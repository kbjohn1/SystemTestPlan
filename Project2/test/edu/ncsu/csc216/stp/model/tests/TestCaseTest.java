package edu.ncsu.csc216.stp.model.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;

/**
 * Test class for the TestCase class.
 * 
 * @author Kevin John
 */
public class TestCaseTest {

	/**
	 * testCase object for testing
	 */
	private TestCase testCase;

	/**
	 * sets up the tests with an example testCase object
	 */
	@Before
	public void setUp() {
		// Initialize the test case with valid parameters
		testCase = new TestCase("TC001", "Unit", "Test case description", "Expected results");
	}

	/**
	 * Test method for constructor TestCase.
	 */
	@Test
	public void testTestCase() {

		TestCase testCase1 = new TestCase("TC001", "Unit", "Test case description", "Expected results");

		// Test case with valid parameters
		assertEquals("TC001", testCase1.getTestCaseId());
		assertEquals("Unit", testCase1.getTestType());
		assertEquals("Test case description", testCase1.getTestDescription());
		assertEquals("Expected results", testCase1.getExpectedResults());

		// Test case with invalid parameters
		try {
			testCase1 = new TestCase("", "Unit", "Test case description", "Expected results");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

		try {
			testCase1 = new TestCase("TC002", "", "Test case description", "Expected results");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

		try {
			testCase1 = new TestCase("TC003", "Unit", "", "Expected results");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

		try {
			testCase1 = new TestCase("TC004", "Unit", "Test case description", "");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}
	}

	/**
	 * Test method for getTestCaseId.
	 */
	@Test
	public void testGetTestCaseId() {
		// Test with valid id
		assertEquals("TC001", testCase.getTestCaseId());

		// Test with different valid id
		testCase.setTestCaseId("TC002");
		assertEquals("TC002", testCase.getTestCaseId());

		// Test with invalid id
		try {
			testCase.setTestCaseId("");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

		try {
			testCase.setTestCaseId(null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}
	}

	/**
	 * Test method for setTestCaseId.
	 */
	@Test
	public void testSetTestCaseId() {
		// Test with valid id
		testCase.setTestCaseId("TC002");
		assertEquals("TC002", testCase.getTestCaseId());

		// Test with different valid id
		testCase.setTestCaseId("TC003");
		assertEquals("TC003", testCase.getTestCaseId());

		// Test with invalid id
		try {
			testCase.setTestCaseId("");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

		try {
			testCase.setTestCaseId(null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

	}

	/**
	 * Test method for getTestType.
	 */
	@Test
	public void testGetTestType() {
		// Test with valid type
		assertEquals("Unit", testCase.getTestType());

		// Test with different valid type
		testCase.setTestType("Integration");
		assertEquals("Integration", testCase.getTestType());

		// Test with invalid type
		try {
			testCase.setTestType("");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

		try {
			testCase.setTestType(null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}
	}

	/**
	 * Test method for setTestType.
	 */
	@Test
	public void testSetTestType() {
		// Test with valid type
		testCase.setTestType("Integration");
		assertEquals("Integration", testCase.getTestType());

		// Test with different valid type
		testCase.setTestType("System");
		assertEquals("System", testCase.getTestType());

		// Test with invalid type
		try {
			testCase.setTestType("");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

		try {
			testCase.setTestType(null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}
	}

	/**
	 * Test method for getTestDescription.
	 */
	@Test
	public void testGetTestDescription() {
		// Test with valid description
		assertEquals("Test case description", testCase.getTestDescription());

		// Test with different valid description
		testCase.setTestDescription("New test case description");
		assertEquals("New test case description", testCase.getTestDescription());

		// Test with invalid description
		try {
			testCase.setTestDescription("");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

		try {
			testCase.setTestDescription(null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}
	}

	/**
	 * Test method for setTestDescription.
	 */
	@Test
	public void testSetTestDescription() {
		// Test with valid description
		testCase.setTestDescription("New test case description");
		assertEquals("New test case description", testCase.getTestDescription());

		// Test with different valid description
		testCase.setTestDescription("Updated test case description");
		assertEquals("Updated test case description", testCase.getTestDescription());

		// Test with invalid description
		try {
			testCase.setTestDescription("");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

		try {
			testCase.setTestDescription(null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

	}

	/**
	 * Test method for getExpectedResults.
	 */
	@Test
	public void testGetExpectedResults() {
		// Test with valid expected results
		assertEquals("Expected results", testCase.getExpectedResults());

		// Test with different valid expected results
		testCase.setExpectedResults("New expected results");
		assertEquals("New expected results", testCase.getExpectedResults());

		// Test with invalid expected results
		try {
			testCase.setExpectedResults("");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

		try {
			testCase.setExpectedResults(null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}
	}

	/**
	 * Test method for setExpectedResults.
	 */
	@Test
	public void testSetExpectedResults() {
		// Test with valid expected results
		testCase.setExpectedResults("New expected results");
		assertEquals("New expected results", testCase.getExpectedResults());

		// Test with different valid expected results
		testCase.setExpectedResults("Updated expected results");
		assertEquals("Updated expected results", testCase.getExpectedResults());

		// Test with invalid expected results
		try {
			testCase.setExpectedResults("");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}

		try {
			testCase.setExpectedResults(null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test information.", e.getMessage());
		}
	}

	/**
	 * Test method for addTestResult.
	 */
	@Test
	public void testAddTestResult() {
		// Test with valid parameters
		testCase.addTestResult(true, "Passing result");
		assertEquals("- PASS: Passing result", testCase.getActualResultsLog().trim());

		// Test with different valid parameters
		testCase.addTestResult(false, "Failing result");
		assertEquals("- PASS: Passing result\n- FAIL: Failing result", testCase.getActualResultsLog().trim());

		// Test with invalid parameters
		try {
			testCase.addTestResult(true, "");
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test results.", e.getMessage());
		}

		try {
			testCase.addTestResult(false, null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test results.", e.getMessage());
		}
	}

	/**
	 * Test method for isTestPassing.
	 */
	@Test
	public void testIsTestPassing() {
		// Test when no test result has been added yet
		assertFalse(testCase.isTestCasePassing());

		// Test with a passing test result
		testCase.addTestResult(true, "Passing result");
		assertTrue(testCase.isTestCasePassing());

		// Test with a failing test result
		testCase.addTestResult(false, "Failing result");
		assertFalse(testCase.isTestCasePassing());
	}

	/**
	 * Test method for getStatus.
	 */
	@Test
	public void testGetStatus() {
		// Test when no test result has been added yet
		assertEquals(TestResult.FAIL, testCase.getStatus());

		// Test with a passing test result
		testCase.addTestResult(true, "Passing result");
		assertEquals(TestResult.PASS, testCase.getStatus());

		// Test with a failing test result
		testCase.addTestResult(false, "Failing result");
		assertEquals(TestResult.FAIL, testCase.getStatus());
	}

	/**
	 * Test method for getActualResultsLog.
	 */
	@Test
	public void testGetActualResultsLog() {
		// Test when no test result has been added yet
		assertEquals("", testCase.getActualResultsLog());

		// Test with one test result added
		testCase.addTestResult(true, "Passing result");
		assertEquals("- PASS: Passing result\n", testCase.getActualResultsLog());

		// Test with multiple test results added
		testCase.addTestResult(false, "Failing result");
		testCase.addTestResult(true, "Another passing result");
		assertEquals("- PASS: Passing result\n- FAIL: Failing result\n- PASS: Another passing result\n",
				testCase.getActualResultsLog());
	}

	/**
	 * Test method for getTestPlan.
	 */
	@Test
	public void testGetTestPlan() {
		// Test with a null test plan initially
		assertNull(testCase.getTestPlan());

		// Create a new test plan
		TestPlan testPlan = new TestPlan("Test Plan 1");

		// Set the test plan for the test case
		testCase.setTestPlan(testPlan);

		// Test if the retrieved test plan matches the initially created test plan
		assertEquals(testPlan, testCase.getTestPlan());
	}

	/**
	 * Test method for setTestPlan.
	 */
	@Test
	public void testSetTestPlan() {
		// Test with valid test plan
		TestPlan testPlan1 = new TestPlan("Test Plan 1");
		testCase.setTestPlan(testPlan1);
		assertEquals(testPlan1, testCase.getTestPlan());

		// Test with different valid test plan
		TestPlan testPlan2 = new TestPlan("Test Plan 2");
		testCase.setTestPlan(testPlan2);
		assertEquals(testPlan2, testCase.getTestPlan());

		// Test with null test plan
		try {
			testCase.setTestPlan(null);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid test plan.", e.getMessage());
		}
	}

	/**
	 * Test method for toString.
	 */
	@Test
	public void testToString() {
		String expectedString = "# TC001,Unit\n" + "* Test case description\n" + "* Expected results\n" + "";
		assertEquals(expectedString, testCase.toString());
	}
}
