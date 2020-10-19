package com.newlecture.app.prj2.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;

import com.newlecture.app.prj2.entity.Boy2;
import com.newlecture.app.prj2.entity.Grass;
import com.newlecture.app.prj2.entity.Tree;
import com.newlecture.app.prj2.service.TreeService;

public class ActionCanvas2 extends Canvas {

	public static Canvas instance;
	private Boy2[] boys;
	private Boy2 currentBoy;
	private Tree[] trees;

	public ActionCanvas2() {
		instance = this;

		// Boy를 생성
		boys = new Boy2[3];
//		boy1 = new Boy2(100, 200);
//		boy2 = new Boy2(200, 200);
//		boy3 = new Boy2(300, 200);
		for (int i = 0; i < 3; i++) {
			boys[i] = new Boy2(100 * i + 200, 300);
		}

		currentBoy = boys[0];

		trees = new Tree[5];

		trees[0] = new Tree(30, 50);
		trees[1] = new Tree(130, 400);
		trees[2] = new Tree(300, 80);
		trees[3] = new Tree(420, 500);
		trees[4] = new Tree(500, 300);
	}

	@Override
	public boolean mouseDown(Event evt, int x, int y) {

//      boolean ?;
//      for(3번)
//         if()
//            ? = ;
		for (int i = 0; i < 3; i++) {
			if (boys[i].isSelected(x, y)) {
				currentBoy = boys[i];
				return super.mouseDown(evt, x, y);
			}
		}

		currentBoy.move(x, y);

		// ? == true 세 소년에게 모두 물었을 때 모두가 선택이 된적이 없다면.
		// 선택된 소년을 이동 currentBoy.move(x, y);
		// else
		// 선택을 변경

		repaint();
		return super.mouseDown(evt, x, y);
	}

	@Override
	public void paint(Graphics g) {
		Grass backGround = new Grass();
		backGround.paint(g);

		for (int i = 0; i < 3; i++) {
			boys[i].paint(g);
		}

		for (int i = 0; i < 5; i++) {
			trees[i].paint(g);
		}

		currentBoy.paint(g);
	}
}

// 1. 소년을 생성할 때 위치를 초기화할 수 있게 하라.
// 2. 세 명의 소년을 띄워라
// 3. 사용자 입력처리 : 마우스로 클릭하면 그 위치로 1번째 소년을 이동시키시오.
// 4. (x,y)가 소년의 좌/상단이 이동의 중심좌표가 된다. 
//     이것을 중심/하단이 되도록 Body.paint 메소드를 수정해서 보정하시오.
// 5. Boy 한테 좌표를 주면서 네가 선택되었니? isSelected(x, y):true/false -> true일 경우 
//    isSelected()의 반환 값이 true 일경우 -> currentBoy.move(x, y)
// 6. 나무, 자동차, 깡통, 로봇....이든 새로운 개체 하나를 등장시키시오.
