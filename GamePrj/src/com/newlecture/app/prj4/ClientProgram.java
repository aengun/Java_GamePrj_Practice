package com.newlecture.app.prj4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientProgram {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {

		Socket sock = new Socket("192.168.0.77", 10000);
		OutputStream nos = sock.getOutputStream(); // 출력버퍼
		InputStream nis = sock.getInputStream(); // 입력버퍼
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(nis);

		PrintStream out = new PrintStream(nos, true); // true : 바로 flush

//		out.println("I'll be back");

		String msg;

		do {

			System.out.print("메세지를 입력하세요 : ");
			msg = scan.nextLine();
			out.println(msg);

			String echo = scan2.nextLine();
			System.out.println(echo);

		} while (!msg.equals("bye"));

		out.close();
		scan.close();
		nis.close();
		nos.close();
		sock.close(); // 닫는 순서는 여는 순서대로

	}

}
