package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;


/**
 * The Class MyPongModel represents the model of the game, handling communication between controller and view. It contains methods for moving and modifying the barkeys, bouncing the ball on the sides or the barkeys, scoring and resetting the game. 
 */
public class MyPongModel implements PongModel {
	
	/** The left barkey's height. */
	private int leftBarHeight;
	
	/** The right barkey's height. */
	private int rightBarHeight;
	
	/** The left barkey's position. */
	private int leftPos;
	
	/** The right barkey's position. */
	private int rightPos;
	
	/** The left player's score. */
	private int leftScore;
	
	/** The right player's score. */
	private int rightScore;
	
	/** The field size. */
	private final Dimension fieldSize;
	
	/** The ball. */
	private Ball ball;
	
	/** The center of the field. */
	public final Point center; 
	
	/** The message showing the status of the game. */
	private String gameMessage = "Welcome to Pong!";
	
	/** The name of the left player. */
	private final String leftPlayer;
	
	/** The name of the right player. */
	private final String rightPlayer;
	
	/** The flag for checking for a new game */
	private boolean newGame = false;

	/**
	 * Instantiates a new pong game.
	 *
	 * @param leftPlayer the name of the left player.
	 * @param rightPlayer the name of the right player.
	 */
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

	/**
	 * Instantiates a new pong game with specified ball starting position and velocity.
	 *
	 * @param leftPlayer the name of the left player.
	 * @param rightPlayer the name of the right player.
	 * @param ballPos the position of the ball.
	 * @param velocity the velocity of the ball.
	 */
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


	

