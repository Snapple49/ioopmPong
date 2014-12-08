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
	Input rightBarKey = new Input(BarKey.RIGHT, Dir.UP);

	@Test
	public void testGame() {
		
		testInput.add(leftBarKey); //Position left barkey
		for (int i = 0; i<300; i++){
			testerModel.compute(testInput, 2);
		}
		testInput.remove(leftBarKey);
				
		testInput.add(rightBarKey); //Position right barkey
		for (int i = 0; i<100; i++){
			testerModel.compute(testInput, 2);
		}
		testInput.remove(rightBarKey);
				
		testerModel.getBall().setBall(new Point(600, 400), new Point(-10, -5)); // Start moving ball in desired track
		for (int i = 0; i < 199; i++) {
			testerModel.compute(testInput, 1);
		}
		assertEquals(testerModel.getBallPos(), new Point(20, 595)); //Ball is in expected position before left barkey misses
		testerModel.compute(testInput, 1);
		//Expected score after left player misses
		assertEquals(testerModel.getScore(BarKey.RIGHT), "1");
		assertEquals(testerModel.getScore(BarKey.LEFT), "0");
			
	}

}
