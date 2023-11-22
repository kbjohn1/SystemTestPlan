package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.tests.TestResult;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Contains a list of test plans, the FailingTestList, and operations to
 * manipulate these lists.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class TestPlanManager {

	/** isChanged boolean value for the test plan manager */
	private boolean isChanged;

	/** current test plan in the manager */
	private AbstractTestPlan currentTestPlan;

	/** list of failing tests */
	private FailingTestList failingTestList;

	/** list of test plans */
	private ISortedList<TestPlan> testPlans;

	/**
	 * Contains an ISortedList of TestPlans, one FailingTestList, an
	 * AbstractTestPlan for the currentTestPlan, and a boolean flag that keeps track
	 * of if the TestPlanManager has been changed since the last save
	 */
	public TestPlanManager() {
		clearTestPlans();
	}

	/**
	 * The TestPlanReader is used to load the file and return a list of potential
	 * TestPlans to add to the list of TestPlans.
	 * 
	 * @param testPlanFile text file being read for test plans
	 */
	public void loadTestPlans(File testPlanFile) {
		try {
			testPlans = TestPlanReader.readTestPlansFile(testPlanFile);
			setCurrentTestPlan(failingTestList.getTestPlanName());
			isChanged = true; // Set isChanged to true when loading test plans.
		} catch (Exception e) {
			// Handle the exception (e.g., print an error message or log the exception).
			e.printStackTrace();
		}
	}

	/**
	 * Saves the current TestPlans to the given file.
	 * 
	 * @param testPlanFile text file that the test plans are being saved to
	 */
	public void saveTestPlans(File testPlanFile) {
		TestPlanWriter.writeTestPlanFile(testPlanFile, testPlans);
		isChanged = false;
	}

	/**
	 * Changes the value of the isChanged boolean
	 * 
	 * @return false if true; false otherwise
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * TestPlan is added to the list of test plans, the current test plan is updated
	 * to the new test plan
	 * 
	 * @param testPlanName name of the test plan being added
	 * @throws IllegalArgumentException if the test plan name is Failing Tests
	 * @throws IllegalArgumentException if the test plan name is already being used. Case insensitive.
	 */
	public void addTestPlan(String testPlanName) {
		try {
			if (testPlanName.equals(FailingTestList.FAILING_TEST_LIST_NAME)) {
				throw new IllegalArgumentException("Invalid name.");
			}
			for (int i = 0; i < testPlans.size(); i++) {
				if (testPlans.get(i).getTestPlanName().equalsIgnoreCase(testPlanName)) {
					throw new IllegalArgumentException("Invalid name.");
				}
			}
			TestPlan newTestPlan = new TestPlan(testPlanName);
			testPlans.add(newTestPlan);
			setCurrentTestPlan(testPlanName);
			isChanged = true;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid name.");
		}
	}

	/**
	 * Returns a list of test plan names. The “Failing Tests” list is always listed
	 * first.
	 * 
	 * @return null string array of the test plan names
	 */
	public String[] getTestPlanNames() {
		String[] testPlanNames = new String[testPlans.size() + 1];
		testPlanNames[0] = failingTestList.getTestPlanName();
		for (int i = 1; i <= testPlans.size(); i++) { // Change here: start from 1
			testPlanNames[i] = testPlans.get(i - 1).getTestPlanName();
		}
		return testPlanNames;
	}

	/**
	 * Helper method useful when working with the FailingTestList
	 */
	private void getFailingTests() {
		failingTestList = new FailingTestList();
		for (int i = 0; i < testPlans.size(); i++) {
			ISwapList<TestCase> testCases = testPlans.get(i).getTestCases();
			for (int j = 0; j < testCases.size(); j++) {
				if (!testCases.get(j).isTestCasePassing()) {
					failingTestList.addTestCase(testCases.get(j));
				}
			}
		}
	}

	/**
	 * Sets the current test plans name to the provided test plan name
	 * 
	 * @param testPlanName new name for the current test plan
	 */
	public void setCurrentTestPlan(String testPlanName) {
		getFailingTests();
		currentTestPlan = failingTestList;
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlanName.equals(testPlans.get(i).getTestPlanName())) {
				currentTestPlan = testPlans.get(i);
			}
		}
	}

	/**
	 * Returns the current test plan
	 * 
	 * @return null the current test plan
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		return currentTestPlan;
	}

	/**
	 * Edits the currentTestPlan without editing the TestPlan in place in the
	 * ISortedList
	 * 
	 * @param testPlanName name of the test plan being edited
	 * @throws IllegalArgumentException if the current test plan is the failing test list
	 * @throws IllegalArgumentException if the name of the test plan is Failing tests. Case insensitive
	 * @throws IllegalArgumentException if the testplanname has already been used
	 */
	public void editTestPlan(String testPlanName) {
		if (currentTestPlan instanceof FailingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		}

		if (testPlanName.toLowerCase().equals("Failing Tests".toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}

		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlanName.toLowerCase().equals(testPlans.get(i).getTestPlanName().toLowerCase())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}

		currentTestPlan.setTestPlanName(testPlanName);

		isChanged = true;
	}

	/**
	 * Removes the the current test plan and sets it to the failing test list.
	 * isChanged is updated to true
	 * 
	 * @throws IllegalArgumentException if the current test plan is the failing test list
	 */
	public void removeTestPlan() {
		if (currentTestPlan instanceof FailingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		}

		for (int i = 0; i < testPlans.size(); i++) {
			if (currentTestPlan.equals(testPlans.get(i))) {
				testPlans.remove(i);
			}
		}
		currentTestPlan = failingTestList;
		isChanged = true;
	}

	/**
	 * Adds the provided test case object to the test plan
	 * 
	 * @param t test case being added to the test plan
	 */
	public void addTestCase(TestCase t) {
		if (!(currentTestPlan instanceof TestPlan)) {
			return;
		}

		currentTestPlan.addTestCase(t);

		if (t.getStatus().equals(TestResult.FAIL)) {
			failingTestList.addTestCase(t);
			getFailingTests();
		}

		isChanged = true;
	}

	/**
	 * Adds the test result to the test case at the given index in the current test
	 * plan. If the tests are failing, then the Failing Test List should be updated.
	 * 
	 * @param idx          index at which the test case is being added
	 * @param passing      represents is the test is passing
	 * @param actualResult the actual results of the test
	 */
	public void addTestResult(int idx, boolean passing, String actualResult) {
		currentTestPlan.getTestCase(idx).addTestResult(passing, actualResult);

		if (!passing) {
			failingTestList.addTestCase(currentTestPlan.getTestCase(idx));
			getFailingTests();
		}
	}

	/**
	 * Clears out the TestPlanManager by setting testPlans to an empty SortedList,
	 * failingTestList to an empty FailingTestList(), currentTestPlan to the
	 * failingTestList, and isChanged to false.
	 */
	public void clearTestPlans() {
		SortedList<TestPlan> clearPlans = new SortedList<TestPlan>();
		testPlans = clearPlans;
		FailingTestList clearFailing = new FailingTestList();
		failingTestList = clearFailing;
		currentTestPlan = failingTestList;
		isChanged = false;
	}

}
