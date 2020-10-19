package com.newlecture.app.prj3_2.canvas;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.newlecture.app.prj3_2.GameFrame;
import com.newlecture.app.prj3_2.entity.Button;
import com.newlecture.app.prj3_2.entity.IntroBackground;

public class IntroCanvas extends Canvas {

	private Button button;
	private Button button2;
	private Button button3;
	private Button[] buttons;
	private IntroBackground background;

	public IntroCanvas() {
		button = new Button(10, 10, 200, 50, "Start!");
		button2 = new Button(10, 100, 200, 50, "help");
		button3 = new Button(10, 200, 200, 50, "Exit");

		buttons = new Button[3];
		buttons[0] = button;
		buttons[1] = button2;
		buttons[2] = button3;

		background = new IntroBackground();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

//				for (int i = 0; i < buttons.length; i++)
//					if (buttons[i].contains(x, y))
				if (button.isSelected(x, y)) { // START
					try {
						System.out.println("ActionCanvas");
						GameFrame.instance.switchCanvas(IntroCanvas.this, ActionCanvas.class);
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (button2.isSelected(x, y)) { // HELP

					try {
						System.out.println("RuleCanvas");
						GameFrame.instance.switchCanvas(IntroCanvas.this, RuleCanvas.class);
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (button3.isSelected(x, y)) { // EXIT 할 때 구현 필요
					System.exit(0);
				}

				// JOptionPane.showMessageDialog(IntroCanvas.this,"+i);

			}
		});

	}

	@Override
	public void paint(Graphics g) {
		Image buf = this.createImage(this.getWidth(), getHeight());
		Graphics bg = buf.getGraphics();

		background.paint(bg);
		button.paint(bg);
		button2.paint(bg);
		button3.paint(bg);

		g.drawImage(buf, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

}
