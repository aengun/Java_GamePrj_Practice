package com.newlecture.app.prj3.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.newlecture.app.prj3.canvas.ActionCanvas;

public class Enemy extends Item {

	private int timeoutForMoving = 120;
	private Random rand;
	private static Image img;

	private EnemyMoveListener moveListener;

	public void setMoveListener(EnemyMoveListener moveListener) {
		this.moveListener = moveListener;
	}

	static { // ����ƽ ������ : ��ü ������ ��� ���� ����Ǵ� ��
		try {
			img = ImageIO.read(new File("res/enemy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Enemy() {
		this(100, 100);
	}

	public Enemy(double x, double y) {

		super(x, y, 58, 35, "res/enemy.png");

		setMovIndex(0);
		setSpeed(3);
		setWalkTempo(6);

//		Toolkit tk = Toolkit.getDefaultToolkit();
//		img = tk.getImage("res/alien.gif");
//
//		this.x = x;
//		this.y = y;
//		this.width = 58;
//		this.height = 35;

		rand = new Random();
	}

	public void update() {

		double x = getX();
		double y = getY();
		double dx = getDx();
		double dy = getDy();
		double vx = getVx();
		double vy = getVy();
		int movIndex = getMovIndex();

		timeoutForMoving--;
		if (timeoutForMoving == 0) {

			double width = getWidth();
			double height = getHeight();

			int w = ActionCanvas.instance.getWidth() - (int) width;
			int h = ActionCanvas.instance.getHeight() - (int) height;
			dx = rand.nextInt(w) + (int) width / 2;
			dy = rand.nextInt(h) + (int) height / 2;

			this.move(dx, dy);

			// move �� �� ���� ���⿡�� �ڵ带 �ְ� ���� ������??
			if (moveListener != null) // �������̽��� �������� ���� �� �� �ֵ���.. 
				moveListener.onMove();

			timeoutForMoving = rand.nextInt(60) + 60;// 0~59+60 // 60~119

		}

		// �������� �ڽ��� ����� ���� ��
		if ((dx - 1 <= x && x <= dx + 1) && (dy - 1 <= y && y <= dy + 1)) {
			// �������� ��ġ�� �ڽ��� ǥ���ؼ� ��
//		if((this.x - 1 <= this.dx && this.dx <= this.x + 1) && 
//				(this.y - 1 <= this.dy && this.dy <= this.y + 1)) {
			vx = 0;
			vy = 0;
			movIndex = 0;
			timeoutForMoving = 1;
			this.setVx(vx);
			this.setVy(vy);
		}

		x += vx;
		y += vy;

		this.setX(x);
		this.setY(y);
		this.setMovIndex(movIndex);
	}

	public void paint(Graphics g) {// bg

		int w = (int) getWidth();
		int h = (int) getHeight();
		int x1 = (int) getX() - w / 2;
		int y1 = (int) getY() - h + 13;
		int x2 = x1 + w;
		int y2 = y1 + h;
		Image img = getImg();
		int walkTempo = getWalkTempo();
		int movIndex = getMovIndex();

		if (walkTempo == 0) {
			movIndex++;
			movIndex = movIndex % 12;

			walkTempo = 3;
		} else
			walkTempo--;

		int offsetX = movIndex * w;

//		if ( movIndex < 5 ) 
//			offsetX -= 1;

//		System.out.printf("Enemy - index:%d, offset:%d\n", movIndex, offsetX);

		g.drawImage(img, x1, y1, x2, y2, 0 + offsetX, 0, 0 + w + offsetX, h, ActionCanvas.instance);

		setWalkTempo(walkTempo);
		setMovIndex(movIndex);
	}

	public boolean isSelected(int x, int y) {
		int w = (int) getWidth();
		int h = (int) getHeight();
		int x1 = (int) getX() - w / 2;
		int y1 = (int) getY() - h + 13;
		int x2 = x1 + w;
		int y2 = y1 + h;

		if ((x1 < x && x < x2) && (y1 < y && y < y2))
			return true;

		return false;
	}

	@Override
	protected Image getImage() {
		return img;
	}

}
