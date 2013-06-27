package render;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NinePatchImage {
	public int X = 0;
	public int Y = 0;
	public int W = 0;
	public int H = 0;
	public NinePatch N;
	public NinePatchImage(NinePatch n, int x, int y, int w, int h){
		N = n;
		X = x;
		Y = y;
		W = w;
		H = h;
	}
	
	public void draw(SpriteBatch batch){
		N.draw(batch, X, Y, W, H);
	}
}
