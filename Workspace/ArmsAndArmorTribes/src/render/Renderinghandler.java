package render;

import static com.badlogic.gdx.Gdx.*;

import java.util.HashMap;
import java.util.Map;

import world.GameMap;

import static com.badlogic.gdx.graphics.GL10.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderinghandler {
	public static float w = 0;
	public static float h = 0;
	public static float zoom = graphics.getWidth() * 2;
	public static OrthographicCamera camera;
	public static SpriteBatch batch;
	public static boolean initialized = false;
	public static HashMap<String, Renderer> renderers = new HashMap<String, Renderer>();
	public static String activeRenderer = "";

    public BitmapFont com64;
    public BitmapFont com32;
    public BitmapFont com16;
    public BitmapFont com10;
    public BitmapFont com32_BI;
    public BitmapFont com16_BI;
    public BitmapFont com10_BI;
    
    public static Sprite testImg;
	
	public static void setup(){
		w = graphics.getWidth();
		h = graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		testImg = new Sprite(new Texture(Gdx.files.internal("data/testImg.png")));
		setupRenderers();
	}
	
	public static void setupRenderers(){
		Menu_Renderer menuRenderer = new Menu_Renderer();
		renderers.put(menuRenderer.ID, menuRenderer);
		Game_Renderer gameRenderer = new Game_Renderer();
		renderers.put(gameRenderer.ID, gameRenderer);
		Editor_Renderer editorRenderer = new Editor_Renderer();
		renderers.put(editorRenderer.ID, editorRenderer);
		
		activeRenderer = menuRenderer.ID;
	}
	
	public static void render(){
		newFrame();
		mobileRender();
		staticRender();
	}
	
	public static void mobileRender(){
		camera.zoom = zoom;
		prepRender();
		doRender(true);
		endRender();
	}
	
	public static void staticRender(){
		camera.zoom = w;
		prepRender();
		doRender(false);
		endRender();
	}
	
	public static void newFrame(){
		graphics.getGL10().glClearColor(0, 0.05f, 0.05f, 1.0f);
		graphics.getGL10().glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		graphics.getGL10().glDisable(GL_CULL_FACE);
	}
	
	public static void prepRender(){
		camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		batch.begin();
	}
	
	public static void endRender(){
		batch.end();
	}
	
	public static void doRender(boolean mobile){
		if(mobile){
			renderers.get(activeRenderer).mobileRender();
		}else{
			renderers.get(activeRenderer).staticRender();
		}
	}
	
	public static void dispose(){
		batch.dispose();
	}
    
    public static float getZoomScale(){
    	return zoom / w;
    }
    
    public static float getZoomScale_In(){
    	return zoom / w - 1;
    }


    public static float getScreenX() {
        return -(w / 2);
    }

    public static float getScreenY() {
        return -(h / 2);
    }
	
}
