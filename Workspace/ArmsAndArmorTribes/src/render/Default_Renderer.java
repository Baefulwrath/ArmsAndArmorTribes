package render;

import ui.UIhandler;
import world.Worldhandler;

import com.badlogic.gdx.graphics.Color;

import editor.Brush;
import editor.Editorhandler;

public class Default_Renderer extends Renderer {

	public Default_Renderer() {
		super("DEFAULT");
	}

	@Override
	public void loadSpecificResources() {
	}

	@Override
	public void mobileRender() {
	}

	@Override
	public void staticRender() {
		drawMenu(UIhandler.getMenu());
	}

}
