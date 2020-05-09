package sl.model.game.board;

import java.util.Map;

import sl.model.game.SLEntity;

public interface SLGameBoard extends SLEntity {
	
	public Map<Integer, SLSnake> getSnakes();
	
	public Map<Integer, SLLadder> getLadders();

	public int getStartBox();
	
	public int getEndBox();
}
