package ui;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class ButtonStyle {
	public NinePatch UP;
	public NinePatch HOVER;
	public NinePatch DOWN;
	public LabelStyle LABELSTYLE;
	
	public ButtonStyle(NinePatch up, NinePatch hover, NinePatch down, LabelStyle labelstyle){
		UP = up;
		HOVER = hover;
		DOWN = down;
		LABELSTYLE = labelstyle;
	}
}
