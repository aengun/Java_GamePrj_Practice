package com.newlecture.app.prj4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientProgram {

	public static void main(String[] args) throws UnknownHostException, IOException {

		ClientFrame frame = new ClientFrame();
		frame.setVisible(true);

	}

}
