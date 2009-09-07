package mancala;

import java.util.ArrayList;

public class ManCala {

	/*
	 * public static void main(String args[]) { Random myRandom = new Random();
	 * ManCala myMancala = new ManCala();
	 * 
	 * // myMancala.move(2);
	 * 
	 * // while(!myMancala.isFinished) // { //
	 * myMancala.move(myRandom.nextInt(13)); // //myMancala.printBoard(); // }
	 * 
	 * myMancala = new ManCala();
	 * 
	 * 
	 * 
	 * while(!myMancala.isFinished) {
	 * myMancala.move(myMancala.getBestNextMove(30)); myMancala.printBoard(); }
	 * }
	 */

	ArrayList<Integer> topBoard = new ArrayList<Integer>();
	ArrayList<Integer> bottomBoard = new ArrayList<Integer>();
	boolean turn;

	public ManCala() {
		for (int i = 0; i < 6; ++i) {
			topBoard.add(4);
		}
		topBoard.add(0);
		for (int i = 7; i < 13; ++i) {
			bottomBoard.add(4);
		}
		bottomBoard.add(0);
		turn = true;
		printBoard();
	}

	public ManCala(ArrayList<Integer> top, ArrayList<Integer> bottom) {
		topBoard = (ArrayList<Integer>) top.clone();
		bottomBoard = (ArrayList<Integer>) bottom.clone();
		turn = true;

	}

	public int getBestNextMove() {
		return getBestNextMove(10);
	}

	public int getBestNextMove(int searchDepth) {
		double bestValue = -1000;
		double newValue = 0;
		int bestMove = -1;
		for (int i = 5; i > -1; --i) {
			ManCala testMancala;

			if (turn) {
				if (topBoard.get(i) == 0)
					continue;
				testMancala = ManCala.newGameWithMove(topBoard, bottomBoard, i);
			} else {
				if (bottomBoard.get(i) == 0)
					continue;
				testMancala = ManCala.newGameWithMove(bottomBoard, topBoard, i);
			}

			// testMancala.printBoard();
			if (testMancala.turn) {
				newValue = ManCala.findBestNextMove(testMancala.topBoard,
						testMancala.bottomBoard, 50, bestValue, searchDepth);
			} else {
				newValue = -ManCala.findBestNextMove(testMancala.bottomBoard,
						testMancala.topBoard, -bestValue, -50, searchDepth);
			}

			System.out.println("NEW xxxMOVE " + i + " " + newValue);
			if (newValue > bestValue) {

				bestMove = i;
				bestValue = newValue;
			}

		}

		System.out.println("best move " + bestMove + " " + bestValue);

		if (turn) {
			return bestMove;
		} else {
			return bestMove + 7;
		}
	}

	public static void printBoardPieces(ArrayList<Integer> topBoard,
			ArrayList<Integer> bottomBoard) {
		System.out.print("\n  ");
		for (int i = 0; i < 6; ++i) {
			System.out.print(topBoard.get(i) + " ");
		}
		System.out.println();
		System.out.print(bottomBoard.get(6) + "              "
				+ topBoard.get(6) + "\n  ");
		for (int i = 5; i > -1; --i) {
			System.out.print(bottomBoard.get(i) + " ");
		}

		System.out.println();
		System.out.println("Top player's turn? " + true);
	}

	public void printBoard() {
		System.out.print("\n  ");
		for (int i = 0; i < 6; ++i) {
			System.out.print(topBoard.get(i) + " ");
		}
		System.out.println();
		System.out.print(bottomBoard.get(6) + "              "
				+ topBoard.get(6) + "\n  ");
		for (int i = 5; i > -1; --i) {
			System.out.print(bottomBoard.get(i) + " ");
		}

		System.out.println();
		System.out.println("Top player's turn? " + turn);
	}

	public boolean move(int position) // returns false if game is over
	{
		// System.out.println("moving position " + position);
		// printBoard();
		boolean needFinishedCheck;
		if (turn) {
			if (position > 5 || position < 0) {
				System.out.println("tried to move position " + position
						+ " on top player's turn");
				return true;
			}
			needFinishedCheck = doMove(position, topBoard, bottomBoard);
		} else {
			if (position > 12 || position < 7) {
				System.out.println("tried to move position " + position
						+ " on bottom player's turn");
				return true;
			}
			needFinishedCheck = doMove(position - 7, bottomBoard, topBoard);
		}

		if (needFinishedCheck) {

			int bottomCount = 0;
			int topCount = 0;
			for (int i = 0; i < 6; ++i) {
				bottomCount += bottomBoard.get(i);
				topCount += topBoard.get(i);
			}
			if (topCount == 0 || bottomCount == 0) {
				finishGame(topCount, bottomCount);
				return false;
			}

		}
		// printBoard();
		return true;
	}

