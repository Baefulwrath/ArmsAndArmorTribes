package ui.menus;

import static editor.Editorhandler.*;
import static render.Renderinghandler.*;
import arms.State;
import ui.Menu;
import static render.Assethandler.*;

public class Editor_Hud extends Menu{
	
	public Editor_Hud(String id){
		super(id);
		set("", State.EDITOR, false, 1.0f, false, true, -300, 200);
	}
	
	@Override
	public void setup(){
		addNinePatch(basicHud, (int) getScreenX(), (int) getScreenY(), 200, (int) h);
		addCollisionArea((int) getScreenX(), (int) getScreenY(), 200, (int) h);
		addNinePatch(basicHud, (int) getScreenX() + 200, (int) getScreenY(), (int) w - 200, 200);
		addCollisionArea((int) getScreenX() + 200, (int) getScreenY(), (int) w - 200, 200);
		addCellImage(brush, (int) getScreenX() + 100, (int) -getScreenY() - 100, 128, 128);
		addLabel("Climate: " + climates.get(brush.CLIMATE).CLIMATE, messageLabelStyle, (int) getScreenX() + 20, (int) -getScreenY() - 200);
		addButton("Change Climate", "changeBrushClimate_", (int) getScreenX() + 20, (int) -getScreenY() - 242, 150, 32, basicButtonStyle);
		addLabel("Terrain: " + terrains.get(brush.TERRAIN).TERRAIN, messageLabelStyle, (int) getScreenX() + 20, (int) -getScreenY() - 262);
		addButton("Change Terrain", "changeBrushTerrain_", (int) getScreenX() + 20, (int) -getScreenY() - 304, 150, 32, basicButtonStyle);
	}
}
