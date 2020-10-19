package com.newlecture.app.prj3_2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.app.prj3_2.canvas.ActionCanvas;

public abstract class Item {
	private double x;
	private double y;
	
	// 애니메이션을 위한 변수
	private double vx;
	private double vy;
	private double dx;
	private double dy;
	
	private double width;
	private double height;
	private Image img;
	
	private int movIndex;
	private int speed;
	private int walkTempo;
	
	public Item() {
		this(0, 0, 0, 0, null);
	}
	
	public Item(double x, double y, String imgSrc) {
		this(x, y, 0, 0, imgSrc);
	}
	
	public Item(double x, double y, int width, int height, String imgSrc) {
		//Toolkit tk = Toolkit.getDefaultToolkit();
		img = getImage(); // tk.getImage(imgSrc);
				
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
		
		movIndex=0;
		speed = 3;
		walkTempo = 6;
	}

	protected abstract Image getImage();

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

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getMovIndex() {
		return movIndex;
	}

	public void setMovIndex(int movIndex) {
		this.movIndex = movIndex;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getWalkTempo() {
		return walkTempo;
	}

	public void setWalkTempo(int walkTempo) {
		this.walkTempo = walkTempo;
	}

	public abstract void update();
	
	public abstract void paint(Graphics g);
	
	public void move(double x, double y) {
//		this.x = x;
//		this.y = y;
		this.dx = x;
		this.dy = y;
		
		// 동일한 속도로 이동하는 단위벡터
		double w = this.dx - this.x;
		double h = this.dy - this.y;
		double d = Math.sqrt(w*w + h*h);	
		this.vx = w/d*speed;
		this.vy = h/d*speed;
		
		// 동일한 시간내에 이동하는 단위벡터
		//this.vx = (this.dx - this.x) / 15;
		//this.vy = (this.dy - this.y) / 15;
		
//		this.x = x;
//		this.y = y;
	}
	
	public boolean isSelected(int x, int y) {
		int w = (int)this.width;
		int h = (int)this.height;
		int x1 = (int)this.x - w/2;
		int y1 = (int)this.y- h+13;
		int x2 = x1+w;
		int y2 = y1+h;
		
		if((x1  < x && x < x2)  
				&& (y1 < y && y < y2))
			return true;
		
		return false;
	}
}
