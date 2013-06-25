package ui;

import java.awt.Rectangle;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
<<<<<<< HEAD
import render.UIAssethandler;
=======
import render.Assethandler;
>>>>>>> 01e4ed344755e25614f4f935ece973b2258c55c2
import scripting.Scripthandler;

public class Button {
	
	public boolean HOVER = false;
	public boolean ACTIVE = false;
	public boolean BLOCKED = false;
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
	
	public void unblock(){
		BLOCKED = false;
	}
	public void block(){
		BLOCKED = true;
	}
	
	public void update(){
		if(ACTIVE && !BLOCKED){
			Scripthandler.handleScript(SCRIPT);
			block();
		}else if(!ACTIVE){
			unblock();
		}
	}
	
	public LabelStyle getLabelStyle(){
		return STYLE.LABELSTYLE;
	}
	
	public NinePatch getTex(){
		NinePatch n;
		if(ACTIVE){
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
		Scripthandler.handleScript(SCRIPT);
	}
	
}
