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
		// Boy�� ����
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

// 1. �ҳ��� ������ �� ��ġ�� �ʱ�ȭ �� �� �ְ� �϶�.
// 2. �� ���� �ҳ��� �����
// 3. ����� �Է� ó�� : ���콺 Ŭ���ϸ� �� ��ġ�� 1��° �ҳ��� �̵���Ű�ÿ�
// 4. (x,y)�� �ҳ��� ��/����� �̵��� �߽���ǥ�� �ȴ�.
//    �̰��� �߽�/�ϴ��� �ǵ��� Boy.paint �޼ҵ带 �����ؼ� �����Ͻÿ�.
// 5. Boy ���� ��ǥ�� �ָ鼭 �װ� ���õǾ���? isSelected(x, y); 
//    --> true/false --> true�� ���  --> currentBoy.move(x,y); 
// 6. ����, �ڵ���, ����, �κ� ... �̵� ���ο� ��ü �ϳ��� �����Ű�ÿ�.