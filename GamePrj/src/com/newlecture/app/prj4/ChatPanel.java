package com.newlecture.app.prj4;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChatPanel extends Panel {

	private TextArea textBox;
	private Panel inputPanel;
	private Button btnSend;
	private TextArea inputTextBox;

	private ChatListener chatListener;

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
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String chatMsg = inputTextBox.getText();
				if(chatListener != null)
					chatListener.onSend(chatMsg);
			}
		});
		inputPanel.add(btnSend, BorderLayout.LINE_END);

	}

	public void setOutputText(String message) {
//		textBox.setText(message); // 문자열을 바꿔버림
		textBox.append(message + "\n"); // 문자열을 추가함
	}

	public void setChatListener(ChatListener chatListener) {
		this.chatListener = chatListener;
	}

}
