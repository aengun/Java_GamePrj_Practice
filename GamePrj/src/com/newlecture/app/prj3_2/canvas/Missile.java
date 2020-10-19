package com.newlecture.app.prj3_2.canvas;

import java.awt.Graphics;
import java.awt.Image;

import com.newlecture.app.prj3_2.entity.Item;

public class Missile extends Item {

	private Image img;

	@Override
	protected Image getImage() {

		return img;
	}

	@Override
	public void update() {
	}

	@Override
	public void paint(Graphics g) {
	}

}
