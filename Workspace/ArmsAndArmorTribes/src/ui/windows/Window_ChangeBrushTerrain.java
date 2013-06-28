package ui.windows;

import render.Assethandler;
import ui.Window;
import ui.WindowType;

public class Window_ChangeBrushTerrain extends Window{

	public Window_ChangeBrushTerrain(int x, int y, int w, int h, String id) {
		super(x, y, w, h, id);
		setWindow("Change Brush Terrain", "Change Brush Terrain", "", WindowType.NUMBERINPUT);
	}
	
	@Override
	public void setup(){
	}
	
	@Override
	public void setupActivation(){
		SCRIPT = "setBrushTerrain_" + INPUT;
	}

}
