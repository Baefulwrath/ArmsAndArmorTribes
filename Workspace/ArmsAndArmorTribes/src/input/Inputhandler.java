package input;

import java.awt.Rectangle;

import render.Renderinghandler;

import AAAT.AAAT;

import com.badlogic.gdx.InputProcessor;

public class Inputhandler implements InputProcessor {

    public static Rectangle mouse = new Rectangle(0, 0, 0, 0);
    public static Rectangle staticMouse = new Rectangle(0, 0, 0, 0);
    
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		updateMouse(screenX, screenY);
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		updateMouse(screenX, screenY);
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
