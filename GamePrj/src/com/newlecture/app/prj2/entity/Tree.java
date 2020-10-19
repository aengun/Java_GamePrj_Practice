package com.newlecture.app.prj2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.app.prj2.canvas.ActionCanvas;

public class Tree {

	private double x;
	private double y;
	private Image img;

	public Tree() {
		this(0, 0);
	}

	public Tree(double x, double y) {
		// 이미지 로드하기
		this.x = x;
		this.y = y;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/tree.png");
	}

	public void move(double x, double y) {
		this.x = x;
		this.y = y;

	}

	public void paint(Graphics g) {
		int x = (int) this.x;
		int y = (int) this.y;

		int w = 130;
		int h = 163;

		g.drawImage(img, x, y, x + w/2, y + h/2, 0, 0, w, h, ActionCanvas.instance);
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

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

}
