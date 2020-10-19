package com.newlecture.app.prj3;

import java.awt.Frame;

import com.newlecture.app.prj3.canvas.ActionCanvas;

public class GameFrame extends Frame {

	public static int frameWidth = 360;
	public static int frameHeight = 600;

	public GameFrame() {
		// Canvas canvas = new PuzzleCanvas();
		ActionCanvas canvas = new ActionCanvas();
		canvas.start();

		setSize(frameWidth, frameHeight);
		setVisible(true);
		add(canvas);
	}

}
