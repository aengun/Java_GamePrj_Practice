package com.newlecture.app.prj3_2.canvas;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.newlecture.app.prj3_2.entity.Background;
import com.newlecture.app.prj3_2.entity.Boy;
import com.newlecture.app.prj3_2.entity.BoyMoveListener;
import com.newlecture.app.prj3_2.entity.Enemy;
import com.newlecture.app.prj3_2.entity.EnemyMoveListener;
import com.newlecture.app.prj3_2.entity.Item;
import com.newlecture.app.prj3_2.entity.Missile;
import com.newlecture.app.prj3_2.entity.MissileListener;

public class ActionCanvas extends Canvas {

	public static Canvas instance;

	private Enemy enemy;
	private Boy[] boys;
	private Boy boy1;
	private Boy boy2;
	private Boy boy3;
	private Boy currentBoy;

	private Background background;

	private Missile[] missiles;
	int missileSize = 0;

	private Item[] items;
	private int itemSize = 0;

	public ActionCanvas() {
		instance = this;
		missiles = new Missile[100];

		enemy = new Enemy();

		enemy.setMoveListener(new EnemyMoveListener() {
			@Override
			public void onMove() {
				System.out.println("우주선 움직임");
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

		background = new Background(0, -600);

		items = new Item[100];
		items[0] = background;
		items[1] = boy1;
		items[2] = boy2;
		items[3] = boy3;
		items[4] = enemy;
		itemSize = 5;

		currentBoy = boy1;

		for (int i = 0; i < boys.length; i++) {

			boys[i].setCanvasListener(new BoyMoveListener() {

				@Override
				public void speak() {

					System.out.println("그만 가");

				}
			});

		}

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				currentBoy.stop(key);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();

				switch (code) {
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_UP:
				case KeyEvent.VK_DOWN:
					currentBoy.move(code);
					break;
				case KeyEvent.VK_SPACE:
					Missile missile = currentBoy.fire();
					missile.setListener(new MissileListener() {

						@Override
						public void onOut(Missile missile) {
							System.out.println("미사일 나감");
							int i;
							for (i = 0; i < itemSize; i++) { // 지우려는 미사일이 아이템 배열의 몇 번째 인덱스인지 찾기
								if (items[i] == missile)
									break;
							}
							System.out.printf("%d %s 아이템\n", i, items[i] == missile);
							for (int j = 0; j < itemSize - (i + 1); j++) { // 해당 인덱스의 아이템을 제거하고 뒤에 있는 아이템들을 한 칸씩 땡긴다.
								items[i + j] = items[i + j + 1];
							}
							itemSize--;

							for (i = 0; i < missileSize; i++) {
								if (missiles[i] == missile)
									break;
							}
							System.out.printf("%d %s\n 미사일", i, missiles[i] == missile);
							for (int j = 0; j < missileSize - (i + 1); j++) {
								missiles[i + j] = missiles[i + j + 1];
							}
							missileSize--;
						}
					});
					items[itemSize++] = missile;
					missiles[missileSize++] = missile;
					break;
				}

			}
		});

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				for (int i = 0; i < 3; i++)
					if (boys[i].isSelected(x, y))
						currentBoy = boys[i];

				currentBoy.move(x, y);
			}

		});

	}

	public void start() {

		Runnable sub = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {

//					enemy.update();
//					
//					for(int i=0; i<boys.length; i++)
//						boys[i].update();

					for (int i = 0; i < itemSize; i++)
						items[i].update();

					repaint();
					// -> Canvas.update() : ����� -> Canvas.paint(g) -> �ٽ� �׸���

					// System.out.println("repaint");

					try {
						Thread.sleep(17);
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

//	@Override
//	public boolean mouseDown(Event evt, int x, int y) {

//		boolean ?;
//		for(3��)
//			if()
//				? = ;
//		for (int i = 0; i < 3; i++)
//			if (boys[i].isSelected(x, y)) {
//				currentBoy = boys[i];
//				// System.out.printf("boy[%d] selected", i);
//				return true;// super.mouseDown(evt, x, y);
//			}
//		currentBoy.move(x, y);

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

	// ? == true �� �ҳ⿡�� ��� ������ �� ��ΰ� ������ ������ ���ٸ�.
	// ���õ� �ҳ��� �̵� currentBoy.move(x, y);
	// else
	// ������ ����

//		repaint();
//		return true;// super.mouseDown(evt, x, y);

//	}

//	@Override
//	public boolean keyDown(Event evt, int key) {
//
//		// ��� 1
//
//		// ----------------------------
//		// ��� 2
//		System.out.println(key);
//		currentBoy.move(key);
//
//		return super.keyDown(evt, key);
//	}
//
//	@Override
//	public boolean keyUp(Event evt, int key) {
//
//		currentBoy.stop(key);
//		return super.keyUp(evt, key);
//	}

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

		for (int i = 0; i < itemSize; i++)
			items[i].paint(bg);
//		boy1.paint(bg);
//		boy2.paint(bg);//
//		boy3.paint(bg);
//		
//		enemy.paint(bg);

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
