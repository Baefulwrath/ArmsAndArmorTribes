package ui.windows;

import render.Assethandler;
import ui.Window;
import ui.WindowType;
import world.Climate;

public class Window_ChangeBrushClimate extends Window{

	public Window_ChangeBrushClimate(String id) {
		super(-400, -250, 800, 500, id);
		setWindow("Change Brush Climate", "Change Brush Climate", "", WindowType.LISTINPUT, true);
	}
	
	@Override
	public void setupReactiveObjects(){
		for(int i = 0; i < Assethandler.climates.size(); i++){
			Climate c = Assethandler.climates.get(i);
			int bx = getButtonListX(i, 155, 10);
			int by = getButtonListY(i, 30, 10);
			addButton(c.CLIMATE, "setCurrentWindowInput_" + i, bx, by, 150, 26, Assethandler.basicButtonStyle);
		}
	}
	
	@Override
	public void setupActivation(){
		SCRIPT = "setBrushClimate_" + Assethandler.getClimateIdByString(INPUT);
	}

	@Override
	public void updateInput() {
		if(INPUT.matches("\\d+") && !INPUT.isEmpty()){
			INPUT = Assethandler.climates.get(Integer.parseInt(INPUT)).CLIMATE;
		}
	}

}
