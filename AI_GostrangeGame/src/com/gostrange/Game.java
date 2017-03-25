package com.gostrange;

import java.util.Scanner;

/*
 * @Developed by Batuhan Düzgün (Senior Java Application Developer)
 * 
 * Number : 20163505004
 * 
 * Computer Engineering Department
 * 
 * Artificial Intelligence
 * 
 * 2016-2017
 * 
 */

public class Game {

	private static char[] rowLetters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g' };
	
	public static void main(String[] args) {
		
		Board gameBoard = new Board();
		
		MiniMaxAlgorithm minmax = new MiniMaxAlgorithm();
		
		Scanner scanner = new Scanner(System.in);
		
		int depthLimit = 3;
		
		int howManyStepRepeated = 0;
		
		System.out.println("Who will be the first? (Player1 or Player2)");
		int choice = scanner.nextInt();
		
		if(choice == 1) {
			System.out.println("----- Initial Board State -----");
			gameBoard.printBoardState();
			System.out.println("-------------------------------");
			humanAction(scanner, gameBoard, howManyStepRepeated);
		}
		
		boolean gameState = true;
		
		while(true) {
			
			howManyStepRepeated = computerAction(minmax, gameBoard, howManyStepRepeated, depthLimit);
			
			gameState = MiniMaxAlgorithmHelper.detectGameTerminateForPlayer1(gameBoard);
			
			if(gameState == false) {
				System.out.println(">>> Computer won ! <<<");
				break;
			}
			
			System.out.println();
			
			howManyStepRepeated = humanAction(scanner, gameBoard, howManyStepRepeated);
			
			gameState = MiniMaxAlgorithmHelper.detectGameTerminateForPlayer2(gameBoard);
			
			if(gameState == false) {
				System.out.println(">>> Human won ! <<<");
				break;
			}
			
			if(howManyStepRepeated > 2 && howManyStepRepeated < 15) {
				depthLimit = 4;
			}
			else if(howManyStepRepeated > 40 && howManyStepRepeated < 50) {
				depthLimit = 5;
			}
			else if(howManyStepRepeated > 60 && howManyStepRepeated < 70) {
				depthLimit = 6;
			}
			else if(howManyStepRepeated > 72) {
				depthLimit = 7;
			}
			
			System.out.println();
		}

	}
	
	private static int computerAction(MiniMaxAlgorithm minmax, Board gameBoard, int howManyStepRepeated, int depthLimit) {
		
		howManyStepRepeated++;
		
		long operationStartedTime = System.currentTimeMillis();
		
		Node node = minmax.generateAction(gameBoard, depthLimit);
		
		long operationEndedTime = System.currentTimeMillis();
		
		double operationInSeconds = ((operationEndedTime - operationStartedTime) / 1000.0);
		
		int playerMoveX = node.getPlayerXPosition();
		int playerMoveY = node.getPlayerYPosition();
		
		int playerRemoveMoveX = node.getRemoveXPosition();
		int playerRemoveMoveY = node.getRemoveYPosition();
		
		gameBoard.setSymbolAt(gameBoard.getPlayer2X(), gameBoard.getPlayer2Y(), gameBoard.getEmptyBoardSquareSymbol());
		gameBoard.setSymbolAt(playerMoveX, playerMoveY, gameBoard.getPlayer2Symbol());
		gameBoard.setSymbolAt(playerRemoveMoveX, playerRemoveMoveY, gameBoard.getPlayer2RemoveSymbol());
		
		
		System.out.println("Computer moves to " + rowLetters[playerMoveX] + (playerMoveY + 1));
		System.out.println("Computer removes " + rowLetters[playerRemoveMoveX] + (playerRemoveMoveY + 1));
		
		System.out.println("Step-" + howManyStepRepeated + " was processed in " + operationInSeconds + " seconds");
		
		System.out.println();
		
		System.out.println("----- Computer Action -----");
		gameBoard.printBoardState();
		System.out.println("---------------------------");
		
		gameBoard.setPlayer2X(playerMoveX);
		gameBoard.setPlayer2Y(playerMoveY);
		
		return howManyStepRepeated;
	}
	
