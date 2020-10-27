package com.newlecture.app.prj4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ClientFrame extends JFrame {
	
	private Socket socket;
	
	// �޴� �������..
	private JMenuBar menuBar;
	
	private JMenu mnFile;
	private JMenuItem miExit;
	private JMenuItem miSettings;
	
	private JMenu mnServer;
	private JMenuItem miConnect;
	private JMenuItem miClose;
	
	
	private PaintCanvas canvas;
	private ChatPanel panel;
	//private Button btnSend;

	protected Scanner nscan;

	protected PrintStream nout;

	private String nicName;
	
	public ClientFrame() {
		setSize(800, 500);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("Menu");
		menuBar.add(mnFile);
		
		mnServer = new JMenu("Server");
		menuBar.add(mnServer);
		
		miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		miSettings = new JMenuItem("Settings");
		mnFile.add(miSettings);
		miSettings.addActionListener((e)->{
			this.nicName = JOptionPane.showInputDialog("��ȭ���� �Է��ϼ���");			
		});
		
		miConnect = new JMenuItem("Connect");
		mnServer.add(miConnect);
		miConnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					socket = new Socket("192.168.0.3", 10000);
					
					if(socket.isConnected()) {
												
						InputStream nis = socket.getInputStream();
						OutputStream nos = socket.getOutputStream();
						nscan = new Scanner(nis);
						nout = new PrintStream(nos);
						
						nout.println("1,"+nicName);
						//========================
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								while(nscan.hasNextLine()) {
									//�����͸� �޴� ��
									String line = nscan.nextLine();
									// ������ �����͸� �޾��� ��
									String[] tokens = line.split(",");
									int type = Integer.parseInt(tokens[0]);
									
									switch(type) {
									case 1:
										
										String names = "";
										for(int i=0; i<tokens.length-1; i++)
											names += tokens[i+1];
										
										panel.setUserNames(names);
										
										break;
									case 2:
										break;
									case 3:
										//"3,hello"
										panel.setOutputText(tokens[1]);
										break;
									}									
									//  Ÿ��(1) - newlec,dragon,hoho
									
									/*
									"1,newlec,dragon,hoho"
									*/
																	
									
									// ����/�׸���/���� ���� �����͸� �޾��� ��
									//  Ÿ��(2) - x1,y1,x2,y2
									/*
									"2,x1,y1,x2,y2"
									*/
									
									// ä�� �����͸� �޾��� ��
									// Ÿ��(3) - msg
									/*
									"3,hello"
									*/
									
								}								
							}
						}).start();						
						//=======================
						canvas.setActive();
						panel.setOutputText("������ ����Ǿ����ϴ�.");
						
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
		
		
		
		canvas = new PaintCanvas();
		add(canvas);
		
		panel = new ChatPanel();
		panel.setPreferredSize(new Dimension(250, 0));
		add(panel, BorderLayout.LINE_END);
		panel.setChatListener((String chatMsg)->{
			nout.println(String.format("%s:%s",nicName, chatMsg));
		});
		
		/*
		 
		 void f(x, y){
		     system.out.println("aa");
		 }
		 
		 ()->{return 3;}
		 ()->3
		 
		 (x)->{return x+1;}
		 (x)->x+1;
		 x->x+1;
		 
		 (s)->{ System.out.println("aaa"); }
		 
		 */
		
		//btnSend = new Button("Send");
		//add(btnSend, BorderLayout.LINE_END);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);				
			}
			
		});		
		
	}
}
