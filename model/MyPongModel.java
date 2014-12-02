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
	private String gameMessage = "Welcome to Pong!";
	private final String leftPlayer;
	private final String rightPlayer;

	public MyPongModel(String leftPlayer, String rightPlayer) {
		this.leftBarHeight = 150;
		this.rightBarHeight = 150;
		this.fieldSize = new Dimension(1200,800);
		this.center = new Point(((fieldSize.width)/2), ((fieldSize.height)/2));
		this.leftPos = center.y;
		this.rightPos = center.y;
		this.leftScore = 0;
		this.rightScore = 0;
		this.ball = new Ball(center, new Point(15, 0));
		this.leftPlayer = leftPlayer;
		this.rightPlayer = rightPlayer;

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
		this.leftPlayer = leftPlayer;
		this.rightPlayer = rightPlayer;

	}

	public boolean barkeysAreInField(){
		return(getBarPos(BarKey.RIGHT) < fieldSize.height-getBarHeight(BarKey.RIGHT)/2 &&
				getBarPos(BarKey.RIGHT) > getBarHeight(BarKey.RIGHT)/2 &&
				getBarPos(BarKey.LEFT) < fieldSize.height-getBarHeight(BarKey.LEFT)/2 &&
				getBarPos(BarKey.LEFT) > getBarHeight(BarKey.LEFT)/2);
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

					if(getBarPos(BarKey.RIGHT) > getBarHeight(BarKey.RIGHT)/2)
						rightPos-=delta_t/1.5;
					break;
				case DOWN:
					if(getBarPos(BarKey.RIGHT) < fieldSize.height-getBarHeight(BarKey.RIGHT)/2)
						rightPos+=delta_t/1.5;

					break;
				}	
				break;

			case LEFT:
				switch(i.dir){
				case UP:
					if(getBarPos(BarKey.LEFT) > getBarHeight(BarKey.LEFT)/2)
						leftPos-=delta_t/1.5;
					break;
				case DOWN:
					if(getBarPos(BarKey.LEFT) < fieldSize.height-getBarHeight(BarKey.LEFT)/2)
						leftPos+=delta_t/1.5;
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
			this.ball.setBall(getBallPos(), new Point(ball.velocity.x+5, ball.velocity.y));
			break;


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
			this.ball.setBall(getBallPos(), new Point(ball.velocity.x-5, ball.velocity.y));
			break;

		}




	}

	private void score(boolean rightGetsPoint){
		if (rightGetsPoint) {
			rightScore++;
			leftBarHeight -= 10;
			gameMessage = "Score to " + this.rightPlayer + "!";
		}else{
			leftScore++;
			rightBarHeight -= 10;
			gameMessage = "Score to " + this.leftPlayer + "!";
		}
		if (rightScore == 10){
			gameMessage = this.rightPlayer + " won! Starting new game.";
			reset(true);
		}else if (leftScore == 10){
			gameMessage = this.leftPlayer + " won! Starting new game.";
			reset(true);
		}
		reset(false);
	}

	private void reset(boolean someoneWon) {
		int dir = (int) (Math.random()*2 + 1);
		if (dir == 1){
			dir = -15;
		}else{
			dir = 15;
		}
		this.ball.setBall(this.center, new Point(dir, 0));
		this.rightPos = this.center.y;
		this.leftPos = this.center.y;

		if(someoneWon){
			rightScore = 0;
			leftScore = 0;
			rightBarHeight = 150;
			leftBarHeight = 150;
		}
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
		return gameMessage;
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