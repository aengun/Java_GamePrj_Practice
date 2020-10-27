package com.newlecture.app.prj4;

import java.io.Serializable;

public class ChatData implements Serializable {
	private int type;
	private String msg;
	
	public ChatData() {
	}

	public ChatData(int type, String msg) {	
		this.type = type;
		this.msg = msg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
