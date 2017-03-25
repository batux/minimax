package com.gostrange;

public class StateValueGenerator {
	
	
	public static int[][] weightMatrix = {  
											{  -50,  -10,  -10,  -10,  -10,  -10,  -50 },
											{  -10,   30,   30,   30,   30,   30,  -10 },
											{  -10,   30,   40,   40,   40,   30,  -10 },
											{  -10,   30,   50,   50,   50,   30,  -10 },
											{  -10,   30,   40,   40,   40,   30,  -10 },
											{  -10,   30,   30,   30,   30,   30,  -10 },
											{  -50,  -10,  -10,  -10,  -10,  -10,  -50 }
										 }; 
	
//	public static int[][] removeWeightedMatrix = {  
//											{  70,  50,  50,  50,  50,  50, 70 },
//											{  50,  30,  30,  30,  30,  30, 50 },
//											{  50,  30,  20,  20,  20,  30, 50 },
//											{  50,  30,  20,  20,  20,  30, 50 },
//											{  50,  30,  20,  20,  20,  30, 50 },
//											{  50,  30,  30,  30,  30,  30, 50 },
//											{  70,  50,  50,  50,  50,  50, 70 }
//										 }; 
	

	public static double calculateValueOfPlayerMove(Board gameBoard,  char currentPlayerSymbol, char currentPlayerRemoveSymbol) {
		
		boolean gameStateForPlayer1 = MiniMaxAlgorithmHelper.detectGameTerminateForPlayer1(gameBoard);
		
		boolean gameStateForPlayer2 = MiniMaxAlgorithmHelper.detectGameTerminateForPlayer2(gameBoard);
		
		if(!gameStateForPlayer1 || !gameStateForPlayer2) {
			
			if(!gameStateForPlayer1) {
				return +100000;
			}
			else if(!gameStateForPlayer2) {
				return -100000;
			}
			else {
				return 0;
			}
		}
		else {
			
			double stateValueForPlayer1 = 0;
			double stateValueForPlayer2 = 0;
			
			stateValueForPlayer1 += calculateOpponentSquaresAroundPlayer(gameBoard, gameBoard.getPlayer1Symbol(), gameBoard.getPlayer1RemoveSymbol());
			
			stateValueForPlayer1 += calculateRemoveMoveValue(gameBoard, gameBoard.getPlayer1Symbol(), gameBoard.getPlayer1RemoveSymbol());
			
			
			stateValueForPlayer2 += calculateOpponentSquaresAroundPlayer(gameBoard, gameBoard.getPlayer2Symbol(), gameBoard.getPlayer2RemoveSymbol());
			
			stateValueForPlayer2 += calculateRemoveMoveValue(gameBoard, gameBoard.getPlayer2Symbol(), gameBoard.getPlayer2RemoveSymbol());
		
			return stateValueForPlayer2 - stateValueForPlayer1;
		}
		
	}
	
