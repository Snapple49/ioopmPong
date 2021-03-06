package modeltests;

import java.awt.Point;
import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.*;
import model.BarKey;
import model.Input;
import model.MyPongModel;
import model.Input.Dir;

import org.junit.Test;

public class MyPongModelTest {


	@Test
	public void computeTest() {
		MyPongModel testModel = new MyPongModel("hej", "hoj");
		Set<Input> testInput = new HashSet<Input>();
		Set<Input> testInput2 = new HashSet<Input>();
		
		Input testBarRightDown = new Input(BarKey.RIGHT, Dir.DOWN);
		Input testBarRightUp = new Input(BarKey.RIGHT, Dir.UP);
		Input testBarLeftDown = new Input(BarKey.LEFT, Dir.DOWN);
		Input testBarLeftUp = new Input(BarKey.LEFT, Dir.UP);
		
		testInput.add(testBarRightDown);
		testInput2.add(testBarRightUp);
		testInput2.add(testBarLeftDown);
		testInput.add(testBarLeftUp);
		
		testModel.compute(testInput, 2);
		assertTrue(testModel.getBarPos(BarKey.LEFT) == 399);
		
		assertTrue(testModel.getBarPos(BarKey.RIGHT) == 401);
		testModel.compute(testInput2, 2);
		assertTrue(testModel.getBarPos(BarKey.LEFT) == 400);
		assertTrue(testModel.getBarPos(BarKey.RIGHT) == 400);
		
		
		testModel.compute(testInput, 2);
		testModel.compute(testInput, 2);
		testModel.compute(testInput, 2);
		assertTrue(testModel.getBarPos(BarKey.RIGHT) == 403);
		assertTrue(testModel.getBarPos(BarKey.LEFT) == 397);
		testModel.compute(testInput2, 2);
		testModel.compute(testInput2, 2);
		testModel.compute(testInput2, 2);
		assertTrue(testModel.getBarPos(BarKey.RIGHT) == 400);
		assertTrue(testModel.getBarPos(BarKey.LEFT) == 400);
		
		
	}
	@Test
	public void scoreUsingBallTest() {
		Set<Input> testInput = new HashSet<Input>();
		Input testBarRightDown = new Input(BarKey.RIGHT, Dir.DOWN);
		testInput.add(testBarRightDown);
		MyPongModel testModel = new MyPongModel("hej", "hoj", new Point(1191, 500), new Point(5,0));
		testModel.compute(testInput, 1);
		testModel.compute(testInput, 1);
		assertTrue(testModel.getScore(BarKey.LEFT).equals("1"));	
	}
	@Test
	public void checkCollissionTopBottomTest() {
		MyPongModel testModel = new MyPongModel("hej", "hoj", new Point(50, 5), new Point(0,-5));
		
		Set<Input> testInput = new HashSet<Input>();
		Input testBarRightDown = new Input(BarKey.RIGHT, Dir.DOWN);
		testInput.add(testBarRightDown);
		
		testModel.compute(testInput, 1);
		assertTrue(testModel.getBallPos().y == 0);
		testModel.compute(testInput, 1);
		assertTrue(testModel.getBallPos().y == 5);
		MyPongModel testModel2 = new MyPongModel("hej", "hoj", new Point(50, 795), new Point(0,5));
		testModel2.compute(testInput, 1);
		assertTrue(testModel2.getBallPos().y == 800);
		testModel2.compute(testInput, 1);
		assertTrue(testModel2.getBallPos().y == 795);
	}
	@Test
	public void testCheckCollisionBars() {
		MyPongModel testModel = new MyPongModel("hej", "hoj", new Point(1195, 400), new Point(5,0));
		
		Set<Input> testInput = new HashSet<Input>();
		Input testBarRightDown = new Input(BarKey.RIGHT, Dir.DOWN);
		testInput.add(testBarRightDown);
		
		testModel.compute(testInput, 1);
		assertTrue(testModel.getBallPos().x == 1200);
		
		testModel.compute(testInput, 1);
		assertTrue(testModel.getBallPos().x == 1190);
		
		MyPongModel testModel2 = new MyPongModel("hej", "hoj", new Point(5, 400), new Point(-5,0));

		testModel2.compute(testInput, 1);
		assertTrue(testModel2.getBallPos().x == 0);
		
		testModel2.compute(testInput, 1);
		assertTrue(testModel2.getBallPos().x == 10);
		
		
	}
	@Test
	public void checkCollisionNoBarTest() {
		MyPongModel testModel = new MyPongModel("hej", "hoj", new Point(1195, 0), new Point(5,0));
		
		Set<Input> testInput = new HashSet<Input>();
		Input testBarRightDown = new Input(BarKey.RIGHT, Dir.DOWN);
		testInput.add(testBarRightDown);
		
		testModel.compute(testInput, 1);
		assertTrue(testModel.getBallPos().x == 600);
		
		MyPongModel testModel2 = new MyPongModel("hej", "hoj", new Point(5, 0), new Point(-5,0));
		
		testModel2.compute(testInput, 1);
		assertTrue(testModel2.getBallPos().x == 600);
		
		
	}
	@Test
	public void hitBarkeyMiddleTest() {
		MyPongModel testModel = new MyPongModel("hej", "hoj", new Point(1199, 400), new Point(5,0));
		int oldPosY = testModel.getBallPos().y;
		Set<Input> testInput = new HashSet<Input>();
		testModel.compute(testInput, 2);
		testModel.compute(testInput, 2);
		testModel.compute(testInput, 2);
		int newPosY = testModel.getBallPos().y;
		assertEquals(oldPosY, newPosY);
	}
	
	@Test
	public void hitBarkeySmallCurveTest() {
		MyPongModel testModel = new MyPongModel("hej", "hoj", new Point(1199, 374), new Point(5,0));
		int oldPosY = testModel.getBallPos().y;
		Set<Input> testInput = new HashSet<Input>();
		testModel.compute(testInput, 2);
		testModel.compute(testInput, 2);
		int newPosY = testModel.getBallPos().y;
		assertEquals(oldPosY - 4, newPosY);
	}
		
		
	@Test
	public void hitBarkeyBigCurveTest() {
		MyPongModel testModel = new MyPongModel("hej", "hoj", new Point(1199, 349), new Point(5,0));
		int oldPosY = testModel.getBallPos().y;
		Set<Input> testInput = new HashSet<Input>();
		testModel.compute(testInput, 2);
		testModel.compute(testInput, 2);
		int newPosY = testModel.getBallPos().y;
		assertEquals(oldPosY - 8, newPosY);
	}
}
