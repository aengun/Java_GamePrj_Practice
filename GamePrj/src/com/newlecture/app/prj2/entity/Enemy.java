package com.newlecture.app.prj2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import com.newlecture.app.prj2.canvas.ActionCanvas;
import com.newlecture.app.prj2.canvas.ActionCanvas3;

public class Enemy {

	private double x;
	private double y;

	// 애니메이션을 위한 변수
	private double vx;
	private double vy;
	private double dx;
	private double dy;

	private double width;
	private double height;
	private Image img;

	private int movIndex = 0;
	private double speed = 3;
	private int walkTempo = 12;
	private int timeoutForMoving = 120;
	private Random rand;

	public Enemy() {
		this(100, 100);
	}

	public Enemy(double x, double y) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/enemy.gif");

		this.x = x;
		this.y = y;
		this.width = 58;
		this.height = 35;

		rand = new Random();
	}

	public void move(double x, double y) {

		// 목적지의 값
		this.dx = x;
		this.dy = y;

		// 동일한 시간 내에 이동하는 단위벡터
		// this.vx = (this.dx - this.x) / 60;
		// this.vy = (this.dy - this.y) / 60;

		// 동일한 속도로 이동하는 단위 벡터
		double w = this.dx - this.x;
		double h = this.dy - this.y;
		double d = Math.sqrt(w * w + h * h);

		this.vx = w / d * speed;
		this.vy = h / d * speed;

	}

	public void update() {

		int w = (int) (ActionCanvas3.instance.getWidth() - this.width);
		int h = (int) (ActionCanvas3.instance.getHeight() - this.height);

		timeoutForMoving--;
		if (timeoutForMoving == 0) {
			int x = (int) (rand.nextInt(w) + width / 2);
			int y = (int) (rand.nextInt(h) + height / 2);

			this.move(x, y);
			timeoutForMoving = rand.nextInt(60) + 60; // 60 ~ 119
		}

		if ((this.x - 2 <= this.dx && this.dx <= this.x + 2) && (this.y - 2 <= this.dy && this.dy <= this.y + 2)) {
			this.vx = 0;
			this.vy = 0;
			this.movIndex = 0;

			// 안 멈추게 하는 법
			timeoutForMoving = 1;
		}

		this.x += this.vx;
		this.y += this.vy;

	}

	public void paint(Graphics g) {

		int w = (int) this.width;
		int h = (int) this.height;
		int x1 = (int) this.x - w / 2;
		int y1 = (int) this.y - h + 13;
		int x2 = x1 + w;
		int y2 = y1 + h;

//		if (walkTempo == 0) {
			movIndex++;
			movIndex = movIndex % 12;

//			walkTempo = 1;
//		} else
//			walkTempo--;

		int offsetX = (movIndex) * w;

		g.drawImage(img, x1, y1, x2, y2, 0 + offsetX, 0, w + offsetX, h, ActionCanvas.instance);

	}

	public boolean isSelected(int x, int y) {
		int w = (int) this.width;
		int h = (int) this.height;
		int x1 = (int) (this.x - w / 2);
		int y1 = (int) (this.y - h + 13);
		int x2 = x1 + w;
		int y2 = y1 + h;

		if ((x1 < x && x < x2) && (y1 < y && y < y2))
			return true;

		return false;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

}