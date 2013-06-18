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

public class Menu_testMenu extends Menu{
	
	public Menu_testMenu(){
		set("DEFAULT_testmenu", "Test Menu", State.DEFAULT, 1.0f, true, 0, 0);
	}
	
	@Override
	public void setup(){
        Label l1 = new Label("LABELTEST", UIAssethandler.messageLabelStyle);
        l1.setPosition(-200, 50);
		labels.add(l1);
		Image img1 = new Image(new TextureRegion(Editorhandler.brush.getClimateSprite()));
		img1.setBounds(0, -100, 64, 64);
		images.add(img1);
		TextButton tb1 = new TextButton("test textButton", UIAssethandler.basicTextbuttonStyle);
		tb1.setBounds(0, 100, 150, 32);
		textButtons.add(tb1);
	}
	
}
