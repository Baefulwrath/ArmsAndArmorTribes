package ui.windows;

import render.Assethandler;
import ui.Window;
import ui.WindowType;

public class Window_Test extends Window{

	public Window_Test(int x, int y, int w, int h) {
		super(x, y, w, h);
		set("Test Message", "Test Title", WindowType.DEFAULT);
	}
	
	@Override
	public void setup(){
		addButton("test", "", 0, 0, 100, 50, Assethandler.basicButtonStyle);
	}

}
