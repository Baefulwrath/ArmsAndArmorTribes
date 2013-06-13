import java.awt.Rectangle;


public class Button {
	public String TITLE = "";
	public String CMD = "";
	public Rectangle BOX = new Rectangle();
	public boolean HOVER = false;
	public Button(String title, String cmd, Rectangle box){
		TITLE = title;
		CMD = cmd;
		BOX = box;
	}
}
