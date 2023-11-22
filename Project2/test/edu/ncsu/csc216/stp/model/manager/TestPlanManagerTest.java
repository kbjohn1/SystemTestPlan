package edu.ncsu.csc216.stp.model.manager;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;

/**
 * Test class for the TestPlanManager class.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class TestPlanManagerTest {

	/** Test plan name for testing */
	private static final String TESTPLANNAME = "Test Plan";

	/**
	 * valid test file
	 */
	private final String actuallyValidTestFile = "test-files/test-plans0.txt";

	/**
	 * Test method for addTestPlan.
	 */
	@Test
	public void testAddTestPlan() {
		TestPlanManager testPlanManager = new TestPlanManager();
		testPlanManager.addTestPlan(TESTPLANNAME);
		assertTrue(testPlanManager.isChanged());
		assertEquals(2, testPlanManager.getTestPlanNames().length);

		// additional tests for add test plan to troubleshoot

		assertThrows(IllegalArgumentException.class, () -> testPlanManager.addTestPlan(TESTPLANNAME));

		TestPlanManager tpm = new TestPlanManager();
		tpm.loadTestPlans(new File(actuallyValidTestFile));
		assertEquals(3, tpm.getTestPlanNames().length);

		String[] assertStrings = { "Failing Tests", "PackScheduler", "WolfScheduler" };
		String[] testPlanStrings = tpm.getTestPlanNames();

		for (int i = 0; i < tpm.getTestPlanNames().length; i++) {
			assertEquals(assertStrings[i], testPlanStrings[i]);
		}

//		for (int i = 0; i < testPlans.size(); i++) {
//			
//			tpm.addTestPlan(testPlans.get(i).toString());
//		}
//		
//		String[] testStrings = {"Failing Tests", "PackScheduler", "WolfScheduler"};
//		
//		assertArrayEquals(tpm.getTestPlanNames(), testStrings);

	}

	/**
	 * Test method for addTestPlan.
	 */
	@Test
	public void testAddTestPlanInvalid() {
		TestPlanManager testPlanManager = new TestPlanManager();
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> testPlanManager.addTestPlan(FailingTestList.FAILING_TEST_LIST_NAME));
		assertEquals("Invalid name.", exception.getMessage());
	}

	/**
	 * Test method for setCurrentTestPlan.
	 */
	@Test
	public void testEditTestPlanValid() {
		TestPlanManager testPlanManager = new TestPlanManager();
		testPlanManager.addTestPlan(TESTPLANNAME);
		testPlanManager.editTestPlan("Test");
		assertEquals("Test", testPlanManager.getCurrentTestPlan().getTestPlanName());
	}

	/**
	 * Test method for setCurrentTestPlan.
	 */
	@Test
	public void testEditTestPlanInvalid() {
		TestPlanManager testPlanManager = new TestPlanManager();
		testPlanManager.addTestPlan(TESTPLANNAME);
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> testPlanManager.editTestPlan(TESTPLANNAME));
		assertEquals("Invalid name.", exception.getMessage());
	}

	/**
	 * Test method for editTestPlan.
	 */
	@Test
	public void testEditTestPlan() {
		TestPlanManager testPlanManager = new TestPlanManager();
		FailingTestList failingTestList = new FailingTestList();
		Exception exceptionTwo = assertThrows(IllegalArgumentException.class,
				() -> testPlanManager.editTestPlan(failingTestList.getTestPlanName()));
		assertEquals("The Failing Tests list may not be edited.", exceptionTwo.getMessage());
	}

//    /**
//     * Test method for editTestPlan.
//     */
//    @Test
//    public void testEditTestPlanFailing() {
//    	TestPlanManager testPlanManager = new TestPlanManager();
//    	Exception exception = assertThrows(IllegalArgumentException.class,
//				() -> testPlanManager.editTestPlan("Failing Tests"));
//    	assertEquals("Invalid name.", exception.getMessage());
//    }
	/**
	 * Test method for removeTestPlan
	 */
	@Test
	public void testRemoveTestPlan() {
		TestPlanManager testPlanManager = new TestPlanManager();
		Exception exception = assertThrows(IllegalArgumentException.class, () -> testPlanManager.removeTestPlan());
		assertEquals("The Failing Tests list may not be deleted.", exception.getMessage());

		// Create TestPlanManager
		TestPlanManager testPlanManager1 = new TestPlanManager();

		// Add a test plan
		testPlanManager1.addTestPlan("TestPlan1");

		// Add a failing test case to the test plan
		TestCase failingTestCase = new TestCase("Failing Test", "Description", "Expected Result", "FAIL");
		testPlanManager1.setCurrentTestPlan("TestPlan1");
		testPlanManager1.addTestCase(failingTestCase);

		// Verify that the Failing Tests list is not empty before removing the test plan
		assertTrue(testPlanManager1.getCurrentTestPlan().getTestCases().size() > 0);

		// Remove the test plan
		testPlanManager1.removeTestPlan();

		// Verify that the current test plan is set to the Failing Tests list
		assertEquals(FailingTestList.FAILING_TEST_LIST_NAME, testPlanManager1.getCurrentTestPlan().getTestPlanName());
		// Verify that isChanged is set to true after removing the test plan
		assertTrue(testPlanManager1.isChanged());
	}

	/**
	 * Tests the testLoadTestPlan() method
	 */
	@Test
	public void testLoadTestPlan() {
		// creates a new tpm
		TestPlanManager tpm = new TestPlanManager();
		// creates a file to load
		File load = new File(actuallyValidTestFile);
		// uses the tpm to load the file
		tpm.loadTestPlans(load);
		// string to compare and assert
		String[] results = tpm.getTestPlanNames();
		assertEquals("PackScheduler", results[1]);
	}

	/**
	 * Tests the testAddPassingTestCase() method
	 */
	@Test
	public void testAddPassingTestCase() {
		// creates a new tpm
		TestPlanManager testPlanManager = new TestPlanManager();
		// adds a test plan
		testPlanManager.addTestPlan("TestPlan1");

		// creates a new test case
		TestCase passingTestCase = new TestCase("Passing Test", "Description", "Expected Result", "PASS");
		// sets the current test plan
		testPlanManager.setCurrentTestPlan("TestPlan1");
		// adds a passing test case
		testPlanManager.addTestCase(passingTestCase);

		// Get the test cases from the current test plan
		ISwapList<TestCase> testCases = testPlanManager.getCurrentTestPlan().getTestCases();

		// Verify that the passing test case was added
		assertEquals(1, testCases.size());
		assertEquals(passingTestCase, testCases.get(0));
	}

}
