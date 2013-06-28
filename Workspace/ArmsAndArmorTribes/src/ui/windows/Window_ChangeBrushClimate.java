package ui.windows;

import render.Assethandler;
import ui.Window;
import ui.WindowType;

public class Window_ChangeBrushClimate extends Window{

	public Window_ChangeBrushClimate(int x, int y, int w, int h, String id) {
		super(x, y, w, h, id);
		setWindow("Change Brush Climate", "Change Brush Climate", "", WindowType.NUMBERINPUT);
	}
	
	@Override
	public void setup(){
	}
	
	@Override
	public void setupActivation(){
		SCRIPT = "setBrushClimate_" + INPUT;
	}

}
