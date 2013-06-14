package arms;

import input.Inputhandler;
import render.UIAssethandler;
import render.Renderinghandler;
import ui.Menuhandler;
import world.Worldhandler;

import com.badlogic.gdx.ApplicationListener;

import static arms.State.*;
import static com.badlogic.gdx.Gdx.*;

import editor.Editorhandler;

public class AAAT implements ApplicationListener {
	private static long lastRender = 0;
	private static long renderInterval = 5;
    public static int fps = 120;
    public static State state = MENU;
	
	@Override
	public void create() {
		Renderinghandler.setup();
		Worldhandler.load();
		Editorhandler.setup();
		Inputhandler inputhandler = new Inputhandler();
		Menuhandler.loadMenus();
		input.setInputProcessor(inputhandler);
	}

	@Override
	public void dispose() {
		Renderinghandler.dispose();
		UIAssethandler.dispose();
	}

	@Override
	public void render(){
		try{
			Thread.sleep(2);
			switch(state){
				case DEFAULT:
					break;
				case MENU:
					break;
				case EDITOR:
					Editorhandler.update();
					break;
				case GAME:
					Worldhandler.update();
					break;
			}
			Menuhandler.update();
			if(readyToRender()){
				Renderinghandler.render();
			}
		}catch(Exception ex){
			ex.printStackTrace(System.out);
		}
	}
	
	public boolean readyToRender(){
		boolean temp = false;
		renderInterval = 1000 / fps;
		if(lastRender + renderInterval <= System.currentTimeMillis()){
			temp = true;
			lastRender = System.currentTimeMillis();
		}
		return temp;
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
