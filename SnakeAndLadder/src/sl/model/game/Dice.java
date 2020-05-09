package sl.model.game;

public abstract class Dice {
	
	private int id;

	public Dice(int id) {
		super();
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
