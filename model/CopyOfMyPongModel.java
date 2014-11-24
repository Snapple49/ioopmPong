package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

public class CopyOfMyPongModel implements PongModel {
	private PointList<BarKey2> barKeys;
	private String leftScore;
	private String rightScore;
	private final Dimension fieldSize;
	private PointList<Ball> ball;


	public CopyOfMyPongModel(String leftPlayer, String rightPlayer) {
		this.ball = new PointList<Ball>(new Ball(600,400, new Point(-8, -8)), null);
		PointList<BarKey2> temp1 = new PointList<BarKey2>(new BarKey2(0, 400, 150), null);
		this.barKeys = new PointList<BarKey2>(new BarKey2(1200, 400, 150), temp1);
		
		this.leftScore = "0";
		this.rightScore = "0";
		this.fieldSize = new Dimension(1200,800);


	}
	

	/**
	 * Takes the inputs and applies them to the model, computing one
	 * simulation step. delta_t is the time that has passed since the
	 * last compute step -- use this in your time integration to have
	 * the items move at the same speed, regardless of the framerate.
	 */
	public void compute(Set<Input> input, long delta_t) {
		this.ball.move();
		//if (Input input == BarKey input) {
			//this.checkCollision(input);
		//}
		for(Input i : input){
			switch (i.key) {
			case RIGHT:
				switch(i.dir){
				case UP:
					rightPos--;
				case DOWN:
					rightPos++;
				}	
			case LEFT:
				switch(i.dir){
				case UP:
					leftPos--;
				case DOWN:
					leftPos++;
				}

			}
		}

	}

	public void checkCollision(BarKey bar){
		if (this.ball.y >= fieldSize.height || this.ball.y <= 0){
			this.ball.changeDirection(false);
		}
		switch (bar) {
		case LEFT:
		if (this.ball.x <= 0){
			if(this.ball.y < this.getBarPos(bar)-(leftBarHeight/2) && this.ball.y > this.getBarPos(bar)+(leftBarHeight/2)){				
				this.ball.changeDirection(true);
			}
		}
		case RIGHT:
		if (this.ball.x >= this.fieldSize.width){
			if(this.ball.y < this.getBarPos(bar)-(rightBarHeight/2) && this.ball.y > this.getBarPos(bar)+(rightBarHeight/2)){				
				this.ball.changeDirection(true);	
			}
		}
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


	/**
	 * Will output information about the state of the game to be
	 * displayed to the players
	 */
	public String getMessage() {
		return "Det här är ett kul spel!";
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
	public Point getBallPos() {
		return (Point) ball;

	}



	/**
	 * getter for the scores.
	 */
	public String getScore(BarKey k) {
		switch (k) {
		case LEFT: 
			return this.leftScore;
		case RIGHT:
			return this.rightScore;
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
