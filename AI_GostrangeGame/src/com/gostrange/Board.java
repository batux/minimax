package com.gostrange;

public class Board {

	private char player1Symbol;
	private char player2Symbol;
	private char player1RemoveSymbol;
	private char player2RemoveSymbol;
	private char emptyBoardSquareSymbol;
	private char[][] boardSquares;
	private char[] rowNames = { 'a', 'b', 'c', 'd', 'e', 'f', 'g' };
	
	private int player1X = -1;
	private int player1Y = -1;
	
	private int player2X = -1;
	private int player2Y = -1;
	
	public Board() {
		
		setEmptyBoardSquareSymbol(' ');
		setBoardSquares(new char[7][7]);
		
		setPlayer1Symbol('1');
		setPlayer2Symbol('2');
		
		setPlayer1RemoveSymbol('X');
		setPlayer2RemoveSymbol('O');
		
		setPlayer1X(3);
		setPlayer1Y(2);
		
		setPlayer2X(3);
		setPlayer2Y(4);
		
		fillBoardWithEmptySymbol();
		
		boardSquares[getPlayer1X()][getPlayer1Y()] = this.getPlayer1Symbol();
		boardSquares[getPlayer2X()][getPlayer2Y()] = this.getPlayer2Symbol();
	}
	
	public Board(boolean emptyBoard) {
		
		setEmptyBoardSquareSymbol(' ');
		setBoardSquares(new char[7][7]);
		
		setPlayer1Symbol('1');
		setPlayer2Symbol('2');
		
		setPlayer1RemoveSymbol('X');
		setPlayer2RemoveSymbol('O');
		
		fillBoardWithEmptySymbol();
		
		if(!emptyBoard) {
			
			setPlayer1X(3);
			setPlayer1Y(2);
			
			setPlayer2X(3);
			setPlayer2Y(4);
			
			boardSquares[getPlayer1X()][getPlayer1Y()] = this.getPlayer1Symbol();
			boardSquares[getPlayer2X()][getPlayer2Y()] = this.getPlayer2Symbol();
		}
	}
	
//	public char[][] temparr1 =
//		   { { ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
//	         { ' ', ' ', ' ', 'O', ' ', ' ', ' ' },
//	         { ' ', ' ', 'O', ' ', 'O', 'O', ' ' },
//	         { ' ', 'O', 'O', ' ', 'X', 'X', 'O' },
//	         { 'X', 'X', ' ', ' ', ' ', ' ', 'O' },
//	         { ' ', ' ', ' ', 'O', ' ', '2', '1' },
//	         { ' ', ' ', ' ', ' ', ' ', ' ', 'O' }
//	       };
	
	public void loadSampleBoard() {
		

//		    1 2 3 4 5 6 7 
//		  a               
//		  b       O       
//		  c     O   O O   
//		  d   O O   X X O 
//		  e X X       2   
//		  f       O   O 1 
//		  g             O              
		
		
		char[][] temparr = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
					         { ' ', ' ', ' ', 'O', ' ', ' ', ' ' },
					         { ' ', ' ', 'O', ' ', 'O', 'O', ' ' },
					         { ' ', 'O', 'O', ' ', 'X', 'X', 'O' },
					         { 'X', 'X', ' ', ' ', ' ', '2', ' ' },
					         { ' ', ' ', ' ', 'O', ' ', 'O', '1' },
					         { ' ', ' ', ' ', ' ', ' ', ' ', 'O' }
					       };
		
		setBoardSquares(temparr);
		
		
		setPlayer1X(5);
		setPlayer1Y(6);
		
