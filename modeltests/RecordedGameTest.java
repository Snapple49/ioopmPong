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

public class RecordedGameTest {

	MyPongModel testerModel = new MyPongModel("p1", "p2", new Point(600, 400), new Point(0, 0));
	Set<Input> testInput = new HashSet<Input>();
	Input leftBarKey = new Input(BarKey.LEFT, Dir.UP);
	Input rightBarKey = new Input(BarKey.RIGHT, Dir.DOWN);

	@Test
	public void testGame() {
		
		testInput.add(leftBarKey); //Position left barkey
		for (int i = 0; i<300; i++){
			testerModel.compute(testInput, 1);
		}
		testInput.remove(leftBarKey);
		
		testInput.add(rightBarKey); //Position right barkey
		for (int i = 0; i<100; i++){
			testerModel.compute(testInput, 1);
		}
		testInput.remove(rightBarKey);
		
		testerModel.getBall().setBall(new Point(600, 400), new Point(-10, -5)); // Start moving ball in desired track
		for (int i = 0; i < 60; i++) {
			testerModel.compute(testInput, 1);
		}
		System.out.println("Ball position: " + testerModel.getBallPos().toString());
		System.out.println("Ball velocity: " + testerModel.getBall().velocity.toString());
		System.out.println("Left barkey position: " + testerModel.getBarPos(BarKey.LEFT));
		assertEquals(testerModel.getScore(BarKey.RIGHT), "1");
		assertEquals(testerModel.getBallPos(), new Point(0, 500));
		//TODO fix increasing speed consideration in positioning
			
	}

}