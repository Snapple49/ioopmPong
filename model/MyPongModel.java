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
    		return leftPos;
    	case RIGHT:
    		return rightPos;
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
    		return leftScore;
    	case RIGHT:
    		return rightScore;
    	default:
    		return "";
    		
    	}
    	
    }
    
    /**
     * a valid implementation of the model will keep the field size
     * will remain constant forever.
     */
    public Dimension getFieldSize() {
    	
    }

}
