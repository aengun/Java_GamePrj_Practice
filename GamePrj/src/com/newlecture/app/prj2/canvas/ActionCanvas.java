package com.newlecture.app.prj2.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;

import com.newlecture.app.prj2.entity.Grass;
import com.newlecture.app.prj2.service.BoyService;
import com.newlecture.app.prj2.service.TreeService;

public class ActionCanvas extends Canvas {

	public static Canvas instance;
	private BoyService service;
	private TreeService servicet;
//	private Boy boy1;
//	private Boy boy2;
//	private Boy boy3;
//	private Boy currentBoy;

	public ActionCanvas() {

		instance = this;

//		boy1 = new Boy(100, 200);
//		boy2 = new Boy(200, 200);
//		boy3 = new Boy(300, 200);
		// Boy를 생성
		service = new BoyService();
		servicet = new TreeService();

	}

	@Override
	public boolean mouseDown(Event evt, int x, int y) {
		boolean isChoiced = false;

		for (int i = 0; i < 3; i++) {
			if (service.get(i).isSelected(x, y)) {
				service.setCurrentBoy(service.get(i));
				isChoiced = true;
			}
		}

		if (!isChoiced && service.getClass() != null)
			service.getCurrentBoy().move(x, y);

		repaint();

		return super.mouseDown(evt, x, y);
	}

	public void paint(Graphics g) {
//		boy1.paint(g);
//		boy2.paint(g);
//		boy3.paint(g);

		Grass backGround = new Grass();
		backGround.paint(g);

		for (int i = 0; i < 3; i++) {
			service.get(i).paint(g);
		}

		for (int i = 0; i < 5; i++) {
			servicet.get(i).paint(g);
		}
		
		

	}

}

// 1. 소년을 생성할 때 위치를 초기화 할 수 있게 하라.
// 2. 세 개의 소년을 띄워라
// 3. 사용자 입력 처리 : 마우스 클릭하면 그 위치로 1번째 소년을 이동시키시오
// 4. (x,y)가 소년의 좌/상단이 이동의 중심좌표가 된다.
//    이것을 중심/하단이 되도록 Boy.paint 메소드를 수정해서 보정하시오.
// 5. Boy 한테 좌표를 주면서 네가 선택되었니? isSelected(x, y); 
//    --> true/false --> true일 경우  --> currentBoy.move(x,y); 
// 6. 나무, 자동차, 깡통, 로봇 ... 이든 새로운 개체 하나를 등장시키시오.