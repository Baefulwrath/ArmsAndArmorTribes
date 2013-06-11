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
	public static HashMap<Integer, Sprite> terrainImages = new HashMap<Integer, Sprite>();
	public static HashMap<Integer, Sprite> climateImages = new HashMap<Integer, Sprite>();
    public static ArrayList<Terrain> terrains = new ArrayList<Terrain>();
	public static ArrayList<Climate> climates = new ArrayList<Climate>();
	public static Texture terrainmap;
	public static Texture climatemap;
	public static Sprite gridImg;
	public static int activeMap = 0;
    public static int mapSpeed = 32;
    public static int hexDiameter = 128;
    public static int hexWidth = 110;
	public static boolean showGrid = true;

	public static boolean moveUp = false;
	public static boolean moveDown = false;
	public static boolean moveLeft = false;
	public static boolean moveRight = false;
	private static long lastMovement = 0;
	private static long movementInterval = 50;

    
	public static void update(){
		if(readyToMove()){
			move();
		}
		getMap().update();
	}
    
    public static void load(){
    	loadTerrains();
    	loadClimates();
    	loadTerrainImages();
    	loadClimateImages();
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
    
    public static void loadTerrainImages(){
    	try{
    		terrainImages.clear();
    		terrainmap = new Texture(Gdx.files.internal("data/images/terrainmap.png"));
    		int length = terrainmap.getWidth() / hexDiameter;
    		for(int i = 0; i < length; i++){
    			TextureRegion img = new TextureRegion(terrainmap, hexDiameter * i, 0, hexDiameter, hexDiameter);
    			Sprite sprite = new Sprite(img);
    			terrainImages.put(i, sprite);
    		}
    	}catch(Exception ex){}
    }
    
    public static void loadClimateImages(){
    	try{
    		climateImages.clear();
    		climatemap = new Texture(Gdx.files.internal("data/images/climatemap.png"));
    		int length = climatemap.getWidth() / hexDiameter;
    		for(int i = 0; i < length; i++){
    			TextureRegion img = new TextureRegion(climatemap, hexDiameter * i, 0, hexDiameter, hexDiameter);
    			Sprite sprite = new Sprite(img);
    			climateImages.put(i, sprite);
    		}
    	}catch(Exception ex){}
    }
    
    public static void loadTerrains(){
    	try{
    		terrains.clear();
    		Scanner reader = new Scanner(Gdx.files.internal("data/content/terrains.txt").readString());
    		while(reader.hasNextLine()){
    			reader.nextLine();
    			String terrain = reader.nextLine();
    			String climate = reader.nextLine();
    			String title = reader.nextLine();
    			int id = Integer.parseInt(reader.nextLine());
    			terrains.add(new Terrain(terrain, climate, title, id));
    		}
    		reader.close();
    	}catch(Exception ex){ex.printStackTrace();}
    }
    
    public static void loadClimates(){
    	try{
    		climates.clear();
    		Scanner reader = new Scanner(Gdx.files.internal("data/content/climates.txt").readString());
    		while(reader.hasNextLine()){
    			reader.nextLine();
    			String climate = reader.nextLine();
    			String title = reader.nextLine();
    			int id = Integer.parseInt(reader.nextLine());
    			climates.add(new Climate(climate, title, id));
    		}
    		reader.close();
    	}catch(Exception ex){ex.printStackTrace();}
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
    
    public static int getClimateIdByTerrain(int terrain){
    	int temp = 0;
    	for(int i = 0; i < climates.size(); i++){
    		if(terrains.get(terrain).CLIMATE.equals(climates.get(i).CLIMATE)){
    			temp = i;
    			break;
    		}
    	}
    	return temp;
    }
    
    public static Sprite getClimateImage(int id){
    	if(climateImages.containsKey(id)){
    		return climateImages.get(id);
    	}else{
    		return climateImages.get(0);
    	}
    }
    
    public static Sprite getTerrainImage(int id){
    	if(terrainImages.containsKey(id)){
    		return terrainImages.get(id);
    	}else{
    		return terrainImages.get(0);
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
