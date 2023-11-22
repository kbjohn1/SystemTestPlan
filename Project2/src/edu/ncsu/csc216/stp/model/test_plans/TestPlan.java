package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;

/**
 * Hold test cases that belong to a name project.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {
	
	/**
	 * Test plan constructor
	 * @param testPlanName name of the test plan
	 * @throws IllegalArgumentException if the test plan name is Failing Tests List
	 */
	public TestPlan(String testPlanName) {
		super(testPlanName);
		if(testPlanName.toLowerCase().equals(FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
	}
	
	/**
	 * Returns a 2D string array of the test cases and their respective information
	 * @return null 2D string array of the test cases
	 */
	public String[][] getTestCasesAsArray() {
		ISwapList<TestCase> testCases = getTestCases();
		String[][] testCasesArray = new String[testCases.size()][3];
		for(int i = 0; i < testCases.size(); i++) {
			testCasesArray[i][0] = testCases.get(i).getTestCaseId(); 
			testCasesArray[i][1] = testCases.get(i).getTestType();
			testCasesArray[i][2] = testCases.get(i).getStatus();
		}
		return testCasesArray;
	}
	
	/**
	 * Adds the provided test cases to the test plan
	 * @param t Test case being added to the test plan
	 */
	public void addTestCase(TestCase t) {
		super.addTestCase(t);
		t.setTestPlan(this);
	}
	
	/**
	 * Compares the names of the TestPlans. This comparison is case insensitive.
	 * @param p name of the test plan being compared to the list of test plans
	 * @return 0 number of test plans with the same name
	 */
	public int compareTo(TestPlan p) {
		return getTestPlanName().toLowerCase().compareTo(p.getTestPlanName().toLowerCase());
	}
}