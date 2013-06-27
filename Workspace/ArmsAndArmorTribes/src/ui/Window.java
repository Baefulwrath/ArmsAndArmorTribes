package ui;

import java.awt.Rectangle;

import render.Assethandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Window extends Menu{
	public Rectangle BOX = new Rectangle(0, 0, 100, 50);
	public WindowType TYPE = WindowType.DEFAULT;
	public String MESSAGE = "MESSAGE UNSET";
	public String TITLE = "TITLE UNSET";
	
	public Window(int x, int y, int w, int h){
		BOX = new Rectangle(x, y, w, h);
		RENDERTITLE = false;
	}
	
	public void set(String message, String title, WindowType type){
		MESSAGE = message;
		TITLE = title;
	}
	
	public boolean closable(){
		boolean temp = false;
		switch(TYPE){
		case DEFAULT:
			temp = true;
			break;
		case MESSAGE:
			temp = true;
			break;
		case WARNING:
			temp = true;
			break;
		default:
			break;
		}
		return temp;
	}

	
	public float getMessageY() {
		int temp = BOX.y + (BOX.height / 4) * 3;
		return temp;
	}

	public LabelStyle getLabelStyle() {
		LabelStyle temp = Assethandler.basicLabelStyle;
		switch(TYPE){
			case MESSAGE:
				temp = Assethandler.messageLabelStyle;
				break;
			case TEXTINPUT:
				temp = Assethandler.messageLabelStyle;
				break;
			case NUMBERINPUT:
				temp = Assethandler.messageLabelStyle;
				break;
			case WARNING:
				temp = Assethandler.warningLabelStyle;
				break;
		}
		return temp;
	}
	
	public Color getTint() {
		Color c = Color.WHITE;
		switch(TYPE){
		case MESSAGE:
			c = Color.CYAN;
			break;
		case WARNING:
			c = Color.RED;
			break;
	}
		return c;
	}
}
