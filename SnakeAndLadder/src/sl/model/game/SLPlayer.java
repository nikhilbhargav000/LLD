package sl.model.game;

import sl.util.GamePlayConstants;

public class SLPlayer extends Player implements SLEntity {
	
	private int curPos;

	public SLPlayer(String name) {
		super(name);
		this.curPos = GamePlayConstants.PLAYER_DEFAULT_POSITION;
	}

	public int getCurPos() {
		return curPos;
	}

	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}
}
