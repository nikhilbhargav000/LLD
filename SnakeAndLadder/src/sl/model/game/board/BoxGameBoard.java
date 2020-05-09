package sl.model.game.board;

public abstract class BoxGameBoard {
	
	private final int startBox;
	private final int endBox;
	
	public BoxGameBoard(int startBox, int endBox) {
		super();
		this.startBox = startBox;
		this.endBox = endBox;
	}

	public int getStartBox() {
		return startBox;
	}

	public int getEndBox() {
		return endBox;
	}
	
}