	private static int humanAction(Scanner scanner, Board gameBoard, int howManyStepRepeated) {
		
		howManyStepRepeated++;
		
		int playerMoveX = -1;
		int playerMoveY = -1;
		
		
		while(true) {
			
			System.out.print("Player moves to ");
			
			String inputFromPlayer = scanner.next();
			
			if(inputFromPlayer.length() > 2) {
				System.out.println(">> Invalid move, Try again!");
				continue;
			}
			
			if(!Character.isLetter(inputFromPlayer.charAt(0))) {
				System.out.println(">> Invalid move, Try again!");
				continue;
			}
			
			if(!Character.isDigit(inputFromPlayer.charAt(1))) {
				System.out.println(">> Invalid move, Try again!");
				continue;
			}
			
			playerMoveX = findRowIndex(inputFromPlayer.charAt(0));
			playerMoveY = Integer.valueOf(String.valueOf(inputFromPlayer.charAt(1))) - 1;
			
			boolean validMove = validPlayerNextMove(gameBoard, playerMoveX, playerMoveY);
			
			if(!validMove) {
				System.out.println(">> Invalid move, Try again!");
			}
			else {
				break;
			}
		}
		
		gameBoard.setSymbolAt(playerMoveX, playerMoveY, gameBoard.getPlayer1Symbol());
		
		
		int playerRemoveMoveX = -1;
		int playerRemoveMoveY = -1;
		
		while(true) {
			
			System.out.print("Player removes to ");
			
			String inputFromPlayer = scanner.next();
			
			if(inputFromPlayer.length() > 2) {
				System.out.println(">> Invalid move, Try again!");
				continue;
			}
			
			if(!Character.isLetter(inputFromPlayer.charAt(0))) {
				System.out.println(">> Invalid move, Try again!");
				continue;
			}
			
			if(!Character.isDigit(inputFromPlayer.charAt(1))) {
				System.out.println(">> Invalid move, Try again!");
				continue;
			}
			
			playerRemoveMoveX = findRowIndex(inputFromPlayer.charAt(0));
			playerRemoveMoveY = Integer.valueOf(String.valueOf(inputFromPlayer.charAt(1))) - 1;
		
			boolean validMove = validPlayerNextRemoveMove(gameBoard, playerMoveX, playerMoveY, playerRemoveMoveX, playerRemoveMoveY);
			
			if(!validMove) {
				System.out.println(">> Invalid move, Try again!");
			}
			else {
				break;
			}
		}
		
		gameBoard.setSymbolAt(gameBoard.getPlayer1X(), gameBoard.getPlayer1Y(), gameBoard.getEmptyBoardSquareSymbol());
		gameBoard.setSymbolAt(playerRemoveMoveX, playerRemoveMoveY, gameBoard.getPlayer1RemoveSymbol());
		
		gameBoard.setPlayer1X(playerMoveX);
		gameBoard.setPlayer1Y(playerMoveY);
		
		System.out.println("Step-" + howManyStepRepeated + " was processed");
		
		System.out.println();
		
		System.out.println("------- Human Action -------");
		gameBoard.printBoardState();
		System.out.println("----------------------------");
		
		return howManyStepRepeated;
	}
	
	private static boolean validPlayerNextRemoveMove(Board gameBoard, int playerMoveX, int playerMoveY, int playerRemoveMoveX, int playerRemoveMoveY) {
		
		if(playerMoveX == playerRemoveMoveX && playerMoveY == playerRemoveMoveY) {
			return false;
		}
		
		if(gameBoard.getPlayer1X() == playerRemoveMoveX && gameBoard.getPlayer1Y() == playerRemoveMoveY) {
			return false;
		}
		
		char[][] gameState = gameBoard.getBoardSquares();
		
		char currentSquareSymbol = gameState[playerRemoveMoveX][playerRemoveMoveY];
		
		if(currentSquareSymbol == gameBoard.getEmptyBoardSquareSymbol()) {
			return true;
		}
		
		return false;
	}
	
	private static boolean validPlayerNextMove(Board gameBoard, int playerNextMoveX, int playerNextMoveY) {
		
		char[][] gameState = gameBoard.getBoardSquares();
		
		int currentPlayerX = gameBoard.getPlayer1X();
		int currentPlayerY = gameBoard.getPlayer1Y();
		
		if(playerNextMoveX == currentPlayerX - 1 && playerNextMoveY == currentPlayerY) {
			
			char currentBoardSymbol = gameState[playerNextMoveX][playerNextMoveY];
			
			if(currentBoardSymbol == gameBoard.getEmptyBoardSquareSymbol() || currentBoardSymbol == gameBoard.getPlayer1RemoveSymbol()) {
				return true;
			}
			return false;
		}
		else if(playerNextMoveX == currentPlayerX + 1 && playerNextMoveY == currentPlayerY) {
			
			char currentBoardSymbol = gameState[playerNextMoveX][playerNextMoveY];
			
			if(currentBoardSymbol == gameBoard.getEmptyBoardSquareSymbol() || currentBoardSymbol == gameBoard.getPlayer1RemoveSymbol()) {
				return true;
			}
			return false;
		}
		else if(playerNextMoveX == currentPlayerX && playerNextMoveY == currentPlayerY - 1) {
			
			char currentBoardSymbol = gameState[playerNextMoveX][playerNextMoveY];
			
			if(currentBoardSymbol == gameBoard.getEmptyBoardSquareSymbol() || currentBoardSymbol == gameBoard.getPlayer1RemoveSymbol()) {
				return true;
			}
			return false;
		}
		else if(playerNextMoveX == currentPlayerX && playerNextMoveY == currentPlayerY + 1) {
			
			char currentBoardSymbol = gameState[playerNextMoveX][playerNextMoveY];
			
			if(currentBoardSymbol == gameBoard.getEmptyBoardSquareSymbol() || currentBoardSymbol == gameBoard.getPlayer1RemoveSymbol()) {
				return true;
			}
			return false;
		}
		
		return false;
	}
	
	private static int findRowIndex(char playerMoveX) {
		
		for(int i=0; i < rowLetters.length; i++) {
			if(rowLetters[i] == playerMoveX) {
				return i;
			}
		}
		
		return -1;
	}
}
