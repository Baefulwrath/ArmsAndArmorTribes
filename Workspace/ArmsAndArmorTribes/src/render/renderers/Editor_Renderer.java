package render.renderers;

import render.Renderer;
import render.Renderinghandler;
import ui.UIhandler;
import world.Worldhandler;

import com.badlogic.gdx.graphics.Color;

import editor.Brush;
import editor.Editorhandler;

public class Editor_Renderer extends Renderer {

	public Editor_Renderer() {
		super("EDITOR");
	}

	@Override
	public void loadSpecificResources() {
	}

	@Override
	public void mobileRender() {
		Brush brush = Editorhandler.brush;
		drawMap(Editorhandler.map);
		drawNinePatch(brush.img, brush.BOX, Color.WHITE);
	}

	@Override
	public void staticRender() {
		drawMessages(UIhandler.messages, Renderinghandler.getScreenX(), -Renderinghandler.getScreenY(), false);
		drawMenus(UIhandler.getInsideMenus());
		if(Editorhandler.paused){
			drawMenu(UIhandler.getMenu());
		}
		if(UIhandler.showWindow){
			drawWindow(UIhandler.getWindow());
		}
	}

}
