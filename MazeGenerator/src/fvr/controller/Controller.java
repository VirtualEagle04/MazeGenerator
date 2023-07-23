package fvr.controller;

import java.util.Scanner;

import fvr.model.Cell;
import fvr.model.MazeMatrixGenerator;
import fvr.view.MainWindow;

public class Controller {
	
	private MainWindow mw;
	private MazeMatrixGenerator mmg;
	private Scanner sc;
	
	public Controller() {
		
	}
	
	public void run() {
		System.out.println("Max Rows and Columns: 57");
		sc = new Scanner(System.in);
		System.out.println("Rows: ");
		int _rows = sc.nextInt();
		System.out.println("Columns: ");
		int _cols = sc.nextInt();
		
		mmg = new MazeMatrixGenerator(_cols, _rows);
		
		mw = new MainWindow();
		mw.drawMaze(_rows, _cols, mmg.get_cells());
	}

}
