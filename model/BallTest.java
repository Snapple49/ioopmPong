package model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class BallTest {
	Point startPoint = new Point(500, 500);
	
	@Test
	public void testMoveHorizontal() {
		Ball ball = new Ball(startPoint, new Point(10, 0));
		Point destPos = startPoint;
		for (int i = 0; i < 10; i++) {
			ball.move();
			destPos.x += 10;
			assertTrue(ball.x == destPos.x);
		}		
	}

	@Test
	public void testMoveVertical() {
		Ball ball = new Ball(startPoint, new Point(0, 5));
		Point destPos = startPoint;
		for (int i = 0; i < 10; i++) {
			ball.move();
			destPos.y +=5;
			assertTrue(ball.y == destPos.y);
		}
	}

	@Test
	public void testMoveDiagonal() {
		Ball testBall = new Ball(startPoint, new Point(9, -7));
		Point destPos = startPoint;
		for (int i = 0; i < 10; i++) {
			testBall.move();
			destPos.x +=9;
			destPos.y +=-7;
			assertTrue(((Point)testBall).equals(destPos));
		}
	}
	
	@Test
	public void testChangeDir() {
		Point vel = new Point(-8, 3);
		Ball testBall = new Ball(startPoint, vel);
		testBall.changeDirection(false);
		assertTrue(testBall.velocity.x == -8 && testBall.velocity.y == -3);
		testBall.changeDirection(true);
		assertTrue(testBall.velocity.x == 8 && testBall.velocity.y == -3);
		testBall.changeDirection(true);
		assertTrue(testBall.velocity.x == -8 && testBall.velocity.y == -3);
		testBall.changeDirection(false);
		assertTrue(testBall.velocity.x == -8 && testBall.velocity.y == 3);
	}

	@Test
	public void testSetter() {
		Ball testBall = new Ball(startPoint, new Point(0, 0));
		try {
			testBall.setBall(startPoint, new Point(10, 0));
		} catch(Ball.InvalidVelocityException e){
		}
		assertEquals((Point)testBall, startPoint);
		
		testBall.move();
		Point destPos = startPoint;
		destPos.x += 10;
		assertEquals(((Point)testBall), destPos);
		
		int excptTest = 0;
		try{
			testBall.setBall(startPoint, new Point(20, 20));
		} catch (Ball.InvalidVelocityException e){
			excptTest = 1;
		}
		assertTrue(excptTest == 1);
	}
}
