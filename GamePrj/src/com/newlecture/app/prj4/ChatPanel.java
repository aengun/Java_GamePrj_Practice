package com.newlecture.app.prj4;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.TextArea;

public class ChatPanel extends Panel {

	private TextArea textBox;
	private Panel inputPanel;
	private Button btnSend;
	private TextArea inputTextBox;

	public ChatPanel() {

		setLayout(new BorderLayout());

		textBox = new TextArea();
		textBox.setEditable(false); // 읽기 전용으로 만들기
		inputPanel = new Panel();
		inputPanel.setPreferredSize(new Dimension(0, 100));
		inputPanel.setLayout(new BorderLayout());

		add(textBox);
		add(inputPanel, BorderLayout.PAGE_END);

		inputTextBox = new TextArea();
		inputPanel.add(inputTextBox);

		btnSend = new Button("send");
		inputPanel.add(btnSend, BorderLayout.LINE_END);

	}

	public void setOutputText(String string) {
		System.out.println(string);
	}

}
