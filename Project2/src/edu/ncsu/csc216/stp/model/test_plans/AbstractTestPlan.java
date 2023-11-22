package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * Represents a test plan in the SystemTestPlan system. There are two main types
 * of test plans, normal test plans associated with a named project under
 * development where test cases can be added to the end of the list and the
 * failing test list that holds test cases from across all test plans that are
 * currently failing.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public abstract class AbstractTestPlan {

	/** Name of the test plan object */
	private String testPlanName;

	/** Test cases list*/
	private ISwapList<TestCase> testCases;
	
	/**
	 * Top of the
	 * 
	 * @param testPlanName name of the test plan
	 * @throws IllegalArgumentException if the name is invalid
	 */
	public AbstractTestPlan(String testPlanName) {
		try {
			setTestPlanName(testPlanName);
			testCases = new SwapList<TestCase>();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid name.");
		}
	}

	/**
	 * Sets the test plan name to the provided testPlanName param
	 * 
	 * @param testPlanName name of the test plan
	 * @throws IllegalArgumentException if the testPlanName is null or an empty string
	 */
	public void setTestPlanName(String testPlanName) {
		if("".equals(testPlanName) || testPlanName == null) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.testPlanName = testPlanName;
	}

	/**
	 * Returns the name of the current test plan
	 * 
	 * @return testPlanName name of the current test plan
	 */
	public String getTestPlanName() {
		return testPlanName;
	}

	/**
	 * Returns a list of test cases in the test plan
	 * 
	 * @return testCases list of test cases for the test plan
	 */
	public ISwapList<TestCase> getTestCases() {
		return testCases;
	}

	/**
	 * Adds a test case object to the test plan by the test cases name
	 * 
	 * @param t name of the test case object being added
	 */
	public void addTestCase(TestCase t) {
		testCases.add(t);
	}

	/**
	 * Removes a test case from the test plan based on the test cases index in the
	 * test plan
	 * 
	 * @param idx index of the test case being removed
	 * @return removed the test case that has been removed
	 */
	public TestCase removeTestCase(int idx) {
		TestCase removed = testCases.get(idx);
		try {
			testCases.remove(idx);
		} catch(Exception e) {
			//nothing, all exceptions should be thrown out
		}
		return removed;
	}

	/**
	 * Returns the test case with the index provided
	 * 
	 * @param idx index of the test case being sought
	 * @return testCase test case with the index of the param
	 */
	public TestCase getTestCase(int idx) {
		TestCase testCase = testCases.get(idx);
		return testCase;
	}

	/**
	 * Returns the number failing test cases inside the test plan
	 * 
	 * @return failingTests number test cases inside the test plan
	 */
	public int getNumberOfFailingTests() {
		int failingTests = 0;
		for(int i = 0; i < testCases.size(); i++) {
			if(!testCases.get(i).isTestCasePassing()) {
				failingTests++;
			}
		}
		return failingTests;
	}

	/**
	 * Sends the test result parameters to the TestCase at the given index.
	 * 
	 * @param idx           index of the test case that is having its result updated
	 * @param passing       true if passing; false otherwise
	 * @param actualResults string version of the boolean value
	 */
	public void addTestResult(int idx, boolean passing, String actualResults) {
		try {
			TestCase testCase = getTestCase(idx);
			testCase.addTestResult(passing, actualResults);
		} catch(Exception e) {
			//nothing, all exceptions should be thrown out
		}
	}

	/**
	 * Returns a 2D string array of the test cases
	 * 
	 * @return 2D string array of the test cases information
	 */
	public abstract String[][] getTestCasesAsArray();

	/**
	 * Returns a hash code value for the object. The hash code is generated based on
	 * the test plan name of the current object.
	 * 
	 * @return the hash code value for the object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testPlanName == null) ? 0 : testPlanName.hashCode());
		return result;
	}

	/**
	 * Indicates whether some other object is "equal to" this one. This method
	 * checks if the provided object is equal to the current object based on their
	 * test plan names.
	 * 
	 * @param obj the reference object with which to compare
	 * @return `true` if the test plan names of the two objects are equal, `false`
	 *         otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		AbstractTestPlan other = (AbstractTestPlan) obj;
		return testPlanName.equalsIgnoreCase(other.testPlanName);
	}

	
	
	

}
