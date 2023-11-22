package edu.ncsu.csc216.stp.model.tests;

/**
 * Represents the result of a test case execution.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class TestResult {

	/** test pass string constant*/
	public static final String PASS = "PASS";
	
	/** test fail string constant*/
	public static final String FAIL = "FAIL";
	
	/** status of the test result*/
	private boolean passing;
	
	/** actual result of the test*/
	private String actualResults;
	
	/**
	 * Constructs the Test Result with the given parameters 
	 * @param passing passing status of the test result
	 * @param actualResults the actual results of the test
	 * @throws IllegalArgumentException if an exception is thrown when creating the TestResult object
	 */
	public TestResult(boolean passing, String actualResults) {
		try {
			setPassing(passing);
			setActualResults(actualResults);
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid test results.");
		}
		
	}

	/**
	 * Returns the passing boolean
	 * @return passing test results status
	 */
	public boolean isPassing() {
		return passing;
	}

	/**
	 * Sets the passing boolean to the value of the parameter
	 * @param passing the passing to set
	 */
	public void setPassing(boolean passing) {
		this.passing = passing;
	}

	/**
	 * Returns the actual results string
	 * @return the actualResults
	 */
	public String getActualResults() {
		return actualResults;
	}

	/**
	 * Checks for a valid actualResults string
	 * @param actualResults the actualResults to set
	 * @throws IllegalArgumentException if parameter is null or empty string
	 */
	public void setActualResults(String actualResults) {
		if("".equals(actualResults) || actualResults == null) {
			throw new IllegalArgumentException("Invalid test results.");
		}
		this.actualResults = actualResults;
	}

	/**
	 * Returns a string representation of the TestResult for printing to a file and listing in the GUI.
	 */
	@Override
	public String toString() {
		if(passing) {
			return PASS + ": " + actualResults;
		} else {
			return FAIL + ": " + actualResults; 
		}
	}
	
	
}
