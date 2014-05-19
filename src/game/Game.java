package game;


/**
 * 
 * @author zhihuaPeng
 *
 */
public class Game {
	
	public static final int TYPE_UNSTARTED = 0;
	public static final int TYPE_UNFINISHED = 1;
	public static final int TYPE_WIN = 2;
	public static final int TYPE_FAILED = 3;
	
	private int state = 0;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
