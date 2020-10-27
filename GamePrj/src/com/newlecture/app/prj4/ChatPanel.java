package com.newlecture.app.prj4;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends Panel {
	private TextArea textBox;
	private Label label;
	
	private Panel inputPanel;
	private TextArea inputTextBox;
	private Button btnSend;
	private ChatListener chatListener;
	
	public ChatPanel() {
		setLayout(new BorderLayout());
		label = new Label();
		label.setPreferredSize(new Dimension(0, 50));
		add(label, BorderLayout.PAGE_START);
		
		textBox = new TextArea();
		textBox.setEditable(false);
		inputPanel = new Panel();
		inputPanel.setLayout(new BorderLayout());
		inputPanel.setPreferredSize(new Dimension(0, 100));
		
		add(textBox);
		add(inputPanel, BorderLayout.PAGE_END);
		
		inputTextBox = new TextArea();
		inputPanel.add(inputTextBox);
		
		btnSend = new Button("send");
		
		//btnSend.addMouseListener(l);
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 1. 
				String chatMsg = inputTextBox.getText();
				chatListener.onSend(chatMsg);
			}
		});
		inputPanel.add(btnSend, BorderLayout.LINE_END);
	}

	public void setOutputText(String message) {
		textBox.append(message + "\r\n");		
	}

	public void setChatListener(ChatListener chatListener) {
		this.chatListener = chatListener;
	}

	public void setUserNames(String names) {
		label.setText(names);
	}	
	
}
