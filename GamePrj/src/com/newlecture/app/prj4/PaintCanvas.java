package com.newlecture.app.prj4;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintCanvas extends Canvas {

	public PaintCanvas() {

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

		});
	}

	@Override
	public void paint(Graphics g) {
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
}
