package logic.bean;

import java.lang.reflect.Method;

public class ShowMessageBean extends MessageBean{

	private Object gui; 
	
	public void setGui(Object gui) {
		this.gui = gui;
	}
	
	public Object getGui() {
		return gui;
	}

	
	public void showMessage() {
		try {
			Method methodToBeCalled = gui.getClass().getMethod("showMessage", String.class, boolean.class);
			methodToBeCalled.invoke(gui, super.getMessage(), super.getType());
		} catch (Exception e){
			//messaggio perso poichè l'intefaccia non implementa questo metodo
		}
	}
}
