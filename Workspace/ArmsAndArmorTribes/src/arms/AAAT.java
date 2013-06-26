package arms;

import input.Inputhandler;
import render.Assethandler;
import render.Renderinghandler;
import scripting.Scripthandler;
import ui.UIhandler;
import world.Worldhandler;

import com.badlogic.gdx.ApplicationListener;

import static arms.State.*;
import static com.badlogic.gdx.Gdx.*;

import editor.Editorhandler;

public class AAAT implements ApplicationListener {
    public static State state = DEFAULT;
    public static boolean exitProgram = false;
    public static boolean gamePaused = false;
    public static boolean editorPaused = false;
    
    public AAAT(State startupState){
    	state = startupState;
    }
	
	@Override
	public void create() {
		Renderinghandler.setup();
		Worldhandler.load();
		Editorhandler.setup();
		Inputhandler inputhandler = new Inputhandler();
		UIhandler.setup();
		Scripthandler.setup();
		input.setInputProcessor(inputhandler);
	}

	@Override
	public void dispose() {
		Renderinghandler.dispose();
		Assethandler.dispose();
		UIhandler.reset();
	}

	@Override
	public void render(){
		if(!exitProgram){
			try{
				Thread.sleep(1);
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
				Scripthandler.update();
				UIhandler.update();
				Renderinghandler.render();
			}catch(Exception ex){
				ex.printStackTrace(System.out);
			}
		}else{
			exit();
		}
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
	
	public void exit(){
		dispose();
		System.exit(0);
	}
	
	public static void changeState(State s){
		state = s;
		UIhandler.reset();
	}
	
	public static void changeState(String s){
		state = State.parseState(s);
		UIhandler.reset();
	}
}
