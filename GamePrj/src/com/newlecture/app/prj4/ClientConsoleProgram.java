package com.newlecture.app.prj4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientConsoleProgram {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket sock = new Socket("127.0.0.1", 10000);
		OutputStream nos = sock.getOutputStream();
		InputStream nis = sock.getInputStream();

		PrintStream nout = new PrintStream(nos, true);
		Scanner nscan = new Scanner(nis);
		Scanner scan = new Scanner(System.in);

		String msg;
		do {

			msg = scan.nextLine();
			nout.println(msg);

			String echo = nscan.nextLine();
			System.out.println(echo);
		} while (!msg.equals("bye"));

		nout.close();
		nos.close();
		nis.close();
		sock.close();
	}

}