	/**
	 * 
	 * Takes the inputs and applies them to the model, computing one
	 * simulation step. This includes moving the ball, bouncing the ball on a side or barkey, scoring and moving the barkeys.   
	 *
	 * @param input the input from the keyboard for the different barkeys. 
	 * @param delta_t the time that has passed since the last compute step.
	 */
	public void compute(Set<Input> input, long delta_t) { //TODO Move both simultaneously
		if (newGame) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newGame = false;
			this.gameMessage = "New game started!";
			this.rightPos = this.center.y;
			this.leftPos = this.center.y;
			
		}
		if (delta_t > 1000){
			delta_t = 0;
		}
		this.ball.move();
		this.checkCollision(BarKey.LEFT, BarKey.RIGHT);
		for(Input i : input){

			switch (i.key) {
			case RIGHT:
				switch(i.dir){
				case UP:

					if(getBarPos(BarKey.RIGHT) > getBarHeight(BarKey.RIGHT)/2)
						rightPos-=delta_t/2;
					break;
				case DOWN:
					if(getBarPos(BarKey.RIGHT) < fieldSize.height-getBarHeight(BarKey.RIGHT)/2)
						rightPos+=delta_t/2;

					break;
				}	
				break;

			case LEFT:
				switch(i.dir){
				case UP:
					if(getBarPos(BarKey.LEFT) > getBarHeight(BarKey.LEFT)/2)
						leftPos-=delta_t/2;
					break;
				case DOWN:
					if(getBarPos(BarKey.LEFT) < fieldSize.height-getBarHeight(BarKey.LEFT)/2)
						leftPos+=delta_t/2;
					break;
				}
				break;

			}

		}
	}


	/**
	 * Checks whether the ball collides with either of the four sides of the game view. If the ball hits the upper or lower side, it will bounce of with the inverted velocity. If the ball hits the right or left side, the method hitSide is called.
	 *
	 * @param left the left barkey.
	 * @param right the right barkey.
	 */
	public void checkCollision(BarKey left, BarKey right){
		if (this.ball.y >= fieldSize.height || this.ball.y <= 0){
			this.ball.bounce(false);
		}
		if (this.ball.x >= this.fieldSize.width){ //Right side of board
			hitSide(right, rightBarHeight, false); //Check if right missed
		}
		if (this.ball.x <= 0){ //Left side of board
			hitSide(left, leftBarHeight, true); //Check if left missed
		}
	}	

	/**
	 * Determines whether the ball hits a barkey or not when the ball hits a side. 
	 *
	 * @param bar the barkey of the side that the ball hits.
	 * @param barHeight the height of the barkey.
	 * @param rightGetsPoint true if right player should be awarded the point if the ball does not hit the barkey.
	 */
	private void hitSide(BarKey bar, int barHeight, boolean rightGetsPoint) {
		if(this.ball.y > this.getBarPos(bar)-(barHeight/2) && this.ball.y < this.getBarPos(bar)+(barHeight/2)){	//Ball within barkey			
			hitBarKey(bar, barHeight);
		}else{
			score(rightGetsPoint);
		}
	}


	/**
	 * Determines how the ball's velocity should change depending on where the ball hits the barkey.
	 *
	 * @param bar the barkey that is hit by the ball.
	 * @param barHeight the height of the barkey that is hit by the ball.
	 */
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
			smallUpperCurve = this.leftPos - (this.leftBarHeight/6);
			bigUpperCurve = smallUpperCurve - (this.leftBarHeight/6);
			barLowerEnd = this.leftPos + (this.leftBarHeight/2);
			smallLowerCurve = this.leftPos + (this.leftBarHeight/6);
			bigLowerCurve = smallLowerCurve + (this.leftBarHeight/6);
			if (ballPos >= barUpperEnd && ballPos <= bigUpperCurve) {
				this.ball.setBall(new Point(this.ball.x, this.ball.y), new Point(this.ball.velocity.x, this.ball.velocity.y - 8));;
			}
			if (ballPos > bigUpperCurve && ballPos <= smallUpperCurve) {
				this.ball.setBall(new Point(this.ball.x, this.ball.y), new Point(this.ball.velocity.x, this.ball.velocity.y - 4));;
			}
			if (ballPos >= smallLowerCurve && ballPos <= bigLowerCurve) {
				this.ball.setBall(new Point(this.ball.x, this.ball.y), new Point(this.ball.velocity.x, this.ball.velocity.y + 4));;
			}
			if (ballPos >= bigLowerCurve && ballPos <= barLowerEnd) {
				this.ball.setBall(new Point(this.ball.x, this.ball.y), new Point(this.ball.velocity.x, this.ball.velocity.y + 8));;
			}
			this.ball.bounce(true);
			this.ball.setBall(getBallPos(), new Point(ball.velocity.x+5, ball.velocity.y));
			break;


		case RIGHT:	
			barUpperEnd = this.rightPos - (this.rightBarHeight/2);
			smallUpperCurve = this.rightPos - (this.rightBarHeight/6);
			bigUpperCurve = smallUpperCurve - (this.rightBarHeight/6);
			barLowerEnd = this.rightPos + (this.rightBarHeight/2);
			smallLowerCurve = this.rightPos + (this.rightBarHeight/6);
			bigLowerCurve = smallLowerCurve + (this.rightBarHeight/6);
			if (ballPos >= barUpperEnd && ballPos <= bigUpperCurve) {
				this.ball.setBall(new Point(this.ball.x, this.ball.y), new Point(this.ball.velocity.x, this.ball.velocity.y - 8));;
			}
			if (ballPos > bigUpperCurve && ballPos <= smallUpperCurve) {
				this.ball.setBall(new Point(this.ball.x, this.ball.y), new Point(this.ball.velocity.x, this.ball.velocity.y - 4));;
			}
			if (ballPos >= smallLowerCurve && ballPos <= bigLowerCurve) {
				this.ball.setBall(new Point(this.ball.x, this.ball.y), new Point(this.ball.velocity.x, this.ball.velocity.y + 4));;
			}
			if (ballPos >= bigLowerCurve && ballPos <= barLowerEnd) {
				this.ball.setBall(new Point(this.ball.x, this.ball.y), new Point(this.ball.velocity.x, this.ball.velocity.y + 8));;
			}
			this.ball.bounce(true);
			this.ball.setBall(getBallPos(), new Point(ball.velocity.x-5, ball.velocity.y));
			break;

		}




	}

	/**
	 * Decides what will happen when a player scores. When the ball hits a side without hitting the barkey, it increases the opposing players score and shortens the opposing player's barkey.
	 *
	 * @param rightGetsPoint if true, right player gets the point. 
	 */
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
			this.reset(true);
			this.newGame = true;
			

		}else if (leftScore == 10){
			gameMessage = this.leftPlayer + " won! Starting new game.";
			reset(true);
			this.newGame = true;
		}
		reset(false);
	}

	/**
	 * Resets the game by placing the ball in the middle heading either left or right, placing both barkeys in the middle. If someone has won, it also resets both scores and sets the barkeys' length to the initial value.    
	 *
	 * @param someoneWon true if someone has won. 
	 */
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
	 * and return positions of the various items on the board.
	 *
	 * @param k the k
	 * @return the bar height
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

	/**
	 * Sets the bar height of either LEFT or RIGHT.
	 *
	 * @param direction the barkey for which the height will be set. 
	 * @param newBarHeight the new height of the barkey.
	 */
	public void setBarHeight(BarKey direction, int newBarHeight) {
		switch(direction) {
		case LEFT:
			this.leftBarHeight = newBarHeight;
		case RIGHT:
			this.rightBarHeight = newBarHeight;

		}
	}



	

	/**
	 * Gets the string message with information about the state of the game to be
	 * displayed to the players.
	 *
	 * @return the message that will be displayed.
	 */
	public String getMessage() {
		return gameMessage;

	}



	/**
	 * getters that take a BarKey LEFT or RIGHT
	 * and return positions of the various items on the board.
	 *
	 * @param direction the direction of the barkey of which the position will be returned.
	 * @return the position of the barkey.
	 */
	public int getBarPos(BarKey direction) {
		switch (direction) {
		case LEFT: 
			return this.leftPos;
		case RIGHT:
			return this.rightPos;
		default:
			return 0;
		}

	}

	/**
	 * Getter for the ball position on the field
	 * 
	 * @return the position of the ball as a Point
	 */

	public Point getBallPos() {
		return new Point(this.ball.x, this.ball.y);
	}

	public Ball getBall() { //Intended for testing only
		return this.ball;
	}

	/**
	 * Gets the score of either the LEFT or the RIGHT barkey. 
	 *
	 * @param direction the direction of the barkey corresponding to the score that will be returned. 
	 * @return the score of the specified barkey.
	 */
	public String getScore(BarKey direction) {
		switch (direction) {
		case LEFT: 
			return ((Integer)this.leftScore).toString();
		case RIGHT:
			return ((Integer)this.rightScore).toString();
		default:
			return "";

		}

	}

	/**
	 * Gets the field size.
	 *
	 * @return the field size.
	 */
	public Dimension getFieldSize() {
		return this.fieldSize;
	}

}