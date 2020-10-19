package com.newlecture.app.prj2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.app.prj2.canvas.ActionCanvas;

public class Boy2 {

	private double x;
	private double y;
	private double width;
	private double height;
	private Image img;

	public Boy2() {
		this(0, 0);
	}

	public Boy2(double x, double y) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/boy.png");

		this.x = x;
		this.y = y;
		this.width = 64;
		this.height = 96;
	}

	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g) {

		int w = (int) this.width;
		int h = (int) this.height;
		int x1 = (int) this.x - w / 2;
		int y1 = (int) this.y - h + 13;
		int x2 = x1 + w;
		int y2 = y1 + h;

		g.drawImage(img, x1, y1, x2, y2, 0, 0, w, h, ActionCanvas.instance);
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

}