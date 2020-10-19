package com.newlecture.app.prj2.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import com.newlecture.app.prj2.service.PuzzleService;

public class PuzzleCanvas extends Canvas {

	public static Canvas instance;

	private int imgCount;
	private PuzzleService service;

	public PuzzleCanvas() {
		instance = this;

		// 그림 조각 6개를 만들어 섞는다.

		imgCount = 6;

		service = new PuzzleService();

		service.shuffle();

	}

	@Override
	public boolean mouseDown(Event evt, int x, int y) {
		System.out.printf("x : %d, y : %d \n", x, y);

		service.shuffle();

		repaint();

		return super.mouseDown(evt, x, y);
	}

	@Override
	public void paint(Graphics g) {

		for (int i = 0; i < 6; i++) {
			service.get(i).paint(g);

		}

	}

}
