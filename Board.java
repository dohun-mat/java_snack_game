package com.java.SnakeEx2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, KeyListener {

	private Image imgApple;
	private Image imgDot;
	private Image imgHead;

	public static final int Snake_width = 1000;
	public static final int Snake_height = 1000;

	// 50
	// 50
	private int oneSpaceSnakeWidthNumber = Snake_width / 20;
	private int oneSpaceSnakeHeightNumber = Snake_height / 20;

	private Timer timer;

	private HashMap<Integer, SaveXY> saveHash = new HashMap();

	private Random ran = new Random();

	private boolean xPlusMoveBoolean = false;
	private boolean xMinusMoveBoolean = false;
	private boolean yPlusMoveBoolean = false;
	private boolean yMinusMoveBoolean = false;

	private boolean makeApple = false;

	public Board(Snake parentFrame) {

		timer = new Timer(100, this);

		ImageIcon apple = new ImageIcon("C:\\Users\\LG\\Desktop\\java image\\snake\\apple.png");
		ImageIcon dot = new ImageIcon("C:\\Users\\LG\\Desktop\\java image\\snake\\dot.png");
		ImageIcon head = new ImageIcon("C:\\Users\\LG\\Desktop\\java image\\snake\\head.png");

		imgApple = apple.getImage();

		imgDot = dot.getImage();

		imgHead = head.getImage();

//		System.out.println(Snake_width);

		imgApple.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		imgDot.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		imgHead.getScaledInstance(10, 10, Image.SCALE_SMOOTH);

		requestFocus();
		setFocusable(true);
		addKeyListener(this);

	}

	private int moveX = 0;
	private int moveY = 0;

	private int newNumber = 0;

	private int tempx = 0;
	private int tempy = 0;

	private void updateXposYpos() {
//		SaveXY saveXY = new SaveXY();

		if (saveHash.size() > 0) {

			saveHash.get(0).xNumber = saveHeadXpos;
			saveHash.get(0).yNumber = saveHeadYpos;

//			saveHash.replace(key, value)

			// 0은 head따라가야함

		}

		if (saveHash.size() > 1) {

			for (int i = saveHash.size() - 1; i > 0; i--) {

//				int xpos = saveHash.get(i).xNumber;
//				int ypos = saveHash.get(i).yNumber;

//				0 1 2 3 4

				saveHash.get(i).xNumber = saveHash.get(i - 1).xNumber;

				saveHash.get(i).yNumber = saveHash.get(i - 1).yNumber;

//				repaint();
			}
		}
	}

	private void printHashXY() {
		SaveXY saveXY = new SaveXY();

		System.out.println("ㅡㅡ프린트ㅡㅡ");

		for (int i = 0; i < saveHash.size(); i++) {
			System.out.println("X값은 : " + saveHash.get(i).xNumber);
			System.out.println("Y값은 : " + saveHash.get(i).yNumber);

		}

	}

	private int currentPositionX = 500; // 25칸
	private int currentPositionY = 500; // 25칸
	private int RandDot = 20;

	private void RandomApple(Graphics g) {

		g.drawImage(imgApple, RandDot * 20, RandDot * 20, 20, 20, this);

//		System.out.println("RandDot = " + RandDot);
//		System.out.println("RandDot * 20 = " + RandDot * 20);
//
//		System.out.println("makeApple : " + makeApple);

		eatApple();

	}

	private void eatApple() {

		if ((saveHeadXpos == RandDot * 20) && (saveHeadYpos == RandDot * 20)) {

			System.out.println("같다");
			makeApple = false;

			putDot();
		}

	}

	private boolean gamestart = true;

	private void equalDot() {

		for (int i = 0; i < saveHash.size(); i++) {
			for (int j = 0; j < saveHash.size(); j++) {
				if (saveHash.get(i).xNumber == saveHash.get(j).xNumber
						|| saveHash.get(i).yNumber == saveHash.get(j).yNumber) {
					gamestart = false;
				}
			}
		}
	}

	private void drawLarva(Graphics g) {

		for (int i = 0; i < saveHash.size(); i++) {
//			if(saveHash.get(i) == null || saveHash.get(i)==null)
//				System.out.println("~~~~~~~~~~~~ :" + i );

			g.drawImage(imgDot, saveHash.get(i).xNumber, saveHash.get(i).yNumber, 20, 20, this);

//			printHashXY();

		}
	}

	private void drawApple(Graphics g) {
		if (makeApple == true) {

//			System.out.println("makeApple = true");

			RandomApple(g);

		}

		if (makeApple == false) {

			RandDot = ran.nextInt(40) + 1;

			makeApple = true;
		}
	}

	private void autoMove() {
		if (xPlusMoveBoolean == true) {

			moveX = moveX + 20;

			repaint();
		}

		if (xMinusMoveBoolean == true) {

//			System.out.println("Xminus");

			moveX = moveX - 20;
			repaint();
		}

		if (yMinusMoveBoolean == true) {

//			System.out.println("Yminus");
			moveY = moveY + 20;

			repaint();
		}
		if (yPlusMoveBoolean == true) {

			// System.out.println("Yplus");

			moveY = moveY - 20;
			repaint();
		}

	}

	private int saveHeadXpos = 0;
	private int saveHeadYpos = 0;

	private int number = 0;

	@Override
	public void paint(Graphics g) {
//		System.out.println("paint");
		// TODO Auto-generated method stub
		super.paint(g);

//		g.drawImage(imgApple, 0, 0, 50 , 50, this);

		g.drawImage(imgHead, currentPositionX + moveX, currentPositionY + moveY, 20, 20, this);

//		printHashXY();

		saveHeadXpos = currentPositionX + moveX;
		saveHeadYpos = currentPositionY + moveY;

//		System.out.println("movex" + moveX);

		drawLarva(g);
		drawApple(g);

	}

	private void printboolean() {

		System.out.println("xPlusMoveBoolean :" + xPlusMoveBoolean);
		System.out.println("xMinusMoveBoolean :" + xMinusMoveBoolean);
		System.out.println("yMinusMoveBoolean :" + yMinusMoveBoolean);
		System.out.println("yPlusMoveBoolean :" + yPlusMoveBoolean);

	}

	private void putDot() {

		SaveXY saveXY = new SaveXY();

		printboolean();
		System.out.println("~~ " + saveHash.size());
		if (saveHash.size() == 0) {
			saveXY.xNumber = saveHeadXpos;
			saveXY.yNumber = saveHeadYpos;
			// 첫번재는 헤드

			number = number + 1;

			saveHash.put(newNumber, saveXY);
		}

		else {
			System.out.println("else받음");
			// 두번째 꼬리부터
			if (xPlusMoveBoolean == true) {

				saveXY.xNumber = saveHeadXpos - 20 * (number + 1);
				saveXY.yNumber = saveHeadYpos;

				saveHash.put(newNumber, saveXY);

				number = number + 1;
				System.out.println("오른쪽 해쉬 저장");

			}

			if (xMinusMoveBoolean == true) {

				saveXY.xNumber = saveHeadXpos + 20 * (number + 1);
				saveXY.yNumber = saveHeadYpos;

				saveHash.put(newNumber, saveXY);

				number = number + 1;
				System.out.println("왼쪽 해쉬 저장");

			}

			if (yMinusMoveBoolean == true) {
				for (int i = 1; i < saveHash.size(); i++) {
					saveXY.xNumber = saveHeadXpos;
					saveXY.yNumber = saveHeadYpos - 20 * (number + 1);

					saveHash.put(newNumber, saveXY);

					number = number + 1;
					System.out.println("위쪽 해쉬 저장");
				}

			}

			if (yPlusMoveBoolean == true) {
				for (int i = 1; i < saveHash.size(); i++) {
					saveXY.xNumber = saveHeadXpos;
					saveXY.yNumber = saveHeadYpos + 20 * (number + 1);

					saveHash.put(newNumber, saveXY);

					number = number + 1;
					System.out.println("아래쪽 해쉬 저장");
				}
			}
		}

		repaint();

		newNumber = newNumber + 1;

		System.out.println("newNumber :" + newNumber);
		System.out.println("number :" + number);
		System.out.println("해쉬의 크기는 : " + saveHash.size());

		printHashXY();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("actionPerformed");

		autoMove();
		updateXposYpos();

		if (gamestart = false) {
			// 멈춤
			System.out.println("gameover");
		}

	}

	public void start() {

		timer.start();

		xPlusMoveBoolean = true;
		makeApple = true;
		gamestart = true;
		System.out.println("dfsafdas");

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_RIGHT:

			xPlusMoveBoolean = true;
			xMinusMoveBoolean = false;
			yPlusMoveBoolean = false;
			yMinusMoveBoolean = false;

			break;

		case KeyEvent.VK_LEFT:
			xPlusMoveBoolean = false;
			xMinusMoveBoolean = true;
			yPlusMoveBoolean = false;
			yMinusMoveBoolean = false;

			break;

		case KeyEvent.VK_DOWN:
			xPlusMoveBoolean = false;
			xMinusMoveBoolean = false;
			yPlusMoveBoolean = false;
			yMinusMoveBoolean = true;
			break;

		case KeyEvent.VK_UP:
			xPlusMoveBoolean = false;
			xMinusMoveBoolean = false;
			yPlusMoveBoolean = true;
			yMinusMoveBoolean = false;
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
