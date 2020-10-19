package com.newlecture.app.prj3.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.app.prj3.GameFrame;
import com.newlecture.app.prj3.canvas.ActionCanvas;

public class Background extends Item {

	private static Image img;

	static { // 스태틱 생성자 : 객체 생성과 상관 없이 실행되는 블럭
		try {
			img = ImageIO.read(new File("res/space.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Background() {
		this(0, 0, "res/space.jpg");
	}

	public Background(double x, double y, String imgSrc) {
		super(x, y, imgSrc);

//		Image img = getImg();
//		int w = img.getWidth(ActionCanvas.instance);
//		int h = img.getHeight(ActionCanvas.instance);
//
//		System.out.println(w + " " + h);

	}

	@Override
	public void paint(Graphics g) {
		Image img = getImg();
		int h = img.getHeight(ActionCanvas.instance);

		int offsetY = h - ActionCanvas.instance.getHeight();

		double x = getX();
		double y = getY();
		double back1 = y - offsetY;
		double back2 = img.getHeight(null);

		g.drawImage(img, (int) x, (int) back1, ActionCanvas.instance);
		g.drawImage(img, (int) x, (int) back2, ActionCanvas.instance);

		// System.out.println(x + " " + y);
	}

	@Override
	public void update() {
		// y값의 변화를 주도하고

//		this.setY(getY() + 5);

		// 연속으로 할라면 두 개를 이어붙인다.
	}

	@Override
	protected Image getImage() {
		return img;
	}

}
