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

		// Boy�� ����
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

// 1. �ҳ��� ������ �� ��ġ�� �ʱ�ȭ�� �� �ְ� �϶�.
// 2. �� ���� �ҳ��� �����
// 3. ����� �Է�ó�� : ���콺�� Ŭ���ϸ� �� ��ġ�� 1��° �ҳ��� �̵���Ű�ÿ�.
// 4. (x,y)�� �ҳ��� ��/����� �̵��� �߽���ǥ�� �ȴ�. 
//     �̰��� �߽�/�ϴ��� �ǵ��� Body.paint �޼ҵ带 �����ؼ� �����Ͻÿ�.
// 5. Boy ���� ��ǥ�� �ָ鼭 �װ� ���õǾ���? isSelected(x, y):true/false -> true�� ��� 
//    isSelected()�� ��ȯ ���� true �ϰ�� -> currentBoy.move(x, y)
// 6. ����, �ڵ���, ����, �κ�....�̵� ���ο� ��ü �ϳ��� �����Ű�ÿ�.
