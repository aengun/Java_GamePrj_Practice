package com.newlecture.app.prj2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.app.prj2.canvas.ActionCanvas;

public class Boy {

	private double x;
	private double y;
	private Image img;

	private int w = 64;
	private int h = 96;

	public Boy() {
		this(0, 0);
	}

	public Boy(double x, double y) {
		// 이미지 로드하기
		this.x = x;
		this.y = y;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/boy.png");
	}

	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g) {
		int x = (int) this.x;
		int y = (int) this.y;

		g.drawImage(img, x - w / 2, y - h, x + w / 2, y, 0, 0, w, h, ActionCanvas.instance);
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

	public boolean isSelected(int a, int b) {

		boolean flag = false;
		int w = 64;
		int h = 96;
		if ((x - (w / 2) <= a && a <= x + (w / 2)) && y - h <= b && b <= y)
			flag = true;

		return flag;

	}

}
