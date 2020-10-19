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

	static { // ����ƽ ������ : ��ü ������ ��� ���� ����Ǵ� ��
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
		// y���� ��ȭ�� �ֵ��ϰ�

//		this.setY(getY() + 5);

		// �������� �Ҷ�� �� ���� �̾���δ�.
	}

	@Override
	protected Image getImage() {
		return img;
	}

}
