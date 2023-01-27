package com.java.SnakeEx2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Snake extends JFrame {

	public BorderLayout borderLayout = new BorderLayout();;
	public Color color = new Color(64, 64, 64);

	private JLabel label = new JLabel();

	public Snake() {

		Board board = new Board(this);

		setSize(1000, 1000);
		Dimension Screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension Frame = super.getSize();

		int xpos = (int) (Screen.getWidth() / 2 - Frame.getWidth() / 2);
		int ypos = (int) (Screen.getHeight() / 2 - Frame.getWidth() / 2);

		this.add(board);
		board.add(label);

		board.setBackground(color.BLACK);

		setTitle("game Start");
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
//		setFocusable(true); //프레임에 포커스에이블 줘서 안된거

//		setLocationRelativeTo(null); //프레임 위치 중앙에맞춰주는거

		setLocation(xpos, ypos);

		validate(); /// 그리는순서

		
		board.start();
	}


	public static void main(String[] args) {

		Snake snake = new Snake();

		snake.setVisible(true);
	}
}
