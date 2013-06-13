package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ui.menus.Menu_MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Menuhandler {
	public static HashMap<String, Menu> menus = new HashMap<String, Menu>();
	public static String activeMenu = "";
	
	public static void update(){
    	for(Map.Entry<String, Menu> entry : menus.entrySet()){
    		menus.get(entry.getKey()).update();
    	}
	}
	
	public static void loadMenus(){
		menus.put("MENU_mainmenu", new Menu_MainMenu());
		//loadMenusFromFolder();
		activeMenu = "MENU_mainmenu";
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
	
}
