package com.newlecture.app.prj4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerProgram2 {

	static int outSize = 0; // 원래 22번째 줄에 있었음 >> outSize++할 때 오류나서 전역변수로

	public static void main(String[] args) throws IOException {

		boolean isRunning = true;

		Socket[] sockets = new Socket[30];
		int socketSize = 0;
		PrintStream[] outs = new PrintStream[30];

		ServerSocket svrSock = new ServerSocket(10000);
		System.out.println("Server Stated ...");

		while (isRunning) {
			Socket sock = svrSock.accept(); // blocking 상태
			sockets[socketSize] = sock;
			socketSize++;

			System.out.println("connected from : " + sock.getRemoteSocketAddress());

			// ===============================================================
			new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						OutputStream nos = sock.getOutputStream();
						InputStream nis = sock.getInputStream();

						PrintStream nout = new PrintStream(nos, true);
						Scanner scan = new Scanner(nis);

						outs[outSize] = nout;
						outSize++;

						String msg;

						do { // 이 do while문 : blocking함수 : 더이상 진행 되지 못하게 쓰레드를 할당하고 있는 함수
//							msg = scan.nextLine();
							// nout.println(msg);
							
							line = scan.
							
							for (int i = 0; i < outSize; i++)
								outs[i].println(msg);
							System.out.println(sock.getRemoteSocketAddress() + " : " + msg);

						} while (!msg.equals("bye"));

						scan.close();
						nout.close();
						nis.close();
						nos.close();
						sock.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}).start();

		}
		// ===============================================================
	}

}
