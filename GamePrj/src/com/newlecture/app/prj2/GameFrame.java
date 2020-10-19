package com.newlecture.app.prj2;

import java.awt.Frame;

import com.newlecture.app.prj2.canvas.ActionCanvas3;

public class GameFrame extends Frame {

	public GameFrame() {

		// Canvas canvas = new PuzzleCanvas();
		// Canvas canvas = new ActionCanvas();
		// Canvas canvas = new ActionCanvas2();
		ActionCanvas3 canvas = new ActionCanvas3();
		canvas.start(); // 60¹ø µ¹°Ô

		setSize(660, 680);
//		setSize(500, 700);
		setVisible(true);
		add(canvas);

	}

}
