package com.newlecture.app.prj3_2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.app.prj3_2.entity.BoyMoveListener;
import com.newlecture.app.prj3_2.canvas.ActionCanvas;

public class Boy extends Item {

//	private double x;
//	private double y;
//	
//	// �ִϸ��̼��� ���� ����
//	private double vx;
//	private double vy;
//	private double dx;
//	private double dy;
//	
//	private double width;
//	private double height;
//	private Image img;
//	
//	private int movIndex = 0;
//	private int speed = 3;
//	private int walkTempo = 6;

	private static Image img;

	private static final int UP = 38; // fianl Ű���带 ������� ������ case��Ͽ� ������ �� �ִ´�. case��Ͽ��� ������ �ʴ�
										// ��(���<->����)�� �� �� �ִ�.
	private static final int DOWN = 40;
	private static final int LEFT = 37;
	private static final int RIGHT = 39;

	private int E = 0; // ��
	private int W = 0; // ��
	private int S = 0; // ��
	private int N = 0; // ��

	private int speed = 5;

	private BoyMoveListener canvasListener;

	public void setCanvasListener(BoyMoveListener canvasListener) {
		this.canvasListener = canvasListener;
	}

	static {
		try {
			img = ImageIO.read(new File("res/boy.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boy() {
		this(100, 100);
	}

	public Boy(double x, double y) {
		super(x, y, 64, 96, "res/boy.png");
		setWalkTempo(6);
		setSpeed(3);
//		Toolkit tk = Toolkit.getDefaultToolkit();
//		img = tk.getImage("res/boy.png");
//		
//		this.x=x;
//		this.y=y;
//		this.width = 64;
//		this.height = 96;
	}

//	public void move(double x, double y) {
////		this.x = x;
////		this.y = y;
//		this.dx = x;
//		this.dy = y;
//		
//		// ������ �ӵ��� �̵��ϴ� ��������
//		double w = this.dx - this.x;
//		double h = this.dy - this.y;
//		double d = Math.sqrt(w*w + h*h);
//		this.vx = w/d*speed;
//		this.vy = h/d*speed;
//		
//		// ������ �ð����� �̵��ϴ� ��������
//		//this.vx = (this.dx - this.x) / 15;
//		//this.vy = (this.dy - this.y) / 15;
//		
////		this.x = x;
////		this.y = y;
//	}

	public void update() {

		if (N == 1)
			setY(getY() - 1 * speed);
		if (E == 1)
			setX(getX() + 1 * speed);
		if (S == 1)
			setY(getY() + 1 * speed);
		if (W == 1)
			setX(getX() - 1 * speed);
//		double x = getX();
//		double y = getY();
//		double dx = getDx();
//		double dy = getDy();
//		double vx = getVx();
//		double vy = getVy();
//		int movIndex = getMovIndex();
//
//		// �������� �ڽ��� ����� ���� ��
//		if ((dx - 1 <= x && x <= dx + 1) && (dy - 1 <= y && y <= dy + 1)) {
//			// �������� ��ġ�� �ڽ��� ǥ���ؼ� ��
////		if((this.x - 1 <= this.dx && this.dx <= this.x + 1) && 
////				(this.y - 1 <= this.dy && this.dy <= this.y + 1)) {
//			vx = 0;
//			vy = 0;
//			movIndex = 0;
//		}
//
//		x += vx;
//		y += vy;
//
//		this.setX(x);
//		this.setY(y);
//		this.setVx(vx);
//		this.setVy(vy);
//		this.setMovIndex(movIndex);
//
//		int w = (int) getWidth();
//		int h = (int) getHeight();
//		int x1 = (int) (x - w / 2);
//		int x2 = x1 + w;
//		int y1 = (int) (y - h);
//
//		if ((x1 <= 0 || x2 >= ActionCanvas.instance.getWidth())
//				|| (y1 <= 0 || y >= ActionCanvas.instance.getHeight())) {
//
//			if (canvasListener != null)
//				canvasListener.speak();
//		}
	}

	public void paint(Graphics g) {// bg

		int w = (int) this.getWidth();
		int h = (int) this.getHeight();
		int x1 = (int) this.getX() - w / 2;
		int y1 = (int) this.getY() - h + 13;
		int x2 = x1 + w;
		int y2 = y1 + h;
		int walkTempo = getWalkTempo();
		int movIndex = getMovIndex();
		Image img = getImg();
		double vx = getVx();
		double vy = getVy();

		if (vx != 0 || vy != 0) {
			if (walkTempo == 0) {
				movIndex++;
				movIndex = movIndex % 4;

				walkTempo = 6;
			} else
				walkTempo--;
		}

		int offsetX = movIndex * w;

		// System.out.printf("index:%d, offset:%d\n", movIndex, offsetX);

		g.drawImage(img, x1, y1, x2, y2, 0 + offsetX, 0, 0 + w + offsetX, h, ActionCanvas.instance);

		setWalkTempo(walkTempo);
		setMovIndex(movIndex);
	}

	@Override
	protected Image getImage() {
		// TODO Auto-generated method stub
		return img;
	}

	public void move(int key) {
		double x = getX();
		double y = getY();

		switch (key) {
		case UP:
			N = 1;
			break;
		case DOWN: // Down Key
			S = 1;
			break;
		case LEFT: // Left Key
			W = 1;
			break;
		case RIGHT: // Right Key
			E = 1;
			break;
		}

		int w = (int) getWidth();
		int h = (int) getHeight();
		int x1 = (int) (x - w / 2);
		int x2 = x1 + w;
		int y1 = (int) (y - h + 13);

		if ((x1 <= 0 || x2 >= ActionCanvas.instance.getWidth())
				|| (y1 <= 0 || y >= ActionCanvas.instance.getHeight())) {

			if (canvasListener != null)
				canvasListener.speak();
		}

	}

	public void stop(int key) {
		switch (key) {
		case UP:
			N = 0;
			break;
		case DOWN: // Down Key
			S = 0;
			break;
		case LEFT: // Left Key
			W = 0;
			break;
		case RIGHT: // Right Key
			E = 0;
			break;
		}

	}

	public Missile fire() {
		Missile missile = new Missile();

		double x = getX();
		double y = getY();

		missile.setX(x + 20);
		missile.setY(y - 10);

		return missile;
	}

//	public boolean isSelected(int x, int y) {
//		int w = (int)this.width;
//		int h = (int)this.height;
//		int x1 = (int)this.x - w/2;
//		int y1 = (int)this.y- h+13;
//		int x2 = x1+w;
//		int y2 = y1+h;
//		
//		if((x1  < x && x < x2)  
//				&& (y1 < y && y < y2))
//			return true;
//		
//		return false;
//	}

}
