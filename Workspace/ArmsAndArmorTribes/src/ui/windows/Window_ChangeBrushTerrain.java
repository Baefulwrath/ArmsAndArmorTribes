package ui.windows;

import render.Assethandler;
import ui.Window;
import ui.WindowType;
import world.Climate;
import world.Terrain;

public class Window_ChangeBrushTerrain extends Window{

	public Window_ChangeBrushTerrain(String id) {
		super(-400, -250, 800, 500, id);
		setWindow("Change Brush Terrain", "Change Brush Terrain", "", WindowType.LISTINPUT, true);
	}
	
	@Override
	public void setupReactiveObjects(){
		for(int i = 0; i < Assethandler.terrains.size(); i++){
			Terrain t = Assethandler.terrains.get(i);
			int x = getButtonListX(i, 155, 10);
			int y = getButtonListY(i, 30, 10);
			addButton(t.TERRAIN, "setCurrentWindowInput_" + i, x, y, 150, 26, Assethandler.basicButtonStyle);
		}
	}
	
	@Override
	public void setupActivation(){
		SCRIPT = "setBrushClimate_" + Assethandler.getTerrainIdByString(INPUT);
	}

	@Override
	public void updateInput() {
		if(INPUT.matches("\\d+") && !INPUT.isEmpty()){
			INPUT = Assethandler.terrains.get(Integer.parseInt(INPUT)).TERRAIN;
		}
	}

}
