package ui;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Scanner;

import render.Assethandler;
import render.NinePatchImage;
import render.Renderinghandler;
import world.Cell;

import arms.State;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Menu {
	public ArrayList<Label> labels = new ArrayList<Label>();
	public ArrayList<Image> images = new ArrayList<Image>();
	public ArrayList<NinePatchImage> ninepatches = new ArrayList<NinePatchImage>();
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public ArrayList<CellImage> cellImages = new ArrayList<CellImage>();
	public ArrayList<Rectangle> collisionAreas = new ArrayList<Rectangle>();
	public State STATE = State.DEFAULT;
	public boolean MAIN = false;
	public String ID = "";
	public String TITLE = "";
	public float OPACITY = 1.0f;
	public boolean RENDERTITLE = false;
	public int TITLEX = 0;
	public int TITLEY = 0;
	private int ACTIVETB = 0;
	public boolean RENDERACTIVETB = false;
	
	public void update(boolean active){
		testActiveButton();
		clear();
		setup();
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).update(active);
		}
	}
	
	public void clear(){
		labels.clear();
		images.clear();
		ninepatches.clear();
		buttons.clear();
		cellImages.clear();
		collisionAreas.clear();
	}
	
	public void testActiveButton(){
		if(ACTIVETB < 0){
			ACTIVETB = buttons.size() - 1;
		}else if(ACTIVETB > buttons.size() - 1){
			ACTIVETB = 0;
		}
	}
	
	public void setup(){}
	
	public void set(String id, String title, State state, boolean main, float opacity, boolean renderTitle, boolean renderActiveButton, int titleX, int titleY){
		ID = id;
		TITLE = title;
		STATE = state;
		MAIN = main;
		OPACITY = opacity;
		RENDERTITLE = renderTitle;
		RENDERACTIVETB = renderActiveButton;
		TITLEX = titleX;
		TITLEY = titleY;
	}
	
	public void set(String id, State state, float opacity){
		ID = id;
		TITLE = "UNTITLED";
		STATE = state;
		OPACITY = opacity;
		RENDERTITLE = false;
		TITLEX = 0;
		TITLEY = 0;
	}
	
	public static Menu parseMenu(String s){
		Menu m = new Menu();
		Scanner reader = new Scanner(s);
		m.ID = reader.nextLine();
		m.TITLE = reader.nextLine();
		m.STATE = State.parseState(reader.nextLine());
		if(s.contains("<")){
			String content = s.substring(s.indexOf(":") + 3);
			String type = content.substring(0, content.indexOf("<"));
			while(content.contains("<")){
				type = content.substring(0, content.indexOf("<"));
				readContent(type, content.substring(content.indexOf("<") + 3, content.indexOf(">") - 2), m);
				content = content.substring(content.indexOf(">") + 1);
			}
		}
		reader.close();
		return m;
	}
	
	public static void readContent(String type, String s, Menu m){
		switch(type){
			case "LABEL":m.labels.add(parseLabel(s));
				break;
		}
	}
	
	public static Label parseLabel(String s){
		Scanner reader = new Scanner(s);
		String text = reader.nextLine();
		int x = Integer.parseInt(reader.nextLine());
		int y = Integer.parseInt(reader.nextLine());
		Color color = Color.valueOf(reader.nextLine());
		BitmapFont font = Assethandler.parseFont(reader.nextLine());
        LabelStyle style = new LabelStyle(font, color);
		Label l = new Label(text, style);
        l.setPosition(x, y);
		reader.close();
        return l;
	}
	
	public void addLabel(String text, LabelStyle style, int x, int y){
		Label l = new Label(text, style);
		l.setPosition(x, y);
		labels.add(l);
	}
	
	public void addImage(TextureRegion r, int x, int y, int w, int h){
		addImage(new Sprite(r), x, y, w, h);
	}
	
	public void addImage(Texture t, int x, int y, int w, int h){
		addImage(new Sprite(t), x, y, w, h);
	}
	
	public void addNinePatch(NinePatch n, int x, int y, int w, int h){
		NinePatchImage npi = new NinePatchImage(n, x, y, w, h);
		ninepatches.add(npi);
	}
	
	public void addNinePatch(NinePatchImage n){
		ninepatches.add(n);
	}
	
	public void addImage(Sprite s, int x, int y, int w, int h){
		Image i = new Image(s);
		i.setBounds(x, y, w, h);
		images.add(i);
	}
	
	public void addButton(String text, String script, int x, int y, int w, int h, ButtonStyle bs){
		buttons.add(new Button(text, script, new Rectangle(x, y, w, h), bs));
	}
	
	public void addCellImage(Cell c, int x, int y, int w, int h){
		cellImages.add(new CellImage(c.WIDTH, c.TERRAIN, c.CLIMATE, x, y, w, h));
	}
	
	public void addCollisionArea(int x, int y, int w, int h){
		collisionAreas.add(new Rectangle(x, y, w, h));
	}
	
	public boolean intersects(Rectangle r){
		boolean temp = false;
		for(int i = 0; i < buttons.size(); i++){
			if(buttons.get(i).intersects(r)){
				temp = true;
				break;
			}
		}
		if(!temp){
			for(int i = 0; i < collisionAreas.size(); i++){
				if(collisionAreas.get(i).intersects(r)){
					temp = true;
					break;
				}
			}
		}
		return temp;
	}

	public void process() {
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).activate();
		}
	}

	public void systemUpdate(boolean active) {
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).systemUpdate(active);
		}
	}
	
	public float getScreenX(){
		return Renderinghandler.getScreenX();
	}
	
	public float getScreenY(){
		return Renderinghandler.getScreenY();
	}

	public void unhoverAll() {
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).HOVER = false;
		}
	}
}
