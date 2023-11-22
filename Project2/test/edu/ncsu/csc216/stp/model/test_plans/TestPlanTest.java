package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Test class for the TestPlan class.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class TestPlanTest {

	/** Test plan name for testing*/
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
	 * Test method for getTestCasesAsArray.
	 */
	@Test
	public void testGetTestCasesAsArray() {
		TestCase testCase = new TestCase(TESTCASEID, TESTTYPE, TESTDESCRIPTION, EXPECTEDRESULT);
		testCase.addTestResult(true, EXPECTEDRESULT);
        TestPlan testPlan = new TestPlan(TESTPLANNAME);
        testPlan.addTestCase(testCase);
        String[][] testCasesArray = testPlan.getTestCasesAsArray();
        assertEquals(TESTCASEID, testCasesArray[0][0]);
        assertEquals(TESTTYPE, testCasesArray[0][1]);
        assertEquals("PASS", testCasesArray[0][2]);
        
	}

	/**
	 * Test method for addTestCase.
	 */
	@Test
	public void invalidTestPlan() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new TestPlan(FailingTestList.FAILING_TEST_LIST_NAME));
		assertEquals("Invalid name.", exception.getMessage());
	}

}
