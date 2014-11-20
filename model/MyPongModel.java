package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

public class MyPongModel implements PongModel {
	private int leftPos;
	private int rightPos;
	private String leftScore;
	private String rightScore;
	private Dimension fieldSize;
	private Point ball;
	
	public MyPongModel(String leftPlayer, String rightPlayer) {
		this.leftPos = 0;
		this.rightPos = 0;
		this.leftScore = "0";
		this.leftScore = "0";
		this.fieldSize = new Dimension(1200,800);
		this.ball = new Point(300,300);
		
				
	}
	
	
	/**
     * Takes the inputs and applies them to the model, computing one
     * simulation step. delta_t is the time that has passed since the
     * last compute step -- use this in your time integration to have
     * the items move at the same speed, regardless of the framerate.
     */
    public void compute(Set<Input> input, long delta_t) {
    	
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
    public int getBarHeight(BarKey k) {
    	
    }
    public Point getBallPos() {
    	return ball;
    	
    }

    /**
     * Will output information about the state of the game to be
     * displayed to the players
     */
    public String getMessage() {
    	
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
