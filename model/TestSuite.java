/* Code borrowed http://www.tutorialspoint.com/junit/junit_suite_test.htm */


package model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MyPongModelTest.class, BallTest.class })
public class TestSuite {

} 
