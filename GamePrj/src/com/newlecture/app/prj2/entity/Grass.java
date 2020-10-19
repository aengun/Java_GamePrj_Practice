package com.newlecture.app.prj2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.app.prj2.canvas.ActionCanvas;

public class Grass {

	private Image img;

	public Grass() {

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/grass.png");
	}

	public void paint(Graphics g) {
		int w = 640;
		int h = 640;

		g.drawImage(img, 0, 0, w, h, 0, 0, w, h, ActionCanvas.instance);
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

}
