package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assethandler {
	
	public static AssetManager manager = new AssetManager();
	
    private static BitmapFont com64;
    private static BitmapFont com32;
    private static BitmapFont com16;
    private static BitmapFont com10;
    private static BitmapFont com32_BI;
    private static BitmapFont com16_BI;
    private static BitmapFont com10_BI;
    
    public static LabelStyle messageStyle;
    public static LabelStyle logoStyle;
    public static LabelStyle debugStyle;
    
    public static Skin debugSkin;
    
    public static void load(){
    	try{
            com64 = new BitmapFont(Gdx.files.internal("data/fonts/com64.fnt"), Gdx.files.internal("data/fonts/com64.png"), false, false);
            com32 = new BitmapFont(Gdx.files.internal("data/fonts/com32.fnt"), Gdx.files.internal("data/fonts/com32.png"), false, false);
            com16 = new BitmapFont(Gdx.files.internal("data/fonts/com16.fnt"), Gdx.files.internal("data/fonts/com16.png"), false, false);
            com10 = new BitmapFont(Gdx.files.internal("data/fonts/com10.fnt"), Gdx.files.internal("data/fonts/com10.png"), false, false);
            com32_BI = new BitmapFont(Gdx.files.internal("data/fonts/com32_BI.fnt"), Gdx.files.internal("data/fonts/com32_BI.png"), false, false);
            com16_BI = new BitmapFont(Gdx.files.internal("data/fonts/com16_BI.fnt"), Gdx.files.internal("data/fonts/com16_BI.png"), false, false);
            com10_BI = new BitmapFont(Gdx.files.internal("data/fonts/com10_BI.fnt"), Gdx.files.internal("data/fonts/com10_BI.png"), false, false);

        	messageStyle = new LabelStyle(Assethandler.com10, Color.CYAN);
        	logoStyle = new LabelStyle(Assethandler.com64, Color.WHITE);
        	logoStyle = new LabelStyle(Assethandler.com10, Color.RED);
        	manager.load("data/fonts/debuSkinAtlas.atlas", TextureAtlas.class);
        	TextureAtlas debuSkinAtlas = manager.get("debuSkinAtlas.atlas", TextureAtlas.class);
        	debugSkin = new Skin(Gdx.files.internal("data/fonts/debugSkin01.json"), debuSkinAtlas);
    	}catch(Exception ex){
    		ex.printStackTrace(System.out);
    	}
    }
    
    public static BitmapFont parseFont(String s){
    	BitmapFont font = new BitmapFont();
    	switch(s){
	    	case "com10": font = com10;
	    		break;
	    	case "com16": font = com16;
    			break;
	    	case "com32": font = com32;
    			break;
	    	case "com64": font = com64;
    			break;
	    	case "com32_BI": font = com32_BI;
				break;
	    	case "com16_BI": font = com16_BI;
				break;
	    	case "com10_BI": font = com10_BI;
				break;
    	}
    	return font;
    }
    
}
