package com.gostrange;

import java.util.ArrayList;
import java.util.List;

public class Actions {

	private Position playerPossiblePosition;
	private List<Position> removePossiblePositions;
	
	public Actions() {
		this.setPlayerPossiblePosition(null);
		this.setRemovePossiblePositions(new ArrayList<Position>());
	}

	public List<Position> getRemovePossiblePositions() {
		return removePossiblePositions;
	}

	public void setRemovePossiblePositions(List<Position> removePossiblePositions) {
		this.removePossiblePositions = removePossiblePositions;
	}

	public Position getPlayerPossiblePosition() {
		return playerPossiblePosition;
	}

	public void setPlayerPossiblePosition(Position playerPossiblePosition) {
		this.playerPossiblePosition = playerPossiblePosition;
	}
	
}
