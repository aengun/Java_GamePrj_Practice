package com.newlecture.app.prj4;

import java.io.IOException;
import java.net.UnknownHostException;

public class ClientProgram {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {

		ClientFrame frame = new ClientFrame();
		frame.setVisible(true);

//		Socket sock = new Socket("192.168.0.77", 10000);
//		OutputStream nos = sock.getOutputStream(); // 출력버퍼
//		InputStream nis = sock.getInputStream(); // 입력버퍼
//
//		PrintStream nout = new PrintStream(nos, true); // true : 바로 flush
//		Scanner nscan = new Scanner(nis);
//		Scanner scan = new Scanner(System.in);
//
//		// out.println("I'll be back");
//
//		String msg;
//
//		do {
//
//			System.out.print("메세지를 입력하세요 : ");
//			msg = scan.nextLine();
//			nout.println(msg);
//
//			String echo = nscan.nextLine();
//			System.out.println(echo);
//
//		} while (!msg.equals("bye"));
//
//		scan.close();
//		nscan.close();
//		nout.close();
//		nis.close();
//		nos.close();
//		sock.close(); // 닫는 순서는 여는 순서대로

	}

}
