package com.newlecture.app.prj3.canvas;

import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

import com.newlecture.app.prj3.entity.Aseifjijsdf;
import com.newlecture.app.prj3.entity.Background;
import com.newlecture.app.prj3.entity.Boy;
import com.newlecture.app.prj3.entity.BoyMoveListener;
import com.newlecture.app.prj3.entity.Enemy;
import com.newlecture.app.prj3.entity.EnemyMoveListener;
import com.newlecture.app.prj3.entity.Item;

public class ActionCanvas extends Canvas {

	public static Canvas instance;

	private Enemy enemy;
	private Boy[] boys;
	private Boy boy1;
	private Boy boy2;
	private Boy boy3;
	private Boy currentBoy;
	private Background background;

	private Item[] items;
	private int itemSize = 0;

	// static : 하나를 공유,, static 없으면 객체를 만들 때마다 생성
//	private static final int UP = 1004; // fianl 키워드를 사용하지 않으면 case블록에 변수로 못 넣는다. case블록에는 변하지 않는 수(상수<->변수)만 올 수 있다.
//	private static final int DOWN = 1005;
//	private static final int LEFT = 1006;
//	private static final int RIGHT = 1007;

	public ActionCanvas() {
		instance = this;

		enemy = new Enemy();

		enemy.setMoveListener(new EnemyMoveListener() {

			@Override
			public void onMove() {
				System.out.println("에헤라 디야");

			}
		});

		// Boy를 생성
		boys = new Boy[3];

		boy1 = new Boy(100, 200);
		boy2 = new Boy(200, 200);
		boy3 = new Boy(300, 200);

		boys[0] = boy1;
		boys[1] = boy2;
		boys[2] = boy3;

		background = new Background();

		items = new Item[100];
		items[0] = background;
		items[1] = boy1;
		items[2] = boy2;
		items[3] = boy3;
		items[4] = enemy;
//		items[5] = new item();  << 얘는 생성되면 안됨. 따라서 abstract 한정사를 써야한다.
		itemSize = 5;

		currentBoy = boy1;

		for (int i = 0; i < boys.length; i++) {

			boys[i].setCanvasListener(new BoyMoveListener() {

				@Override
				public void speak() {

					System.out.println("멈춰");
				}

			});
		}

		// addMouseListener();
	}

	public void start() {

		Runnable sub = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {

//					enemy.update();
//
//					for (int i = 0; i < boys.length; i++)
//						boys[i].update();

					// 위의 코드를 일괄처리
					for (int i = 0; i < itemSize; i++)
						items[i].update();

					repaint();
					// -> Canvas.update() : 지우기 -> Canvas.paint(g) -> 다시 그리기

					// System.out.println("repaint");

					try {
						Thread.sleep(1000 / 60);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		};

		Thread th = new Thread(sub);
		th.start();

	}

	@Override
	public boolean keyDown(Event evt, int key) {

//		//방법 1
//		double x = currentBoy.getX();
//		double y = currentBoy.getY();
//		
//		switch (key) {
//		case UP:
//			currentBoy.move(x-3,y);
//			break;

		// 방법2
		currentBoy.move(key);

		return super.keyDown(evt, key);
	}

	@Override
	public boolean keyUp(Event evt, int key) {

		return super.keyUp(evt, key);
	}

	@Override
	public boolean mouseDown(Event evt, int x, int y) {

//		boolean ?;
//		for(3번)
//			if()
//				? = ;
		for (int i = 0; i < 3; i++)
			if (boys[i].isSelected(x, y)) {
				currentBoy = boys[i];
				System.out.printf("boy[%d] selected", i);
				return super.mouseDown(evt, x, y);
			}

//		if(boy2.isSelected(x, y)) {
//			currentBoy = boy2;
//			System.out.println("boy2 selected");
//			return super.mouseDown(evt, x, y);
//		}
//		
//		if(boy3.isSelected(x, y)) {
//			currentBoy = boy3;
//			System.out.println("boy3 selected");
//			return super.mouseDown(evt, x, y);
//		}

		currentBoy.move(x, y);

		// ? == true 세 소년에게 모두 물었을 때 모두가 선택이 된적이 없다면.
		// 선택된 소년을 이동 currentBoy.move(x, y);
		// else
		// 선택을 변경

		repaint();
		return super.mouseDown(evt, x, y);
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		// super.update(g);
		paint(g);
	}

	@Override
	public void paint(Graphics g) {

		Image buf = this.createImage(this.getWidth(), getHeight());
		Graphics bg = buf.getGraphics();

//		boy1.paint(bg);
//		boy2.paint(bg);
//		boy3.paint(bg);
//		enemy.paint(bg);

		for (int i = 0; i < itemSize; i++)
			items[i].paint(bg);

		g.drawImage(buf, 0, 0, this);//
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
