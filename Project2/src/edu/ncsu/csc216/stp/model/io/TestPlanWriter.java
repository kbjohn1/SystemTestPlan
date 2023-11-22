package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.PrintStream;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Writes the open test plan’s to the given file.
 * 
 * @author Kevin John
 * @author Jordan Robertson
 */
public class TestPlanWriter {

	/**
	 * Receives a file with the file name to write to and an ISortedList of
	 * TestPlans to write to file.
	 * 
	 * @param file file name of the information being written to
	 * @param plan information being written to the file
	 * @throws IllegalArgumentException if any exceptions are thrown when trying to write the file
	 */
	public static void writeTestPlanFile(File file, ISortedList<TestPlan> plan) {
		try {
			PrintStream fileWriter = new PrintStream(file);
			for (int i = 0; i < plan.size(); i++) {
				fileWriter.println("! " + plan.get(i).getTestPlanName());
				for (int j = 0; j < plan.get(i).getTestCases().size(); j++) {
					fileWriter.print(plan.get(i).getTestCases().get(j).toString());
				}
			}
			fileWriter.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
