package modeltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	RecordedGameTest.class, 
	AngleAfterHitTest.class
})	

public class RegressionTests {
}
