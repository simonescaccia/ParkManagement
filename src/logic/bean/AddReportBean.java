package logic.bean;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import logic.controlgrafico.AddReportGuiControl;

public class AddReportBean {

	private String queueLen;
	private boolean isLast;
	private Object gui;
	private String attractionName;
	
	public void setQueueLen(String s) {
		queueLen = s;
	}
	
	public void setIsLast(boolean b) {
		isLast = b;
	}
	
	public void setGui(Object gui) {
		this.gui = gui;
	}
	
	public void setAttraction(String a) {
		attractionName = a;
	}
	
	public String getAttraction() {
		return attractionName;
	}
	
	public String getQueueLen() {
		return queueLen;
	}
	
	public boolean getIsLast() {
		return isLast;
	}
	
	public Object getGui() {
		return gui;
	}
	
	private boolean isANaturalNumber() {
	    Pattern pattern = Pattern.compile("\\d*[1-9]\\d*");
	    Matcher matcher = pattern.matcher(this.queueLen);
	    return matcher.matches();
	}
	
	public void addReport() {
		//control
		boolean res = isANaturalNumber();
		
		if(!res) {
			//return failure
			ShowMessageBean bSM = new ShowMessageBean();
			bSM.setMessage("Inserire un numero da 0 a 100");
			bSM.setType(false);
			bSM.setGui(gui);
			bSM.showMessage();
		} else {
			//call GUI controller Add Report
			AddReportGuiControl cGAR = new AddReportGuiControl(this);
			cGAR.insertQueueLenght();
		}
	}
}
