package ui;

public enum WindowType {
	DEFAULT, MESSAGE, WARNING, TEXTINPUT, NUMBERINPUT, LISTINPUT;
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    public static WindowType parseState(String type){
    	WindowType temp = DEFAULT;
    	for(int i = 0; i < values().length; i++){
    		if(type.equals(values()[i].toString())){
    			temp = values()[i];
    		}
    	}
    	return temp;
    }
}
