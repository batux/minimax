package com.gostrange;

import java.util.ArrayList;
import java.util.List;

public class MiniMaxAlgorithm {

	public Node generateAction(Board gameBoard, int depthLimit) {
		
		List<Actions> successorActionResultSet = MiniMaxAlgorithmHelper.generateAvailablePositions(gameBoard, gameBoard.getPlayer2Symbol(), gameBoard.getPlayer2RemoveSymbol());
		
		int alfa = -Integer.MAX_VALUE;
		int beta = Integer.MAX_VALUE;
		
		List<Node> mintermChildren = new ArrayList<Node>();
		
		for(Actions actionInSuccessor : successorActionResultSet) {
			
			Position playerPossibleNextMove = actionInSuccessor.getPlayerPossiblePosition();
			List<com.gostrange.Position> playerPossibleNextRemoveMoves = actionInSuccessor.getRemovePossiblePositions();
			
			for(com.gostrange.Position playerPossibleNextRemoveMove : playerPossibleNextRemoveMoves) {
				
				Board nextBoardState = MiniMaxAlgorithmHelper.cloneBoardGame(gameBoard); //MiniMaxAlgorithmHelper.createNextStateOfBoard(gameBoard, playerPossibleNextMove, gameBoard.getPlayer2Symbol(), playerPossibleNextRemoveMove, gameBoard.getPlayer2RemoveSymbol());
				
				nextBoardState.setPlayer1X(gameBoard.getPlayer1X());
				nextBoardState.setPlayer1Y(gameBoard.getPlayer1Y());
				
				nextBoardState.setPlayer2X(playerPossibleNextMove.getX());
				nextBoardState.setPlayer2Y(playerPossibleNextMove.getY());
				
				nextBoardState.setSymbolAt(gameBoard.getPlayer2X(), gameBoard.getPlayer2Y(), gameBoard.getEmptyBoardSquareSymbol());
				nextBoardState.setSymbolAt(playerPossibleNextMove.getX(), playerPossibleNextMove.getY(), gameBoard.getPlayer2Symbol());
				nextBoardState.setSymbolAt(playerPossibleNextRemoveMove.getX(), playerPossibleNextRemoveMove.getY(), nextBoardState.getPlayer2RemoveSymbol());
				
				Node node = minTerm(nextBoardState, nextBoardState.getPlayer2Symbol(), nextBoardState.getPlayer2RemoveSymbol(), alfa, beta, 1, depthLimit);
				
				
				node.setPlayerXPosition(playerPossibleNextMove.getX());
				node.setPlayerYPosition(playerPossibleNextMove.getY());
				node.setRemoveXPosition(playerPossibleNextRemoveMove.getX());
				node.setRemoveYPosition(playerPossibleNextRemoveMove.getY());
				
				mintermChildren.add(node);
			}
			
		}
		
		Node bestNode = mintermChildren.get(0);
		
		for(int i=1; i < mintermChildren.size(); i++) {
			
			Node currentNode = mintermChildren.get(i);
			
			if(currentNode.getValue() > bestNode.getValue()) {
				
				bestNode = currentNode;
				
			}
			
		}
		
		return bestNode;
	}
	
	private Node maxTerm(Board gameBoard, char whoPlaysNowMoveSymbol, char whoPlaysNowRemoveSymbol, int alfa, int beta, int currentDepthValue, int depthLimit) {
		
		currentDepthValue++;
		
		List<Actions> successorActionResultSet = MiniMaxAlgorithmHelper.generateAvailablePositions(gameBoard, gameBoard.getPlayer2Symbol(), gameBoard.getPlayer2RemoveSymbol());
		
		if((depthLimit == currentDepthValue) || successorActionResultSet.size() == 0) {
			
			double stateValue = StateValueGenerator.calculateValueOfPlayerMove(gameBoard, whoPlaysNowMoveSymbol, whoPlaysNowRemoveSymbol);
			
			Node leafNode = new Node(stateValue);
			leafNode.setBoard(gameBoard);
			return leafNode;
		}
		
		double v = -(Double.MAX_VALUE - 1);
		
		Node bestNode = null;
		com.gostrange.Position playerBestMove = null;
		com.gostrange.Position playerBestRemoveMove = null;
		
		
		for(Actions actionInSuccessor : successorActionResultSet) {
			
			Position playerPossibleNextMove = actionInSuccessor.getPlayerPossiblePosition();
			List<com.gostrange.Position> playerPossibleNextRemoveMoves = actionInSuccessor.getRemovePossiblePositions();
			
			for(com.gostrange.Position playerPossibleNextRemoveMove : playerPossibleNextRemoveMoves) {
				
				Board nextBoardState = MiniMaxAlgorithmHelper.cloneBoardGame(gameBoard); //MiniMaxAlgorithmHelper.createNextStateOfBoard(gameBoard, playerPossibleNextMove, gameBoard.getPlayer2Symbol(), playerPossibleNextRemoveMove, gameBoard.getPlayer2RemoveSymbol());
				
				nextBoardState.setPlayer1X(gameBoard.getPlayer1X());
				nextBoardState.setPlayer1Y(gameBoard.getPlayer1Y());
				
				nextBoardState.setPlayer2X(playerPossibleNextMove.getX());
				nextBoardState.setPlayer2Y(playerPossibleNextMove.getY());
				
				nextBoardState.setSymbolAt(gameBoard.getPlayer2X(), gameBoard.getPlayer2Y(), gameBoard.getEmptyBoardSquareSymbol());
				nextBoardState.setSymbolAt(playerPossibleNextMove.getX(), playerPossibleNextMove.getY(), gameBoard.getPlayer2Symbol());
				nextBoardState.setSymbolAt(playerPossibleNextRemoveMove.getX(), playerPossibleNextRemoveMove.getY(), nextBoardState.getPlayer2RemoveSymbol());
				
				
				Node node = minTerm(nextBoardState, nextBoardState.getPlayer2Symbol(), nextBoardState.getPlayer2RemoveSymbol(), alfa, beta, currentDepthValue, depthLimit);
				
				if(node.getValue() > v) {
					bestNode = node;
					playerBestMove = playerPossibleNextMove;
					playerBestRemoveMove = playerPossibleNextRemoveMove;
					v = node.getValue();
				}
				
				if(v >= beta) {
					bestNode.setPlayerXPosition(playerBestMove.getX());
					bestNode.setPlayerYPosition(playerBestMove.getY());
					bestNode.setRemoveXPosition(playerBestRemoveMove.getX());
					bestNode.setRemoveYPosition(playerBestRemoveMove.getY());
					return bestNode;
				}
				
				alfa = Math.max(alfa, (int)v);
				
			}
		}
		
		
	
		bestNode.setPlayerXPosition(playerBestMove.getX());
		bestNode.setPlayerYPosition(playerBestMove.getY());
		bestNode.setRemoveXPosition(playerBestRemoveMove.getX());
		bestNode.setRemoveYPosition(playerBestRemoveMove.getY());
		
		
		return bestNode;
	}
	
