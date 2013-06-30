package ui.windows;

import render.Assethandler;
import ui.Window;
import ui.WindowType;

public class Window_Test extends Window{

	public Window_Test(String id) {
		super(-250, -100, 500, 200, id);
		setWindow("Test Message", "Test Title", "", WindowType.DEFAULT, true);
	}
	
	@Override
	public void setupReactiveObjects(){
		addButton("test", "print_test", 0, 0, 100, 50, Assethandler.basicButtonStyle);
	}
	
	@Override
	public void setupActivation(){
		SCRIPT = "print_" + INPUT;
	}

	@Override
	public void updateInput() {
	}

}
