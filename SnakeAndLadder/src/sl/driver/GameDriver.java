package sl.driver;

import java.util.ArrayList;
import java.util.List;

import sl.gameplay.GamePlay;
import sl.gameplay.SLGamePlay;
import sl.model.game.board.SLLadder;
import sl.model.game.board.SLSnake;
import sl.service.GamePlayService;
import sl.service.SLGamePlayServiceImpl;

public class GameDriver {

	public static void main(String[] args) {
		new GameDriver().driver();
	}
	
	public void driver() {
		List<SLSnake> snakes = new ArrayList<SLSnake>();
		snakes.add(new SLSnake.SLSnakeBuilder().start(95).end(6).build());
		snakes.add(new SLSnake.SLSnakeBuilder().start(56).end(33).build());
		snakes.add(new SLSnake.SLSnakeBuilder().start(58).end(30).build());
		snakes.add(new SLSnake.SLSnakeBuilder().start(54).end(23).build());
		snakes.add(new SLSnake.SLSnakeBuilder().start(22).end(6).build());
		
		List<SLLadder> ladders = new ArrayList<SLLadder>();
		ladders.add(new SLLadder.SLLadderBuilder().start(10).end(90).build());
		ladders.add(new SLLadder.SLLadderBuilder().start(15).end(80).build());
		ladders.add(new SLLadder.SLLadderBuilder().start(20).end(67).build());
		ladders.add(new SLLadder.SLLadderBuilder().start(35).end(90).build());
		ladders.add(new SLLadder.SLLadderBuilder().start(67).end(93).build());
		ladders.add(new SLLadder.SLLadderBuilder().start(70).end(86).build());
		ladders.add(new SLLadder.SLLadderBuilder().start(34).end(56).build());
		
		List<String> playerNames = new ArrayList<>();
		playerNames.add("A");
		playerNames.add("B");
		playerNames.add("C");
		playerNames.add("D");
		playerNames.add("E");
		
		// Get a single Game play object
		GamePlay gamePlay = SLGamePlay.createInstance(100, playerNames, snakes, ladders, 6);
		GamePlayService gamePlayService = new SLGamePlayServiceImpl();
		gamePlayService.playGame(gamePlay);
	}

}
