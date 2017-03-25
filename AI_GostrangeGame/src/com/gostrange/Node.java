package com.gostrange;

public class Node {

	private double value;
	private int playerXPosition;
	private int playerYPosition;
	
	private int removeXPosition;
	private int removeYPosition;
	
	private Board board;
	
	public Node() {
		this.setValue(0);
	}
	
	public Node(double value) {
		super();
		this.setValue(value);
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getPlayerXPosition() {
		return playerXPosition;
	}

	public void setPlayerXPosition(int playerXPosition) {
		this.playerXPosition = playerXPosition;
	}

	public int getPlayerYPosition() {
		return playerYPosition;
	}

	public void setPlayerYPosition(int playerYPosition) {
		this.playerYPosition = playerYPosition;
	}

	public int getRemoveXPosition() {
		return removeXPosition;
	}

	public void setRemoveXPosition(int removeXPosition) {
		this.removeXPosition = removeXPosition;
	}

	public int getRemoveYPosition() {
		return removeYPosition;
	}

	public void setRemoveYPosition(int removeYPosition) {
		this.removeYPosition = removeYPosition;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public String toString() {
		
		if(this.getBoard() == null)
			return "";
		
		return this.getBoard().toString();
	}

}
