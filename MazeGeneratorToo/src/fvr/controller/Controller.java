package fvr.controller;

import java.util.Scanner;

import fvr.model.DepthFirstSearch;
import fvr.view.MainWindow;

public class Controller {
	
	private Scanner sc;
	private DepthFirstSearch dfs;
	private MainWindow mw;
	
	
	private int rows, cols, pxSize;
	
	public Controller() {
		sc = new Scanner(System.in);
		mw = new MainWindow();
	}
	
	public void run() {
		
		System.out.println("Rows: ");
		rows = sc.nextInt();
		System.out.println("Columns: ");
		cols = sc.nextInt();
		System.out.println("Pixel Size: ");
		pxSize = sc.nextInt();
		
		dfs = new DepthFirstSearch(rows, cols);
		mw.drawMaze(dfs.getGrid(), pxSize);
		
	}

}
