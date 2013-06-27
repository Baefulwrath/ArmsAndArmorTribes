package ui;

public class Message {
	public String TEXT = "";
	public Long creationTime = (long) 0;
	public int LIFETIME = 0;
	
	public Message(String text, int lifetime){
		TEXT = text;
		LIFETIME = lifetime;
		creationTime = System.currentTimeMillis();
	}
}
