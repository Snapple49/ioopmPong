package modeltests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import model.BarKey;
import model.Input;
import model.MyPongModel;
import model.Input.Dir;

import org.junit.Test;

public class AngleAfterHitTest {

	MyPongModel testerModel = new MyPongModel("p1", "p2", new Point(600, 400), new Point(20, 0));
	Set<Input> testInput = new HashSet<Input>();
	Input leftBarKey = new Input(BarKey.LEFT, Dir.UP);
	Input rightBarKey = new Input(BarKey.RIGHT, Dir.DOWN);

	@Test
	public void centralCollisionTest1() {
		for (int i = 0; i < 28; i++) {			
			testerModel.compute(testInput, 1);
		}
		Point velocityBeforeCollision = testerModel.getBallPos();
		
		for (int i = 0; i < 4; i++) {			
			testerModel.compute(testInput, 1);
		}
		Point velocityAfterCollision = testerModel.getBallPos();
		assertTrue(velocityAfterCollision == velocityBeforeCollision); // Samma objekt, hämta istället y-värde och jämför.
		assertEquals(velocityAfterCollision.y, velocityBeforeCollision.y);


	}
	@Test
	public void centralCollisionTest2() {
		//TODO angled test, position barkey then move ball at it's center
	}
}
