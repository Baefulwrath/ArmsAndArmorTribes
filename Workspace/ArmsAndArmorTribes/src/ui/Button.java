package ui;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Button extends TextButton{
	
	public String script = "";

	public Button(String text, String buttonScript, TextButtonStyle style) {
		super(text, style);
		script = buttonScript;
	}
	
}
