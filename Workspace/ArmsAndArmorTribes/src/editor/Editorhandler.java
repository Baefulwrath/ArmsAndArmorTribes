package editor;

import input.Inputhandler;
import world.GameMap;
import world.Worldhandler;

public class Editorhandler {
	
	public static Brush brush;
	public static int newMapW = 12;
	public static int newMapH = 12;
	public static GameMap map = new GameMap(newMapW, newMapH, Worldhandler.hexWidth, Worldhandler.hexDiameter);	
	
	public static void setup(){
		brush = new Brush(Worldhandler.hexWidth, Worldhandler.hexDiameter, 0, 0);
	}
	
	public static void update(){
		brush.update(Inputhandler.mouse.x, Inputhandler.mouse.y);
		map.update();
	}
}
