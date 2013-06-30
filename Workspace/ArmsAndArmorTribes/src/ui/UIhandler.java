package ui;

import input.Inputhandler;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import scripting.Scripthandler;
import ui.menus.*;
import ui.windows.*;

import arms.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import arms.AAAT;

public class UIhandler {
	public static HashMap<String, Menu> mainMenus = new HashMap<String, Menu>();
	public static HashMap<String, Menu> insideMenus = new HashMap<String, Menu>();
	public static ArrayList<Message> messages = new ArrayList<Message>();
	public static HashMap<String, Window> windows = new HashMap<String, Window>();
	public static String activeMainMenu = "";
	public static String activeWindow = "";
	public static boolean showWindow = false;
    private static long lastUpdate = 0;
    private static int updateInterval = 25;
    
    public static void setup(){
    	load();
    	updateMenus();
    	updateWindows();
    }
	
	public static void update(){
		if(readyToUpdate()){
			if(showWindow){
				unhoverAll();
			}
			systemUpdateWindows();
			systemUpdateMenus();
	    	updateMessages();
		}
	}
	
	public static void systemUpdateMenus(){
	    for(Map.Entry<String, Menu> entry : mainMenus.entrySet()){
	   		mainMenus.get(entry.getKey()).systemUpdate(getIfActiveMenu(entry.getKey(), true));
	   	}
	   	for(Map.Entry<String, Menu> entry : insideMenus.entrySet()){
	   		insideMenus.get(entry.getKey()).systemUpdate(getIfActiveMenu(entry.getKey(), false));
    	}
	}
	
	public static void systemUpdateWindows(){
	    getWindow().systemUpdate(true);
	}
	
	private static void unhoverAll() {
    	for(Map.Entry<String, Menu> entry : mainMenus.entrySet()){
    		mainMenus.get(entry.getKey()).unhoverAll();
    	}
    	for(Map.Entry<String, Menu> entry : insideMenus.entrySet()){
    		insideMenus.get(entry.getKey()).unhoverAll();
    	}
	}

	public static void updateMessages(){
    	for(int i = 0; i < messages.size(); i++){
    		Message m = messages.get(i);
    		if(m.creationTime + m.LIFETIME <= System.currentTimeMillis()){
    			//System.out.println("--<" + m.TEXT + ">--");
    			messages.remove(i);
    			updateMessages();
    			break;
    		}
    	}
	}
	
	public static void updateMenus(){
    	for(Map.Entry<String, Menu> entry : mainMenus.entrySet()){
    		mainMenus.get(entry.getKey()).update(getIfActiveMenu(entry.getKey(), true));
    	}
    	for(Map.Entry<String, Menu> entry : insideMenus.entrySet()){
    		insideMenus.get(entry.getKey()).update(getIfActiveMenu(entry.getKey(), false));
    	}
	}
	
	public static void updateWindows(){
	    for(Map.Entry<String, Window> entry : windows.entrySet()){
	    	windows.get(entry.getKey()).update(true);
	   	}
	}
    
    public static boolean readyToUpdate(){
    	boolean temp = false;
    	if(lastUpdate + updateInterval <= System.currentTimeMillis()){
    		temp = true;
    		lastUpdate = System.currentTimeMillis();
    	}
    	return temp;
    }
	
	public static void load(){
		mainMenus.put("DEFAULT_testmenu", new Menu_TestMenu("DEFAULT_testmenu"));
		mainMenus.put("MENU_mainmenu", new Menu_MainMenu("MENU_mainmenu"));
		
		insideMenus.put("EDITOR_Hud", new Editor_Hud("EDITOR_Hud"));
		
		windows.put("test", new Window_Test("test"));
		windows.put("changebrushclimate", new Window_ChangeBrushClimate("changebrushclimate"));
		windows.put("changebrushterrain", new Window_ChangeBrushTerrain("changebrushterrain"));
		//loadMenusFromFolder();
		activeWindow = "test";
		resetActiveMenu(AAAT.state);
	}
	
	public static void resetActiveMenu(State s){
    	for(Map.Entry<String, Menu> entry : mainMenus.entrySet()){
    		if(mainMenus.get(entry.getKey()).STATE == s){
    			activeMainMenu = entry.getKey();
    			break;
    		}
    	}
	}
	
	private static void loadMenusFromFolder(){
		String path = "data/content/menus/";
    	String index = Gdx.files.internal(path + "INDEX.txt").readString();
    	ArrayList<String> files = new ArrayList<String>();
    	while(index.contains(":")){
    		files.add(index.substring(1, index.indexOf(";")));
    		index = index.substring(index.indexOf(";") + 1);
    	}
        if(files.size() > 0){
            for(int i = 0; i < files.size(); i++){
                FileHandle file = Gdx.files.internal(path + files.get(i));
                if(file.extension().equals("txt")){
                	String s = file.readString();
                    Menu m = Menu.parseMenu(s);
                    mainMenus.put(m.ID, m);
                }else{
                	System.out.println("Non-txt in index of menus");
                }
            }
        }else{
        	System.out.println("NO MENUS TO LOAD");
        }
	}
	
