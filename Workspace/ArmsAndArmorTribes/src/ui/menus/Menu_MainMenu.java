package ui.menus;

import java.awt.Rectangle;

import render.Assethandler;
import arms.State;

import ui.Button;
import ui.Menu;

public class Menu_MainMenu extends Menu{
	
	public Menu_MainMenu(){
		set("MENU_mainmenu", "Main Menu", State.MENU, true, 1.0f, true, true, -300, 200);
	}
	
	@Override
	public void setup(){
		addButton("Play", "setState_GAME", -250, 120, 150, 32, Assethandler.basicButtonStyle);
		addButton("Options", "print_Unavailable!", -250, 80, 150, 32, Assethandler.basicButtonStyle);
		addButton("Editor", "setState_EDITOR", -250, 40, 150, 32, Assethandler.basicButtonStyle);
		addButton("Exit", "exit_", -250, 0, 150, 32, Assethandler.basicButtonStyle);
	}
	
}
