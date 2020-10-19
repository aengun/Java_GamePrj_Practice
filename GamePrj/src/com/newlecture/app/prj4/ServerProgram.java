package com.newlecture.app.prj4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerProgram {

	public static void main(String[] args) throws IOException {

		ServerSocket svrSock = new ServerSocket(10000);
		System.out.println("Server Stated ...");
		Socket sock = svrSock.accept(); // blocking 상태
		System.out.println("connected from : " + sock.getRemoteSocketAddress());

		OutputStream nos = sock.getOutputStream();
		InputStream nis = sock.getInputStream();

		PrintStream out = new PrintStream(nos, true);

		Scanner scan = new Scanner(nis);
		String msg;

		do {
			msg = scan.nextLine();
			System.out.println(msg);

			out.println("echo : " + msg);

		} while (!msg.equals("bye"));

		scan.close();
		out.close();
		nis.close();
		nos.close();
		sock.close();
	}

}
