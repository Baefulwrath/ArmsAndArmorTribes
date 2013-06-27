package ui;

import java.awt.Rectangle;

import world.Cell;

public class CellImage extends Cell {
	
	public Rectangle BOX = new Rectangle();

	public CellImage(int width, int terrain, int climate, int x, int y, int w, int h) {
		super(width, terrain, climate);
		BOX = new Rectangle(x, y, w, h);
	}
	
}
