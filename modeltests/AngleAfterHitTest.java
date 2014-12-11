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

	Set<Input> testInput = new HashSet<Input>();
	Input leftBarKey = new Input(BarKey.LEFT, Dir.UP);
	Input rightBarKey = new Input(BarKey.RIGHT, Dir.DOWN);

	@Test
	public void centralCollisionTestStraight() {
		MyPongModel testerModel = new MyPongModel("p1", "p2", new Point(600, 400), new Point(20, 0));
		for (int i = 0; i < 28; i++) {			
			testerModel.compute(testInput, 1);
		}
		Point velocityBeforeCollision = testerModel.getBall().velocity;

		for (int i = 0; i < 4; i++) {			
			testerModel.compute(testInput, 1);
		}
		Point velocityAfterCollision = testerModel.getBall().velocity;
		assertEquals(velocityAfterCollision.y, velocityBeforeCollision.y);
		

	}
	@Test
	public void centralCollisionTestAngled() {
		MyPongModel testerModel = new MyPongModel("p1", "p2", new Point(40, 380), new Point(-10, 5));
		for (int i = 0; i < 3; i++) {			
			testerModel.compute(testInput, 1);
		}
		Point velocityBeforeCollision = testerModel.getBall().velocity;

		for (int i = 0; i < 2; i++) {			
			testerModel.compute(testInput, 1);
		}
		Point velocityAfterCollision = testerModel.getBall().velocity;
		assertEquals(velocityAfterCollision.y, velocityBeforeCollision.y);
	}
}
