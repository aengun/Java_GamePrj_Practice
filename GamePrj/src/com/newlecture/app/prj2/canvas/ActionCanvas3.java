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

		// Boy�� ����
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

				while (true) { // �������� ������Ʈ �ϵ���

					enemy.update();
					currentBoy.update();
					repaint(); // -> Canvas.update() : ����� -> Canvas.paint(g) : �ٽ� �׸���
					// update�� �������̵� �ؼ� ��������
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
//      for(3��)
//         if()
//            ? = ;
		for (int i = 0; i < 3; i++) {
			if (boys[i].isSelected(x, y)) {
				currentBoy = boys[i];
				return super.mouseDown(evt, x, y);
			}
		}

		currentBoy.move(x, y);

		// ? == true �� �ҳ⿡�� ��� ������ �� ��ΰ� ������ ������ ���ٸ�.
		// ���õ� �ҳ��� �̵� currentBoy.move(x, y);
		// else
		// ������ ����

		repaint();
		return super.mouseDown(evt, x, y);
	}

	@Override
	public void update(Graphics g) { // ȭ�� ������ ����
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

// 1. �ҳ��� ������ �� ��ġ�� �ʱ�ȭ�� �� �ְ� �϶�.
// 2. �� ���� �ҳ��� �����
// 3. ����� �Է�ó�� : ���콺�� Ŭ���ϸ� �� ��ġ�� 1��° �ҳ��� �̵���Ű�ÿ�.
// 4. (x,y)�� �ҳ��� ��/����� �̵��� �߽���ǥ�� �ȴ�. 
//     �̰��� �߽�/�ϴ��� �ǵ��� Body.paint �޼ҵ带 �����ؼ� �����Ͻÿ�.
// 5. Boy ���� ��ǥ�� �ָ鼭 �װ� ���õǾ���? isSelected(x, y):true/false -> true�� ��� 
//    isSelected()�� ��ȯ ���� true �ϰ�� -> currentBoy.move(x, y)
// 6. ����, �ڵ���, ����, �κ�....�̵� ���ο� ��ü �ϳ��� �����Ű�ÿ�.
