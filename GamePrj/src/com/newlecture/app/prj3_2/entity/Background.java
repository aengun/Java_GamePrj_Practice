package com.newlecture.app.prj3_2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.app.prj3_2.canvas.ActionCanvas;

public class Background extends Item {

	private int width = 360;
	private int height = 1200;
	private static Image img;
	double y2 = -img.getHeight(ActionCanvas.instance);
	double back2 = -img.getHeight(null) - 600;
	private double y;

	static {
		try {
			img = ImageIO.read(new File("res/space.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Background() {
		this(0, 0);
	}

	public Background(double x, double y) {
		this.y=-600;
		Image img = getImg();
	}

	@Override
	public void paint(Graphics g) {
		Image img = getImg();
		int h = img.getHeight(ActionCanvas.instance);

//		int offsetY = h - ActionCanvas.instance.getHeight();

		double x = getX();
//		double y = getY();
//		double back1 = y - offsetY;
//		double back2 = img.getHeight(null);

		g.drawImage(img, (int) x, (int) y, width, height, ActionCanvas.instance);
		g.drawImage(img, (int) x, (int) back2, ActionCanvas.instance);

	}


	@Override
	public void update() {
		// y 값의 변화를 주도하고
//		double y = getY();
		y+=3;
		back2 += 3;
		if (y > ActionCanvas.instance.getHeight())
			y = -img.getHeight(null) - 600;
		if (back2 > ActionCanvas.instance.getHeight())
			back2 = -img.getHeight(null) - 600;
	}

	@Override
	protected Image getImage() {
		return img;
	}

}
