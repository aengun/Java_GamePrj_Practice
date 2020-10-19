package com.newlecture.app.prj2.service;

import java.util.Random;

import com.newlecture.app.prj2.entity.Puzzle;

public class PuzzleService {

	private Puzzle[] puzzles;
	private int[] dis;
	private int[] sis;

	public PuzzleService() {

		dis = new int[] { 0, 1, 2, 3, 4, 5 };
		sis = new int[] { 0, 1, 2, 3, 4, 5 };

		puzzles = new Puzzle[6];
		puzzles[0] = new Puzzle(dis[0], sis[0]);
		// .. 6번 생성
		// 초기화
	}

	public void shuffle() {

		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			int j = rand.nextInt(6);
			int k = rand.nextInt(6);

			int temp = dis[j];
			dis[j] = dis[k];
			dis[k] = temp;
		}

		for (int i = 0; i < 6; i++) {
			puzzles[i].setDi(dis[i]);
			puzzles[i].setSi(sis[i]);
		}
		
	}

	public Puzzle get(int index) {

		return puzzles[index];
	}

}
