package render.renderers;

import render.Renderer;
import render.Renderinghandler;
import ui.UIhandler;
import world.Worldhandler;

import com.badlogic.gdx.graphics.Color;

public class Menu_Renderer extends Renderer {

	public Menu_Renderer() {
		super("MENU");
	}

	@Override
	public void loadSpecificResources() {
	}

	@Override
	public void mobileRender() {
	}

	@Override
	public void staticRender() {
		drawMessages(UIhandler.messages, Renderinghandler.getScreenX(), -Renderinghandler.getScreenY(), false);
		drawMenu(UIhandler.getMenu());
		if(UIhandler.showWindow){
			drawWindow(UIhandler.getWindow());
		}
	}

}
