package world;

import java.awt.Polygon;
import java.awt.Rectangle;

public class Cell {
	public int TERRAIN = 0;
	public int CLIMATE = 0;
    public boolean ACTIVE = false;
    private Polygon polygon = new Polygon();
    private Polygon realPolygon = new Polygon();
    public int WIDTH = 0;
    public int DIAMETER = 0;
    public int RADIUS = 0;
    public int SIDE = 0;
    public int HALFSIDE = 0;
    public int X = 0;
    public int Y = 0;
    public int imgWDif = 0;
    
    public Cell(int width, int terrain, int climate){
        set(width);
        TERRAIN = terrain;
        CLIMATE = climate;
    }
    public Cell(int width, int diameter, int terrain, int climate){
        set(width, diameter);
        TERRAIN = terrain;
        CLIMATE = climate;
    }
    
    public void update(int x, int y, int mx, int my){
    	X = x + mx;
    	Y = y + my;
    	setRealPolygon(mx, my);
    }
    
    private void setRealPolygon(int mx, int my){
        int[] vertX = {0 + X, RADIUS + X, RADIUS + X, 0 + X, -RADIUS + X, -RADIUS + X};
        int[] vertY = {SIDE + Y, HALFSIDE + Y, -HALFSIDE + Y, -SIDE + Y, -HALFSIDE + Y, HALFSIDE + Y};
        realPolygon = new Polygon(vertX, vertY, 6);
    }
    
    public Polygon getPolygon(){
        return polygon;
    }
    
    public Polygon getRealPolygon(){
    	return realPolygon;
    }
    
    public void set(int width){
        WIDTH = width;
        DIAMETER = (int) (WIDTH * 1.1547);
        RADIUS = WIDTH / 2;
        SIDE = DIAMETER / 2;
        HALFSIDE = SIDE / 2;
        imgWDif = (DIAMETER - WIDTH) / 2;
        setupPolygon();
    }
    
    public void set(int width, int diameter){
        WIDTH = width;
        DIAMETER = diameter;
        RADIUS = WIDTH / 2;
        SIDE = DIAMETER / 2;
        HALFSIDE = SIDE / 2;
        imgWDif = (DIAMETER - WIDTH) / 2;
        setupPolygon();
    }
    
    public void setupPolygon(){
        int[] vertX = {0, RADIUS, RADIUS, 0, -RADIUS, -RADIUS};
        int[] vertY = {SIDE, HALFSIDE, -HALFSIDE, -SIDE, -HALFSIDE, HALFSIDE};
        /*
        int[] verts0 = {0, SIDE};
        int[] verts1 = {RADIUS, HALFSIDE};
        int[] verts2 = {RADIUS, -HALFSIDE};
        int[] verts3 = {0, -SIDE};
        int[] verts4 = {-RADIUS, -HALFSIDE};
        int[] verts5 = {-RADIUS, HALFSIDE};*/
        /*
        int[] verts0 = {0, 0, 0, SIDE, RADIUS, HALFSIDE};
        int[] verts1 = {0, 0, RADIUS, HALFSIDE, RADIUS, -HALFSIDE};
        int[] verts2 = {0, 0, RADIUS, -HALFSIDE, 0, -SIDE};
        int[] verts3 = {0, 0, 0, -SIDE, -RADIUS, -HALFSIDE};
        int[] verts4 = {0, 0, -RADIUS, -HALFSIDE, -RADIUS, HALFSIDE};
        int[] verts5 = {0, 0, -RADIUS, HALFSIDE, 0, SIDE};
        */
        polygon = new Polygon(vertX, vertY, 6);
    }
    
    public boolean intersects(Rectangle r, boolean real){
    	if(real){
	    	if(getRealPolygon().intersects(r)){
	    		return true;
	    	}else{
	    		return false;
	    	}
    	}else{
	    	if(getPolygon().intersects(r)){
	    		return true;
	    	}else{
	    		return false;
	    	}
    	}
    }
    
    public void mirror(Cell C){
    	set((int) C.WIDTH, (int) C.DIAMETER);
    	CLIMATE = C.CLIMATE;
    	TERRAIN = C.TERRAIN;
    }

}
