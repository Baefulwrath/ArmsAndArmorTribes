package ui.menus;

import java.awt.Rectangle;

import render.Assethandler;
import arms.State;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import editor.Editorhandler;

import ui.Button;
import ui.Menu;
import ui.UIhandler;

public class Menu_TestMenu extends Menu{
	
	public Menu_TestMenu(String id){
		super(id);
		set("Test Menu", State.DEFAULT, true, 1.0f, true, true, 0, 0);
	}
	
	@Override
	public void setup(){
		addLabel("LABELTEST", Assethandler.messageLabelStyle, -200, 50);
		addImage(Editorhandler.brush.getClimateSprite(), 0, -100, 64, 64);
		addButton("Test Button", "", 0, 100, 150, 32, Assethandler.basicButtonStyle);
	}
	
}
