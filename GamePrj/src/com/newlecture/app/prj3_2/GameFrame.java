package com.newlecture.app.prj3_2;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import com.newlecture.app.prj3_2.canvas.ActionCanvas;
import com.newlecture.app.prj3_2.canvas.IntroCanvas;

public class GameFrame extends Frame {

	public static GameFrame instance;

	private IntroCanvas introCanvas;
	private ActionCanvas canvas;

	public GameFrame() {
		instance = this;
		// Canvas canvas = new PuzzleCanvas();
		introCanvas = new IntroCanvas();
		add(introCanvas);

//		canvas = new ActionCanvas();
//		canvas.start();

		setSize(360, 600);
		setVisible(true);
//		add(canvas);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Object options[] = { "굿", "밷~" };
				JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
//				int input = JOptionPane.showConfirmDialog(GameFrame.this, "정말 갈끄야?", "야속해~~ 힝!",
//						JOptionPane.OK_CANCEL_OPTION);
//
//				if (input == JOptionPane.OK_OPTION)
//					System.exit(0);
			}

		});

	}

	public void switchCanvas(Canvas oldCanvas, Class newCanvas) throws InstantiationException, IllegalAccessException {

		Canvas canvas = (Canvas) newCanvas.newInstance();
		add(canvas);
		canvas.setFocusable(true); // currentBoy가 활성화 되려면 윈도우가 활성화 되어야함. 마우스 클릭으로 가능하지만 자동으로 하는 법
		canvas.requestFocus(); // setfocusable : 포커스 받을 수 있는 상태 , requestfocus : 포커스 맞춤 ,,

		if (canvas instanceof ActionCanvas)
			((ActionCanvas) canvas).start();
		// (ActionCanvas)canvas.start(); // 이렇게 하면 "."이 연산 우선순위이기 때문에 괄호를 잘 써야함

		revalidate();

		// remove(oldCanvas); // 화면이 하얗게 질리면 이 코드 혹은 밑의 코드를 마지막으로 실행해야한다. 지우고나서 표시할 화면이
		// 없어서 하얗게 뜨는거니까
		oldCanvas.setVisible(false);
	}

}
