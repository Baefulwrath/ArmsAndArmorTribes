
import java.awt.Rectangle;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
public class Main {

	/**
	 * @param args
	 */
	
	public static JFrame frame = new JFrame();
	public static Screen scr = new Screen();
	public static Inputhandler IH = new Inputhandler();
	public static int width = 600;
	public static int height = 200;
	public static Rectangle mouse = new Rectangle();
	
	public static String inputDir = System.getProperty("user.dir");
	public static String outputDir = System.getProperty("user.dir");
	public static String packFileName = "";
	
	public static boolean inputSet = false;
	public static boolean outputSet = false;
	public static boolean filenameSet = false;
	
	public static boolean processing = false;
	
	public static Button setIDB = new Button("Set Input Dir", "setInputDir", new Rectangle(10, 10, 100, 20));
	public static Button setODB = new Button("Set Output Dir", "setOutputDir", new Rectangle(10, 40, 100, 20));
	public static Button setPFNB = new Button("Set Pack filename", "setPackFileName", new Rectangle(120, 10, 100, 20));
	public static Button processB = new Button("Process", "process", new Rectangle(120, 40, 100, 20));
	
	public static Button[] buttons = new Button[4];
	
	public static void main(String[] args) {
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Texturepacker");
		frame.add(scr);
		frame.addKeyListener(IH);
		frame.addMouseListener(IH);
		frame.addMouseMotionListener(IH);
		frame.setResizable(false);
		buttons[0] = setIDB;
		buttons[1] = setODB;
		buttons[2] = setPFNB;
		buttons[3] = processB;
		while(true){
			scr.repaint();
			try{
				mouse = new Rectangle(scr.getMousePosition().x, scr.getMousePosition().y, 1, 1);
			}catch(Exception ex){}
		}
	}
	
	public static void click(){
		for(int i = 0; i < buttons.length; i++){
			if(buttons[i].HOVER){
				Cmdhandler.handleCmd(buttons[i].CMD);
				break;
			}
		}
	}
	
	public static void process(){
		if(inputSet && outputSet && filenameSet){
			processing = true;
			scr.repaint();
			TexturePacker2.process(inputDir, outputDir, packFileName);
			processing = false;
			scr.repaint();
		}else{
			JOptionPane.showMessageDialog(frame, "Please make sure everything is set before processing", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void setInputDir(){
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(inputDir));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setDialogTitle("Set Input Directory");
		fc.setAcceptAllFileFilterUsed(false);
		if(fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
			inputDir = fc.getSelectedFile().getAbsolutePath() + "\\";
			inputSet = true;
		}
	}
	
	public static void setOutputDir(){
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(outputDir));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setDialogTitle("Set Output Directory");
		fc.setAcceptAllFileFilterUsed(false);
		if(fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
			outputDir = fc.getSelectedFile().getAbsolutePath() + "\\";
			outputSet = true;
		}
	}
	
	public static void setPackFileName(){
		String name = "";
		name = JOptionPane.showInputDialog(frame, "Enter File Name", "Enter File Name");
		try{
			if(name.length() < 6 || !name.substring(name.length() - 6).equals(".atlas")){
				name += ".atlas";
			}
			packFileName = name;
			filenameSet = true;
		}catch(Exception ex){}
	}
}
