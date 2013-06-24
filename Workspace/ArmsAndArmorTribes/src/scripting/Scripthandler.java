package scripting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import ui.UIhandler;

import arms.AAAT;
import arms.State;

public class Scripthandler {
    private static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    private static BufferedReader reader = new BufferedReader(inputStreamReader);
    private static boolean initialized = false;
    private static HashMap<String, Integer> genInts = new HashMap<String, Integer>();
    
    public static void initialize(){
    	loadGenericVariables();
    	initialized = true;
    }
    
    public static void update() {
        try {
            if (reader.ready()) {
                handleScript(reader.readLine());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadGenericVariables(){
    }

    public static void handleScript(String script) {
        // Ta bort kommentarer, mellanrum o.s.v
        script = cleanupScript(script);
        // Kolla efter metoder såsom GET_ och fyll i variabler.
        script = fillScript(script);
        // Loopa igenom olika kommandon och utför dem.
        while (script.contains("#")) {
            activateScript(script);
            script = script.substring(script.indexOf("#") + 1);
        }
        activateScript(script);

        System.out.println(script);
    }

    public static void activateScript(String script) {
        if (script.length() > 0) {
            if (script.length() > 1) {
                if (script.contains("#")) {
                    readLine(script.substring(0, script.indexOf("#")));
                } else {
                    readLine(script);
                }
            } else {
                if (!script.contains("#")) {
                    readLine(script);
                }
            }
        }
    }

    public static String cleanupScript(String script) {
        while (script.contains(" ")) {
            script = script.substring(0, script.indexOf(" ")) + script.substring(script.indexOf(" ") + 1);
        }
        return script;
    }

    public static String fillScript(String script) {
        while (script.contains("GET_")) {
            script = script.substring(0, script.indexOf("GET_")) + getVar(script.substring(script.indexOf("GET_") + 4, script.indexOf("_TEG"))) + script.substring(script.indexOf("_TEG") + 4);
        }
        return script;
    }

    public static String getVar(String id) {
        String value = "[GET-ERROR]";
        if(id.substring(0, 4).equals("loc_")){
            value += "[LOC]";
            id = id.substring(4);
        }else{
    		if(genInts.containsKey(id)){
    			value = genInts.get(id).toString();
    		}
        }
        return value;
    }

    public static void readLine(String line) {
        String cmd = line.substring(0, line.indexOf("_") + 1);
        if (line.length() == 5) {
            if (cmd.equals("exit_")) {
            	AAAT.exitProgram = true;
            }
        }
        if (line.length() == 5) {
            if (cmd.equals("test_")) {
                System.out.println("Test successful!");
            }
        }
        if (line.length() > 9) {
            if (cmd.equals("openMenu_")) {
                openMenu(line);
            }
        }
        if (line.length() > 6) {
            if (cmd.equals("print_")) {
                System.out.println(line.substring(6));
            }
        }
        if (line.length() > 9) {
            if (cmd.equals("setState_")) {
                setState(line.substring(9));
            }
        }
    }

	private static void setState(String s) {
		AAAT.changeState(s);
	}

	private static void openMenu(String id) {
		UIhandler.activeMenu = id;
	}
}
