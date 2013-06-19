package ui.menus;

import render.UIAssethandler;
import arms.State;

import ui.Button;
import ui.Menu;

public class Menu_MainMenu extends Menu{
	
	public Menu_MainMenu(){
		set("MENU_mainmenu", "Main Menu", State.MENU, 1.0f, true, -300, 200);
	}
	
	@Override
	public void setup(){
		Button tb1 = new Button("Play", "", UIAssethandler.basicTextbuttonStyle);
		tb1.setBounds(-250, 120, 150, 32);
		textButtons.add(tb1);
		Button tb2 = new Button("Options", "", UIAssethandler.basicTextbuttonStyle);
		tb2.setBounds(-250, 80, 150, 32);
		textButtons.add(tb2);
		Button tb3 = new Button("Editor", "", UIAssethandler.basicTextbuttonStyle);
		tb3.setBounds(-250, 40, 150, 32);
		textButtons.add(tb3);
		Button tb4 = new Button("Exit", "", UIAssethandler.basicTextbuttonStyle);
		tb4.setBounds(-250, 0, 150, 32);
		textButtons.add(tb4);
	}
	
}
