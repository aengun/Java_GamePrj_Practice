package com.newlecture.app.prj3.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.app.prj3.canvas.ActionCanvas;

public class Boy extends Item {

	private static Image img;

	private static final int UP = 1004; // fianl 키워드를 사용하지 않으면 case블록에 변수로 못 넣는다. case블록에는 변하지 않는 수(상수<->변수)만 올 수 있다.
	private static final int DOWN = 1005;
	private static final int LEFT = 1006;
	private static final int RIGHT = 1007;

	private BoyMoveListener canvasListener;

	public void setCanvasListener(BoyMoveListener canvasListener) {
		this.canvasListener = canvasListener;
	}

	static { // 스태틱 생성자 : 객체 생성과 상관 없이 실행되는 블럭
		try {
			img = ImageIO.read(new File("res/boy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boy() {
		this(100, 100);
	}

	public Boy(double x, double y) {

		super(x, y, 64, 96, "res/boy.png");

		setMovIndex(0);
		setSpeed(3);
		setWalkTempo(6);

//		Toolkit tk = Toolkit.getDefaultToolkit(); //비동기 이미지 로드 방법
//		img = tk.getImage("res/boy.png");
//
//		this.x = x;
//		this.y = y;
//		this.width = 64;
//		this.height = 96;
	}

	public void update() {

		double x = getX();
		double y = getY();
		double dx = getDx();
		double dy = getDy();
		double vx = getVx();
		double vy = getVy();
		int movIndex = getMovIndex();

		// 목적지에 박스를 만들어 놓고 비교
		if ((dx - 1 <= x && x <= dx + 1) && (dy - 1 <= y && y <= dy + 1)) {
			// 전투기의 위치를 박스로 표현해서 비교
//		if((this.x - 1 <= this.dx && this.dx <= this.x + 1) && 
//				(this.y - 1 <= this.dy && this.dy <= this.y + 1)) {
			vx = 0;
			vy = 0;
			movIndex = 0;
		}

		x += vx;
		y += vy;

		this.setX(x);
		this.setY(y);
		this.setVx(vx);
		this.setVy(vy);
		this.setMovIndex(movIndex);

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

	public void paint(Graphics g) {// bg

		int w = (int) getWidth();
		int h = (int) getHeight();
		int x1 = (int) getX() - w / 2;
		int y1 = (int) getY() - h + 13;
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

//		System.out.printf("Boy - index:%d, offset:%d\n", movIndex, offsetX);

		g.drawImage(img, x1, y1, x2, y2, 0 + offsetX, 0, 0 + w + offsetX, h, ActionCanvas.instance);

		setWalkTempo(walkTempo);
		setMovIndex(movIndex);
	}

	@Override
	protected Image getImage() {
		return img;
	}

	public void move(int key) {

		double x = getX();
		double y = getY();

		switch (key) {
		case UP:
			move(x, y - 30);
			break;
		case DOWN: // Down Key
			move(x, y + 30);
			break;
		case LEFT: // Left Key
			move(x - 30, y);
			break;
		case RIGHT: // Right Key
			move(x + 30, y);
			break;
		}

	}

}