		setPlayer2X(4);
		setPlayer2Y(5);
		
	}
	
	public void fillBoardWithEmptySymbol() {
		
		for(int i=0; i < boardSquares.length; i++) {
			for(int j=0; j < boardSquares[i].length; j++) {
				boardSquares[i][j] = this.getEmptyBoardSquareSymbol();
			}
		}
		
	}
	
	public void setSymbolAt(int x, int y, char symbol) {
		this.getBoardSquares()[x][y] = symbol;
	}
	
	public char getSymbolAt(int x, int y) {
		return this.getBoardSquares()[x][y];
	}
	
	public void printBoardState() {
		
		for(int i=0; i < boardSquares.length + 1; i++) {
			if(i==0) {
				System.out.print("  ");
			}
			else {
				System.out.print((i) + " ");
			}
		}
		System.out.println();
		
		for(int i=0; i < boardSquares.length; i++) {
			System.out.print(this.getRowNames()[i] + " ");
			for(int j=0; j < boardSquares[i].length; j++) {
				System.out.print(boardSquares[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	public char[][] getBoardSquares() {
		return boardSquares;
	}

	public void setBoardSquares(char[][] boardSquares) {
		this.boardSquares = boardSquares;
	}
	
	public char getEmptyBoardSquareSymbol() {
		return emptyBoardSquareSymbol;
	}

	public void setEmptyBoardSquareSymbol(char emptyBoardSquareSymbol) {
		this.emptyBoardSquareSymbol = emptyBoardSquareSymbol;
	}

	public char[] getRowNames() {
		return rowNames;
	}

	public void setRowNames(char[] rowNames) {
		this.rowNames = rowNames;
	}

	public char getPlayer1Symbol() {
		return player1Symbol;
	}

	public void setPlayer1Symbol(char player1Symbol) {
		this.player1Symbol = player1Symbol;
	}

	public char getPlayer2Symbol() {
		return player2Symbol;
	}

	public void setPlayer2Symbol(char player2Symbol) {
		this.player2Symbol = player2Symbol;
	}

	public char getPlayer1RemoveSymbol() {
		return player1RemoveSymbol;
	}

	public void setPlayer1RemoveSymbol(char player1RemoveSymbol) {
		this.player1RemoveSymbol = player1RemoveSymbol;
	}

	public char getPlayer2RemoveSymbol() {
		return player2RemoveSymbol;
	}

	public void setPlayer2RemoveSymbol(char player2RemoveSymbol) {
		this.player2RemoveSymbol = player2RemoveSymbol;
	}

	public int getPlayer1X() {
		return player1X;
	}

	public void setPlayer1X(int player1x) {
		player1X = player1x;
	}

	public int getPlayer1Y() {
		return player1Y;
	}

	public void setPlayer1Y(int player1y) {
		player1Y = player1y;
	}

	public int getPlayer2X() {
		return player2X;
	}

	public void setPlayer2X(int player2x) {
		player2X = player2x;
	}

	public int getPlayer2Y() {
		return player2Y;
	}

	public void setPlayer2Y(int player2y) {
		player2Y = player2y;
	}
	
	@Override
	public String toString() {
		
		String total = "";
		
		for(int i=0; i < boardSquares.length + 1; i++) {
			if(i==0) {
				total += ("  ");
			}
			else {
				total += ((i) + " ");
			}
		}
		total += System.lineSeparator();
		
		for(int i=0; i < boardSquares.length; i++) {
			total += (this.getRowNames()[i] + " ");
			for(int j=0; j < boardSquares[i].length; j++) {
				
				char c = boardSquares[i][j];
				if(c == ' ')
					c = '-';
				total += (c + " ");
			}
			total += System.lineSeparator();
		}
		
		return total;
	}
	
	
//	public String toStringTemp() {
//		
//		String total = "";
//		
//		for(int i=0; i < temparr1.length + 1; i++) {
//			if(i==0) {
//				total += ("  ");
//			}
//			else {
//				total += ((i) + " ");
//			}
//		}
//		total += System.lineSeparator();
//		
//		for(int i=0; i < temparr1.length; i++) {
//			total += (this.getRowNames()[i] + " ");
//			for(int j=0; j < temparr1[i].length; j++) {
//				
//				char c = temparr1[i][j];
//				if(c == ' ')
//					c = '-';
//				total += (c + " ");
//			}
//			total += System.lineSeparator();
//		}
//		
//		return total;
//	}
}
