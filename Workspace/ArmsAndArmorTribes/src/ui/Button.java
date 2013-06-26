package ui;

import input.Inputhandler;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import render.Assethandler;
import scripting.Scripthandler;

public class Button {
	
	public boolean HOVER = false;
	public boolean ACTIVE = false;
	public String TITLE = "";
	public String SCRIPT = "";
	public ButtonStyle STYLE;
	public Rectangle BOX = new Rectangle();
	public int TITLEX = 16;

	public Button(String text, String buttonScript, Rectangle locdim, ButtonStyle style) {
		TITLE = text;
		SCRIPT = buttonScript;
		BOX = locdim;
		STYLE = style;
	}
	
	public void update(boolean active){
	}
	
	public boolean intersects(Rectangle r) {
		boolean temp = false;
		if(BOX.intersects(r)){
			temp = true;
		}
		return temp;
	}

	public LabelStyle getLabelStyle(){
		return STYLE.LABELSTYLE;
	}
	
	public NinePatch getTex(){
		NinePatch n;
		if(ACTIVE && HOVER){
			n = STYLE.DOWN;
		}else if(HOVER){
			n = STYLE.HOVER;
		}else{
			n = STYLE.UP;
		}
		return n;
	}
	
	public int getTextY(){
		int temp = (int) ((BOX.height / 2) + (STYLE.LABELSTYLE.font.getCapHeight() / 2));
		return temp;
	}
	
	public void activate(){
		if(ACTIVE && HOVER){
			Scripthandler.handleScript(SCRIPT);
		}
		ACTIVE = false;
	}

	public void systemUpdate(boolean active) {
		if(intersects(Inputhandler.staticMouse) && active){
			HOVER = true;
		}else{
			HOVER = false;
		}
	}
	
}
