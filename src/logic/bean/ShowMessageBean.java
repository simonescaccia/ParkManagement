package logic.bean;


import logic.boundary.view.AddReportView;

public class ShowMessageBean extends MessageBean{

	private AddReportView gui; 
	
	public void setGui(AddReportView gui) {
		this.gui = gui;
	}
	
	public AddReportView getGui() {
		return gui;
	}

	
	public void showMessage() {
		gui.showMessage(super.getMessage(), super.getType());
	}
}