	void finishGame(int topBonus, int bottomBonus) {
		// printBoard();
		int topScore = topBoard.get(6) + topBonus;
		int bottomScore = bottomBoard.get(6) + bottomBonus;
		// System.out.println("Top score " + topScore);
		// finalTopCount = topScore;
		// System.out.println("Bottom score " + bottomScore);
		// finalBottomCount = bottomScore;
		topBoard.set(6, topScore);
		bottomBoard.set(6, bottomScore);
		isFinished = true;
	}

	boolean isFinished = false;

	public boolean isGameFinished() {
		return isFinished;
	}

	// int finalTopCount = 0;
	// int finalBottomCount = 0;

	// implements move, determines if we need to check if the game is over
	public boolean doMove(int move, ArrayList<Integer> startingBoard,
			ArrayList<Integer> otherBoard) {
		boolean possiblyOver = false;
		int stonesToBeMoved = startingBoard.get(move);
		if (stonesToBeMoved == 0) {
			System.out.println("tried to move an empty spot");
			return possiblyOver;
		}

		startingBoard.set(move, 0);

		int currentPosition = move + 1;
		for (int stonesLeft = stonesToBeMoved; stonesLeft > 0; --stonesLeft) {
			if (currentPosition < 7) // ie still on starting board
			{
				startingBoard.set(currentPosition, startingBoard
						.get(currentPosition) + 1);
				currentPosition++;
			} else if (currentPosition < 13)// ie on top board
			{
				otherBoard.set(currentPosition - 7, otherBoard
						.get(currentPosition - 7) + 1);
				currentPosition++;
			} else // have looped around
			{
				startingBoard.set(0, startingBoard.get(0) + 1);
				currentPosition = 1;
			}
		}

		currentPosition--; // undo last increment since it ran out of stones

		if (currentPosition < 6 && startingBoard.get(currentPosition) == 1) {
			// special rule of last stone going into an empty place

			startingBoard.set(6, startingBoard.get(6)
					+ otherBoard.get(5 - currentPosition) + 1);
			startingBoard.set(currentPosition, 0);
			otherBoard.set(5 - currentPosition, 0);
			// System.out.println("o snap");
			possiblyOver = true;
		}

		if (currentPosition != 6) {
			turn = !turn;

		}

		return possiblyOver || move == 5;

	}

	public double getBoardValue() {

		// double turnValue = turn ? 1.4 : 0;
		return topBoard.get(6) - bottomBoard.get(6);// + turnValue;
	}

	public int getBoardValueAt(int position) {
		if (position > 0 && position < 7) {
			return topBoard.get(position);
		} else if (position > 6 && position < 14) {
			return bottomBoard.get(position - 7);
		} else {
			System.out.println("bad position asked for " + position);
			return -1;
		}

	}

	public static ManCala newGameWithMove(ArrayList<Integer> myTopBoard,
			ArrayList<Integer> myBottomBoard, int position) {
		ManCala myMancala = new ManCala(myTopBoard, myBottomBoard);
		myMancala.move(position);
		return myMancala;

	}

	// best guaranteed means that the player can do no better than that
	public static double findBestNextMove(ArrayList<Integer> myBoard,
			ArrayList<Integer> otherBoard, double bestGuaranteed,
			double worstGuaranteed, int depthLeft) {

		// System.out.println("depthLeft " + depthLeft);
		// ManCala.printBoardPieces(myBoard, otherBoard);
		// stop recursion somewhere
		if (depthLeft <= 0 || myBoard.get(6) + otherBoard.get(6) == 48) {
			// System.out.println("SDFCVC " + (myBoard.get(6)-otherBoard.get(6)
			// ));
			// if(myBoard.get(6) + otherBoard.get(6) ==48)
			// System.out.println("finsihedsfs");
			return myBoard.get(6) - otherBoard.get(6) + .4;
		}

		double best = bestGuaranteed;
		double worst = worstGuaranteed;
		double currentValue = -99999999;
		for (int i = 5; i > -1; --i) {
			if (myBoard.get(i) != 0) // ie valid move
			{
				ManCala nextBoard = newGameWithMove(myBoard, otherBoard, i);
				double boardValue;

				if (nextBoard.turn) {
					boardValue = findBestNextMove(nextBoard.topBoard,
							nextBoard.bottomBoard, best, worst, depthLeft - 1);
				} else {
					boardValue = -findBestNextMove(nextBoard.bottomBoard,
							nextBoard.topBoard, -worst, -best, depthLeft - 1);
				}
				// System.out.println("board value return " + boardValue);
				if (best <= boardValue) {
					return boardValue;
				}
				if (boardValue > worst) {
					worst = boardValue;
				}
				if (boardValue > currentValue) {
					// System.out.println("best move so far " + boardValue);
					// nextBoard.printBoard();
					currentValue = boardValue;
				}
			}
		}
		return currentValue;
	}
}
