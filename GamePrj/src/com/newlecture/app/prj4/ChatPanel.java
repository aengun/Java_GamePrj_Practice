package com.newlecture.app.prj4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.TextArea;

public class ChatPanel extends Panel {

	private TextArea textBox;
	private Panel inputPanel;

	public ChatPanel() {

		setLayout(new BorderLayout());

		textBox = new TextArea();
		inputPanel = new Panel();
		inputPanel.setPreferredSize(new Dimension(0, 100));

		add(textBox);
		add(inputPanel, BorderLayout.PAGE_END);
	}

}
