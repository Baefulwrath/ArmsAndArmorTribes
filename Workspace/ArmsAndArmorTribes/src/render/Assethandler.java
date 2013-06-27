package render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import ui.ButtonStyle;
import world.Climate;
import world.Terrain;
import world.Worldhandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Assethandler {
	
	public static HashMap<Integer, Sprite> terrainImages = new HashMap<Integer, Sprite>();
	public static HashMap<Integer, Sprite> climateImages = new HashMap<Integer, Sprite>();
    public static ArrayList<Terrain> terrains = new ArrayList<Terrain>();
	public static ArrayList<Climate> climates = new ArrayList<Climate>();
	public static Texture terrainmap;
	public static Texture climatemap;
	
    private static BitmapFont com64;
    private static BitmapFont com32;
    private static BitmapFont com16;
    private static BitmapFont com10;
    private static BitmapFont com32_BI;
    private static BitmapFont com16_BI;
    private static BitmapFont com10_BI;
    
    public static LabelStyle messageLabelStyle;
    public static LabelStyle logoLabelStyle;
    public static LabelStyle debugLabelStyle;
    public static LabelStyle basicLabelStyle;
    public static LabelStyle titleLabelStyle;
    public static LabelStyle warningLabelStyle;

	public static NinePatch basicButton_u;
	public static NinePatch basicButton_h;
	public static NinePatch basicButton_d;
	
	public static NinePatch basicHud;
	public static NinePatch brushImg;
	public static NinePatch windowPatch;
	
	public static ButtonStyle basicButtonStyle;
	
	public static Sprite basicExitButton;
	
    public static void load(){
    	try{
            com64 = new BitmapFont(Gdx.files.internal("data/fonts/com64.fnt"), Gdx.files.internal("data/fonts/com64.png"), false, false);
            com32 = new BitmapFont(Gdx.files.internal("data/fonts/com32.fnt"), Gdx.files.internal("data/fonts/com32.png"), false, false);
            com16 = new BitmapFont(Gdx.files.internal("data/fonts/com16.fnt"), Gdx.files.internal("data/fonts/com16.png"), false, false);
            com10 = new BitmapFont(Gdx.files.internal("data/fonts/com10.fnt"), Gdx.files.internal("data/fonts/com10.png"), false, false);
            com32_BI = new BitmapFont(Gdx.files.internal("data/fonts/com32_BI.fnt"), Gdx.files.internal("data/fonts/com32_BI.png"), false, false);
            com16_BI = new BitmapFont(Gdx.files.internal("data/fonts/com16_BI.fnt"), Gdx.files.internal("data/fonts/com16_BI.png"), false, false);
            com10_BI = new BitmapFont(Gdx.files.internal("data/fonts/com10_BI.fnt"), Gdx.files.internal("data/fonts/com10_BI.png"), false, false);
        	
            messageLabelStyle = new LabelStyle(com10, Color.CYAN);
            logoLabelStyle = new LabelStyle(com64, Color.WHITE);
            debugLabelStyle = new LabelStyle(com10, Color.RED);
            titleLabelStyle = new LabelStyle(com32_BI, Color.WHITE);
            basicLabelStyle = new LabelStyle(com10, Color.WHITE);
            warningLabelStyle = new LabelStyle(com16, Color.RED);

            basicButton_u = parsePatch(Gdx.files.internal("data/images/ninepatches/basicButton_u_p.txt").readString(), new Texture(Gdx.files.internal("data/images/ninepatches/basicButton_u.png")));
            basicButton_h = parsePatch(Gdx.files.internal("data/images/ninepatches/basicButton_h_p.txt").readString(), new Texture(Gdx.files.internal("data/images/ninepatches/basicButton_h.png")));
            basicButton_d = parsePatch(Gdx.files.internal("data/images/ninepatches/basicButton_d_p.txt").readString(), new Texture(Gdx.files.internal("data/images/ninepatches/basicButton_d.png")));
            
            basicHud = parsePatch(Gdx.files.internal("data/images/ninepatches/basicHud_p.txt").readString(), new Texture(Gdx.files.internal("data/images/ninepatches/basicHud.png")));
            brushImg = parsePatch(Gdx.files.internal("data/images/ninepatches/brushImg_p.txt").readString(), new Texture(Gdx.files.internal("data/images/ninepatches/brushImg.png")));
            windowPatch = parsePatch(Gdx.files.internal("data/images/ninepatches/windowPatch_p.txt").readString(), new Texture(Gdx.files.internal("data/images/ninepatches/windowPatch.png")));
            
            basicButtonStyle = new ButtonStyle(basicButton_u, basicButton_h, basicButton_d, basicLabelStyle);

            basicExitButton = new Sprite(new Texture(Gdx.files.internal("data/images/basicExitButton.png")));

        	loadTerrains();
        	loadClimates();
        	loadTerrainImages();
        	loadClimateImages();
    	}catch(Exception ex){
    		ex.printStackTrace(System.out);
    	}
    }
    
    public static BitmapFont parseFont(String s){
    	BitmapFont font = new BitmapFont();
    	switch(s){
	    	case "com10": font = com10;
	    		break;
	    	case "com16": font = com16;
    			break;
	    	case "com32": font = com32;
    			break;
	    	case "com64": font = com64;
    			break;
	    	case "com32_BI": font = com32_BI;
				break;
	    	case "com16_BI": font = com16_BI;
				break;
	    	case "com10_BI": font = com10_BI;
				break;
    	}
    	return font;
    }
    
    public static void dispose(){
    	
    }
    
    public static NinePatch parsePatch(String info, Texture tex){
    	NinePatch NP;
    	int left = Integer.parseInt(info.substring(0, info.indexOf(",")));
    	info = info.substring(info.indexOf(",") + 1);
    	int right = Integer.parseInt(info.substring(0, info.indexOf(",")));
    	info = info.substring(info.indexOf(",") + 1);
    	int top = Integer.parseInt(info.substring(0, info.indexOf(",")));
    	int bottom = Integer.parseInt(info.substring(info.indexOf(",") + 1));
    	NP = new NinePatch(tex, left, right, top, bottom);
    	return NP;
    }
    
    public static void loadTerrainImages(){
    	try{
    		terrainImages.clear();
    		terrainmap = new Texture(Gdx.files.internal("data/images/terrainmap.png"));
    		int length = terrainmap.getWidth() / Worldhandler.hexDiameter;
    		for(int i = 0; i < length; i++){
    			TextureRegion img = new TextureRegion(terrainmap, Worldhandler.hexDiameter * i, 0, Worldhandler.hexDiameter, Worldhandler.hexDiameter);
    			Sprite sprite = new Sprite(img);
    			terrainImages.put(i, sprite);
    		}
    	}catch(Exception ex){}
    }
    
    public static void loadClimateImages(){
    	try{
    		climateImages.clear();
    		climatemap = new Texture(Gdx.files.internal("data/images/climatemap.png"));
    		int length = climatemap.getWidth() / Worldhandler.hexDiameter;
    		for(int i = 0; i < length; i++){
    			TextureRegion img = new TextureRegion(climatemap, Worldhandler.hexDiameter * i, 0, Worldhandler.hexDiameter, Worldhandler.hexDiameter);
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
    
}


