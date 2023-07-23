package fvr.view;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fvr.model.Cell;

public class MainWindow extends JFrame{
	
	public JPanel mainPanel;
	public int pxSize = 5;
	
	public int _rows, _cols;

    public MainWindow() {

        setTitle("MazeGenerator");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(null);
        
        //COMPONENTS
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 500, 500);
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setLayout(null);
        add(mainPanel);
        
        
        setVisible(true);
    }
    
    public void drawMaze(int _rows, int _cols, Cell[] _cells) {
    	
    	this._rows = _rows;
    	this._cols = _cols;
    	
    	int width = (_cols * pxSize * 3) - ((_cols - 1) * pxSize);
    	int height = (_rows * pxSize * 3) - ((_rows - 1) * pxSize);
    	
    	mainPanel.setSize(width, height);
    	getContentPane().setPreferredSize(new Dimension(width, height));
		pack();
    	
    	for (int _row = 0; _row < _rows; _row++) {
    		
    		for (int _col = 0; _col < _cols; _col++) {
        		
    			int _listIndex = (_row * _rows) + _col;
    			
    			
    			
    			drawCell(_cells[_listIndex], _row, _col);
        		
        	}
    		
    	}
    	
    	mainPanel.repaint();
    	
    	
    }
    
    public void drawMaze(int[][] _mazeMatrix, Cell[] _cells) {
    	
    	this._rows = _mazeMatrix.length;
    	this._cols = _mazeMatrix[0].length;
    	
    	int width = (_mazeMatrix[0].length * pxSize * 3) - ((_mazeMatrix[0].length - 1) * pxSize);
    	int height = (_mazeMatrix.length * pxSize * 3) - ((_mazeMatrix.length - 1) * pxSize);
    	
    	mainPanel.setSize(width, height);
    	getContentPane().setPreferredSize(new Dimension(width, height));
		pack();
    	
    	for (int _row = 0; _row < _mazeMatrix.length; _row++) {
    		
    		for (int _col = 0; _col < _mazeMatrix[0].length; _col++) {
        		
    			int _listIndex = (_row * _mazeMatrix.length) + _col;
    			
    			drawCell(_cells[_listIndex], _row, _col);
    			
        		
        	}
    		
    	}
    	
    	mainPanel.repaint();
    	
    	
    }
    
    public void drawCell(Cell _cell, int _row, int _col) {
    	
    	JPanel bgPanel = new JPanel();
    	bgPanel.setSize(pxSize * 3, pxSize * 3);
    	bgPanel.setLocation(_col * (pxSize * 2), _row * (pxSize * 2));
    	bgPanel.setLayout(null);
    	
    	JPanel topLeftCorner = new JPanel();
    	topLeftCorner.setBounds(0, 0, pxSize, pxSize);
    	topLeftCorner.setBackground(Color.BLACK);
    	bgPanel.add(topLeftCorner);
    	
    	JPanel topRightCorner = new JPanel();
    	topRightCorner.setBounds(2 * pxSize, 0, pxSize, pxSize);
    	topRightCorner.setBackground(Color.BLACK);
    	bgPanel.add(topRightCorner);
    	
    	JPanel bottomLeftCorner = new JPanel();
    	bottomLeftCorner.setBounds(0, 2 * pxSize, pxSize, pxSize);
    	bottomLeftCorner.setBackground(Color.BLACK);
    	bgPanel.add(bottomLeftCorner);
    	
    	JPanel bottomRightCorner = new JPanel();
    	bottomRightCorner.setBounds(2 * pxSize, 2 * pxSize, pxSize, pxSize);
    	bottomRightCorner.setBackground(Color.BLACK);
    	bgPanel.add(bottomRightCorner);
    	
    	JPanel center = new JPanel();
    	center.setBounds(pxSize, pxSize, pxSize, pxSize);
    	
//    	if(_row == 0 && _col == 0) {
//    		center.setBackground(Color.GREEN);
//    	}
//    	if(_row == (_rows - 1) && _col == (_cols - 1)) {
//    		center.setBackground(Color.RED);
//    	}
    	
//    	if(!(_row == 0 && _col == 1)) {
//    		center.setBackground(Color.RED);
//    	}
    	bgPanel.add(center);
    	
    	//TOP
    	if(_cell._walls[0]) {
    		JPanel topWall = new JPanel();
        	topWall.setBounds(1 * pxSize, 0, pxSize, pxSize);
        	topWall.setBackground(Color.BLACK);
        	bgPanel.add(topWall);
    	}
    	//BOTTOM
    	if(_cell._walls[1]) {
    		JPanel bottomWall = new JPanel();
    		bottomWall.setBounds(1 * pxSize, 2 * pxSize, pxSize, pxSize);
    		bottomWall.setBackground(Color.BLACK);
    		bgPanel.add(bottomWall);
    	}
    	//LEFT
    	if(_cell._walls[2]) {
    		JPanel leftWall = new JPanel();
    		leftWall.setBounds(0, 1 * pxSize, pxSize, pxSize);
    		leftWall.setBackground(Color.BLACK);
    		bgPanel.add(leftWall);
    	}
    	//RIGHT
    	if(_cell._walls[3]) {
    		JPanel rightWall = new JPanel();
    		rightWall.setBounds(2 * pxSize, 1 * pxSize, pxSize, pxSize);
    		rightWall.setBackground(Color.BLACK);
    		bgPanel.add(rightWall);
    	}
    	
    	mainPanel.add(bgPanel);
//    	System.out.println("(" + _row +", " + _col +"): " + Arrays.toString(_cell.get_walls()));
    	
    	
    }

}