	private static double calculateOpponentSquaresAroundPlayer(Board gameBoard, char currentPlayerSymbol, char currentPlayerRemoveSymbol) {
	
		char opponentPlayerSymbol = gameBoard.getEmptyBoardSquareSymbol();
		
		if(currentPlayerSymbol == gameBoard.getPlayer1Symbol()) {
			opponentPlayerSymbol = gameBoard.getPlayer2Symbol();
		}
		else if(currentPlayerSymbol == gameBoard.getPlayer2Symbol()) {
			opponentPlayerSymbol = gameBoard.getPlayer1Symbol();
		}
		
		char opponentPlayerRemoveSymbol = gameBoard.getEmptyBoardSquareSymbol();
		
		if(currentPlayerRemoveSymbol == gameBoard.getPlayer1RemoveSymbol()) {
			opponentPlayerRemoveSymbol = gameBoard.getPlayer2RemoveSymbol();
		}
		else if(currentPlayerRemoveSymbol == gameBoard.getPlayer2RemoveSymbol()) {
			opponentPlayerRemoveSymbol = gameBoard.getPlayer1RemoveSymbol();
		}
		
		double stateValueForPlayerMove = 0;
		
		char[][] boardState = gameBoard.getBoardSquares();
		
		boolean breakAllLoops = false;
		
		for(int i=0; i < boardState.length; i++) {
			
			for(int j=0; j < boardState.length; j++) {
				
				char currentBoardSymbol = boardState[i][j];
				
				if(currentPlayerSymbol == currentBoardSymbol) {
					
					int newRowIndex    = i - 1;
					int newColumnIndex = j - 1;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);
					stateValueForPlayerMove += calculateWeightValue(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);
					
					newRowIndex    = i - 1;
					newColumnIndex = j;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);
					stateValueForPlayerMove += calculateWeightValue(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					newRowIndex    = i - 1;
					newColumnIndex = j + 1;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);
					stateValueForPlayerMove += calculateWeightValue(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					newRowIndex    = i;
					newColumnIndex = j - 1;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);
					stateValueForPlayerMove += calculateWeightValue(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					newRowIndex    = i;
					newColumnIndex = j + 1;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);
					stateValueForPlayerMove += calculateWeightValue(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					newRowIndex    = i + 1;
					newColumnIndex = j - 1;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);
					stateValueForPlayerMove += calculateWeightValue(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					newRowIndex    = i + 1;
					newColumnIndex = j;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);
					stateValueForPlayerMove += calculateWeightValue(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					newRowIndex    = i + 1;
					newColumnIndex = j + 1;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);
					stateValueForPlayerMove += calculateWeightValue(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					stateValueForPlayerMove += weightMatrix[i][j];
					
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					newRowIndex    = i - 2;
					newColumnIndex = j;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					newRowIndex    = i - 2;
					newColumnIndex = j - 1;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					newRowIndex    = i - 2;
					newColumnIndex = j - 2;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					newRowIndex    = i - 2;
					newColumnIndex = j + 1;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					newRowIndex    = i - 2;
					newColumnIndex = j + 2;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					
					
					
					
					
					newRowIndex    = i - 1;
					newColumnIndex = j - 2;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					
					newRowIndex    = i - 1;
					newColumnIndex = j + 2;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					
					
					
					
					
					newRowIndex    = i;
					newColumnIndex = j - 2;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					
					newRowIndex    = i;
					newColumnIndex = j + 2;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					
					
					
					
					
					newRowIndex    = i + 1;
					newColumnIndex = j - 2;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					
					newRowIndex    = i + 1;
					newColumnIndex = j + 2;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					


					
					newRowIndex    = i + 2;
					newColumnIndex = j;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					
					newRowIndex    = i + 2;
					newColumnIndex = j - 1;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					
					newRowIndex    = i + 2;
					newColumnIndex = j - 2;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					
					newRowIndex    = i + 2;
					newColumnIndex = j + 1;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);

					
					newRowIndex    = i + 2;
					newColumnIndex = j + 2;
					
					stateValueForPlayerMove += calculateStateValueOfPlayerMove(gameBoard, currentPlayerRemoveSymbol, opponentPlayerSymbol, opponentPlayerRemoveSymbol, newRowIndex, newColumnIndex);
					
					
					breakAllLoops = true;
					break;
				}
				
			}
			
