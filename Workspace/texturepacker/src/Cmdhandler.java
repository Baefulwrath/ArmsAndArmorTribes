
public class Cmdhandler {
	public static void handleCmd(String cmd){
		switch(cmd){
			case "setInputDir":
				Main.setInputDir();
				break;
			case "setOutputDir":
				Main.setOutputDir();
				break;
			case "setPackFileName":
				Main.setPackFileName();
				break;
			case "process":
				Main.process();
				break;
		}
	}
}
