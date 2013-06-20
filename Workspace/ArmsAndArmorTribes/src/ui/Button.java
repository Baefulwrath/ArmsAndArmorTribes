package ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import render.UIAssethandler;

public class Button extends TextButton{
	
	public boolean HOVER = false;
	public String SCRIPT = "";
	public TextButtonStyle BUTTONSTYLE = UIAssethandler.basicTextbuttonStyle;
	public LabelStyle LABELSTYLE = UIAssethandler.debugLabelStyle;

	public Button(String text, String buttonScript, TextButtonStyle textButtonStyle, LabelStyle labelstyle) {
		super(text, textButtonStyle);
		SCRIPT = buttonScript;
		BUTTONSTYLE = textButtonStyle;
		LABELSTYLE = labelstyle;
	}
	
}
