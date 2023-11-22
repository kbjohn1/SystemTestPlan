package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ILog;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * Represents a test case that can be part of a test plan.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class TestCase {

	/** test case Id */
	private String testCaseId;

	/** test case type */
	private String testType;

	/** test case description */
	private String testDescription;

	/** test case expected results */
	private String expectedResults;

	/** test plan for the test case */
	private TestPlan testPlan;

	/** result of the test case */
	private ILog<TestResult> testResults;

	/**
	 * Constructs the TestCase with the given parameters.
	 * 
	 * @param testCaseId      Id of the test case
	 * @param testType        type of the test case
	 * @param testDescription description for the test case
	 * @param expectedResults expected result of the test case
	 * @throws IllegalArgumentException if the test case cannot be created
	 */
	public TestCase(String testCaseId, String testType, String testDescription, String expectedResults) {
		try {
			setTestCaseId(testCaseId);
			setTestType(testType);
			setTestDescription(testDescription);
			setExpectedResults(expectedResults);
			testResults = new Log<TestResult>();
			testPlan = null;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid test information.");
		}
	}

	/**
	 * Returns the test case id
	 * 
	 * @return the testCaseId
	 */
	public String getTestCaseId() {
		return testCaseId;
	}

	/**
	 * Sets the test case id to the parameter
	 * 
	 * @param testCaseId the testCaseId to set
	 * @throws IllegalArgumentException if the test case Id string is null or empty
	 */
	public void setTestCaseId(String testCaseId) {
		if ("".equals(testCaseId) || testCaseId == null) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testCaseId = testCaseId;
	}

	/**
	 * returns the type of the test case
	 * 
	 * @return the testType
	 */
	public String getTestType() {
		return testType;
	}

	/**
	 * Sets the test type to the parameter
	 * 
	 * @param testType the testType to set
	 * @throws IllegalArgumentException if the test type string is null or empty
	 */
	public void setTestType(String testType) {
		if ("".equals(testType) || testType == null) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testType = testType;
	}

	/**
	 * Returns the test description of the test case
	 * 
	 * @return the testDescription
	 */
	public String getTestDescription() {
		return testDescription;
	}

	/**
	 * Sets the test description to the provided string
	 * 
	 * @param testDescription the testDescription to set
	 * @throws IllegalArgumentException if the test description string is null or empty
	 */
	public void setTestDescription(String testDescription) {
		if ("".equals(testDescription) || testDescription == null) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testDescription = testDescription;
	}

	/**
	 * Returns the expected results
	 * 
	 * @return the expectedResults
	 */
	public String getExpectedResults() {
		return expectedResults;
	}

	/**
	 * Sets the expectedResults to the provided string
	 * 
	 * @param expectedResults the expectedResults to set
	 * @throws IllegalArgumentException if the expected results is null or empty
	 */
	public void setExpectedResults(String expectedResults) {
		if ("".equals(expectedResults) || expectedResults == null) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.expectedResults = expectedResults;
	}

	/**
	 * Creates a TestResult from the given values and adds the TestResult to the end
	 * of the testResults log.
	 * 
	 * @param passing      true if test passing; false otherwise
	 * @param actualResult actual results of the test result
	 * @throws IllegalArgumentException if the test result cannot be created or added
	 */
	public void addTestResult(boolean passing, String actualResult) {
		try {
			TestResult testResult = new TestResult(passing, actualResult);
			testResults.add(testResult);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid test results.");
		}

	}

	/**
	 * Returns true if the last TestResult in the Log is passing; False otherwise
	 * 
	 * @return if the test case is passing or not. true if passing, false otherwise
	 */
	public boolean isTestCasePassing() {

		if (testResults.size() == 0) {
			return false;
		}
		return testResults.get(testResults.size() - 1).isPassing();
	}

	/**
	 * Returns the status of the TestCase as “PASS” or “FAIL”.
	 * 
	 * @return Test.Result.PASS if the status of the test case is passing. return
	 *         fail otherwise
	 */
	public String getStatus() {
		if (isTestCasePassing()) {
			return TestResult.PASS;
		}
		return TestResult.FAIL;
	}

	/**
	 * Returns a string representation of the testResults Log
	 * 
	 * @return null the testResults log as a string
	 */
	public String getActualResultsLog() {
		String resultsLog = "";
		for (int i = 0; i < testResults.size(); i++) {
			resultsLog += "- " + testResults.get(i).toString() + "\n";
		}
		return resultsLog;
	}

	/**
	 * Gets the test plan of the test case
	 * 
	 * @return the testPlan
	 */
	public TestPlan getTestPlan() {
		return testPlan;
	}

	/**
	 * Sets the test plan
	 * 
	 * @param testPlan the testPlan to set
	 * @throws IllegalArgumentException if the testplan is null
	 */
	public void setTestPlan(TestPlan testPlan) {
		if (testPlan == null) {
			throw new IllegalArgumentException("Invalid test plan.");
		}
		this.testPlan = testPlan;
	}

	/**
	 * Returns a string version of the test cases information
	 * 
	 * @return null the test case as a string
	 */
	public String toString() {
		String results = "# " + testCaseId + "," + testType + "\n";
		results += "* " + testDescription + "\n";
		results += "* " + expectedResults + "\n";
		results += getActualResultsLog();
		return results;
	}
}
