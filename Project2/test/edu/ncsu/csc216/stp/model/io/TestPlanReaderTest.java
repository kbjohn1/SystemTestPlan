package edu.ncsu.csc216.stp.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Tests the TestPlanReader class
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class TestPlanReaderTest {

	/**
	 * valid test file
	 */
	private final String actuallyValidTestFile = "test-files/test-plans0.txt";

	/**
	 * invalid test file
	 */
	private final String invalidTestFile = "test-files/test-plans3.txt";

	/**
	 * Test method for readTestPlansFile.
	 */
	@Test
	public void testReadTestPlansFile() {
		try {
			ISortedList<TestPlan> testPlans = TestPlanReader.readTestPlansFile(new File(actuallyValidTestFile));
			assertEquals(2, testPlans.size());

			TestCase testCase = testPlans.get(0).getTestCase(0);
			assertFalse(testCase.isTestCasePassing());

			TestCase testCaseTwo = testPlans.get(0).getTestCase(1);
			assertTrue(testCaseTwo.isTestCasePassing());
		} catch (Exception e) {
			fail();
		}
	}
	
	/**
	 * Test method for throwing error for readTestPlansFile
	 */
	@Test
	public void testReadTestPlanFileInvalid() {
		//ISortedList<TestPlan> testPlans = );
		Exception exception = assertThrows(IllegalArgumentException.class, 
				() -> TestPlanReader.readTestPlansFile(new File(invalidTestFile)));
		assertEquals("Unable to load file.", exception.getMessage());
		
		Exception exceptionTwo = assertThrows(IllegalArgumentException.class, 
				() -> TestPlanReader.readTestPlansFile(new File("")));
		assertEquals("Unable to load file.", exceptionTwo.getMessage());
	}

}
