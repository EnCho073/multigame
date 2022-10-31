package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	
	public static int SCREEN_WIDTH =1280;
	public static int SCREEN_HEIGHT=720;
	public static int NOTE_SPEED=3;
	public static int SLEEP_TIME=10;
	public static int REACH_TIME=2;
	
	private Image noteRouteLineImage = new ImageIcon(RythmMain.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgmentInfoImage = new ImageIcon(RythmMain.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(RythmMain.class.getResource("../images/gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
	private Image judgeImage;
	
	private Image keyPadSImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace2Image = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	//private boolean gameMaker=true; //게임제작모드 false면 게임모드
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName,String difficulty,String musicTitle) {
		this.titleName=titleName;
		this.difficulty=difficulty;
		this.musicTitle=musicTitle;
		gameMusic = new Music(this.musicTitle,false);
	}
	
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);	
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgmentInfoImage, 0, 580, null);
		g.drawImage(judgeImage, 460, 420, null);
		g.drawImage(keyPadSImage, 228, 580, null);
		g.drawImage(keyPadDImage, 332, 580, null);
		g.drawImage(keyPadFImage, 436, 580, null);
		g.drawImage(keyPadSpace1Image, 540, 580, null);
		g.drawImage(keyPadSpace2Image, 640, 580, null);
		g.drawImage(keyPadJImage, 774, 580, null);
		g.drawImage(keyPadKImage, 848, 580, null);
		g.drawImage(keyPadLImage, 952, 580, null);
		
		
		for(int i=0; i<noteList.size();i++)
		{
			Note note= noteList.get(i);
			if(note.getY()>620) {
				judgeImage = new ImageIcon(RythmMain.class.getResource("../images/Miss.png")).getImage();
			}
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}
			else {
			note.screenDraw(g);
			}
		}
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString(titleName,20,702);
		g.drawString(difficulty,1190,702);
		g.setFont(new Font("Arial",Font.PLAIN,26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant",Font.BOLD,30));
		g.drawString("000000",565,702);
		
		
	}
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadPressed.png")).getImage();
		//new Music("도.mp3",false).start();
		  //if(gameMaker==true){
			  //System.out.println(gameMusic.getTime()+" S");}//게임제작모드
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadPressed.png")).getImage();
		//new Music("레.mp3",false).start();
		 //if(gameMaker==true){
			 // System.out.println(gameMusic.getTime()+" D");}
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadPressed.png")).getImage();
		//new Music("미.mp3",false).start();
		//if(gameMaker==true){
			 // System.out.println(gameMusic.getTime()+" F");}
		  }//게임제작모드}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(RythmMain.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(RythmMain.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace1Image = new ImageIcon(RythmMain.class.getResource("../images/keyPadPressed.png")).getImage();
		keyPadSpace2Image = new ImageIcon(RythmMain.class.getResource("../images/keyPadPressed.png")).getImage();
		//new Music("파.mp3",false).start();
		//(gameMaker==true){
			  //System.out.println(gameMusic.getTime()+" Space");}//게임제작모드
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace1Image = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
		keyPadSpace2Image = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadPressed.png")).getImage();
		//new Music("솔.mp3",false).start();
		//if(gameMaker==true){
			  //System.out.println(gameMusic.getTime()+" J");}
		  }//게임제작모드}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadPressed.png")).getImage();
		//new Music("라.mp3",false).start();
		//if(gameMaker==true){
			//  System.out.println(gameMusic.getTime()+" K"); }//게임제작모드
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
		
	}
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadPressed.png")).getImage();
		//new Music("시.mp3",false).start();
		//if(gameMaker==true){
			  //System.out.println(gameMusic.getTime()+" L");}
		  }//게임제작모드}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(RythmMain.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(RythmMain.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(this.titleName);
	}
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	public void dropNotes(String titleName) {
		Beat[] beats= null;
		if(titleName.equals("Rain")&& difficulty.equals("Easy")) {
			int startTime=1-RythmMain.REACH_TIME*2000;
			int gap=146;
			beats = new Beat[] 
					{
					 new Beat(5540,"Space"),
					 new Beat(5970,"J"),
					 new Beat(6110,"K"), 
					 new Beat(7650,"K"),
					 new Beat(7970,"J"),	 
					 new Beat(9600,"Space"),
					 new Beat(11610,"F"),
					 new Beat(12020,"F"),
					 new Beat(12160,"F"),
					 new Beat(12480,"K"),
					 new Beat(12620,"K"),
					 new Beat(13110,"J"),
					 new Beat(13480,"L"),
					 new Beat(14150,"L"),
					 new Beat(14510,"J"),
					 new Beat(14580,"L"),
					 new Beat(16150,"L"),
					 new Beat(16430,"D"),
					 new Beat(16590,"S"),
					 new Beat(16710,"K"),
					 new Beat(17020,"J"),
					 new Beat(17150,"L"),
					 new Beat(17540,"K"),
					 new Beat(17610,"K"),
					 new Beat(17950,"J"),
					 new Beat(18390,"K"),
					 new Beat(20180,"J"),
					 new Beat(20400,"K"),
					 new Beat(22040,"J"),
					 new Beat(22120,"K"),
					 new Beat(22390,"J"),
					 new Beat(22670,"K"),
					 new Beat(24060,"L"),
					 new Beat(24390,"L"),
					 new Beat(25460,"D"),
					 new Beat(26030,"K"),
					 new Beat(26140,"Space"),
					 new Beat(26460,"D"),
					 new Beat(25850,"J"),
					 new Beat(26030,"F"),
					 new Beat(26140,"L"),
					 new Beat(26400,"K"),
					 new Beat(26970,"K"),
					 new Beat(27080,"J"),
					 new Beat(27410,"L"),
					 new Beat(27530,"K"),
					 new Beat(27860,"Space"),
					 new Beat(28040,"K"),
					 new Beat(28370,"K"),
					 new Beat(28720,"Space"),
					 new Beat(28970,"K"),
					 new Beat(29280,"L"),
					 new Beat(29570,"D"),
					 new Beat(29570,"K"),
					 new Beat(29890,"F"),
					 new Beat(30000,"L"),
					 new Beat(30430,"K"),
					 new Beat(30960,"K"),
					 new Beat(31490,"K"),
					 new Beat(31980,"K"),
					 new Beat(32510,"K"),
					 new Beat(32980,"K"),
					 new Beat(33910,"Space"),
					 new Beat(34000,"K"),
					 new Beat(34440,"K"),
					 new Beat(34920,"K"),
					 new Beat(35420,"K"),
					 new Beat(35940,"K"),
					 new Beat(36030,"K"),
					 new Beat(36300,"L"),
					 new Beat(36450,"K"),
					 new Beat(36740,"J"),
					 new Beat(36910,"L"),
					 new Beat(37320,"K"),
					 new Beat(37450,"D"),
					 new Beat(37610,"K"),
					 new Beat(37930,"J"),
					 new Beat(38380,"F"),
					 new Beat(40400,"Space"),
					 new Beat(40460,"Space"),
					 new Beat(40490,"K"),
					 new Beat(41610,"J"),
					 new Beat(42380,"Space"),	 
			};
		}
		else if(titleName.equals("Rain")&& difficulty.equals("Hard")) {
			int startTime=4460-RythmMain.REACH_TIME*1000;
			int gap=146;
			beats = new Beat[] {
			};
		}
		else if(titleName.equals("Camille")&& difficulty.equals("Easy")) {
			int startTime=4460-RythmMain.REACH_TIME*1000;
			int gap=146;
			beats = new Beat[] {
			};
		}else if(titleName.equals("Camille")&& difficulty.equals("Hard")) {
			int startTime=4460-RythmMain.REACH_TIME*1000;
			int gap=146;
			beats = new Beat[] {
			};
		}
		else if(titleName.equals("Bensound - Energy")&& difficulty.equals("Easy")) {
			int startTime=1000-RythmMain.REACH_TIME*1000;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
			};
		}
		else if(titleName.equals("Bensound - Energy")&& difficulty.equals("Hard")) {
			int startTime=1000-RythmMain.REACH_TIME*1000;
			beats = new Beat[] {
					new Beat(startTime,"Space"),
			};
		}
		
		int i=0;
		gameMusic.start();
		while(i<beats.length && !isInterrupted()) {
			boolean dropped =false;
			if(beats[i].getTime()<=gameMusic.getTime()) {
				Note note = new Note(beats[i].getNotName());
				note.start();
				noteList.add(note);
				i++;
				dropped=true;
			}
			if(dropped) {
				try {
					Thread.sleep(5);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void judge(String input) {
		for(int i=0; i< noteList.size();i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	public void judgeEvent(String judge) {
		if(judge.equals("Bad")) {
			judgeImage = new ImageIcon(RythmMain.class.getResource("../images/Bad.png")).getImage();
		}
		else if(judge.equals("Good")) {
			judgeImage = new ImageIcon(RythmMain.class.getResource("../images/Good.png")).getImage();
		}
		else if(judge.equals("Great")) {
			judgeImage = new ImageIcon(RythmMain.class.getResource("../images/Great.png")).getImage();
		}
		else if(judge.equals("Perfect")) {
			judgeImage = new ImageIcon(RythmMain.class.getResource("../images/Perfect.png")).getImage();
		}
		else if(judge.equals("Miss")) {
			judgeImage = new ImageIcon(RythmMain.class.getResource("../images/Miss.png")).getImage();
		}
	}
	
}
