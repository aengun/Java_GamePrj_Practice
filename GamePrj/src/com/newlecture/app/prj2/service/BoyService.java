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
//		// for 3��
//		// �� �ҳ⿡�� ��� ������ �� ��ΰ� ������ �� ���� ���ٸ�
//		// ���õ� �ҳ��� �̵� currentBoy.move(x,y);
//		// else
//		// ������ ����
//
//	}

}
