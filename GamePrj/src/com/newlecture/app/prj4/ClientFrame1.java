package com.newlecture.app.prj4;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Parameter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ClientFrame1 extends JFrame {
	private Socket socket;

	private PaintCanvas canvas;
	private Button btnSend;
	private ChatPanel panel;

	private JMenuBar menuBar;

	private JMenu mnFile;
	private JMenuItem miExit;

	private JMenu mnServer;
	private JMenuItem miConnect;
	private JMenuItem miClose;

	private JMenuItem miSettings;

	private Scanner nscan;
	private PrintStream nout;
	private String nicName;

	public ClientFrame1() {
		setSize(800, 500);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("Menu");
		menuBar.add(mnFile);
		miSettings = new JMenuItem("Settings");
		mnFile.add(miSettings);
		miSettings.addActionListener((e) -> {
			this.nicName = JOptionPane.showInputDialog("대화명을 입력하세요: ");

		});

		miExit = new JMenuItem("Exit");
		mnFile.add(miExit);
		miExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		mnServer = new JMenu("Server");
		menuBar.add(mnServer);

		miConnect = new JMenuItem("Connet");
		miClose = new JMenuItem("Close");
		mnServer.add(miConnect);
		miConnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					socket = new Socket("192.168.0.100", 10000);

					if (socket.isConnected()) {

						InputStream nis = socket.getInputStream();
						OutputStream nos = socket.getOutputStream();

						nscan = new Scanner(nis);
						nout = new PrintStream(nos);

						new Thread(new Runnable() {

							@Override
							public void run() {

								while (nscan.hasNextLine()) {
									String msg = nscan.nextLine();
									String[] tokens = msg.split(",");
									int type = Integer.parseInt(tokens[0]);

									switch (type) {
									case 1:

										break;
									case 2:

										break;
									case 3:
										panel.setOutputText(tokens[1]);
										break;
									}

								}

							}
						}).start();

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

		mnServer.add(miClose);
		miClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnSend = new Button("Send");
//      add(btnSend,BorderLayout.LINE_END);

		canvas = new PaintCanvas();
		add(canvas);

		panel = new ChatPanel();
		panel.setPreferredSize(new Dimension(300, 0));
		add(panel, BorderLayout.LINE_END);
		panel
//      .setChatListener(new ChatListener() {
//         
//         @Override
//         public void onSend(String chatMsg) {
//            
//            
//         }
//      });
				.setChatListener(chatMsg -> {
					System.out.println(chatMsg);
					nout.println(nicName + ": " + chatMsg);

				});

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			}

		});

	}

}