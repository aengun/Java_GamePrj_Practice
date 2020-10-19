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

	// static : �ϳ��� ����,, static ������ ��ü�� ���� ������ ����
//	private static final int UP = 1004; // fianl Ű���带 ������� ������ case��Ͽ� ������ �� �ִ´�. case��Ͽ��� ������ �ʴ� ��(���<->����)�� �� �� �ִ�.
//	private static final int DOWN = 1005;
//	private static final int LEFT = 1006;
//	private static final int RIGHT = 1007;

	public ActionCanvas() {
		instance = this;

		enemy = new Enemy();

		enemy.setMoveListener(new EnemyMoveListener() {

			@Override
			public void onMove() {
				System.out.println("����� ���");

			}
		});

		// Boy�� ����
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
//		items[5] = new item();  << ��� �����Ǹ� �ȵ�. ���� abstract �����縦 ����Ѵ�.
		itemSize = 5;

		currentBoy = boy1;

		for (int i = 0; i < boys.length; i++) {

			boys[i].setCanvasListener(new BoyMoveListener() {

				@Override
				public void speak() {

					System.out.println("����");
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

					// ���� �ڵ带 �ϰ�ó��
					for (int i = 0; i < itemSize; i++)
						items[i].update();

					repaint();
					// -> Canvas.update() : ����� -> Canvas.paint(g) -> �ٽ� �׸���

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

//		//��� 1
//		double x = currentBoy.getX();
//		double y = currentBoy.getY();
//		
//		switch (key) {
//		case UP:
//			currentBoy.move(x-3,y);
//			break;

		// ���2
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
//		for(3��)
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

		// ? == true �� �ҳ⿡�� ��� ������ �� ��ΰ� ������ ������ ���ٸ�.
		// ���õ� �ҳ��� �̵� currentBoy.move(x, y);
		// else
		// ������ ����

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

// 1. �ҳ��� ������ �� ��ġ�� �ʱ�ȭ�� �� �ְ� �϶�.
// 2. �� ���� �ҳ��� �����
// 3. ����� �Է�ó�� : ���콺�� Ŭ���ϸ� �� ��ġ�� 1��° �ҳ��� �̵���Ű�ÿ�.
// 4. (x,y)�� �ҳ��� ��/����� �̵��� �߽���ǥ�� �ȴ�. 
//     �̰��� �߽�/�ϴ��� �ǵ��� Body.paint �޼ҵ带 �����ؼ� �����Ͻÿ�.
// 5. Boy ���� ��ǥ�� �ָ鼭 �װ� ���õǾ���? isSelected(x, y):true/false -> true�� ��� 
//    isSelected()�� ��ȯ ���� true �ϰ�� -> currentBoy.move(x, y)
// 6. ����, �ڵ���, ����, �κ�....�̵� ���ο� ��ü �ϳ��� �����Ű�ÿ�.
