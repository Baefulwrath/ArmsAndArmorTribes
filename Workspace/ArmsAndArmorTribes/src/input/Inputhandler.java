package input;

import java.awt.Rectangle;
import render.Renderinghandler;
import ui.UIhandler;
import world.Worldhandler;
import static com.badlogic.gdx.Input.Keys.*;
import arms.AAAT;
import static arms.State.*;
import arms.State;
import com.badlogic.gdx.InputProcessor;
import editor.Editorhandler;

public class Inputhandler implements InputProcessor {

    public static Rectangle mouse = new Rectangle(0, 0, 0, 0);
    public static Rectangle staticMouse = new Rectangle(0, 0, 0, 0);
    
	@Override
	public boolean keyDown(int keycode) {
		if(UIhandler.showWindow){
			keyDown_Window(keycode);
		}else{
			switch(keycode){
				case PAGE_DOWN:
					Renderinghandler.zoomOut = true;
					break;
				case PAGE_UP:
					Renderinghandler.zoomIn = true;
					break;
				case ESCAPE:
					AAAT.exitProgram = true;
					break;
			}
			if(AAAT.state == EDITOR){
				if(AAAT.editorPaused){
					switch(keycode){
					case SPACE:
						UIhandler.activateMainButton();
						break;
					case ENTER:
						UIhandler.activateMainButton();
						break;
					}
				}else{
					switch(keycode){
						case DOWN:
							Editorhandler.moveUp = true;
							break;
						case UP:
							Editorhandler.moveDown = true;
							break;
						case LEFT:
							Editorhandler.moveRight = true;
							break;
						case RIGHT:
							Editorhandler.moveLeft = true;
							break;
					}
				}
			}else if(AAAT.state == GAME){
				if(AAAT.gamePaused){
					switch(keycode){
					case SPACE:
						UIhandler.activateMainButton();
						break;
					case ENTER:
						UIhandler.activateMainButton();
						break;
					}
				}else{
					switch(keycode){
						case DOWN:
							Worldhandler.moveUp = true;
							break;
						case UP:
							Worldhandler.moveDown = true;
							break;
						case LEFT:
							Worldhandler.moveRight = true;
							break;
						case RIGHT:
							Worldhandler.moveLeft = true;
							break;
					}
				}
			}else if(AAAT.state == State.MENU){
				switch(keycode){
				case SPACE:
					UIhandler.activateMainButton();
					break;
				case ENTER:
					UIhandler.activateMainButton();
					break;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if(UIhandler.showWindow){
			keyUp_Window(keycode);
		}else{
			switch(keycode){
				case PAGE_DOWN:
					Renderinghandler.zoomOut = false;
					break;
				case PAGE_UP:
					Renderinghandler.zoomIn = false;
					break;
			}
			if(AAAT.state == EDITOR){
				switch(keycode){
					case DOWN:
						Editorhandler.moveUp = false;
						break;
					case UP:
						Editorhandler.moveDown = false;
						break;
					case LEFT:
						Editorhandler.moveRight = false;
						break;
					case RIGHT:
						Editorhandler.moveLeft = false;
						break;
				}	
			}else if(AAAT.state == GAME){
				switch(keycode){
					case DOWN:
						Worldhandler.moveUp = false;
						break;
					case UP:
						Worldhandler.moveDown = false;
						break;
					case LEFT:
						Worldhandler.moveRight = false;
						break;
					case RIGHT:
						Worldhandler.moveLeft = false;
						break;
				}
			}
		}
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		updateMouse(screenX, screenY);
		if(UIhandler.showWindow){
			touchDown_Window(button);
		}else{
			switch(AAAT.state){
				case DEFAULT:
					break;
				case MENU:
					UIhandler.activateMainButton();
					break;
				case EDITOR:
					if(UIhandler.intersectsMenus(staticMouse)){
						if(UIhandler.intersectsMainMenus(staticMouse) && AAAT.editorPaused){
							UIhandler.activateMainButton();
						}else{
							UIhandler.activateInsideButton();
						}
					}else{
						Editorhandler.painting = true;
					}
					break;
				case GAME:
					break;
			}
		}
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		updateMouse(screenX, screenY);
		if(UIhandler.showWindow){
			touchUp_Window(button);
		}else{
			UIhandler.process();
			switch(AAAT.state){
				case DEFAULT:
					break;
				case MENU:
					break;
				case EDITOR:
					Editorhandler.painting = false;
					break;
				case GAME:
					break;
			}
		}
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		updateMouse(screenX, screenY);
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		updateMouse(screenX, screenY);
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	public void keyDown_Window(int keycode) {
	}
	
	public void keyUp_Window(int keycode) {
	}
	
	public void touchDown_Window(int button) {
	}
	
	public void touchUp_Window(int button) {
	}
    
    public void updateMouse(int screenX, int screenY){
        mouse = new Rectangle((int) ((screenX + Renderinghandler.getScreenX()) * Renderinghandler.getZoomScale()), (int) ((-screenY - Renderinghandler.getScreenY()) * Renderinghandler.getZoomScale()), 1, 1);
        staticMouse = new Rectangle((int) (screenX + Renderinghandler.getScreenX()), (int) (-screenY - Renderinghandler.getScreenY()), 1, 1);
    }

}
