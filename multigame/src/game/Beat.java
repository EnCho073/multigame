package game;

public class Beat {
	public static int SCREEN_WIDTH =1280;
	public static int SCREEN_HEIGHT=720;
	public static int NOTE_SPEED=3;
	public static int SLEEP_TIME=10;
	public static int REACH_TIME=2;
	private int time;
	private String notName;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getNotName() {
		return notName;
	}
	public void setNotName(String notName) {
		this.notName = notName;
	}
	public Beat(int time, String notName) {
		super();
		this.time = time;
		this.notName = notName;
	}
	
}
