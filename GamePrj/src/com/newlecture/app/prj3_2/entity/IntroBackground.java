package com.newlecture.app.prj3_2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.app.prj3_2.canvas.ActionCanvas;

public class IntroBackground extends Item {

	private int width = 360;
	private int height = 1200;
	private static Image img;

	static {
		try {
			img = ImageIO.read(new File("res/bg01.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public IntroBackground() {
		this(0, 0, "res/bg01.jpg");
		width = img.getWidth(null);
	}

	public IntroBackground(double x, double y, String imgSrc) {
		super(x, y, imgSrc);
		Image img = getImage();

		width = img.getWidth(null);
		height = img.getHeight(null);
	}

	@Override
	public void paint(Graphics g) {
		Image img = getImg();
		int h = img.getHeight(ActionCanvas.instance);
		double x = getX();
		double y = getY();

		g.drawImage(img, (int) x, (int) y, ActionCanvas.instance);
	}

	@Override
	public void update() {

	}

	@Override
	protected Image getImage() {
		return img;
	}

}
