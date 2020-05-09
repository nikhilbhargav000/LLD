package sl.model.game.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sl.exception.SLInvalidLadderException;
import sl.exception.SLInvalidSnakeException;

public class SLGameBoardImpl extends BoxGameBoard implements SLGameBoard {
	
	private final Map<Integer, SLSnake> snakes;
	private final Map<Integer, SLLadder> ladders;
	
	private int objectIdCounter;

	public SLGameBoardImpl(int noOfBoxes, List<String> playerNames, List<SLSnake> snakes, 
			List<SLLadder> ladders) {
		super(1, noOfBoxes);
		this.objectIdCounter = 0;
		this.snakes = new HashMap<Integer, SLSnake>();
		this.ladders = new HashMap<Integer, SLLadder>(); 
		populateSLGameSnakesMap(snakes);
		populateSLGameLadderMap(ladders);
	}
	
	@Override
	public Map<Integer, SLSnake> getSnakes() {
		return snakes;
	}

	@Override
	public Map<Integer, SLLadder> getLadders() {
		return ladders;
	}

	private void populateSLGameSnakesMap(List<SLSnake> snakeList) {
		
		for (SLSnake snake : snakeList) {
			if (this.snakes.containsKey(snake.getStart()) || this.ladders.containsKey(snake.getStart())) {
				throw new SLInvalidSnakeException("Only one snake or ladder can start from one box");
			}
			if (snake.getStart() < this.getStartBox() || snake.getEnd() > this.getEndBox()) {
				throw new SLInvalidSnakeException("Snake should be in board borders");
			}
			// Creating new object to add immutable for snakes
			snakes.put(snake.getStart(), new SLSnake(getNewObjectId(), snake.getStart(), snake.getEnd()));
		}
	}
	
	private void populateSLGameLadderMap(List<SLLadder> ladderList) {
		for (SLLadder ladder : ladderList) {
			
			if (this.snakes.containsKey(ladder.getStart()) || this.ladders.containsKey(ladder.getStart())) {
				throw new SLInvalidLadderException("Only one snake or ladder can start from one box");
			}
			if (ladder.getStart() < this.getStartBox() || ladder.getEnd() > this.getEndBox()) {
				throw new SLInvalidSnakeException("Ladder should be in board borders");
			}
			// Creating new object to add immutable for ladder
			this.ladders.put(ladder.getStart(), new SLLadder(getNewObjectId(), ladder.getStart(), 
					ladder.getEnd()));
		}
	}
	
	private int getNewObjectId() {
		this.objectIdCounter++;
		return objectIdCounter;
	}
	
	
}
