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
	
	public Menu_TestMenu(){
		set("DEFAULT_testmenu", "Test Menu", State.DEFAULT, true, 1.0f, true, true, 0, 0);
	}
	
	@Override
	public void setup(){
        Label l1 = new Label("LABELTEST", Assethandler.messageLabelStyle);
        l1.setPosition(-200, 50);
		labels.add(l1);
		Image img1 = new Image(new TextureRegion(Editorhandler.brush.getClimateSprite()));
		img1.setBounds(0, -100, 64, 64);
		images.add(img1);
		buttons.add(new Button("Test Button", "", new Rectangle(0, 100, 150, 32), Assethandler.basicButtonStyle));
	}
	
}
