package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.tests.TestResult;
import edu.ncsu.csc216.stp.model.util.ISwapList;

/**
 * Hold failing test cases for all projects currently available in the system.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class FailingTestList extends AbstractTestPlan {

	/** Default name of the failing test list*/
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";
	
	/** Constructor for the failing test list*/
	public FailingTestList() {
		super(FAILING_TEST_LIST_NAME);
	}

	/**
	 * Adds the provided test cases to the test plan
	 * @param t Test case being added to the test plan
	 * @throws IllegalArgumentException if the testcase is not failing
	 */
	@Override
	public void addTestCase(TestCase t) {
		if(!t.getStatus().equals(TestResult.FAIL)) {
			throw new IllegalArgumentException("Cannot add passing test case.");
		}
		super.addTestCase(t);
	}
	
	/**
	 * Overrides the method to ensure that the parameter value matches the expected name. 
	 * If so, the name is set to the constant value.
	 * @param testPlanName name of the test plan 
	 */
	@Override
	public void setTestPlanName(String testPlanName) {
		if(!testPlanName.equals(FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("The Failing Tests list cannot be edited.");
		}
		super.setTestPlanName(testPlanName);
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
			if(testCases.get(i).getTestPlan() == null) {
				testCasesArray[i][2] = "";
			} else {
				testCasesArray[i][2] = super.getTestPlanName();
			}
			
		}
		return testCasesArray;
	}
	
	/**
	 * Clears the FailingTestList of all TestCases
	 */
	public void clearTests() {
		while (super.getTestCases().size() != 0) {
			super.removeTestCase(0);
		}
	}

}