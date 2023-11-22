# SystemTestPlan Overview
An STP tool that helps with keeping track of multiple projects and their respective black box testing statuses.

Software engineers are often working on multiple projects at once. Each project has its own system test plan (STP) and set of system test cases. In an attempt to eliminate the multiple documents, this STP tool will store STPs for multiple projects in one place.

System testing ignores the internals of the program and instead focuses on the inputs and the expected results of the program as defined by the requirements. System testing is also called functional testing, closed-box testing, and black-box testing. The tester treats the program as a “closed box”; the program implementation that generates the program output is unknown. The tester identifies the expected program output from the requirements and can compare the actual output with the expected output.

Each system test tracks four pieces of information: (1) test ID, (2) description, (3) expected results, and (4) actual results.

<img width="1312" alt="image" src="https://github.com/kbjohn1/SystemTestPlan/assets/144649225/970bcc8c-4cd9-42d8-aa14-81e3ea8b2986">

# Data format 
<img width="854" alt="image" src="https://github.com/kbjohn1/SystemTestPlan/assets/144649225/b19bf714-a21f-4a7e-b7bd-5a713c992049">

The first line of the file starts with a ‘!’ and is followed by the name of a test plan. If the first line doesn’t start with ‘!’, then the entire file is invalid and is not loaded.

Lines starting with a ‘#’ represent test cases. Test Cases have a name and the type of test case (e.g., equivalence class, boundary value, etc.)

Lines starting with ‘*’ represent the test description OR the expected results. A valid test case has a test description and expected results (exactly two * items). These items may span multiple lines.

Lines representing test results in the results log start with ‘-‘. A valid line has either PASS or FAIL, followed by a “: “ and then a record of the actual results of execution. The actual results of execution can span multiple lines.

Any text between two lines starting with ‘’ and ‘’ or between two lines starting with ‘’ and ‘#’ or ‘-‘ or end of file is the expected results. This text may include any character except for ‘!’ or ‘#’ or ‘’ or ‘-‘. Formatting of the text should be retained when reading/writing.

The smallest file contains only a test plan name. It is ok to have a test plan with no test cases.
