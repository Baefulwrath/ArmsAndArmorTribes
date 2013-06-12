package input;

import java.awt.Rectangle;
import render.Renderinghandler;
import world.Worldhandler;
import static com.badlogic.gdx.Input.Keys.*;
import arms.AAAT;
import static arms.State.*;
import com.badlogic.gdx.InputProcessor;
import editor.Editorhandler;

public class Inputhandler implements InputProcessor {

    public static Rectangle mouse = new Rectangle(0, 0, 0, 0);
    public static Rectangle staticMouse = new Rectangle(0, 0, 0, 0);
    
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
			case PAGE_DOWN:
				Renderinghandler.zoomOut = true;
				break;
			case PAGE_UP:
				Renderinghandler.zoomIn = true;
				break;
		}
		if(AAAT.state == EDITOR){
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
		}else if(AAAT.state == GAME){
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
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
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
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		updateMouse(screenX, screenY);
		switch(AAAT.state){
			case DEFAULT:
				break;
			case MENU:
				break;
			case EDITOR:
				Editorhandler.painting = true;
				break;
			case GAME:
				break;
		}
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		updateMouse(screenX, screenY);
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
    
    public void updateMouse(int screenX, int screenY){
        mouse = new Rectangle((int) ((screenX + Renderinghandler.getScreenX()) * Renderinghandler.getZoomScale()), (int) ((-screenY - Renderinghandler.getScreenY()) * Renderinghandler.getZoomScale()), 1, 1);
        staticMouse = new Rectangle((int) (screenX + Renderinghandler.getScreenX()), (int) (-screenY - Renderinghandler.getScreenY()), 1, 1);
    }

}
