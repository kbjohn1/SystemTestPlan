package edu.ncsu.csc216.stp.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Test class for the TestPlanWriter class.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class TestPlanWriterTest {

	/** correct text file for testing */
	private final String validTestFile = "test-files/test-plans0.txt";
	///** invalid text file for testing */
	//private final String invalidTestFile = "test-files/test-plans3.txt";

	/**
	 * Test method for writeTestPlanFile.
	 */
	@Test
	public void testWriteTestPlanFile() {
		try {
			ISortedList<TestPlan> testPlans = TestPlanReader.readTestPlansFile(new File(validTestFile));
			TestPlanWriter.writeTestPlanFile(new File("test-files/actual_testplan"), testPlans);
		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}

		// Test writing an empty list of test plans
		ISortedList<TestPlan> emptyTestPlans = new SortedList<>();
		TestPlanWriter.writeTestPlanFile(new File("test-files/empty_testplan"), emptyTestPlans);

		// Check if IllegalArgumentException is thrown for an invalid file
		ISortedList<TestPlan> badPlans = TestPlanReader.readTestPlansFile(new File(validTestFile));
		Exception exception = assertThrows(IllegalArgumentException.class, 
				() -> TestPlanWriter.writeTestPlanFile(new File(""), badPlans));
		assertEquals("Unable to save file.", exception.getMessage());
	}

}