			if(breakAllLoops)
				break;
		}
		
		return stateValueForPlayerMove;
	}
	
	private static double calculateStateValueOfPlayerMove(Board gameBoard, char currentPlayerRemoveSymbol, char opponentPlayerSymbol, char opponentPlayerRemoveSymbol, int newRowIndex, int newColumnIndex) {
		
		double stateValueForPlayerMove = 1000;
		
		char[][] boardState = gameBoard.getBoardSquares();
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			
			char neighborSquareSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(neighborSquareSymbol == opponentPlayerRemoveSymbol || neighborSquareSymbol == opponentPlayerSymbol) {
				stateValueForPlayerMove--;
//				stateValueForPlayerMove = stateValueForPlayerMove - removeWeightedMatrix[newRowIndex][newColumnIndex];
			}
		}
		else {
			stateValueForPlayerMove--;
//			stateValueForPlayerMove = stateValueForPlayerMove - 50;
		}
		
		return stateValueForPlayerMove;
	}

	
	private static double calculateWeightValue(Board gameBoard, char currentPlayerRemoveSymbol, char opponentPlayerSymbol, char opponentPlayerRemoveSymbol, int newRowIndex, int newColumnIndex) {
		
		double value = 0;
		
		char[][] boardState = gameBoard.getBoardSquares();
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			
			char neighborSquareSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(neighborSquareSymbol == gameBoard.getEmptyBoardSquareSymbol() || neighborSquareSymbol == currentPlayerRemoveSymbol) {
				int weightValue = weightMatrix[newRowIndex][newColumnIndex];
				value = value + weightValue;
			}
		}
		
		return value;
	}
	

	private static double calculateRemoveMoveValue(Board gameBoard, char currentPlayerSymbol, char currentPlayerRemoveSymbol) {
		
		double removeMoveValue = 0;
		
		char[][] boardState = gameBoard.getBoardSquares();
		
		int playerX = -1;
		int playerY = -1;
		
		int currentPlayerX = -1;
		int currentPlayerY = -1;
		
		char opponentPlayerSymbol = ' ';
		char opponentPlayerRemoveSymbol = ' ';
		
		if(currentPlayerSymbol == gameBoard.getPlayer1Symbol()) {
			playerX = gameBoard.getPlayer2X();
			playerY = gameBoard.getPlayer2Y();
			
			currentPlayerX = gameBoard.getPlayer1X();
			currentPlayerY = gameBoard.getPlayer1Y();
			
			opponentPlayerSymbol = gameBoard.getPlayer2Symbol();
			opponentPlayerRemoveSymbol = gameBoard.getPlayer2RemoveSymbol();
		}
		else if(currentPlayerSymbol == gameBoard.getPlayer2Symbol()) {
			playerX = gameBoard.getPlayer1X();
			playerY = gameBoard.getPlayer1Y();
			
			currentPlayerX = gameBoard.getPlayer2X();
			currentPlayerY = gameBoard.getPlayer2Y();
			
			opponentPlayerSymbol = gameBoard.getPlayer1Symbol();
			opponentPlayerRemoveSymbol = gameBoard.getPlayer1RemoveSymbol();
		}
		
		for(int i=0; i < boardState.length; i++) {
			for(int j=0; j < boardState[i].length; j++) {
				if(boardState[i][j] == currentPlayerRemoveSymbol) {
					
					double distance = calculateDistanceBetweenSquares(playerX, playerY, i, j);
					distance = distance*1000;
					removeMoveValue += (9000 - distance);
				}
			}
		}
		
		
		int currentPlayerRemoveSymbolCounter = 0;
		
		int newRowIndex = playerX - 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][playerY];
			
			if(currentSymbol == currentPlayerRemoveSymbol || currentSymbol == currentPlayerSymbol) {
				removeMoveValue++;
				currentPlayerRemoveSymbolCounter++;
			}
		}
		else {
			removeMoveValue++;
			currentPlayerRemoveSymbolCounter++;
		}
		
		
		int newColumnIndex = playerY - 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(currentSymbol == currentPlayerRemoveSymbol || currentSymbol == currentPlayerSymbol) {
				removeMoveValue++;
			}
		}
		else {
			removeMoveValue++;
		}
		
		newColumnIndex = playerY + 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(currentSymbol == currentPlayerRemoveSymbol || currentSymbol == currentPlayerSymbol) {
				removeMoveValue++;
			}
		}
		else {
			removeMoveValue++;
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		newRowIndex = playerX;
		
		newColumnIndex = playerY - 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(currentSymbol == currentPlayerRemoveSymbol || currentSymbol == currentPlayerSymbol) {
				removeMoveValue++;
				currentPlayerRemoveSymbolCounter++;
			}
		}
		else {
			removeMoveValue++;
			currentPlayerRemoveSymbolCounter++;
		}
		
		
		newColumnIndex = playerY + 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(currentSymbol == currentPlayerRemoveSymbol || currentSymbol == currentPlayerSymbol) {
				removeMoveValue++;
				currentPlayerRemoveSymbolCounter++;
			}
		}
		else {
			removeMoveValue++;
			currentPlayerRemoveSymbolCounter++;
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		newRowIndex = playerX + 1;
		
		newColumnIndex = playerY - 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(currentSymbol == currentPlayerRemoveSymbol || currentSymbol == currentPlayerSymbol) {
				removeMoveValue++;
			}
		}
		else {
			removeMoveValue++;
		}
		
		newColumnIndex = playerY + 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(currentSymbol == currentPlayerRemoveSymbol || currentSymbol == currentPlayerSymbol) {
				removeMoveValue++;
			}
		}
		else {
			removeMoveValue++;
		}
		
		newColumnIndex = playerY;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(currentSymbol == currentPlayerRemoveSymbol || currentSymbol == currentPlayerSymbol) {
				removeMoveValue++;
				currentPlayerRemoveSymbolCounter++;
			}
		}
		else {
			removeMoveValue++;
			currentPlayerRemoveSymbolCounter++;
		}
		
		
		
