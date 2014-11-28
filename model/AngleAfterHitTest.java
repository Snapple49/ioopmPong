package model;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import model.Input.Dir;

import org.junit.Test;

public class AngleAfterHitTest {

	MyPongModel testerModel = new MyPongModel("p1", "p2", new Point(600, 400), new Point(20, 0));
	Set<Input> testInput = new HashSet<Input>();
	Input leftBarKey = new Input(BarKey.LEFT, Dir.UP);
	Input rightBarKey = new Input(BarKey.RIGHT, Dir.DOWN);

	@Test
	public void straightCollisionTest() {
		for (int i = 0; i < 28; i++) {			
			testerModel.compute(testInput, 1);
		}
		int velocityBeforeCollision;
		velocityBeforeCollision = testerModel.getBallPos().velocity.y;
		for (int i = 0; i < 4; i++) {			
			testerModel.compute(testInput, 1);
		}
		int velocityAfterCollision; 
		velocityAfterCollision = testerModel.getBallPos().velocity.y;
		
		assertEquals(velocityAfterCollision, -velocityBeforeCollision);


	}

}
