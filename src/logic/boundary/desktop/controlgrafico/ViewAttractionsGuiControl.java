package logic.boundary.desktop.controlgrafico;

import java.util.List;

import logic.boundary.desktop.view.AddReportView;
import logic.boundary.desktop.view.AttractionsView;
import logic.boundary.desktop.view.PositionGoogleMapsView;
import logic.control.bean.MessageBean;
import logic.control.bean.ParkAttractionBean;
import logic.control.bean.PositionBean;
import logic.control.bean.ShowAttractionsBean;
import logic.control.controlapplicativo.ViewAttractionsControl;
import logic.exception.FilterValueErrorException;
import logic.exception.OrderValueErrorException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.PositionNotFoundException;
import logic.exception.ReportNotFoundException;

public class ViewAttractionsGuiControl extends GenericGuiControl{
	
	public ViewAttractionsGuiControl(AttractionsView aV) {
		super.gV = aV;
	}
	
	public ViewAttractionsGuiControl(AddReportView aRV) {
		super.gV = aRV;
	}
	
	public void showAttractionInformation(String attractionName) {
		
		ParkAttractionBean pAB = new ParkAttractionBean();
		pAB.setName(attractionName);
		MessageBean mB = new MessageBean();
		
		ViewAttractionsControl vAC = new ViewAttractionsControl();
		try {
			ParkAttractionBean pABean = vAC.showAttractionInformation(pAB);
			
			((AddReportView)super.gV).showattractionInfo(pABean);
			
		} catch (ParkAttractionNotFoundException | ReportNotFoundException e) {
			mB.setMessage(e.getMessage());
			mB.setType(false);
			super.showMessage(mB);
		}
	}
	
	public void showAttractions(String order, String filter) {
			
		//showAttractionsBean
		ShowAttractionsBean sAB = new ShowAttractionsBean();
		MessageBean mB = new MessageBean();

		try {
			
			sAB.setFilter(filter);
			sAB.setOrder(order);
			
			fillPositionBean(sAB);
			
			ViewAttractionsControl vAC = new ViewAttractionsControl();
			List<ParkAttractionBean> listOfParkAttractionBean = vAC.showAttractions(sAB);

			((AttractionsView)super.gV).showAttractions(listOfParkAttractionBean);
			
		} catch (OrderValueErrorException | FilterValueErrorException | ParkAttractionNotFoundException e) {
			//return failure
			mB.setMessage(e.getMessage());
			mB.setType(false);
			super.showMessage(mB);	
		}
		
	}
	
	protected void fillPositionBean(ShowAttractionsBean sAB) {
		try {
			//get the position of user
			PositionBean pB = new PositionBean();
			PositionGoogleMapsView pGM = new PositionGoogleMapsView();
			pGM.getPosition(pB);
			sAB.setPositionBean(pB);
		} catch (PositionNotFoundException e){
			//internet connection failure
			MessageBean mB = new MessageBean();
			
			mB.setMessage(e.getMessage());
			mB.setType(false);
			new Thread(() -> 
				super.showMessage(mB)		
			).start();
		}
	}
	
}
