package fvr.controller;

import java.util.Scanner;

import fvr.model.DepthFirstSearch;
import fvr.model.HuntKill;
import fvr.view.MainWindow;

public class Controller {

	private Scanner sc;
	private DepthFirstSearch dfs;
	private HuntKill hk;
	private MainWindow mw;

	private int rows, cols, pxSize;

	public Controller() {
		sc = new Scanner(System.in);
		mw = new MainWindow();
	}

	public void run() {

		System.out.println("[1] Recursive Backtracking\n[2] Hunt and Kill");
		int op = sc.nextInt();

		switch (op) {

			case 1: {
				System.out.println("Rows: ");
				rows = sc.nextInt();
				System.out.println("Columns: ");
				cols = sc.nextInt();
				System.out.println("Pixel Size: ");
				pxSize = sc.nextInt();
	
				dfs = new DepthFirstSearch(rows, cols);
				mw.drawMaze(dfs.getGrid(), pxSize);
				break;
			}
			case 2: {
				System.out.println("Rows: ");
				rows = sc.nextInt();
				System.out.println("Columns: ");
				cols = sc.nextInt();
				System.out.println("Pixel Size: ");
				pxSize = sc.nextInt();
	
				hk = new HuntKill(rows, cols);
				mw.drawMaze(hk.getGrid(), pxSize);
				break;
			}

		}

	}

}
