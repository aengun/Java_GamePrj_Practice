package com.newlecture.app.prj3_2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.app.prj3_2.canvas.ActionCanvas;

public class Missile extends Item {

	private static Image img;

	static {
		try {
			img = ImageIO.read(new File("res/missile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Missile() {
		this(0,0);
	}

	public Missile(double x, double y) {
		super(x, y, img.getWidth(null)/3, img.getHeight(null), "res/missile.png");
		System.out.println(img.getWidth(null));
	}

	@Override
	protected Image getImage() {
		return img;
	}

	@Override
	public void update() {
		setY(getY() - 3);
	}

	@Override
	public void paint(Graphics g) {

		int w = (int) this.getWidth();
		int h = (int) this.getHeight();
		int x1 = (int) this.getX() - w / 2;
		int y1 = (int) this.getY() - h + 13;
		int x2 = x1 + w;
		int y2 = y1 + h;
		Image img = getImg();

		g.drawImage(img, x1, y1, x2, y2, 0, 0, w, h, ActionCanvas.instance);

	}

}
