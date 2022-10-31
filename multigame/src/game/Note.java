package game;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{
	public static int SCREEN_WIDTH =1280;
	public static int SCREEN_HEIGHT=720;
	public static int NOTE_SPEED=3;
	public static int SLEEP_TIME=10;
	public static int REACH_TIME=2;
	
	private Image NoteBasicImage = new ImageIcon(RythmMain.class.getResource("../images/noteBasic.png")).getImage();
	private int x,y=580-(1000/RythmMain.SLEEP_TIME*RythmMain.NOTE_SPEED)*RythmMain.REACH_TIME;
	private String noteType;
	private boolean proceeded =true;
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	public void close() {
		proceeded=false;
	}
	
	
	public Note(String noteType) {
		if(noteType.equals("S")) {
			x=228;
		}
		else if(noteType.equals("D")) {
			x=332;
		}
		else if(noteType.equals("F")) {
			x=436;
		}
		else if(noteType.equals("Space")) {
			x=540;
		}
		else if(noteType.equals("J")) {
			x=744;
		}else if(noteType.equals("K")) {
			x=848;
		}
		else if(noteType.equals("L")) {
			x=952;
		}
		this.noteType = noteType;
	}
	public void screenDraw(Graphics2D g) {
		if(noteType.equals("Space")) {
			g.drawImage(NoteBasicImage, x, y, null);
			g.drawImage(NoteBasicImage, x+100, y, null);
		}
		else
		{
			g.drawImage(NoteBasicImage, x, y, null);
		}
	}
	public void drop() {
		y+=RythmMain.NOTE_SPEED;
		if(y>620) {
			System.out.println("Miss");
			close();
		}
	}
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				if(proceeded) {
					Thread.sleep(RythmMain.SLEEP_TIME);
				}
				else {
					interrupt();
					break;
				}
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public String judge() {
		if(y>=613)
		{
			System.out.println("Bad");
			close();
			return "Bad";
		}
		else if(y>=600) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y>=587) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y>=573) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if(y>=565) {
			System.out.println("Grate");
			close();
			return "Great";
		}
		else if(y>=550) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y>=535) {
			System.out.println("Bad");
			close();
			return "Bad";
		}
		return "Miss";
	}
	public int getY() {
		return y;
	}

}
