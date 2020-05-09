package sl.model.game;

public class SLRollingDice extends Dice implements SLEntity {
	private final int startMaker;
	private final int endMaker;
	
	public SLRollingDice(int id, int startMaker, int endMaker) {
		super(id);
		this.startMaker = startMaker;
		this.endMaker = endMaker;
	}
	
	public int getStartMaker() {
		return startMaker;
	}

	public int getEndMaker() {
		return endMaker;
	}
	
}
