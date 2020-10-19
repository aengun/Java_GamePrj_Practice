package com.newlecture.app.prj1;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class GameCanvas extends Canvas {

	private Image img;
	private int[] dis;
	private int[] sis;
//	private int imgCount;

	public GameCanvas() {

//		imgCount = 6;
		dis = new int[] { 0, 1, 2, 3, 4, 5 }; // new int[]없이 못함
		sis = new int[] { 0, 1, 2, 3, 4, 5 };

		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			int j = rand.nextInt(6);
			int k = rand.nextInt(6);

			int temp = dis[j];
			dis[j] = dis[k];
			dis[k] = temp;
		}
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/pic1.png");

	}

	@Override
	public boolean mouseDown(Event evt, int x, int y) {
		System.out.printf("x : %d, y : %d \n", x, y);

		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			int j = rand.nextInt(6);
			int k = rand.nextInt(6);

			int temp = dis[j];
			dis[j] = dis[k];
			dis[k] = temp;
		}

		repaint();

		return super.mouseDown(evt, x, y);
	}

	@Override
	public void paint(Graphics g) {

//		g.drawImage(img, 50, 50, this);
//		g.drawImage(img, 50, 50, 100, 100, this);

//		g.drawImage(img, 0 + 200, 0, 100 + 200, 100, 0, 0, 100, 100, this); // 1
//		g.drawImage(img, 100, 0, 200, 100, 100, 0, 200, 100, this); // 2
//		g.drawImage(img, 200 - 200, 0, 300 - 200, 100, 200, 0, 300, 100, this); // 3
//		g.drawImage(img, 0 + 200, 100, 100 + 200, 200, 0, 100, 100, 200, this); // 4
//		g.drawImage(img, 100, 100, 200, 200, 100, 100, 200, 200, this); // 5
//		g.drawImage(img, 200 - 200, 100, 300 - 200, 200, 200, 100, 300, 200, this); // 6

//		// 중간퀴즈
//		int di = 1;
//		int si = 5;
//
//		g.drawImage(img, 
//				0 + (di-1) * 100, 0, 100 + (di-1) * 100, 100, 
//				0 + (si-1) * 100, 0, 100 + (si-1) * 100, 100, this);

//		// 내코드
//		
//		int di = 1;
//		int si = 5;
//		
//		int dLow = 0;
//		int sLow = 0;
//		
//		if(di>=3)
//			dLow = 1;
//		if(si>= 3)
//			sLow = 1;
//		
//		g.drawImage(img,
//				0+(di%3)*100, 0+dLow*100, 100+(di%3)*100, 100+dLow*100,
//				0+(si%3)*100, 0+sLow*100, 100+(si%3)*100, 100+sLow*100, this);

//		// 내 코드 ver.2
//		{
//			int w = 254;
//			int h = 214;
//
//			int di = 5;
//			int si = 3;
//
//			int dLow = 0;
//			int sLow = 0;
//
//			if (di >= 3)
//				dLow = 1;
//
//			if (si >= 3)
//				sLow = 1;
//
//			g.drawImage(img, 0 + (di % 3) * w, 0 + (dLow % 3) * h, w + (di % 3) * w, h + (dLow % 3) * h,
//					0 + (si % 3) * w, 0 + (sLow % 3) * h, w + (si % 3) * w, h + (sLow % 3) * h, this);
//		}

		// 뉴렉 코드
		for (int i = 0; i < 6; i++) {

			int di = dis[i];
			int si = sis[i];

			int w = 254;
			int h = 214;

			int dxi = di % 3;
			int xoffset = dxi * w; // 0,1,2,0...
			int yoffset = di / 3 * h; // 0,0,0*h, 1*h

			int dx1 = 0 + xoffset;
			int dy1 = 0 + yoffset;
			int dx2 = w + xoffset;
			int dy2 = h + yoffset;

			int sxi = si % 3;
			xoffset = sxi * w; // 0,1,2,0...
			yoffset = si / 3 * h; // 0,0,0*h, 1*h

			int sx1 = 0 + xoffset;
			int sy1 = 0 + yoffset;
			int sx2 = w + xoffset;
			int sy2 = h + yoffset;

			g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, this);

		}

	}

}
