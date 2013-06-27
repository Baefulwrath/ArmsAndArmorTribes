package editor;

import render.Renderinghandler;
import arms.*;
import input.Inputhandler;
import ui.UIhandler;
import world.Cell;
import world.GameMap;
import world.Worldhandler;

public class Editorhandler {
	
	public static Brush brush;
	public static int newMapW = 12;
	public static int newMapH = 12;
	public static GameMap map = new GameMap(newMapW, newMapH, Worldhandler.hexWidth, Worldhandler.hexDiameter);	
	public static boolean painting = false;
	public static boolean paused = false;
	
    public static int mapSpeed = 32;
    public static int mapSpeedBase = 16;
	public static boolean moveUp = false;
	public static boolean moveDown = false;
	public static boolean moveLeft = false;
	public static boolean moveRight = false;
	private static long lastMovement = 0;
	private static long movementInterval = 25;
	
	public static void setup(){
		brush = new Brush(Worldhandler.hexWidth, Worldhandler.hexDiameter, 1, 1);
	}
	
	public static void update(){
		brush.update(Inputhandler.mouse.x, Inputhandler.mouse.y);
		map.update();
		if(allowedToPaint()){
			paint();
		}
		if(readyToMove()){
			move();
		}
		mapSpeed = (int) (mapSpeedBase * Renderinghandler.getZoomScale());
	}
	
	public static boolean allowedToPaint(){
		boolean allowed = false;
		if(painting && AAAT.state == State.EDITOR){
			allowed = true;
		}
		if(UIhandler.intersectsInsideMenus(Inputhandler.staticMouse)){
			allowed = false;
		}
		return allowed;
	}
	
	public static void paint(){
		for(int x = 0; x < map.CELLS.length; x++){
			for(int y = 0; y < map.CELLS[x].length; y++){
				if(map.CELLS[x][y].intersects(brush.BOX, true)){
					map.CELLS[x][y].mirror(brush);
				}
			}
		}
	}
    
	public static boolean readyToMove(){
		boolean temp = false;
		if(lastMovement + movementInterval <= System.currentTimeMillis()){
			temp = true;
			lastMovement = System.currentTimeMillis();
		}
		return temp;
	}
	
	public static void move(){
		if(moveUp){
			map.Y += mapSpeed;
		}else if(moveDown){
			map.Y -= mapSpeed;
		}
		if(moveLeft){
			map.X -= mapSpeed;
		}else if(moveRight){
			map.X += mapSpeed;
		}
	}

	public static void changeBrushClimate() {
		
	}
}
