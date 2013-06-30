package ui.windows;

import render.Assethandler;
import ui.Window;
import ui.WindowType;

public class Window_NOID extends Window{

	public Window_NOID() {
		super(0, 0, 0, 0, "NOID");
		setWindow("NOID", "NOID", "", WindowType.DEFAULT, false);
	}
	
	@Override
	public void setupReactiveObjects(){
	}
	
	@Override
	public void setupActivation(){
		SCRIPT = "print_NOID";
	}

	@Override
	public void updateInput() {
	}

}
