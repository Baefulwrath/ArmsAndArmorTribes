package ui.windows;

import render.Assethandler;
import ui.Window;
import ui.WindowType;

public class Window_Test extends Window{

	public Window_Test(int x, int y, int w, int h, String id) {
		super(x, y, w, h, id);
		setWindow("Test Message", "Test Title", "", WindowType.DEFAULT);
	}
	
	@Override
	public void setup(){
		addButton("test", "print_test", 0, 0, 100, 50, Assethandler.basicButtonStyle);
	}
	
	@Override
	public void setupActivation(){
		SCRIPT = "print_" + INPUT;
	}

}
