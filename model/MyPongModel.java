package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

public class MyPongModel implements PongModel {
	private int leftBarHeight;
	private int rightBarHeight;
	private int leftPos;
	private int rightPos;
	private int leftScore;
	private int rightScore;
	private final Dimension fieldSize;
	private Ball ball;
	public final Point center; 
	

	public MyPongModel(String leftPlayer, String rightPlayer) {
		this.leftBarHeight = 150;
		this.rightBarHeight = 150;
		this.fieldSize = new Dimension(1200,800);
		this.center = new Point(((fieldSize.width)/2), ((fieldSize.height)/2));
		this.leftPos = center.y;
		this.rightPos = center.y;
		this.leftScore = 0;
		this.rightScore = 0;
		this.ball = new Ball(center, new Point(8, 0));

	}

	public MyPongModel(String leftPlayer, String rightPlayer, Point ballPos, Point velocity) {
		this.leftBarHeight = 150;
		this.rightBarHeight = 150;
		this.fieldSize = new Dimension(1200,800);
		this.center = new Point(((fieldSize.width)/2), ((fieldSize.height)/2));
		this.leftPos = center.y;
		this.rightPos = center.y;
		this.leftScore = 0;
		this.rightScore = 0;
		this.ball = new Ball(ballPos, velocity);

	}


	/**
	 * Takes the inputs and applies them to the model, computing one
	 * simulation step. delta_t is the time that has passed since the
	 * last compute step -- use this in your time integration to have
	 * the items move at the same speed, regardless of the framerate.
	 */
	public void compute(Set<Input> input, long delta_t) { //TODO Move both simultaneously
		this.ball.move();
		this.checkCollision(BarKey.LEFT, BarKey.RIGHT);
		for(Input i : input){
			switch (i.key) {
			case RIGHT:
				switch(i.dir){
				case UP:
					rightPos -=delta_t/3;
					break;
				case DOWN:
					rightPos +=delta_t/3;
					break;
				}	
				break;
			case LEFT:
				switch(i.dir){
				case UP:
					leftPos -=delta_t/3;
					break;
				case DOWN:
					leftPos +=delta_t/3;
					break;
				}
				break;

			}
		}

	}
	

	public void checkCollision(BarKey left, BarKey right){
		if (this.ball.y >= fieldSize.height || this.ball.y <= 0){
			this.ball.changeDirection(false);
		}
		if (this.ball.x >= this.fieldSize.width){ //Right side of board
			hitSide(right, rightBarHeight, false); //Check if right missed
		}
		if (this.ball.x <= 0){ //Left side of board
			hitSide(left, leftBarHeight, true); //Check if left missed
		}
	}	

