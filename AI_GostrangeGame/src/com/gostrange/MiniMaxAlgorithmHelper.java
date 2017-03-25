package com.gostrange;

import java.util.ArrayList;
import java.util.List;

public class MiniMaxAlgorithmHelper {

	public static Board cloneBoardGame(Board gameBoard) {
		
		char[][] boardState = gameBoard.getBoardSquares();
		
		Board nextStateofBoard = new Board(true);
		
		for(int i=0; i < boardState.length; i++) {
			for(int j=0; j < boardState[i].length; j++) {
				nextStateofBoard.getBoardSquares()[i][j] = boardState[i][j];
			}
		}
		
		return nextStateofBoard;
	}
	
	public static List<Actions> generateAvailablePositions(Board gameBoard, char currentPlayerSymbol, char currentPlayerRemoveSymbol) {
		
		List<Actions> possibleActions = new ArrayList<Actions>();
		
		
		int currentMoveX = -1;
		int currentMoveY = -1;
		
		if(currentPlayerSymbol == gameBoard.getPlayer1Symbol()) {
			currentMoveX = gameBoard.getPlayer1X();
			currentMoveY = gameBoard.getPlayer1Y();
		}
		else if(currentPlayerSymbol == gameBoard.getPlayer2Symbol()) {
			currentMoveX = gameBoard.getPlayer2X();
			currentMoveY = gameBoard.getPlayer2Y();
		}
		
		
		char[][] gameBoardState = gameBoard.getBoardSquares();
		
		if(currentMoveX > -1 && currentMoveY > -1) {
			
			int currentRowIndex    = currentMoveX;
			int currentColumnIndex = currentMoveY;
			
			
			int newRowIndex = currentMoveX - 1;
			
			if(newRowIndex > -1 && newRowIndex < gameBoardState.length) {
				
				char currentBoardSymbol = gameBoardState[newRowIndex][currentMoveY];
				
				if(currentBoardSymbol == gameBoard.getEmptyBoardSquareSymbol() || currentBoardSymbol == currentPlayerRemoveSymbol) {
					Actions actions = generateAvailableAction(gameBoard, currentPlayerSymbol, currentRowIndex, currentColumnIndex, newRowIndex, currentMoveY);
					possibleActions.add(actions);
				}
			}
			
			newRowIndex = currentMoveX + 1;
			
			if(newRowIndex > -1 && newRowIndex < gameBoardState.length) {
				
				char currentBoardSymbol = gameBoardState[newRowIndex][currentMoveY];
				
				if(currentBoardSymbol == gameBoard.getEmptyBoardSquareSymbol() || currentBoardSymbol == currentPlayerRemoveSymbol) {
					Actions actions = generateAvailableAction(gameBoard, currentPlayerSymbol, currentRowIndex, currentColumnIndex, newRowIndex, currentMoveY);
					possibleActions.add(actions);
				}
			}
			

			int newColumnIndex = currentMoveY - 1;
			
			if(newColumnIndex > -1 && newColumnIndex < gameBoardState.length) {
				
				char currentBoardSymbol = gameBoardState[currentMoveX][newColumnIndex];
				
				if(currentBoardSymbol == gameBoard.getEmptyBoardSquareSymbol() || currentBoardSymbol == currentPlayerRemoveSymbol) {
					Actions actions = generateAvailableAction(gameBoard, currentPlayerSymbol, currentRowIndex, currentColumnIndex, currentMoveX, newColumnIndex);
					possibleActions.add(actions);
				}
			}
			
			newColumnIndex = currentMoveY + 1;
			
			if(newColumnIndex > -1 && newColumnIndex < gameBoardState.length) {
				
				char currentBoardSymbol = gameBoardState[currentMoveX][newColumnIndex];
				
				if(currentBoardSymbol == gameBoard.getEmptyBoardSquareSymbol() || currentBoardSymbol == currentPlayerRemoveSymbol) {
					Actions actions = generateAvailableAction(gameBoard, currentPlayerSymbol, currentRowIndex, currentColumnIndex, currentMoveX, newColumnIndex);
					possibleActions.add(actions);
				}
			}
		}	
					
		return possibleActions;
	}
	
