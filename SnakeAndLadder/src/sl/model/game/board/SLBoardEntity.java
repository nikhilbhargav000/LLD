package sl.model.game.board;

public abstract class SLBoardEntity {
	
	private int id;
	private SLBoardEntityType entityType;
	
	public SLBoardEntity(int id, SLBoardEntityType entityType) {
		super();
		this.id = id;
		this.entityType = entityType;
	}
	
	public int getId() {
		return id;
	}
	public SLBoardEntityType getEntityType() {
		return entityType;
	}
	
}
