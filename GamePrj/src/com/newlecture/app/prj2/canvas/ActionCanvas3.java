package com.newlecture.app.prj2.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

import com.newlecture.app.prj2.entity.Boy3;
import com.newlecture.app.prj2.entity.Enemy;
import com.newlecture.app.prj2.entity.Grass;
import com.newlecture.app.prj2.entity.Tree;

public class ActionCanvas3 extends Canvas {

	public static Canvas instance;

	private Grass grass;
	private Enemy enemy;
	private Boy3[] boys;
	private Boy3 boy1;
	private Boy3 boy2;
	private Boy3 boy3;
	private Boy3 currentBoy;
	private Tree[] trees;

	public ActionCanvas3() {
		instance = this;

		grass = new Grass();
		enemy = new Enemy();

		// Boy를 생성
		boys = new Boy3[3];

		boy1 = new Boy3(100, 200);
		boy2 = new Boy3(200, 200);
		boy3 = new Boy3(300, 200);

		boys[0] = boy1;
		boys[1] = boy2;
		boys[2] = boy3;

		currentBoy = boys[0];

		trees = new Tree[5];

		trees[0] = new Tree(230, 250);
		trees[1] = new Tree(130, 400);
		trees[2] = new Tree(350, 80);
		trees[3] = new Tree(420, 500);
		trees[4] = new Tree(500, 300);

	}

	public void start() {

		Runnable sub = new Runnable() {

			@Override
			public void run() {

				while (true) { // 연속으로 리페인트 하도록

					enemy.update();
					currentBoy.update();
					repaint(); // -> Canvas.update() : 지우기 -> Canvas.paint(g) : 다시 그리기
					// update를 오버라이드 해서 수정하자
//					System.out.println("repaint");

					try {
						Thread.sleep(1000 / 60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread th = new Thread(sub);
		th.start();
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
	public void update(Graphics g) { // 화면 깜빡임 보정
		this.paint(g);
	}

	@Override
	public void paint(Graphics g) {

		Image buf = this.createImage(this.getWidth(), this.getHeight());
		Graphics bg = buf.getGraphics();

		grass.paint(bg);

		enemy.paint(bg);

		boy1.paint(bg);
		boy2.paint(bg);
		boy3.paint(bg);

		for (int i = 0; i < 5; i++) {
			trees[i].paint(bg);
		}

		g.drawImage(buf, 0, 0, this);

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
