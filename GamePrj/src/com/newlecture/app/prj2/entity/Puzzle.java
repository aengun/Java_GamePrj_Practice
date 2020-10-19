package com.newlecture.app.prj2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.app.prj2.canvas.PuzzleCanvas;

public class Puzzle {

	private Image img;
	private int di;
	private int si;

	public Puzzle() {
		this(0, 0);
	}

	public Puzzle(int di, int si) {
		this.di = di;
		this.si = si;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/pic1.png");
	}

	public int getDi() {
		return di;
	}

	public void setDi(int di) {
		this.di = di;
	}

	public int getSi() {
		return si;
	}

	public void setSi(int si) {
		this.si = si;
	}

	public void paint(Graphics g) {

		int w = 254;
		int h = 214;

		int dxi = di % 3;
		int xoffset = dxi * w; // 0,1,2,0...
		int yoffset = di / 3 * h; // 0,0,0*h, 1*h

		int dx1 = 0 + xoffset;
		int dy1 = 0 + yoffset;
		int dx2 = w + xoffset;
		int dy2 = h + yoffset;

		int sxi = si % 3;
		xoffset = sxi * w; // 0,1,2,0...
		yoffset = si / 3 * h; // 0,0,0*h, 1*h

		int sx1 = 0 + xoffset;
		int sy1 = 0 + yoffset;
		int sx2 = w + xoffset;
		int sy2 = h + yoffset;

		g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, PuzzleCanvas.instance);
	}

}