	private void hitSide(BarKey bar, int barHeight, boolean rightGetsPoint) {
		if(this.ball.y > this.getBarPos(bar)-(barHeight/2) && this.ball.y < this.getBarPos(bar)+(barHeight/2)){	//Ball within barkey			
			hitBarKey(bar, barHeight);
		}else{
			score(rightGetsPoint);
		}
	}

	
	private void hitBarKey(BarKey bar, int barHeight) {
		int ballPos = this.getBallPos().y;
		int barUpperEnd;
		int smallUpperCurve;
		int bigUpperCurve;
		int barLowerEnd;
		int smallLowerCurve;
		int bigLowerCurve;
		switch (bar) {
			case LEFT:
				barUpperEnd = this.leftPos - (this.leftBarHeight/2);
				smallUpperCurve = this.leftPos - (this.leftBarHeight/3);
				bigUpperCurve = smallUpperCurve - (this.leftBarHeight/3);
				barLowerEnd = this.leftPos + (this.leftBarHeight/2);
				smallLowerCurve = this.leftPos + (this.leftBarHeight/3);
				bigLowerCurve = smallLowerCurve + (this.leftBarHeight/3);
				if (ballPos >= barUpperEnd && ballPos <= bigUpperCurve) {
					this.ball.velocity.y = -4;
				}
				if (ballPos > bigUpperCurve && ballPos <= smallUpperCurve) {
					this.ball.velocity.y = -2;
				}
				if (ballPos >= smallLowerCurve && ballPos <= bigLowerCurve) {
					this.ball.velocity.y = 2;
				}
				if (ballPos >= bigLowerCurve && ballPos <= barLowerEnd) {
					this.ball.velocity.y = 4;
				}
				this.ball.changeDirection(true);
				break;
				
				
				/*int diffBarBallLeft = this.leftPos - this.getBallPos().y;
				int hitPosLeft = diffBarBallLeft + this.leftBarHeight;
				this.ball.changeDirection(true);
				this.ball.velocity.y += (hitPosLeft / 50);*/
				
			case RIGHT:	
				barUpperEnd = this.rightPos - (this.rightBarHeight/2) - 10;
				smallUpperCurve = this.rightPos - (this.rightBarHeight/3);
				bigUpperCurve = smallUpperCurve - (this.rightBarHeight/3);
				barLowerEnd = this.rightPos + (this.rightBarHeight/2) + 10;
				smallLowerCurve = this.rightPos + (this.rightBarHeight/3);
				bigLowerCurve = smallLowerCurve + (this.rightBarHeight/3);
				if (ballPos >= barUpperEnd && ballPos <= bigUpperCurve) {
					this.ball.velocity.y = -2;
				}
				if (ballPos > bigUpperCurve && ballPos <= smallUpperCurve) {
					this.ball.velocity.y = -1;
				}
				if (ballPos >= smallLowerCurve && ballPos <= bigLowerCurve) {
					this.ball.velocity.y = 1;
				}
				if (ballPos >= bigLowerCurve && ballPos <= barLowerEnd) {
					this.ball.velocity.y = 2;
				}
				this.ball.changeDirection(true);
				break;
				/*
				int diffBarBallRight = this.rightPos - this.getBallPos().y;
				int hitPosRight = diffBarBallRight + this.leftBarHeight;
				this.ball.changeDirection(true);
				this.ball.velocity.y += (hitPosRight / 50);*/
				
		}
		

		
		
	}
	
	private void score(boolean rightGetsPoint){
		if (rightGetsPoint) {
			rightScore++;
		}else{
			leftScore++;
		}
		reset();
	}
	
	private void reset() {
		int dir = (int) (Math.random()*2 + 1);
		if (dir == 1){
			dir = -8;
		}else{
			dir = 8;
		}
		this.ball.setBall(this.center, new Point(dir, 0));
		this.rightPos = this.center.y;
		this.leftPos = this.center.y;
	}

	/**
	 * getters that take a BarKey LEFT or RIGHT
	 * and return positions of the various items on the board
	 */

	public int getBarHeight(BarKey k) {
		switch (k){
		case LEFT:
			return leftBarHeight;
		case RIGHT:
			return rightBarHeight;
		default:
			return 0;
		}
	}
	
	public void setBarHeight(BarKey k, int newBarHeight) {
		switch(k) {
		case LEFT:
			this.leftBarHeight = newBarHeight;
		case RIGHT:
			this.rightBarHeight = newBarHeight;
			
		}
	}


	/**
	 * Will output information about the state of the game to be
	 * displayed to the players
	 */
	public String getMessage() {
		return "Det h�r �r ett kul spel!";
	}



	/**
	 * getters that take a BarKey LEFT or RIGHT
	 * and return positions of the various items on the board
	 */
	public int getBarPos(BarKey k) {
		switch (k) {
		case LEFT: 
			return this.leftPos;
		case RIGHT:
			return this.rightPos;
		default:
			return 0;
		}

	}
	public Ball getBallPos() {
		return this.ball;

	}



	/**
	 * getter for the scores.
	 */
	public String getScore(BarKey k) {
		switch (k) {
		case LEFT: 
			return ((Integer)this.leftScore).toString();
		case RIGHT:
			return ((Integer)this.rightScore).toString();
		default:
			return "";

		}

	}

	/**
	 * a valid implementation of the model will keep the field size
	 * will remain constant forever.
	 */
	public Dimension getFieldSize() {
		return this.fieldSize;
	}

}