/* Code borrowed http://www.tutorialspoint.com/junit/junit_suite_test.htm */


package modeltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	UnitTestsSuite.class, 
	RegressionTests.class})


public class OurTestSuite {
} 
