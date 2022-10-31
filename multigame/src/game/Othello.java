package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import game.Othello;

public class Othello extends JPanel implements MouseListener, ActionListener {
	JLayeredPane layeredPane;
	JPanel board;
	JPanel controlPanel;
	JToolBar tool;
	JPanel gui;
	
	JButton restartButton;
	JButton endButton;
	JButton giveUpButton;
	JButton skipTurnButton;
	
	JLabel imgLabel;
	
	BufferedImage black;
	ImageIcon blackIcon;
	
	BufferedImage blue;
	ImageIcon blueIcon;
	
	JPanelSquare[][] squarePanel = new JPanelSquare[8][8];
	String turn = "black";
	
	JLabel blueLabel;
	JLabel blackLabel;
	
	JLabel blueScore;
	JLabel blackScore;
	
	JLabel turnLabel;
	
	int bluecount = 0;
	int blackcount = 0;
	
	boolean flag = true;
	
	public Othello() throws IOException {
		
		blue = ImageIO.read(new File("C:\\Java_Workspace\\game\\src\\game\\whitestar.png"));
		blueIcon = new ImageIcon(blue);
		
		black = ImageIO.read(new File("C:\\Java_Workspace\\game\\src\\game\\blackstar.png"));
		blackIcon = new ImageIcon(black);
		
		Dimension guiSize = new Dimension(600,600);
		Dimension size = new Dimension(600,600);
		
		gui = new JPanel(new BorderLayout());
		gui.setSize(guiSize);
				
		//Toolbar 달기
		tool = new JToolBar();
		
		restartButton = new JButton("Game ReStart");
		endButton = new JButton("Game End");
		giveUpButton = new JButton("Game GiveUp");
		skipTurnButton = new JButton("Skip turn");
		
		blueLabel = new JLabel("White : ");
		blueScore =  new JLabel("2");
		
		blackLabel = new JLabel("Black : ");
		blackScore = new JLabel("2");
		
		turnLabel = new JLabel("Black Turn");
		turnLabel.setFont(new Font("Bold",Font.BOLD,20));
		
		tool.add(restartButton);
		tool.addSeparator();
		tool.add(endButton);
		tool.addSeparator();
		tool.add(giveUpButton);
		tool.addSeparator();
		tool.add(skipTurnButton);
		
		tool.addSeparator();
		tool.add(blackLabel);
		tool.add(blackScore);
		
		tool.addSeparator();
		tool.add(blueLabel);
		tool.add(blueScore);
		
		
		tool.addSeparator();
		tool.add(turnLabel);
		
		gui.add(tool, BorderLayout.NORTH);

		//JFrame에 LayeredPane 넣기
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(size);
		gui.add(layeredPane);
		
		//LayeredPane에 board 넣기
		board = new JPanel();
		board.setLayout(new GridLayout(8,8));
		board.setPreferredSize(size);
		board.setBounds(0, 0, size.width, size.height);
		layeredPane.add(board,JLayeredPane.DEFAULT_LAYER);
		
		//setBoard();
		Color color = new Color(0xD6D7D6);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squarePanel[i][j] = new JPanelSquare(i, j, "Empty");
				squarePanel[i][j].setLayout(new BorderLayout());
				// JPanel square = new JPanel(new BorderLayout());
				squarePanel[i][j].setBackground((i + j) % 2 == 0 ? Color.gray : color);
				ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				imgLabel = new JLabel(icon, SwingConstants.CENTER);

				squarePanel[i][j].add(imgLabel);

				board.add(squarePanel[i][j]);
			}
		} // for

		// 판 초반 세팅 
		
		  squarePanel[3][3].getPiece().setIcon(blueIcon);
		  squarePanel[4][3].getPiece().setIcon(blackIcon);
		  squarePanel[3][4].getPiece().setIcon(blackIcon);
		  squarePanel[4][4].getPiece().setIcon(blueIcon);
		  
		  squarePanel[3][3].setState("blue"); squarePanel[4][3].setState("black");
		  squarePanel[3][4].setState("black"); squarePanel[4][4].setState("blue");
		 
	
		
		board.addMouseListener(this);
		skipTurnButton.addActionListener(this);
		giveUpButton.addActionListener(this);
		endButton.addActionListener(this);
		restartButton.addActionListener(this);
	}
	
	public Dimension setPreferredSize() {
		return new Dimension(700,600);
	}
	
	
	public static void othello() throws IOException {
		// Create and set up the window.
		
		JFrame frame = new JFrame();
		Othello othello = new Othello();
		frame.add(othello.gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("오델로 게임");
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
		//imageprocess.setOpaque(true);
		//frame.setContentPane(imageprocess);

	
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					othello();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Component c = board.findComponentAt(e.getX(), e.getY());
		//System.out.println(c.getParent());
		JPanelSquare square = (JPanelSquare)c.getParent();
		
		int row= square.getRow();
		int column = square.getColumn();
		

		
		Boolean checkL = checkLeft(row, column, turn);
		Boolean checkR = checkRight(row, column, turn);
		Boolean checkU = checkUp(row, column, turn);
		Boolean checkD = checkDown(row, column, turn);
		
		Boolean checkUL = checkUpLeft(row, column, turn);
		Boolean checkUR = checkUpRight(row, column, turn);
		Boolean checkDL = checkDownLeft(row, column, turn);
		Boolean checkDR = checkDownRight(row, column, turn);

		
		//둔 곳이 뒤집을 수 없을경우 Error
		if(!checkR & !checkL & !checkU & !checkD & !checkUR & !checkUL & !checkDL & !checkDR ) {
			JOptionPane.showMessageDialog(null, "둘수없는곳입니다", "Not Allowd Move", 1);
						
					
		// 뒤집을 수 있는 경우	
		}else {
			
			if (turn.equals("black")) { //바둑돌 일단 두기
				square.getPiece().setIcon(blackIcon);
				square.setState("black");
				turn = "blue";
				turnLabel.setText("White Turn");
				
			} else {
				square.getPiece().setIcon(blueIcon);
				square.setState("blue");
				turn = "black";
				turnLabel.setText("Black turn");
				
			}
		}
		
		//점수 카운트
		bluecount = 0;
		blackcount = 0;
		
		//점수 카운트
		for(int i=0;i<=7;i++) { 
			for(int j=0;j<=7;j++) {
				if(squarePanel[i][j].getState() =="blue") {
					bluecount++;		
				}
				else if(squarePanel[i][j].getState() =="black") {
					blackcount++;		
				}
			}
		}//for
		
		blueScore.setText(Integer.toString(bluecount));
		blackScore.setText(Integer.toString(blackcount));

		System.out.println("row : " + row + " column : " + column);
					
		
	} //mouseClicked Event
	
	public boolean checkRight(int row, int column, String turn) {

		if(column>5) { //뒤집을게 없는 경우
			return false;
		}
		
		int j = column;  
		
		//바로 오른돌이 같은 색이거나 비어있으면 종료
		if(squarePanel[row][j+1].getState() == turn || squarePanel[row][j+1].getState() == "Empty" ) { //바로 전돌 확인
			return false;
		}
		
		//뒤집을 수 있는 상태 
		for(int jj=column+2; jj<=7; jj++) {  
			String state = squarePanel[row][jj].getState();
			if(state == "Empty") {  //1.오른칸이 비어있는 경우
				return false;
			}
			
			if(state != turn) {//2. 서로 색깔다름
				continue;
			}
			
			if(state == turn) { //3. 같은 색깔이므로 뒤집은 후 종료.
				int between = jj- column - 1;
				//돌 사이 뒤집기
				for(int z = column+1;z <= column+between; z++) {

					if(turn == "black") {
					squarePanel[row][z].getPiece().setIcon(blackIcon);
					squarePanel[row][z].setState(turn);

					}else {
					squarePanel[row][z].getPiece().setIcon(blueIcon);
					squarePanel[row][z].setState(turn);
					}
				
				}
		 
				return true;
			}
		}
		return false;

	}

	public boolean checkLeft(int row, int column, String turn) {

		
		if(column<2) { //뒤집을게 없는 경우
			return false;
		}
		
		int j = column;  
		
		//바로 왼쪽돌이 같은 색이거나 비어있으면 종료
		if(squarePanel[row][j-1].getState() == turn || squarePanel[row][j-1].getState() == "Empty" ) { //바로 전돌 확인
			return false;
		}
		
		//뒤집을 수 있는 상태 
		for(int jj=column-2; jj>=0; jj--) {  
			String state = squarePanel[row][jj].getState();
			if(state == "Empty") {  //1.왼쪽칸이 비어있는 경우
				return false;
			}
			
			if(state != turn) {//2. 서로 색깔다름
				continue;
			}
			
			if(state == turn) { //3. 같은 색깔이므로 뒤집은 후 종료.
				int between = column - jj - 1;
				//돌 사이 뒤집기
				for(int z = column-1;z >= column-between; z--) {

					if(turn == "black") {
					squarePanel[row][z].getPiece().setIcon(blackIcon);
					squarePanel[row][z].setState(turn);

					}else {
					squarePanel[row][z].getPiece().setIcon(blueIcon);
					squarePanel[row][z].setState(turn);
					}
				
				}
		 
				return true;
			}
		}
		return false;

	}
	
	public boolean checkUp(int row, int column, String turn) {
		
		if(row<2) { //뒤집을게 없는 경우
			return false;
		}
		
		int i = row;  
		
		//바로 위돌이 같은 색이거나 비어있으면 종료
		if(squarePanel[i-1][column].getState() == turn || squarePanel[i-1][column].getState() == "Empty" ) { //바로 전돌 확인
			return false;
		}
		
		//뒤집을 수 있는 상태 
		for(int ii=row-2; ii>=0; ii--) {  
			String state = squarePanel[ii][column].getState();
			if(state == "Empty") {  //1.위칸이 비어있는 경우
				return false;
			}
			
			if(state != turn) {//2. 서로 색깔다름
				continue;
			}
			
			if(state == turn) { //3. 같은 색깔이므로 뒤집은 후 종료.
				int between = row - ii - 1;
				
			    //돌 사이 뒤집기
				for(int z = row-1;z >= row-between; z--) {

					if(turn == "black") {
					squarePanel[z][column].getPiece().setIcon(blackIcon);
					squarePanel[z][column].setState(turn);

					}else {
					squarePanel[z][column].getPiece().setIcon(blueIcon);
					squarePanel[z][column].setState(turn);
					}
				
				}
		 
				return true;
			}
		}
		return false;
		/*
		 * for (int i = 7; i > row; i--) { // 위으로 가는거
		 * 
		 * String state = squarePanel[i][column].getState(); if (state.equals("black")
		 * && turn.equals("black")) { for (int j = i - 1; j > row; j--) {
		 * squarePanel[j][column].getPiece().setIcon(blackIcon);
		 * squarePanel[j][column].setState("black");
		 * 
		 * } break; } else if (state.equals("blue") && turn.equals("blue")) { for (int j
		 * = i - 1; j > row; j--) { squarePanel[j][column].getPiece().setIcon(blueIcon);
		 * squarePanel[j][column].setState("blue");
		 * 
		 * 
		 * } } }
		 */
	}
	
	public boolean checkDown(int row, int column, String turn) {
		
		if(row>5) { //뒤집을게 없는 경우
			return false;
		}
	
		int i = row;  
		
		//바로 아래돌이 같은 색이거나 비어있으면 종료
		if(squarePanel[i+1][column].getState() == turn || squarePanel[i+1][column].getState() == "Empty" ) { //바로 전돌 확인
			return false;
		}
		
		//뒤집을 수 있는 상태 
		for(int ii=row+2; ii<=7; ii++) {  
			String state = squarePanel[ii][column].getState();
			if(state == "Empty") {  //1.아래칸이 비어있는 경우
				return false;
			}
			
			if(state != turn) {//2. 서로 색깔다름
				continue;
			}
			
			if(state == turn) { //3. 같은 색깔이므로 뒤집은 후 종료.
				int between = ii - row - 1;
				
			    //돌 사이 뒤집기
				for(int z = row+1;z <= row+between; z++) {

					if(turn == "black") {
					squarePanel[z][column].getPiece().setIcon(blackIcon);
					squarePanel[z][column].setState(turn);

					}else {
					squarePanel[z][column].getPiece().setIcon(blueIcon);
					squarePanel[z][column].setState(turn);
					}
				
				}
		 
				return true;
			}
		}
		return false;
	}
	
	public boolean checkUpLeft(int row, int column, String turn) {
		
		if(row<2 | column<2) { //뒤집을게 없는 경우
			return false;
		}
		
		int i = row; 
		int j = column;  
		
		//바로 왼쪽대각돌이 같은 색이거나 비어있으면 종료
		if(squarePanel[i-1][j-1].getState() == turn || squarePanel[i-1][j-1].getState() == "Empty" ) { //바로 전돌 확인
			return false;
		}
		
		//뒤집을 수 있는 상태 
		for(int ii=row-2, jj = column-2; ii>=0 && jj>=0; ii--,jj--) {  
			String state = squarePanel[ii][jj].getState();
			if(state == "Empty") {  //1.왼쪽대각칸이 비어있는 경우
				return false;
			}
			
			if(state != turn) {//2. 서로 색깔다름
				continue;
			}
			
			if(state == turn) { //3. 같은 색깔이므로 뒤집은 후 종료.
				int between = row - ii - 1;
			   //돌 사이 뒤집기
				for(int z = row-1, zz = column -1;z >= row-between; z--, zz--) {

					if(turn == "black") {
					squarePanel[z][zz].getPiece().setIcon(blackIcon);
					squarePanel[z][zz].setState(turn);

					}else {
					squarePanel[z][zz].getPiece().setIcon(blueIcon);
					squarePanel[z][zz].setState(turn);
					}
				
				}
		 
				return true;
			}
		}
		return false;
		/*
		 * int i = 0; int j = 0;
		 * 
		 * int gap = Math.min(row, column);
		 * 
		 * i = row - gap; j = column - gap;
		 * 
		 * for (; i < row; i++, j++) { // 오른쪽 대각으로 뒤집기
		 * 
		 * String state = squarePanel[i][j].getState();
		 * 
		 * if (state.equals("black") && turn.equals("black")) { for (i += 1, j += 1; i <
		 * row; i++, j++) { squarePanel[i][j].getPiece().setIcon(blackIcon);
		 * squarePanel[i][j].setState("black");
		 * 
		 * } break; } else if (state.equals("blue") && turn.equals("blue")) { for (i +=
		 * 1, j += 1; i < row; i++, j++) {
		 * squarePanel[i][j].getPiece().setIcon(blueIcon);
		 * squarePanel[i][j].setState("blue");
		 * 
		 * } } }
		 */

	}
	
	public boolean checkUpRight(int row, int column, String turn) { //왼쪽 대각으로 뒤집기
		
		if(row<2 | column>5) { //뒤집을게 없는 경우
			return false;
		}
		
		int i = row; 
		int j = column;  
		
		//바로 오른쪽위대각돌이 같은 색이거나 비어있으면 종료
		if(squarePanel[i-1][j+1].getState() == turn || squarePanel[i-1][j+1].getState() == "Empty" ) { //바로 전돌 확인
			return false;
		}
		
		//뒤집을 수 있는 상태 
		for(int ii=row-2, jj = column+2; ii>=0 && jj<=7; ii--,jj++) {  
			String state = squarePanel[ii][jj].getState();
			if(state == "Empty") {  //1.왼쪽칸이 비어있는 경우
				return false;
			}
			
			if(state != turn) {//2. 서로 색깔다름
				continue;
			}
			
			if(state == turn) { //3. 같은 색깔이므로 뒤집은 후 종료.
				int between = row - ii - 1;
			    //돌 사이 뒤집기
				for(int z = row-1, zz = column +1;z >= row-between; z--, zz++) {

					if(turn == "black") {
					squarePanel[z][zz].getPiece().setIcon(blackIcon);
					squarePanel[z][zz].setState(turn);

					}else {
					squarePanel[z][zz].getPiece().setIcon(blueIcon);
					squarePanel[z][zz].setState(turn);
					}
				
				}
		 
				return true;
			}
		}
		
		return false;
		/*
		 * int i=0; int j=0;
		 * 
		 * int gap = Math.min(row, 7-column);
		 * 
		 * i = row - gap; j = column + gap;
		 * 
		 * for (; i < row; i++, j--) { // 위으로 가는거
		 * 
		 * String state = squarePanel[i][j].getState();
		 * 
		 * if (state.equals("black") && turn.equals("black")) { for (i += 1, j -= 1; i <
		 * row; i++, j--) { squarePanel[i][j].getPiece().setIcon(blackIcon);
		 * squarePanel[i][j].setState("black");
		 * 
		 * } break; } else if (state.equals("blue") && turn.equals("blue")) { for (i +=
		 * 1, j -= 1; i < row; i++, j--) {
		 * squarePanel[i][j].getPiece().setIcon(blueIcon);
		 * squarePanel[i][j].setState("blue");
		 * 
		 * } } }
		 */

	}
	
	public boolean checkDownRight(int row, int column, String turn) {  //이상해서 바꿈 4-25 10:20
		
		if(row>5 | column>5) { //뒤집을게 없는 경우
			return false;
		}
		
		int i = row; //왼쪽으로 가니 row 고정
		int j = column;  
		
		//바로 아래오른쪽돌이 같은 색이거나 비어있으면 종료
		if(squarePanel[i+1][j+1].getState() == turn || squarePanel[i+1][j+1].getState() == "Empty" ) { //바로 전돌 확인
			return false;
		}
		
		//뒤집을 수 있는 상태 
		for(int ii=row+2, jj = column+2; ii<=7 && jj<=7; ii++,jj++) {  
			String state = squarePanel[ii][jj].getState();
			if(state == "Empty") {  //1.왼쪽칸이 비어있는 경우
				return false;
			}
			
			if(state != turn) {//2. 서로 색깔다름
				continue;
			}
			
			if(state == turn) { //3. 같은 색깔이므로 뒤집은 후 종료.
				int between = ii - row - 1;
			
			    //돌 사이 뒤집기
				for(int z = row+1, zz = column+1; z <= row+between; z++, zz++) {

					if(turn == "black") {
					squarePanel[z][zz].getPiece().setIcon(blackIcon);
					squarePanel[z][zz].setState(turn);

					}else {
					squarePanel[z][zz].getPiece().setIcon(blueIcon);
					squarePanel[z][zz].setState(turn);
					}
				
				}
		 
				return true;
			}
		}
		return false;
		
		/*
		 * int i = 0; int j = 0;
		 * 
		 * int gap = Math.min(7-row, 7-column);
		 * 
		 * i = row + gap; j = column + gap;
		 * 
		 * 
		 * for (; i > row; i--, j--) { String state = squarePanel[i][j].getState();
		 * 
		 * if (state.equals("black") && turn.equals("black")) { for (i -= 1, j -= 1; i >
		 * row; i--, j--) { squarePanel[i][j].getPiece().setIcon(blackIcon);
		 * squarePanel[i][j].setState("black");
		 * 
		 * } break; } else if (state.equals("blue") && turn.equals("blue")) { for (i -=
		 * 1, j -= 1; i > row; i--, j--) {
		 * squarePanel[i][j].getPiece().setIcon(blueIcon);
		 * squarePanel[i][j].setState("blue");
		 * 
		 * } } }
		 */
	}
	
	public boolean checkDownLeft(int row, int column, String turn) {
	
		if(row>5 | column<2) { //뒤집을게 없는 경우
			return false;
		}
		
		int i = row; //왼쪽으로 가니 row 고정
		int j = column;  
		
		//바로 아래왼쪽돌이 같은 색이거나 비어있으면 종료
		if(squarePanel[i+1][j-1].getState() == turn || squarePanel[i+1][j-1].getState() == "Empty" ) { //바로 전돌 확인
			return false;
		}
		
		//뒤집을 수 있는 상태 
		for(int ii=row+2, jj = column-2; ii<=7 && jj>=0; ii++,jj--) {  
			String state = squarePanel[ii][jj].getState();
			if(state == "Empty") {  //1.왼쪽칸이 비어있는 경우
				return false;
			}
			
			if(state != turn) {//2. 서로 색깔다름
				continue;
			}
			
			if(state == turn) { //3. 같은 색깔이므로 뒤집은 후 종료.
				int between = ii - row - 1;
				
			    //돌 사이 뒤집기
				for(int z = row+1, zz = column -1;z <= row+between; z++, zz--) {

					if(turn == "black") {
					squarePanel[z][zz].getPiece().setIcon(blackIcon);
					squarePanel[z][zz].setState(turn);

					}else {
					squarePanel[z][zz].getPiece().setIcon(blueIcon);
					squarePanel[z][zz].setState(turn);
					}
				
				}
		 
				return true;
			}
		}
		return false;
		/*
		 * int i = 0; int j = 0;
		 * 
		 * int gap = Math.min(7-row, column);
		 * 
		 * i = row + gap; j = column - gap;
		 * 
		 * for (; i > row; i--, j++) { // 위으로 가는거
		 * 
		 * String state = squarePanel[i][j].getState();
		 * 
		 * if (state.equals("black") && turn.equals("black")) { for (i -= 1, j += 1; i >
		 * row; i--, j++) { squarePanel[i][j].getPiece().setIcon(blackIcon);
		 * squarePanel[i][j].setState("black");
		 * 
		 * } break; } else if (state.equals("blue") && turn.equals("blue")) { for (i -=
		 * 1, j += 1; i > row; i--, j++) {
		 * squarePanel[i][j].getPiece().setIcon(blueIcon);
		 * squarePanel[i][j].setState("blue");
		 * 
		 * } } }
		 */
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String buttonCase = e.getActionCommand();
		
		switch(buttonCase){
		case "Skip turn":
			
			int result = JOptionPane.showConfirmDialog(null, "턴을 넘기시겠습니까?", "Confirm message",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) { //턴을 넘김
				if (turn == "black") {
					turn = "blue";
					turnLabel.setText("White Turn");
				} else if (turn == "blue") {
					turn = "black";
					turnLabel.setText("Black Turn");
				}
			}//턴을 넘김 if
			
			break; //Skip turn 
			
		case "Game GiveUp":
			
			int giveUpResult = JOptionPane.showConfirmDialog(null, "기권 하시겠습니까?", "Confirm message",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (giveUpResult == JOptionPane.YES_OPTION) {
				if (turn == "black") {
					JOptionPane.showMessageDialog(null, "White 승리", "Give Up", 1);

				} else
					JOptionPane.showMessageDialog(null, "Black 승리", "Give Up", 1);

			}

			break; //Game GiveUp
		
		case "Game End":
			
			String bluescores = blueScore.getText();
			int blueScores =Integer.parseInt(bluescores);
			
			
			String blackscores = blackScore.getText();
			int blackScores = Integer.parseInt(blackscores);
			
			if(blueScores > blackScores) {
				JOptionPane.showMessageDialog(null, "White 승리", "Win", 1);

			}else if(blackScores > blueScores) {
				JOptionPane.showMessageDialog(null, "Black 승리", "Win", 1);

			}else
				JOptionPane.showMessageDialog(null, "무승부", "Draw", 1);

			
			break; //Game End
			
		case "Game ReStart":
			resetBoard();
			break;
			
		}//switch
		
		
	
	}//actionPerformed
	
	public void resetBoard() {
		
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				//JPanel square = new JPanel(new BorderLayout());
				ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

				
				squarePanel[i][j].setState("Empty");
				squarePanel[i][j].getPiece().setIcon(icon);
				board.add(squarePanel[i][j]);
				
			}
		}//for
		
		//판 초반 세팅
		squarePanel[3][3].getPiece().setIcon(blueIcon);
		squarePanel[4][3].getPiece().setIcon(blackIcon);
		squarePanel[3][4].getPiece().setIcon(blackIcon);
		squarePanel[4][4].getPiece().setIcon(blueIcon);
		
		squarePanel[3][3].setState("blue");
		squarePanel[4][3].setState("black");
		squarePanel[3][4].setState("black");
		squarePanel[4][4].setState("blue");
		
		//판을 리셋하므로 값도 초기 상태로 초기화
		turn = "black";
		turnLabel.setText("Black Turn");
		blackScore.setText("2");
		blueScore.setText("2");
		
	}

} //Othello Class

class JPanelSquare extends JPanel{
	
	int column;
	int row;
	String state;
	
	JLabel piece = null;
	
	
	
	public JPanelSquare(int row, int column, String state) {
		this.column = column;
		this.row = row;
		this.state = state;
	}	
	
	public int getColumn() {
		return column;
	}


	
	public int getRow() {
		return row;
	}
	
	@Override
	public Component add(Component c) {
		piece = (JLabel) c;
		return super.add(c);
	}
	
    @Override
    public void remove(Component comp) {
        piece = null;
        super.remove(comp);
    }

	public JLabel getPiece() {
		return piece;
	}
	
	public void setPiece() {
		
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
}