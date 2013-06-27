package world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import render.Renderinghandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Worldhandler {
    public static ArrayList<GameMap> maps = new ArrayList<GameMap>();
	public static Sprite gridImg;
	public static int activeMap = 0;
    public static int mapSpeedBase = 16;
    public static int mapSpeed = 32;
    public static int hexDiameter = 128;
    public static int hexWidth = 110;
	public static boolean showGrid = true;

	public static boolean moveUp = false;
	public static boolean moveDown = false;
	public static boolean moveLeft = false;
	public static boolean moveRight = false;
	private static long lastMovement = 0;
	private static long movementInterval = 25;

    
	public static void update(){
		if(readyToMove()){
			move();
		}
		getMap().update();
		mapSpeed = (int) (mapSpeedBase * Renderinghandler.getZoomScale());
	}
    
    public static void load(){
    	loadMaps();
    	gridImg = new Sprite(new Texture(Gdx.files.internal("data/images/grid.png")));
    }
    
    public static void loadMaps(){
    	maps.clear();
    	String index = Gdx.files.internal("data/content/maps/INDEX.txt").readString();
    	while(index.contains(":")){
    		String file = "data/content/maps/" + index.substring(1, index.indexOf(';'));
    		GameMap map = new GameMap(file, hexWidth, hexDiameter);
    		map.X = getCentralMapX(map);
    		map.Y = getCentralMapY(map);
    		maps.add(map);
    		index = index.substring(index.indexOf(';') + 1);
    	}
    }
	
    public static GameMap getMap(){
        return maps.get(activeMap);
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
			getMap().Y += mapSpeed;
		}else if(moveDown){
			getMap().Y -= mapSpeed;
		}
		if(moveLeft){
			getMap().X -= mapSpeed;
		}else if(moveRight){
			getMap().X += mapSpeed;
		}
	}
    
    public static int getCentralMapX(GameMap map){
        float x = - map.getWidth() / 2;
        return (int) x;
    }
    
    public static int getCentralMapY(GameMap map){
        float y = map.getHeight() / 2;
        return (int) y;
    }
	
}
