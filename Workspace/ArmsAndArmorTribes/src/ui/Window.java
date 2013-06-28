package ui;

import java.awt.Rectangle;

import render.Assethandler;
import scripting.Scripthandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Window extends Menu{
	public Rectangle BOX = new Rectangle(0, 0, 100, 50);
	public WindowType TYPE = WindowType.DEFAULT;
	public String MESSAGE = "MESSAGE UNSET";
	public String TITLE = "TITLE UNSET";
	public String INPUT = "INPUT NOT RESET";
	public String SCRIPT = "print_ScriptNotReset";
	
	public Window(int x, int y, int w, int h, String id){
		super(id);
		BOX = new Rectangle(x, y, w, h);
		RENDERTITLE = false;
		NEXT = new Button("Next", "activateCurrentWindowInput_", new Rectangle(x + w - 100, y, 100, 32), Assethandler.basicButtonStyle);
	}
	
	public void setWindow(String message, String title, String defVal, WindowType type){
		MESSAGE = message;
		TITLE = title;
		TYPE = type;
		INPUT = defVal;
	}
	
	public void input(char in){
		switch(TYPE){
			case DEFAULT:
				INPUT += in;
				break;
			case TEXTINPUT:
				INPUT += in;
				break;
			case NUMBERINPUT:
				if(Character.isDigit(in)){
					INPUT += in;
				}
				break;
		}
	}
	
	public void rmChar(){
		if(INPUT.length() > 0){
			INPUT = INPUT.substring(0, INPUT.length() - 1);
		}
	}
	
	public void activateInput(){
		setupActivation();
		Scripthandler.handleScript(SCRIPT);
	}
	
	public void setupActivation(){}
	
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
			case NUMBERINPUT:
				temp = true;
				break;
			case TEXTINPUT:
				temp = true;
				break;
		}
		return temp;
	}
	
	public boolean acceptsKeyboardInput(){
		boolean temp = false;
		switch(TYPE){
			case DEFAULT:
				temp = true;
				break;
			case NUMBERINPUT:
				temp = true;
				break;
			case TEXTINPUT:
				temp = true;
				break;
		}
		return temp;
	}

	public float getMessageY() {
		int temp = BOX.y + (BOX.height / 4) * 3;
		return temp;
	}

	public float getInputY() {
		int temp = BOX.y + (BOX.height / 4);
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
