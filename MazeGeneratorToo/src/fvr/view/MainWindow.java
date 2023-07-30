package fvr.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import fvr.model.Cell;

public class MainWindow extends JFrame {

	public JPanel mainPanel;
	public int pixelSize = 5;
	public int rows, cols;

	public MainWindow() {
		setTitle("MazeGenerator");
		setSize(0, 0);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setLayout(null);

	}

	public void drawMaze(Cell[][] grid, int pxSize) {
		
		this.pixelSize = pxSize;

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setLayout(null);

		rows = grid.length;
		cols = grid[0].length;

		int width = (cols * pixelSize * 3) - ((cols - 1) * pixelSize);
		int height = (rows * pixelSize * 3) - ((rows - 1) * pixelSize);

		mainPanel.setSize(width, height);
		getContentPane().setPreferredSize(new Dimension(width, height));
		pack();

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				drawCell(grid[row][col]);
			}
		}

		add(mainPanel);
		mainPanel.repaint();
		setVisible(true);

	}

	public void drawCell(Cell cell) {

		JPanel cellPanel = new JPanel();
		cellPanel.setSize(pixelSize * 3, pixelSize * 3);
		cellPanel.setLocation(cell.getPosCol() * (pixelSize * 2), cell.getPosRow() * (pixelSize * 2));
		cellPanel.setLayout(null);

		JPanel topLeftCorner = new JPanel();
		topLeftCorner.setBounds(0, 0, pixelSize, pixelSize);
		topLeftCorner.setBackground(Color.BLACK);
		cellPanel.add(topLeftCorner);

		JPanel topRightCorner = new JPanel();
		topRightCorner.setBounds(2 * pixelSize, 0, pixelSize, pixelSize);
		topRightCorner.setBackground(Color.BLACK);
		cellPanel.add(topRightCorner);

		JPanel bottomLeftCorner = new JPanel();
		bottomLeftCorner.setBounds(0, 2 * pixelSize, pixelSize, pixelSize);
		bottomLeftCorner.setBackground(Color.BLACK);
		cellPanel.add(bottomLeftCorner);

		JPanel bottomRightCorner = new JPanel();
		bottomRightCorner.setBounds(2 * pixelSize, 2 * pixelSize, pixelSize, pixelSize);
		bottomRightCorner.setBackground(Color.BLACK);
		cellPanel.add(bottomRightCorner);

		JPanel center = new JPanel();
		center.setBounds(pixelSize, pixelSize, pixelSize, pixelSize);
		cellPanel.add(center);

		// TOP
		if (cell.walls[0]) {
			JPanel topWall = new JPanel();
			topWall.setBounds(1 * pixelSize, 0, pixelSize, pixelSize);
			topWall.setBackground(Color.BLACK);
			cellPanel.add(topWall);
		}
		// BOTTOM
		if (cell.walls[1]) {
			JPanel bottomWall = new JPanel();
			bottomWall.setBounds(1 * pixelSize, 2 * pixelSize, pixelSize, pixelSize);
			bottomWall.setBackground(Color.BLACK);
			cellPanel.add(bottomWall);
		}
		// LEFT
		if (cell.walls[2]) {
			JPanel leftWall = new JPanel();
			leftWall.setBounds(0, 1 * pixelSize, pixelSize, pixelSize);
			leftWall.setBackground(Color.BLACK);
			cellPanel.add(leftWall);
		}
		// RIGHT
		if (cell.walls[3]) {
			JPanel rightWall = new JPanel();
			rightWall.setBounds(2 * pixelSize, 1 * pixelSize, pixelSize, pixelSize);
			rightWall.setBackground(Color.BLACK);
			cellPanel.add(rightWall);
		}

		mainPanel.add(cellPanel);

	}

}