//		if(currentPlayerRemoveSymbolCounter == 4) {
//			return (1000000+1000000);
//		}
		
		

		
		
		
		
		int opponentPlayerSquareCount = 0;
		
		newRowIndex = currentPlayerX - 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][currentPlayerY];
			
			if(currentSymbol == opponentPlayerRemoveSymbol || currentSymbol == opponentPlayerSymbol) {
				opponentPlayerSquareCount++;
			}
		}
		else  {
			opponentPlayerSquareCount++;
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		newRowIndex = currentPlayerX;
		
		newColumnIndex = currentPlayerY - 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(currentSymbol == opponentPlayerRemoveSymbol || currentSymbol == opponentPlayerSymbol) {
				opponentPlayerSquareCount++;
			}
		}
		else  {
			opponentPlayerSquareCount++;
		}
		
		newColumnIndex = currentPlayerY + 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(currentSymbol == opponentPlayerRemoveSymbol || currentSymbol == opponentPlayerSymbol) {
				opponentPlayerSquareCount++;
			}
		}
		else  {
			opponentPlayerSquareCount++;
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		newRowIndex = currentPlayerX + 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][currentPlayerY];
			
			if(currentSymbol == opponentPlayerRemoveSymbol || currentSymbol == opponentPlayerSymbol) {
				opponentPlayerSquareCount++;
			}
		}
		else  {
			opponentPlayerSquareCount++;
		}
		
		
		boolean hasOnlyOneSquareToEscape = false;
		
		newRowIndex = currentPlayerX - 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][currentPlayerY];
			
			if(currentSymbol == currentPlayerRemoveSymbol) {
				hasOnlyOneSquareToEscape = true;
			}
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		newRowIndex = currentPlayerX;
		
		newColumnIndex = currentPlayerY - 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(currentSymbol == currentPlayerRemoveSymbol) {
				hasOnlyOneSquareToEscape = true;
			}
		}
		
		newColumnIndex = currentPlayerY + 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length) && (newColumnIndex > -1 && newColumnIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][newColumnIndex];
			
			if(currentSymbol == currentPlayerRemoveSymbol) {
				hasOnlyOneSquareToEscape = true;
			}
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		newRowIndex = currentPlayerX + 1;
		
		if((newRowIndex > -1 && newRowIndex < boardState.length)) {
			char currentSymbol = boardState[newRowIndex][currentPlayerY];
			
			if(currentSymbol == currentPlayerRemoveSymbol) {
				hasOnlyOneSquareToEscape = true;
			}
		}
		
		
//		if(opponentPlayerSquareCount == 3 && hasOnlyOneSquareToEscape) {
//			return removeMoveValue + 1000000;
//		}
		
		
		
		return removeMoveValue;
	}
	
	public static double calculateDistanceBetweenSquares(int startPointX, int startPointY, int endPointX, int endPointY) {
		
		return Math.sqrt((Math.pow((startPointX - endPointX), 2) + Math.pow((startPointY - endPointY), 2)));
		
	}
}
