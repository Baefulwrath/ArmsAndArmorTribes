package editor;

import java.awt.Rectangle;

import render.Assethandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import world.Cell;
import world.Worldhandler;

public class Brush extends Cell{

	public Rectangle BOX = new Rectangle();
	public int size = 128;
	public NinePatch img;
	
	public Brush(int width, int diameter, int terrain, int climate) {
		super(width, diameter, terrain, climate);
		loadImg();
	}
	
	public Brush(int width, int terrain, int climate) {
		super(width, terrain, climate);
		loadImg();
	}
	
	public void loadImg(){
		img = Assethandler.brushImg;
	}
	
	public void update(int x, int y){
		BOX = new Rectangle(x - (size / 2), y - (size / 2), size, size);
	}
	
	public Sprite getClimateSprite(){
		return Assethandler.getClimateImage(CLIMATE);
	}
	
	public Sprite getTerrainSprite(){
		return Assethandler.getTerrainImage(TERRAIN);
	}
	
}
