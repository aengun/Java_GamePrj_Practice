package com.newlecture.app.prj2.service;

import com.newlecture.app.prj2.entity.Boy2;

public class BoyService2 {

	private Boy2[] boys;

	public BoyService2() {

		boys = new Boy2[3];
		for (int i = 0; i < 3; i++) {
			boys[i] = new Boy2(100 * i + 200, 300);
		}

	}

	public Boy2 get(int i) {
		return boys[i];
	}

}
