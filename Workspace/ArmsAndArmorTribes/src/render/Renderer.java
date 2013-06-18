package render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import ui.Menu;
import world.Cell;
import world.GameMap;
import world.Worldhandler;
import static render.UIAssethandler.*;

public abstract class Renderer {
	public Renderer(String id){
		ID = id;
	}
	public abstract void loadSpecificResources();
	public abstract void mobileRender();
	public abstract void staticRender();
	public String ID;
	
	public void drawMap(GameMap map){
		for(int x = 0; x < map.CELLS.length; x++){
			for(int y = 0; y < map.CELLS[x].length; y++){
				Cell C = map.CELLS[x][y];
				Sprite cliImg = Worldhandler.getClimateImage(C.CLIMATE);
				Sprite terImg = Worldhandler.getTerrainImage(C.TERRAIN);
				Sprite gridImg = Worldhandler.gridImg;
				int diameter = Worldhandler.hexDiameter;
				drawImage(cliImg, C.X, C.Y, diameter, diameter, 0, false, Color.WHITE, 1.0f, true);
				drawImage(terImg, C.X, C.Y, diameter, diameter, 0, false, Color.WHITE, 1.0f, true);
				if(Worldhandler.showGrid){
					drawImage(gridImg, C.X, C.Y, diameter, diameter, 0, false, Color.WHITE, 1.0f, true);
				}
				drawString(x + ", " + y, C.X, C.Y, debugLabelStyle, 0.5f);
			}
		}
	}

    public void drawImage(Sprite sprite, float x, float y, float scale, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
    	if(smooth){
    		sprite.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
    		sprite.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	}
    	if(!centered){
    		x += sprite.getWidth() / 2;
    		y += sprite.getHeight() / 2;
    	}
    	sprite.setSize(1, 1);
    	sprite.setScale(scale);
    	sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    	sprite.setPosition(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2));
    	sprite.setRotation(rotation);
    	sprite.setColor(tint.r, tint.g, tint.b, opacity);
    	actualDrawImage(sprite);
    }
    
    public void drawImage(Sprite sprite, float x, float y, float width, float height, int rotation, boolean smooth, Color tint, float opacity, boolean centered) {
    	if(smooth){
    		sprite.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	}else{
    		sprite.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	}
    	if(!centered){
    		x += width / 2;
    		y += height / 2;
    	}
    	sprite.setSize(1, 1);
    	sprite.setScale(width, height);
    	sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
    	sprite.setPosition(x - (sprite.getWidth() / 2), y - (sprite.getHeight() / 2));
    	sprite.setRotation(rotation);
    	sprite.setColor(tint.r, tint.g, tint.b, opacity);
    	actualDrawImage(sprite);
    }
    
    private void actualDrawImage(Sprite sprite){
    	sprite.draw(Renderinghandler.batch);
    }

    public void drawString(String string, float x, float y, LabelStyle style, float opacity) {
    	y -= style.font.getCapHeight();
        Label lab = new Label(string, style);
        lab.setPosition(x, y);
        lab.draw(Renderinghandler.batch, opacity);
    }
    
    public void drawMenu(Menu m){
    	for(int i = 0; i < m.labels.size(); i++){
            m.labels.get(i).draw(Renderinghandler.batch, m.OPACITY);
    	}
    	for(int i = 0; i < m.images.size(); i++){
            m.images.get(i).draw(Renderinghandler.batch, m.OPACITY);
    	}
    	for(int i = 0; i < m.textButtons.size(); i++){
            m.textButtons.get(i).draw(Renderinghandler.batch, m.OPACITY);
    	}
    	for(int i = 0; i < m.imageButtons.size(); i++){
            m.imageButtons.get(i).draw(Renderinghandler.batch, m.OPACITY);
    	}
    	if(m.RENDERTITLE){
    		drawString(m.TITLE, m.TITLEX, m.TITLEY, UIAssethandler.titleLabelStyle, m.OPACITY);
    		drawString("----------------", m.TITLEX, m.TITLEY - 16, UIAssethandler.titleLabelStyle, m.OPACITY);
    	}
    }
}
