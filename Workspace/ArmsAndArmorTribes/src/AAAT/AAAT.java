package AAAT;

import input.Inputhandler;
import render.Renderinghandler;
import world.Worldhandler;

import com.badlogic.gdx.ApplicationListener;

import static com.badlogic.gdx.Gdx.*;

import editor.Editorhandler;
import static AAAT.State.*;

public class AAAT implements ApplicationListener {
	private long lastRender = 0;
	private long renderInterval = 5;
    public static int fps = 120;
    public State state = EDITOR;
	
	@Override
	public void create() {
		Renderinghandler.setup();
		Worldhandler.load();
		Editorhandler.setup();
		Inputhandler inputhandler = new Inputhandler();
		input.setInputProcessor(inputhandler);
	}

	@Override
	public void dispose() {
		Renderinghandler.dispose();
	}

	@Override
	public void render(){
		try{
			Thread.sleep(2);
			switch(state){
				case DEFAULT:
					break;
				case MENU:
					Renderinghandler.activeRenderer = "MENU";
					break;
				case EDITOR:
					Renderinghandler.activeRenderer = "EDITOR";
					Editorhandler.update();
					break;
				case GAME:
					Renderinghandler.activeRenderer = "GAME";
					Worldhandler.update();
					break;
			}
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
