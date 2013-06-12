package editor;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import world.Cell;

public class Brush extends Cell{

	public Rectangle BOX = new Rectangle();
	public int size = 100;
	public Sprite sprite = new Sprite();
	
	public Brush(int width, int diameter, int terrain, int climate) {
		super(width, diameter, terrain, climate);
		loadImg();
	}
	
	public Brush(int width, int terrain, int climate) {
		super(width, terrain, climate);
		loadImg();
	}
	
	public void loadImg(){
		sprite = new Sprite(new Texture(Gdx.files.internal("data/images/brush.png")));
	}
	
	public void update(int x, int y){
		BOX = new Rectangle(x - (size / 2), y - (size / 2), size, size);
	}
	
}