	public static Menu getMenu(){
		return mainMenus.get(activeMainMenu);
	}
	
	public static Window getWindow(){
		//if(showWindow){
			return windows.get(activeWindow);
		/*}else{
			return new Window_NOID();
		}*/
	}
	
	public static Window getWindow(String id){
		//if(showWindow){
			return windows.get(id);
		/*}else{
			return new Window_NOID();
		}*/
	}
	
	public static ArrayList<Menu> getInsideMenus(){
		ArrayList<Menu> m = new ArrayList<Menu>();
    	for(Map.Entry<String, Menu> entry : insideMenus.entrySet()){
			if(insideMenus.get(entry.getKey()).STATE == AAAT.state){
				m.add(insideMenus.get(entry.getKey()));
			}
		}
    	return m;
	}
	
	public static ArrayList<Menu> getInsideMenus(State s){
		ArrayList<Menu> m = new ArrayList<Menu>();
    	for(Map.Entry<String, Menu> entry : insideMenus.entrySet()){
			if(insideMenus.get(entry.getKey()).STATE == s){
				m.add(insideMenus.get(entry.getKey()));
			}
		}
    	return m;
	}
	
	public static void activateMainButton(){
    	for(Map.Entry<String, Menu> entry : mainMenus.entrySet()){
	   		Menu m = mainMenus.get(entry.getKey());
	   		for(int i = 0; i < m.buttons.size(); i++){
	    		m.buttons.get(i).ACTIVE = true;
	  		}
	   		update();
	    }
	}
	
	public static void activateInsideButton(){
    	for(Map.Entry<String, Menu> entry : insideMenus.entrySet()){
	   		Menu m = insideMenus.get(entry.getKey());
	   		for(int i = 0; i < m.buttons.size(); i++){
	    		m.buttons.get(i).ACTIVE = true;
	  		}
	   		update();
	    }
	}
	
	public static void activateWindowButton(){
    	for(Map.Entry<String, Window> entry : windows.entrySet()){
	   		Window m = windows.get(entry.getKey());
	   		for(int i = 0; i < m.buttons.size(); i++){
	    		m.buttons.get(i).ACTIVE = true;
	  		}
	   		m.NEXT.ACTIVE = true;
	   		update();
	    }
	}
	
	public static boolean getIfActiveMenu(String id, boolean main){
   		boolean active = false;
   		if(main){
	   		if(id.equals(activeMainMenu)){
	   			active = true;
	   		}
   		}else{
   			if(insideMenus.get(id).STATE == AAAT.state){
   				active = true;
   			}
   		}
   		return active;
	}
	
	public static boolean intersectsMenus(Rectangle r){
		boolean temp = false;
		if(intersectsInsideMenus(r) || intersectsMainMenus(r)){
			temp = true;
		}
    	return temp;
	}
	
	public static boolean intersectsMainMenus(Rectangle r){
		boolean temp = false;
    	for(Map.Entry<String, Menu> entry : mainMenus.entrySet()){
    		if(mainMenus.get(entry.getKey()).intersects(r)){
    			temp = true;
    		}
    	}
    	return temp;
	}
	
	public static boolean intersectsInsideMenus(Rectangle r){
		boolean temp = false;
    	for(Map.Entry<String, Menu> entry : insideMenus.entrySet()){
    		if(insideMenus.get(entry.getKey()).intersects(r)){
    			temp = true;
    		}
    	}
    	return temp;
	}
	
	public static void reset(){
    	for(Map.Entry<String, Menu> entry : mainMenus.entrySet()){
    		Menu m = mainMenus.get(entry.getKey());
    		if(m.MAIN && m.STATE == AAAT.state){
    			activeMainMenu = entry.getKey();
    			break;
    		}
		}
	}

	public static void process() {
	    for(Map.Entry<String, Menu> entry : mainMenus.entrySet()){
	    	mainMenus.get(entry.getKey()).process();
	    }
	    for(Map.Entry<String, Menu> entry : insideMenus.entrySet()){
	    	insideMenus.get(entry.getKey()).process();
	    }
   		updateMenus();
	    updateWindows();
	}

	public static void processWindow() {
	    for(Map.Entry<String, Window> entry : windows.entrySet()){
	    	windows.get(entry.getKey()).process();
	    }
   		updateMenus();
	    updateWindows();
	}
	
	public static void print(String s){
		System.out.println(s);
		messages.add(new Message(s, 4000));
	}
	
	public static void print(String s, int time){
		System.out.println(s);
		messages.add(new Message(s, time));
	}
	
}
