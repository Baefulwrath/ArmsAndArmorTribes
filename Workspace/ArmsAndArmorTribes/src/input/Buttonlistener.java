package input;

import ui.UIhandler;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Buttonlistener extends ChangeListener {

	@Override
	public void changed(ChangeEvent event, Actor actor) {
		UIhandler.clickEvent();
		System.out.println("changed event");
	}

}
