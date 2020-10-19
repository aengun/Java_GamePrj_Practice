package com.newlecture.app.prj2.service;

import com.newlecture.app.prj2.entity.Tree;

public class TreeService {

	private Tree[] trees;

	public TreeService() {

		trees = new Tree[5];

		trees[0] = new Tree(30, 50);
		trees[1] = new Tree(130, 400);
		trees[2] = new Tree(300, 80);
		trees[3] = new Tree(420, 500);
		trees[4] = new Tree(500, 300);

	}

	public Tree get(int i) {
		return trees[i];
	}

}
