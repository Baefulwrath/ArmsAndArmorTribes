package ui.menus;

import render.UIAssethandler;
import arms.State;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import editor.Editorhandler;

import ui.Menu;

public class Menu_mainMenu extends Menu{
	
	public Menu_mainMenu(){
		set("MENU_mainmenu", "Main Menu", State.MENU, 1.0f, true, -300, 200);
	}
	
	@Override
	public void setup(){
		TextButton tb1 = new TextButton("Play", UIAssethandler.basicTextbuttonStyle);
		tb1.setBounds(-250, 120, 150, 32);
		textButtons.add(tb1);
		TextButton tb2 = new TextButton("Options", UIAssethandler.basicTextbuttonStyle);
		tb2.setBounds(-250, 80, 150, 32);
		textButtons.add(tb2);
		TextButton tb3 = new TextButton("Editor", UIAssethandler.basicTextbuttonStyle);
		tb3.setBounds(-250, 40, 150, 32);
		textButtons.add(tb3);
		TextButton tb4 = new TextButton("Exit", UIAssethandler.basicTextbuttonStyle);
		tb4.setBounds(-250, 0, 150, 32);
		textButtons.add(tb4);
	}
	
}
