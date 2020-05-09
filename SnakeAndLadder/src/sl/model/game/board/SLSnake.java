package sl.model.game.board;

import sl.exception.SLInvalidSnakeException;
import sl.model.game.SLEntity;

public class SLSnake extends SLBoardEntity implements SLEntity {

	private final int start;
	private final int end;
	
	public SLSnake (int id, int start, int end) {
		super(id, SLBoardEntityType.SNAKE);
		if (start <= end) {
			throw new SLInvalidSnakeException("Invalid start should be greater then end");
		}
		this.start = start;
		this.end = end;
	}
	// Only Getter to make the class immutable 
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	
	/**
	 * Builder Design pattern
	 */
	public static class SLSnakeBuilder {
		int id;
		private int start;
		private int end;
		
		public SLSnakeBuilder() {
			
		}

		public SLSnakeBuilder id(int id) {
			this.id = id;
			return this;
		}
		public SLSnakeBuilder start(int start) {
			this.start = start;
			return this;
		}
		public SLSnakeBuilder end(int end) {
			this.end = end;
			return this;
		}
		
		public SLSnake build() {
			return new SLSnake(id, start, end);
		}
		
	}
	
}
