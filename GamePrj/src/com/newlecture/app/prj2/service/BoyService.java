package com.newlecture.app.prj2.service;

import com.newlecture.app.prj2.entity.Boy;

public class BoyService {

	private Boy[] boys;
	private Boy currentBoy;

	public BoyService() {

		boys = new Boy[3];
		for (int i = 0; i < 3; i++) {
			boys[i] = new Boy(100 * i + 200, 300);
		}

	}

	public Boy get(int i) {
		return boys[i];
	}

	public Boy[] getBoys() {
		return boys;
	}

	public void setBoys(Boy[] boys) {
		this.boys = boys;
	}

	public Boy getCurrentBoy() {
		return currentBoy;
	}

	public void setCurrentBoy(Boy currentBoy) {
		this.currentBoy = currentBoy;
	}

//	public boolean isSelected(int a, int b) {
//
//		boolean flag = false;
//		// for 3번
//		// 세 소년에게 모두 물었을 때 모두가 선택이 된 적이 없다면
//		// 선택된 소년을 이동 currentBoy.move(x,y);
//		// else
//		// 선택을 변경
//
//	}

}
