package com.newlecture.app.prj4;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClientFrame extends JFrame {

	private PaintCanvas canvas;
	private ChatPanel panel;
	private Button btnSend;

	// 메뉴 만들기
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem miExit;

	private JMenu mnServer;
	private JMenuItem miConnect;
	private JMenuItem miClose;

	// paint부분, text부분 모두 소켓이 필요>두개만들지 말고 프레임에 하나만 두자
	private Socket socket;

	public ClientFrame() {
		setSize(800, 700);

		// 메뉴바========================================================================
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("Menu");
		menuBar.add(mnFile);

		miExit = new JMenuItem("Exit");
		mnFile.add(miExit);
		miExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		////////////////
		mnServer = new JMenu("Server");
		menuBar.add(mnServer);

		miConnect = new JMenuItem("Connect");
		mnServer.add(miConnect);
		miConnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					socket = new Socket("192.168.0.72", 10000);

					if (socket.isConnected()) {
						canvas.setActive();
						panel.setOutputText("서버에 연결되었습니다.");
					}

				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		miClose = new JMenuItem("Close");
		mnServer.add(miClose);

		// ========================================================================

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