	public static boolean detectGameTerminateForPlayer2(Board gameBoard) {
		
		int currentX = gameBoard.getPlayer2X();
		int currentY = gameBoard.getPlayer2Y();
		
		int counter = 0;
		
		int possibleMoveX = currentX - 1;
		
		if(possibleMoveX > -1 && possibleMoveX < gameBoard.getBoardSquares().length) {
			
			char currentSymbol = gameBoard.getBoardSquares()[possibleMoveX][currentY];
			
			if(currentSymbol == gameBoard.getPlayer1Symbol() || currentSymbol == gameBoard.getPlayer1RemoveSymbol()) {
				counter++;
			}
		}
		else {
			counter++;
		}
		
		
		possibleMoveX = currentX + 1;
		
		if(possibleMoveX > -1 && possibleMoveX < gameBoard.getBoardSquares().length) {
			
			char currentSymbol = gameBoard.getBoardSquares()[possibleMoveX][currentY];
			
			if(currentSymbol == gameBoard.getPlayer1Symbol() || currentSymbol == gameBoard.getPlayer1RemoveSymbol()) {
				counter++;
			}
		}
		else {
			counter++;
		}
		
		
		int possibleMoveY = currentY - 1;
		
		if(possibleMoveY > -1 && possibleMoveY < gameBoard.getBoardSquares().length) {
			
			char currentSymbol = gameBoard.getBoardSquares()[currentX][possibleMoveY];
			
			if(currentSymbol == gameBoard.getPlayer1Symbol() || currentSymbol == gameBoard.getPlayer1RemoveSymbol()) {
				counter++;
			}
		}
		else {
			counter++;
		}
		
		
		possibleMoveY = currentY + 1;
		
		if(possibleMoveY > -1 && possibleMoveY < gameBoard.getBoardSquares().length) {
			
			char currentSymbol = gameBoard.getBoardSquares()[currentX][possibleMoveY];
			
			if(currentSymbol == gameBoard.getPlayer1Symbol() || currentSymbol == gameBoard.getPlayer1RemoveSymbol()) {
				counter++;
			}
		}
		else {
			counter++;
		}
		
		if(counter == 4) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public static boolean detectGameTerminateForPlayer1(Board gameBoard) {
		
		int currentX = gameBoard.getPlayer1X();
		int currentY = gameBoard.getPlayer1Y();
		
		int counter = 0;
		
		int possibleMoveX = currentX - 1;
		
		if(possibleMoveX > -1 && possibleMoveX < gameBoard.getBoardSquares().length) {
			
			char currentSymbol = gameBoard.getBoardSquares()[possibleMoveX][currentY];
			
			if(currentSymbol == gameBoard.getPlayer2Symbol() || currentSymbol == gameBoard.getPlayer2RemoveSymbol()) {
				counter++;
			}
		}
		else {
			counter++;
		}
		
		
		possibleMoveX = currentX + 1;
		
		if(possibleMoveX > -1 && possibleMoveX < gameBoard.getBoardSquares().length) {
			
			char currentSymbol = gameBoard.getBoardSquares()[possibleMoveX][currentY];
			
			if(currentSymbol == gameBoard.getPlayer2Symbol() || currentSymbol == gameBoard.getPlayer2RemoveSymbol()) {
				counter++;
			}
		}
		else {
			counter++;
		}
		
		
		int possibleMoveY = currentY - 1;
		
		if(possibleMoveY > -1 && possibleMoveY < gameBoard.getBoardSquares().length) {
			
			char currentSymbol = gameBoard.getBoardSquares()[currentX][possibleMoveY];
			
			if(currentSymbol == gameBoard.getPlayer2Symbol() || currentSymbol == gameBoard.getPlayer2RemoveSymbol()) {
				counter++;
			}
		}
		else {
			counter++;
		}
		
		
		possibleMoveY = currentY + 1;
		
		if(possibleMoveY > -1 && possibleMoveY < gameBoard.getBoardSquares().length) {
			
			char currentSymbol = gameBoard.getBoardSquares()[currentX][possibleMoveY];
			
			if(currentSymbol == gameBoard.getPlayer2Symbol() || currentSymbol == gameBoard.getPlayer2RemoveSymbol()) {
				counter++;
			}
		}
		else {
			counter++;
		}
		
		if(counter == 4) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private static Actions generateAvailableAction(Board gameBoard, char playerMoveSymbol, int previousX, int previousY, int x, int y) {
		
		Actions actions = new Actions();
		actions.setPlayerPossiblePosition(new Position(x, y));
		
		List<com.gostrange.Position> removePossiblePositions = generateAvailableRemovePositions(gameBoard, actions.getPlayerPossiblePosition(), playerMoveSymbol, previousX, previousY);
		actions.getRemovePossiblePositions().addAll(removePossiblePositions);
		
		return actions;
	}
	
	private static List<Position> generateAvailableRemovePositions(Board gameBoard, Position playerPossiblePosition, char playerMoveSymbol, int currentRowIndex, int currentColumnIndex) {
		
		List<Position> removePossiblePositions = new ArrayList<Position>();
		
		char[][] gameBoardState  = gameBoard.getBoardSquares();
		
			
		int availableX = playerPossiblePosition.getX();
		int availableY = playerPossiblePosition.getY();
		
		
		char currentSquareSymbol = gameBoardState[availableX][availableY];
		
		gameBoardState[availableX][availableY] = playerMoveSymbol; //gameBoard.getPlayer2Symbol();
		gameBoardState[currentRowIndex][currentColumnIndex] = 'B';
		
		for(int i=0; i < gameBoardState.length; i++) {
			
			for(int j=0; j < gameBoardState[i].length; j++) {
					
				char boardSymbol = gameBoardState[i][j];
				
				if(boardSymbol == gameBoard.getEmptyBoardSquareSymbol()) {
					removePossiblePositions.add(new Position(i, j));
				}
				
			}
		}
		
		gameBoardState[availableX][availableY] = currentSquareSymbol;
		gameBoardState[currentRowIndex][currentColumnIndex] = playerMoveSymbol;
		
		return removePossiblePositions;
	}
	
}
