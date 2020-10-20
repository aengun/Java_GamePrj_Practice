package com.newlecture.app.prj4;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class ClientFrame extends JFrame {

	private PaintCanvas canvas;
	private ChatPanel panel;
	private Button btnSend;

	public ClientFrame() {

		setSize(800, 700);

		canvas = new PaintCanvas();
		add(canvas);

		panel = new ChatPanel();
		panel.setPreferredSize(new Dimension(250, 0));
		add(panel, BorderLayout.LINE_END);

//		btnSend = new Button("Send");
//		add(btnSend, BorderLayout.LINE_END);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

	}

}
