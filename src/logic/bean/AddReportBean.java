package logic.bean;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import logic.boundary.controlgrafico.AddReportGuiControl;
import logic.boundary.view.AddReportView;

public class AddReportBean {

	private String queueLen;
	private boolean isLast;
	private String attractionName;
	private AddReportView gui;
	
	public void setQueueLen(String s) {
		queueLen = s;
	}
	
	public void setIsLast(boolean b) {
		isLast = b;
	}
	
	public void setGui(AddReportView gui) {
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
	
	public AddReportView getGui() {
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
