package edu.ncsu.csc216.stp.model.io;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Processes a file containing test plans with zero to many test cases each with
 * zero to many test results.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class TestPlanReader {

	/**
	 * Reads the file and uses several delimiters to properly identify the
	 * information
	 * 
	 * @param file text file being read for test plan information
	 * @return null files testplans in a SortedList
	 * @throws IllegalArgumentExcpetion if unable to load the file
	 * @throws IllegalArgumentExcpetion if unable to load the file due to FileNotFoundException
	 */
	public static ISortedList<TestPlan> readTestPlansFile(File file) {
		try {
			// read in the file
			Scanner lineScan = new Scanner(file);
			// create testPlan sorted list object
			ISortedList<TestPlan> testPlans = new SortedList<TestPlan>();
			// read file into string
			String fileLines = "";
			// read file line by line
			while (lineScan.hasNextLine()) {
				// add \n to each line as delimiters
				String line = lineScan.nextLine();
				fileLines += line + "\n";
			}
			lineScan.close();
			// make sure the first character is !
			if ('!' == fileLines.charAt(0)) {
				// break the string into test plan tokens
				Scanner testPlanFileReader = new Scanner(fileLines);
				// ! means test plans
				testPlanFileReader.useDelimiter("\\r?\\n?[!]");
				// while the string has more test plans
				while (testPlanFileReader.hasNext()) {
					// tokens for test plan
					String testPlanString = testPlanFileReader.next();
					// strng totest plan object
					TestPlan testPlan = processTestPlan(testPlanString);
					// add test plan
					testPlans.add(testPlan);
				}
				testPlanFileReader.close();
			} else {
				throw new IllegalArgumentException("Unable to load file.");
			}

			return testPlans;
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}

	}

	/**
	 * Helper method. After the first delimiter has been used, this method
	 * differentiates each potential test case in the test plan. It uses the other
	 * helper method processTest to add validated test cases to the current test
	 * plan.
	 * 
	 * @param testPlan Test plan being scanned for test cases
	 * @return null test plan after the test cases have been verified and added
	 */
	private static TestPlan processTestPlan(String testPlan) {
		Scanner testPlanScan = new Scanner(testPlan);
		TestPlan newTestPlan = null;
		if (testPlanScan.hasNextLine()) {
			String testPlanName = testPlanScan.nextLine().trim();
			newTestPlan = new TestPlan(testPlanName);
		}

		testPlanScan.useDelimiter("\\r?\\n?[#]");

		while (testPlanScan.hasNext()) {
			try {
				TestCase testCase = processTest(newTestPlan, testPlanScan.next().trim());
				newTestPlan.addTestCase(testCase);
			} catch (Exception e) {
				// ignore
			}

		}
		testPlanScan.close();
		return newTestPlan;

	}

	/**
	 * Helper method. After another delimiter has been used, this method validates
	 * all potential test cases being scanned. These test cases are then formed
	 * returned.
	 * 
	 * @param testPlan test plan currently being scanned for test cases information
	 * @param test     name of the test
	 * @return null the test case after being constructed
	 * @throws IllegalArgumentException if the tokens of the first test case line does not equal 2
	 * @throws IllegalArgumentException if the tokens of the actual results line does not equal 2
	 * @throws IllegalArgumentException if the status of the result is not either PASS or FAIL
	 */
	private static TestCase processTest(AbstractTestPlan testPlan, String test) {
		try {
			Scanner testCaseScan = new Scanner(test);

			String firstLine = testCaseScan.nextLine();
			String[] firstResults = firstLine.split("[,]");
			if (firstResults.length != 2) {
				testCaseScan.close();
				throw new IllegalArgumentException("");
			}
			String testCaseId = firstResults[0].trim();
			String testType = firstResults[1].trim();

			testCaseScan.useDelimiter("\\r?\\n?[-]");
			String descriptResult = testCaseScan.next();

			String[] descriptResults = descriptResult.split("\\r?\\n?[*]");
			String description = descriptResults[1].trim();
			String expectedResult = descriptResults[2].trim();

			TestCase newTestCase = new TestCase(testCaseId, testType, description, expectedResult);
			while (testCaseScan.hasNext()) {
				String[] results = testCaseScan.next().split(":");
				if (results.length != 2) {
					testCaseScan.close();
					throw new IllegalArgumentException();
				} else {
					boolean passing;
					String status = results[0].trim();
					if ("FAIL".equals(status)) {
						passing = false;
					} else if ("PASS".equals(status)) {
						passing = true;
					} else {
						testCaseScan.close();
						throw new IllegalArgumentException();
					}
					String actualResults1 = results[1].trim();
					newTestCase.addTestResult(passing, actualResults1);
				}
			}
			testCaseScan.close();

			return newTestCase;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

	}
}
