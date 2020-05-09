package sl.gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sl.exception.SLGameException;
import sl.exception.SLGameWinnerFoundException;
import sl.model.game.SLPlayer;
import sl.model.game.SLRollingDice;
import sl.model.game.board.SLGameBoard;
import sl.model.game.board.SLGameBoardImpl;
import sl.model.game.board.SLLadder;
import sl.model.game.board.SLSnake;
import sl.util.GamePlayConstants;

/**
 * Singleton Game play class 
 * @author nikhil
 */
public class SLGamePlay implements GamePlay {
	
	private static SLGamePlay gamePlay; //Singleton
	private Random random; 
	
	private int curPlayerTurn;
	private final List<SLPlayer> players;

	private int objectIdCounter;
	
	private final SLRollingDice dice;
	private SLGameBoard gameBoard;
	
	private SLGamePlay(int noOfBoxes, List<String> playerNames, List<SLSnake> snakes, 
				List<SLLadder> ladders, int lastRollingDiceMarker) {
		
		this.curPlayerTurn = 0;
		this.players = new ArrayList<SLPlayer>();
		populateGamePlayers(playerNames);

		this.dice = new SLRollingDice(getNewObjectId(), 1, lastRollingDiceMarker);
		this.gameBoard = new SLGameBoardImpl(noOfBoxes, playerNames, snakes, ladders);
	}
	/*
	 * Create a singleton object of SLGamePlay 
	 */
	public static SLGamePlay createInstance(int noOfBoxes, List<String> playerNames, List<SLSnake> snakes, 
			List<SLLadder> ladders, int lastRollingDiceMarker) {
		if (SLGamePlay.gamePlay != null) {
			throw new SLGameException("Cannot create one more instance of SLGamePlay");
		}
		if (SLGamePlay.gamePlay == null) {
			synchronized (SLGamePlay.class) {
				if (SLGamePlay.gamePlay == null) {
					SLGamePlay.gamePlay = new SLGamePlay(noOfBoxes, playerNames, snakes, ladders,
							lastRollingDiceMarker);
				}
			}
		}
		
		return SLGamePlay.gamePlay;
	}
	
	public static SLGamePlay getInstance() {
		return SLGamePlay.gamePlay;
	}
	
	@Override
	public synchronized void playTurn() {
		
		SLPlayer player = fetchCurrentPlayer();
		int diceCount = rollDice(player);
		movePlayer(player, diceCount);

		printPlayerPostion(player);
		winnerCheck(player);
		
		updateNextPlayerTurn();
	}
	
	/*************************************************************
	 ****** Privates 
	 *************************************************************/
	
	private void populateGamePlayers(List<String> playerNames) {
		if (playerNames.size() == 0) {
			throw new SLGameException("At least one player should be there to play the game.");
		}
		for (String playerName : playerNames) {
			this.players.add(new SLPlayer(playerName));
		}
	}
	
	private SLPlayer fetchCurrentPlayer() {
		return this.players.get(curPlayerTurn);
	}
	
	private void updateNextPlayerTurn() {
		this.curPlayerTurn = ((this.curPlayerTurn + 1) % this.players.size());
	}
	
	private int rollDice(SLPlayer player) {
		int diceCount = (Math.abs(getRandom().nextInt()) % this.dice.getEndMaker()) + 1;
		printDiceCount(player, diceCount);
		return diceCount;
	}
	
	private void movePlayer(SLPlayer player, int diceCount) {
		if (player.getCurPos() + diceCount > gamePlay.gameBoard.getEndBox()) {
			return;
		}
		if (player.getCurPos() == GamePlayConstants.PLAYER_DEFAULT_POSITION && diceCount != this.dice.getEndMaker()) {
			return;
		} else if (player.getCurPos() == GamePlayConstants.PLAYER_DEFAULT_POSITION && diceCount == this.dice.getEndMaker()) {
			diceCount = rollDice(player);
		}
		
		player.setCurPos(player.getCurPos() + diceCount);
		snakesCheckAndUpdate(player);
		ladderCheckAndUpdate(player);
		
	}
	
	/**
	 * Check for any snake at current position of player.
	 * If any snake exist and update player current position.
	 * 
	 * @param player : current player
	 */
	private void snakesCheckAndUpdate(SLPlayer player) {
		if (this.gameBoard.getSnakes().containsKey(player.getCurPos())) {
			player.setCurPos(this.gameBoard.getSnakes().get(player.getCurPos()).getEnd());
		}
	}
	
	/**
	 * Check for any ladder at current position of player.
	 * If any ladder exist and update player current position.
	 * 
	 * @param player
	 */
	private void ladderCheckAndUpdate(SLPlayer player) {
		if (this.gameBoard.getLadders().containsKey(player.getCurPos())) {
			player.setCurPos(this.gameBoard.getLadders().get(player.getCurPos()).getEnd());
		}
	}

	/**
	 * Check if player won. If yes then throw winner exception.
	 * 
	 * @param player
	 */
	private void winnerCheck(SLPlayer player) {
		if (player.getCurPos() == this.gameBoard.getEndBox()) {
			printWinnerPlayerMessage(player);
			throw new SLGameWinnerFoundException("Winner found");
		}
	}
	private void printPlayerPostion(SLPlayer player) {
		System.out.println("Player '" + player.getName() + "' is at box : " + player.getCurPos());
	}
	private void printDiceCount(SLPlayer player, int diceCount) {
		System.out.println("Player '" + player.getName() + "' dice count : " + diceCount);
	}
	private void printWinnerPlayerMessage(SLPlayer player) {
		System.out.println("***************************************************************");
		System.out.println("Player '" + player.getName() + "' won the game");
		System.out.println("***************************************************************");
	}

	private int getNewObjectId() {
		this.objectIdCounter++;
		return objectIdCounter;
	}
	
	private Random getRandom() {
		if (this.random == null) {
			synchronized(SLGamePlay.class) {
				if (this.random == null) {
					this.random = new Random();
				}
			}
		}
		return this.random;
	}
	
}