	private Node minTerm(Board gameBoard, char whoPlaysNowMoveSymbol, char whoPlaysNowRemoveSymbol, int alfa, int beta, int currentDepthValue, int depthLimit) {
		
		currentDepthValue++;
		
		List<Actions> successorActionResultSet = MiniMaxAlgorithmHelper.generateAvailablePositions(gameBoard, gameBoard.getPlayer1Symbol(), gameBoard.getPlayer1RemoveSymbol());
		
		if((depthLimit == currentDepthValue) || successorActionResultSet.size() == 0) {
			
			double stateValue = StateValueGenerator.calculateValueOfPlayerMove(gameBoard, whoPlaysNowMoveSymbol, whoPlaysNowRemoveSymbol);
			
			Node leafNode = new Node(stateValue);
			leafNode.setBoard(gameBoard);
			return leafNode;
		}
		
		
		double v = (Double.MAX_VALUE - 1);
		
		Node bestNode = null;
		com.gostrange.Position playerBestMove = null;
		com.gostrange.Position playerBestRemoveMove = null;
		
		for(Actions actionInSuccessor : successorActionResultSet) {
			
			Position playerPossibleNextMove = actionInSuccessor.getPlayerPossiblePosition();
			List<com.gostrange.Position> playerPossibleNextRemoveMoves = actionInSuccessor.getRemovePossiblePositions();
			
			for(com.gostrange.Position playerPossibleNextRemoveMove : playerPossibleNextRemoveMoves) {
				
				Board nextBoardState = MiniMaxAlgorithmHelper.cloneBoardGame(gameBoard); //MiniMaxAlgorithmHelper.createNextStateOfBoard(gameBoard, playerPossibleNextMove, gameBoard.getPlayer1Symbol(), playerPossibleNextRemoveMove, gameBoard.getPlayer1RemoveSymbol());
				
				nextBoardState.setPlayer2X(gameBoard.getPlayer2X());
				nextBoardState.setPlayer2Y(gameBoard.getPlayer2Y());
				
				nextBoardState.setPlayer1X(playerPossibleNextMove.getX());
				nextBoardState.setPlayer1Y(playerPossibleNextMove.getY());
				
				nextBoardState.setSymbolAt(gameBoard.getPlayer1X(), gameBoard.getPlayer1Y(), gameBoard.getEmptyBoardSquareSymbol());
				nextBoardState.setSymbolAt(playerPossibleNextMove.getX(), playerPossibleNextMove.getY(), gameBoard.getPlayer1Symbol());
				nextBoardState.setSymbolAt(playerPossibleNextRemoveMove.getX(), playerPossibleNextRemoveMove.getY(), nextBoardState.getPlayer1RemoveSymbol());
				
				
				Node node = maxTerm(nextBoardState, nextBoardState.getPlayer1Symbol(), nextBoardState.getPlayer1RemoveSymbol(), alfa, beta, currentDepthValue, depthLimit);
				
				if(node.getValue() < v) {
					bestNode = node;
					playerBestMove = playerPossibleNextMove;
					playerBestRemoveMove = playerPossibleNextRemoveMove;
					v = node.getValue();
				}
				
				if(v <= alfa) {
					bestNode.setPlayerXPosition(playerBestMove.getX());
					bestNode.setPlayerYPosition(playerBestMove.getY());
					bestNode.setRemoveXPosition(playerBestRemoveMove.getX());
					bestNode.setRemoveYPosition(playerBestRemoveMove.getY());
					return bestNode;
				}
				
				beta = Math.min(beta, (int)v);
				
			}
		}
				
		
		bestNode.setPlayerXPosition(playerBestMove.getX());
		bestNode.setPlayerYPosition(playerBestMove.getY());
		bestNode.setRemoveXPosition(playerBestRemoveMove.getX());
		bestNode.setRemoveYPosition(playerBestRemoveMove.getY());
		
		return bestNode;
	}
	
	
}
