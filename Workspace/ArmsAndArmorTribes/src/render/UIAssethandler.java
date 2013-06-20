package render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class UIAssethandler {
	
    private static BitmapFont com64;
    private static BitmapFont com32;
    private static BitmapFont com16;
    private static BitmapFont com10;
    private static BitmapFont com32_BI;
    private static BitmapFont com16_BI;
    private static BitmapFont com10_BI;
    
    public static LabelStyle messageLabelStyle;
    public static LabelStyle logoLabelStyle;
    public static LabelStyle debugLabelStyle;
    public static LabelStyle basicLabelStyle;
    public static LabelStyle titleLabelStyle;
    
    public static TextButtonStyle basicTextbuttonStyle;
    
    public static Skin basicSkin;
    
    public static void load(){
    	try{
            com64 = new BitmapFont(Gdx.files.internal("data/fonts/com64.fnt"), Gdx.files.internal("data/fonts/com64.png"), false, false);
            com32 = new BitmapFont(Gdx.files.internal("data/fonts/com32.fnt"), Gdx.files.internal("data/fonts/com32.png"), false, false);
            com16 = new BitmapFont(Gdx.files.internal("data/fonts/com16.fnt"), Gdx.files.internal("data/fonts/com16.png"), false, false);
            com10 = new BitmapFont(Gdx.files.internal("data/fonts/com10.fnt"), Gdx.files.internal("data/fonts/com10.png"), false, false);
            com32_BI = new BitmapFont(Gdx.files.internal("data/fonts/com32_BI.fnt"), Gdx.files.internal("data/fonts/com32_BI.png"), false, false);
            com16_BI = new BitmapFont(Gdx.files.internal("data/fonts/com16_BI.fnt"), Gdx.files.internal("data/fonts/com16_BI.png"), false, false);
            com10_BI = new BitmapFont(Gdx.files.internal("data/fonts/com10_BI.fnt"), Gdx.files.internal("data/fonts/com10_BI.png"), false, false);

            messageLabelStyle = new LabelStyle(UIAssethandler.com10, Color.CYAN);
            logoLabelStyle = new LabelStyle(UIAssethandler.com64, Color.WHITE);
            debugLabelStyle = new LabelStyle(UIAssethandler.com10, Color.RED);
            titleLabelStyle = new LabelStyle(UIAssethandler.com32_BI, Color.WHITE);
            basicLabelStyle = new LabelStyle(UIAssethandler.com10, Color.WHITE);
        	
        	TextureAtlas basicSkinAtlas = new TextureAtlas(Gdx.files.internal("data/fonts/basicButton.pack"));
        	basicSkin = new Skin(basicSkinAtlas);
        	basicTextbuttonStyle = new TextButtonStyle();
        	basicTextbuttonStyle.up = basicSkin.getDrawable("basicButtonUp");
        	basicTextbuttonStyle.down = basicSkin.getDrawable("basicButtonDown");
        	basicTextbuttonStyle.font = com10;
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
    
    public static void dispose(){
    	
    }
    
}


