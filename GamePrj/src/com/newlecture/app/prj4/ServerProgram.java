package com.newlecture.app.prj4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerProgram {
	static int outSize = 0;

	public static void main(String[] args) throws IOException {

		boolean isRunning = true;

		Socket[] sockets = new Socket[30];
		int socketSize = 0;
		PrintStream[] outs = new PrintStream[30];

		ServerSocket svrSock = new ServerSocket(10000);
		System.out.println("Server Started ...");

		List<String> users = new ArrayList<>();

		while (isRunning) {

			Socket sock = svrSock.accept(); // blocking : ����� ��û�� ������ ��ٸ�.
			sockets[socketSize] = sock;
			socketSize++;

			System.out.println("connected from:" + sock.getRemoteSocketAddress());
			// ==========================
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						OutputStream nos = sock.getOutputStream();
						InputStream nis = sock.getInputStream();

						PrintStream nout = new PrintStream(nos);
						Scanner scan = new Scanner(nis);

						outs[outSize] = nout;
						outSize++;

						String line;
						String msg = "";
						do {
							line = scan.nextLine();

							String[] tokens = line.split(",");

							int type = Integer.parseInt(tokens[0]);

							switch (type) {
							case 1:
								// ���� ������ ������ users �ݷ��ǿ� ���
								String name = tokens[1];
								users.add(name);

								// users �ݷ����� ����� csv ���ڿ��� ����
								String csv = "1,";
								csv += String.join(",", users);

								// ���� �������� ����� ��ο��� �Ѹ���.
								for (int i = 0; i < outSize; i++)
									outs[i].println(csv);

								break;
							case 2:
								break;
							case 3:
								for (int i = 0; i < outSize; i++)
									outs[i].println(line);

								System.out.println(line);
								break;
							}

						} while (!msg.equals("bye"));

						scan.close();
						nos.close();
						nis.close();
						sock.close();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}).start();

		}

		// ==================================
	}

}
