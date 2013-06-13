import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;


public class Screen extends JPanel{
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fill(new Rectangle(0, 0, getWidth(), getHeight()));
		for(int i = 0; i < Main.buttons.length; i++){
			Rectangle r = Main.buttons[i].BOX;
			String title = Main.buttons[i].TITLE;
			if(Main.buttons[i].HOVER){
				g2d.setColor(Color.GRAY);
			}else{
				g2d.setColor(Color.DARK_GRAY);
			}
			g2d.fill(r);
			if(Main.buttons[i].HOVER){
				g2d.setColor(Color.BLACK);
			}else{
				g2d.setColor(Color.WHITE);
			}
			g2d.drawString(title, r.x + 5, r.y + (r.height / 2) + 4);
			if(Main.buttons[i].HOVER){
				g2d.setColor(Color.BLACK);
			}else{
				g2d.setColor(Color.LIGHT_GRAY);
			}
			g2d.drawRect(r.x, r.y, r.width, r.height);
		}
		g2d.setColor(Color.WHITE);
		g2d.drawString(Main.inputDir, 10, 80);
		g2d.drawString(Main.outputDir, 10, 100);
		g2d.drawString(Main.packFileName, 10, 120);
		if(Main.processing){
			g2d.drawString("Processing...", 10, 140);
		}
	}
}
