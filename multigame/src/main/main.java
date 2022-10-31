package main;

import javax.swing.*;

import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import game.*;

public class main extends JFrame {
	main() {
		
		setTitle("Game!");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextField txtfield = new JTextField();
		JLabel label = new JLabel("게임을 선택해주세요");
		JButton btn1 = new JButton("TicTacToe");
		JButton btn2 = new JButton("오목");
		JButton btn3 = new JButton("1 to 50");
		JButton btn4 = new JButton("오셀로");
		JButton btn5 = new JButton("리듬게임");
		JPanel MainContainer = new JPanel();
		setContentPane(MainContainer);
		
		
		
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SetBoardSize();
			}

		});
		
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OmokGame();
			}

		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long startTime = System.currentTimeMillis();
				new One2FiftyGame(startTime);
			}

		});
		
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Othello.othello();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DynamicBeat();
			}

		});
		
		add(label);
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(btn5);
		
		setLayout(null);
		
		label.setBounds(100, 60, 150, 30);
		btn1.setBounds(90, 110, 120, 40);
		btn2.setBounds(90, 150, 120, 40);
		btn3.setBounds(90, 190, 120, 40);
		btn4.setBounds(90, 230, 120, 40);
		btn5.setBounds(90, 270, 120, 40);
		
		setSize(600, 600);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
			main mf = new main();
		
	}
}