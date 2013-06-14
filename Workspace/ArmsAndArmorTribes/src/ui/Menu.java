package ui;

import java.util.ArrayList;
import java.util.Scanner;

import render.UIAssethandler;

import arms.State;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Menu {
	public ArrayList<Label> labels = new ArrayList<Label>();
	public ArrayList<Image> images = new ArrayList<Image>();
	public ArrayList<TextButton> textButtons = new ArrayList<TextButton>();
	public ArrayList<ImageButton> imageButtons = new ArrayList<ImageButton>();
	public State STATE = State.DEFAULT;
	public String ID = "";
	public String TITLE = "";
	public float OPACITY = 1.0f;
	public boolean RENDERTITLE = false;
	public int TITLEX = 0;
	public int TITLEY = 0;
	
	public void update(){
		clear();
		setup();
	}
	public void clear(){
		labels.clear();
		images.clear();
		textButtons.clear();
		imageButtons.clear();
	}
	
	public void setup(){}
	
	public void set(String id, String title, State state, float opacity, boolean renderTitle, int titleX, int titleY){
		ID = id;
		TITLE = title;
		STATE = state;
		OPACITY = opacity;
		RENDERTITLE = renderTitle;
		TITLEX = titleX;
		TITLEY = titleY;
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
		BitmapFont font = UIAssethandler.parseFont(reader.nextLine());
        LabelStyle style = new LabelStyle(font, color);
		Label l = new Label(text, style);
        l.setPosition(x, y);
		reader.close();
        return l;
	}
}
