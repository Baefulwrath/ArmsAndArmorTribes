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
		buttons.add(new Button("Play", "setState_GAME", new Rectangle(-250, 120, 150, 32), Assethandler.basicButtonStyle));
		buttons.add(new Button("Options", "print_Unavailable!", new Rectangle(-250, 80, 150, 32), Assethandler.basicButtonStyle));
		buttons.add(new Button("Editor", "setState_EDITOR", new Rectangle(-250, 40, 150, 32), Assethandler.basicButtonStyle));
		buttons.add(new Button("Exit", "exit_", new Rectangle(-250, 0, 150, 32), Assethandler.basicButtonStyle));
	}
	
}
