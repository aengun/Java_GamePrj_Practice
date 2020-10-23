package com.newlecture.app.prj3_2.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.newlecture.app.prj3_2.canvas.ActionCanvas;

public class Missile extends Item {

	private static Image img;

	private MissileListener listener;

	static {
		try {
			img = ImageIO.read(new File("res/missile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Missile() {
		this(0, 0);
	}

	public Missile(double x, double y) {
//		super(x, y, img.getWidth(null)/3, img.getHeight(null), "res/missile.png");
		super(x, y, 40, 40, "res/missile.png");
	}

	@Override
	public void update() {
//		if(내가 밖에 있다면..)
//			사라진다..
		if (listener != null && ActionCanvas.instance.getHeight()-100 < getY())
			listener.onOut(this);
		
		setY(getY() + 5);
	}

	@Override
	public void paint(Graphics g) {

		int w = (int) this.getWidth();
		int h = (int) this.getHeight();
		int x1 = (int) this.getX() - w / 2;
		int y1 = (int) this.getY() - h / 2;
		int x2 = x1 + w;
		int y2 = y1 + h;
		Image img = getImg();

		g.drawImage(img, x1, y1, x2, y2, 0, 0, img.getWidth(null)/3, img.getHeight(null), ActionCanvas.instance);
//		g.drawArc(x, y, width, height, startAngle, arcAngle); // 각도만큼 원 그리기
//		g.drawOval(x1, y1, w, h); // 사각형 안에 원 그리기
//		g.setColor(Color.magenta);
//		g.fillOval(x1, y1, w, h);

	}

	@Override
	protected Image getImage() {
		return img;
	}

	public void setListener(MissileListener listener) {
		this.listener = listener;
	}

}
