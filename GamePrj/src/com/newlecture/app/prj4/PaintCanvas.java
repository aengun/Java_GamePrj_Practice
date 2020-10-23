package com.newlecture.app.prj4;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintCanvas extends Canvas {

	private boolean activated;

	public PaintCanvas() {
		activated = false;

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
		if (activated) {
			g.setColor(Color.white);
			g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
		} else {
			g.setColor(Color.pink);
			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

		}

	}

	public void setActive() {
		this.activated = true;
		repaint();
	}
}
