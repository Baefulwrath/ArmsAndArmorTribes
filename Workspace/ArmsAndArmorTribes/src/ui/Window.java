package ui;

import java.awt.Rectangle;

import render.Assethandler;
import scripting.Scripthandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public abstract class Window extends Menu{
	public Rectangle BOX = new Rectangle(0, 0, 100, 50);
	public WindowType TYPE = WindowType.DEFAULT;
	public String MESSAGE = "MESSAGE UNSET";
	public String TITLE = "TITLE UNSET";
	public String INPUT = "INPUT NOT RESET";
	public String SCRIPT = "print_ScriptNotReset";
	public boolean DRAWINPUT = true;
	
	public Window(int x, int y, int w, int h, String id){
		super(id);
		BOX = new Rectangle(x, y, w, h);
		RENDERTITLE = false;
		NEXT = new Button("Next", "activateCurrentWindowInput_", new Rectangle(x + w - 100, y, 100, 32), Assethandler.basicButtonStyle);
	}
	
	public void setWindow(String message, String title, String defVal, WindowType type, boolean drawInput){
		MESSAGE = message;
		TITLE = title;
		TYPE = type;
		INPUT = defVal;
		DRAWINPUT = drawInput;
	}
	
	public void input(char in){
		if(acceptsKeyboardInput()){
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
	}
	
	public void rmChar(){
		if(acceptsKeyboardInput()){
			if(INPUT.length() > 0){
				INPUT = INPUT.substring(0, INPUT.length() - 1);
			}
		}
	}
	
	public void activateInput(){
		setupActivation();
		Scripthandler.handleScript(SCRIPT);
		clearWindow();
		UIhandler.showWindow = false;
	}
	
	@Override
	public void update(boolean active){
		testActiveButton();
		clearReactiveObjects();
		setupReactiveObjects();
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).update(active);
		}
		NEXT.update(active);
	}
	
	@Override
	public void systemUpdate(boolean active) {
		NEXT.systemUpdate(active);
		clearStaticObjects();
		setupStaticObjects();
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).systemUpdate(active);
		}
		updateInput();
	}
	
	private void clearWindow() {
		INPUT = "";
	}

	public abstract void setupActivation();
	public abstract void updateInput();
	
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
		int temp = BOX.y + BOX.height - 50;
		return temp;
	}

	public float getInputY() {
		int temp = BOX.y + BOX.height - 100;
		return temp;
	}
	
	public int getRow(int index, int rowHeightLimit){
		int temp = 0;
		while(index > rowHeightLimit){
			temp++;
			index -= rowHeightLimit;
		}
		return temp;
	}

	public int getButtonListX(int index, int width, int rowHeightLimit) {
		int row = getRow(index, rowHeightLimit);
		int temp = BOX.x + 20 + (width * row);
		return temp;
	}

	public int getButtonListY(int index, int height, int rowHeightLimit) {
		int row = getRow(index, rowHeightLimit);
		int temp = BOX.y + BOX.height - 150 - (height * (index - (row * rowHeightLimit)));
//		int temp = BOX.y + BOX.height - 150 - (height * index);
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
