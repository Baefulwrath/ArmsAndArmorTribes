package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import scripting.Scripthandler;
import ui.menus.*;

import arms.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import arms.AAAT;

public class UIhandler {
	public static HashMap<String, Menu> menus = new HashMap<String, Menu>();
	public static String activeMenu = "";
	
	public static void update(){
    	for(Map.Entry<String, Menu> entry : menus.entrySet()){
    		menus.get(entry.getKey()).update();
    	}
	}
	
	public static void loadMenus(){
		menus.put("DEFAULT_testmenu", new Menu_TestMenu());
		menus.put("MENU_mainmenu", new Menu_MainMenu());
		//loadMenusFromFolder();
		resetActiveMenu(AAAT.state);
	}
	
	public static void resetActiveMenu(State s){
    	for(Map.Entry<String, Menu> entry : menus.entrySet()){
    		if(menus.get(entry.getKey()).STATE == s){
    			activeMenu = entry.getKey();
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
                    menus.put(m.ID, m);
                }else{
                	System.out.println("Non-txt in index of menus");
                }
            }
        }else{
        	System.out.println("NO MENUS TO LOAD");
        }
	}
	
	public static Menu getMenu(){
		return menus.get(activeMenu);
	}
	
	public static void activateButton(){
    	for(Map.Entry<String, Menu> entry : menus.entrySet()){
	   		Menu m = menus.get(entry.getKey());
	   		m.update();
	   		for(int i = 0; i < m.buttons.size(); i++){
	    		if(m.buttons.get(i).HOVER){
	    			m.buttons.get(i).ACTIVE = true;
	   			}else{
	    			m.buttons.get(i).ACTIVE = false;
	   			}
	  		}
	    }
	}
	
	public static void reset(){
    	for(Map.Entry<String, Menu> entry : menus.entrySet()){
    		Menu m = menus.get(entry.getKey());
    		if(m.MAIN && m.STATE == AAAT.state){
    			activeMenu = entry.getKey();
    			break;
    		}
		}
	}

	public static void unblockAll() {
    	for(Map.Entry<String, Menu> entry : menus.entrySet()){
    		Menu m = menus.get(entry.getKey());
    		for(int i = 0; i < m.buttons.size(); i++){
    			m.buttons.get(i).unblock();
    		}
		}
	}

	public static void blockAll() {
    	for(Map.Entry<String, Menu> entry : menus.entrySet()){
    		Menu m = menus.get(entry.getKey());
    		for(int i = 0; i < m.buttons.size(); i++){
    			m.buttons.get(i).block();
    		}
		}
	}

	public static void deactivateAll() {
    	for(Map.Entry<String, Menu> entry : menus.entrySet()){
    		Menu m = menus.get(entry.getKey());
    		for(int i = 0; i < m.buttons.size(); i++){
    			m.buttons.get(i).ACTIVE = false;
    		}
		}
	}
	
}
