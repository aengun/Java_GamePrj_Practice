package com.newlecture.app.prj1;

import java.awt.Canvas;
import java.awt.Frame;

public class GameFrame extends Frame {

	public GameFrame() {

		Canvas canvas = new GameCanvas();
		

		setSize(800, 500);
		setVisible(true);
		add(canvas);

	}

}
