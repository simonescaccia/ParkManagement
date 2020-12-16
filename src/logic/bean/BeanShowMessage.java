package logic.bean;

import java.lang.reflect.Method;

public class BeanShowMessage {

	private String message;
	private boolean type;
	private Object gui; 
	
	public void setMessage(String s) {
		message = s;
	}
	
	public void setType(boolean b) {
		type = b;
	}
	
	public void setGui(Object gui) {
		this.gui = gui;
	}
	
	public Object getGui() {
		return gui;
	}
	
	public boolean getType() {
		return type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void showMessage() {
		try {
			Method methodToBeCalled = gui.getClass().getMethod("showMessage", String.class, boolean.class);
			methodToBeCalled.invoke(gui, message, type);
		} catch (Exception e){
			//messaggio perso poichè l'intefaccia non implementa questo metodo
		}
	}
}
