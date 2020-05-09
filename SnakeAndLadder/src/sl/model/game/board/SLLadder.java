package sl.model.game.board;

import sl.exception.SLInvalidLadderException;
import sl.model.game.SLEntity;

public class SLLadder extends SLBoardEntity implements SLEntity {
	
	private int start;
	private int end;

	public SLLadder(int id, int start, int end) {
		super(id, SLBoardEntityType.LADDER);
		if (start >= end) {
			throw new SLInvalidLadderException("Start should be smaller then end");
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
	public static class SLLadderBuilder {
		int id;
		private int start;
		private int end;
		
		public SLLadderBuilder() {
			
		}

		public SLLadderBuilder id(int id) {
			this.id = id;
			return this;
		}
		public SLLadderBuilder start(int start) {
			this.start = start;
			return this;
		}
		public SLLadderBuilder end(int end) {
			this.end = end;
			return this;
		}
		
		public SLLadder build() {
			return new SLLadder(id, start, end);
		}
		
	}
	
}
