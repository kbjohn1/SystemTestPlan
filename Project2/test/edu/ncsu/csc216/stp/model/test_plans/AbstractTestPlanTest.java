package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Test class for the AbstractTestPlan class.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class AbstractTestPlanTest {

	/** Test plan name for testing */
	private static final String TESTPLANNAME = "Test Plan";

	/** Test case id for testing*/
	private static final String TESTCASEID = "Invalid File";

	/** Test type for testing*/
	private static final String TESTTYPE = "Equivalence Class";

	/** Test description for testing*/
	private static final String TESTDESCRIPTION = "Run WolfSchedulerGUI";

	/** Expected result for testing*/
	private static final String EXPECTEDRESULT = "WolfSchedulerGUI loads";

	/**
	 * Test method for setTestPlanName.
	 */
	@Test
	public void testSetTestPlanName() {
		TestPlan testPlan = new TestPlan(TESTPLANNAME);
		assertEquals(TESTPLANNAME, testPlan.getTestPlanName());
		assertThrows(IllegalArgumentException.class, () -> new TestPlan(""));
		assertThrows(IllegalArgumentException.class, () -> new TestPlan(null));
	}


	/**
	 * Test method for addTestCase.
	 */
	@Test
	public void testAddTestCase() {
		TestCase testCase = new TestCase(TESTCASEID, TESTTYPE, TESTDESCRIPTION, EXPECTEDRESULT);
		TestPlan testPlan = new TestPlan(TESTPLANNAME);
		testPlan.addTestCase(testCase);
		assertEquals(testCase, testPlan.getTestCase(0));
	}

	/**
	 * Test method for removeTestCase.
	 */
	@Test
	public void testRemoveTestCase() {
		TestCase testCase = new TestCase(TESTCASEID, TESTTYPE, TESTDESCRIPTION, EXPECTEDRESULT);
		TestPlan testPlan = new TestPlan(TESTPLANNAME);
		testPlan.addTestCase(testCase);
		testPlan.removeTestCase(0);
		assertEquals(0, testPlan.getTestCases().size());
	}

	/**
	 * Test method for getNumberOfFailingTests.
	 */
	@Test
	public void testGetNumberOfFailingTests() {
		TestCase testCase = new TestCase(TESTCASEID, TESTTYPE, TESTDESCRIPTION, EXPECTEDRESULT);
		testCase.addTestResult(false, EXPECTEDRESULT);
		TestPlan testPlan = new TestPlan(TESTPLANNAME);
		testPlan.addTestCase(testCase);
		assertEquals(1, testPlan.getNumberOfFailingTests());
	}

	/**
	 * Test method for addTestResult.
	 */
	@Test
	public void testAddTestResult() {
		TestCase testCase = new TestCase(TESTCASEID, TESTTYPE, TESTDESCRIPTION, EXPECTEDRESULT);
		TestPlan testPlan = new TestPlan(TESTPLANNAME);
		testPlan.addTestCase(testCase);
		testPlan.addTestResult(0, true, EXPECTEDRESULT);
		boolean var = true;
		assertEquals(var, testPlan.getTestCase(0).isTestCasePassing());
	}

	/**
	 * Test method for getTestCasesAsArray.
	 */
	@Test
	public void testGetTestCasesAsArray() {
		// Creating a new test plan
		TestPlan testPlan = new TestPlan(TESTPLANNAME);

		// Creating a few test cases and adding them to the test plan
		TestCase testCase1 = new TestCase("ID1", "Type1", "Description1", "ExpectedResult1");
		TestCase testCase2 = new TestCase("ID2", "Type2", "Description2", "ExpectedResult2");
		testPlan.addTestCase(testCase1);
		testPlan.addTestCase(testCase2);

		// Getting the test cases as a 2D string array
		String[][] testCasesAsArray = testPlan.getTestCasesAsArray();

		// Asserting that the retrieved 2D string array is of the expected size and
		// content

		
		assertEquals(2, testCasesAsArray.length);
		assertEquals("ID1", testCasesAsArray[0][0]);
		assertEquals("Type1", testCasesAsArray[0][1]);
		
		// TODO double check this one below, tests fail here
		assertEquals("FAIL", testCasesAsArray[0][2]);
		assertEquals("ID2", testCasesAsArray[1][0]);
		assertEquals("Type2", testCasesAsArray[1][1]);
		assertEquals("FAIL", testCasesAsArray[1][2]);
	}

	/**
	 * Test method for equals.
	 */
	@Test
	public void testEquals() {
		// Creating two instances of TestPlan with the same name
		TestPlan testPlan1 = new TestPlan(TESTPLANNAME);
		TestPlan testPlan2 = new TestPlan(TESTPLANNAME);

		// Creating two instances of TestPlan with different names
		TestPlan testPlan3 = new TestPlan("Test Plan 3");
		//TestPlan testPlan4 = new TestPlan("Test Plan 4");

		// Asserting that the two instances with the same name are equal
		assertEquals(testPlan1, testPlan2);

		// Asserting that the two instances with different names are not equal
		assertNotEquals(testPlan1, testPlan3);

	}

	/**
	 * Test method for hashCode.
	 */
	@Test
	public void testHashCode() {
		// Creating two instances of TestPlan with the same name
		TestPlan testPlan1 = new TestPlan(TESTPLANNAME);
		TestPlan testPlan2 = new TestPlan(TESTPLANNAME);

		// Asserting that the hash codes of the two instances are the same
		assertEquals(testPlan1.hashCode(), testPlan2.hashCode());

		// Creating two instances of TestPlan with different names
		TestPlan testPlan3 = new TestPlan("Test Plan 3");
		TestPlan testPlan4 = new TestPlan("Test Plan 4");

		// Asserting that the hash codes of the two instances are different
		assertNotEquals(testPlan3.hashCode(), testPlan4.hashCode());
	}
}
