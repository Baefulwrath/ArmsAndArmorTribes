package render;

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
		drawImage(brush.sprite, brush.BOX.x, brush.BOX.y, brush.BOX.width, brush.BOX.height, 0, false, Color.WHITE, 0.5f, true);
	}

	@Override
	public void staticRender() {
	}

}
