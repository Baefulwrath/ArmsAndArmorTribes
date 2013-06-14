package ui.menus;

import render.UIAssethandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import editor.Editorhandler;

import ui.Menu;

public class Menu_MainMenu extends Menu{
	
	@Override
	public void setup(){
        Label l1 = new Label("LABELTEST", UIAssethandler.messageLabelStyle);
        l1.setPosition(0, 0);
		labels.add(l1);
		Image img1 = new Image(new TextureRegion(Editorhandler.brush.getClimateSprite()));
		img1.setBounds(0, -100, 64, 64);
		images.add(img1);
		TextButton tb1 = new TextButton("test textButton", UIAssethandler.basicTextbuttonStyle);
		tb1.setBounds(0, 100, 150, 32);
		textButtons.add(tb1);
	}
	
}
