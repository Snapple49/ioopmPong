package model;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import model.Input.Dir;

import org.junit.Test;

public class RecordedGameTest {

	@Test
	public void testGame() {
		MyPongModel testerModel = new MyPongModel("p1", "p2", new Point(600, 400), new Point(0, 0));
		Set<Input> testInput = new HashSet<Input>();
		Input leftBarKey = new Input(BarKey.LEFT, Dir.UP);
		Input rightBarKey = new Input(BarKey.RIGHT, Dir.DOWN);
		System.out.println("Start");
		testInput.add(leftBarKey);
		//testInput.add(rightBarKey);
		for (int i = 0; i<300; i++){
			testerModel.compute(testInput, 1);
		}
		System.out.println("" + testerModel.getBarPos(BarKey.RIGHT) + "\n" + testerModel.getBarPos(BarKey.LEFT));
		//testerModel.getBallPos().setBall(testerModel.center, new Point(10, 5));


	}

}
