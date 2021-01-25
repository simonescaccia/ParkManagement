package logic.control.controlapplicativo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import logic.control.bean.ParkAttractionBean;
import logic.control.bean.ReportBean;
import logic.control.bean.ShowAttractionsBean;
import logic.entities.dao.ParkAttractionDAO;
import logic.entities.dao.ReportDAO;
import logic.entities.model.ParkAttraction;
import logic.entities.model.Report;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ReportNotFoundException;

public class ViewAttractionsControl {

	
	public ParkAttractionBean showAttractionInformation(ParkAttractionBean pAB) throws ParkAttractionNotFoundException, ReportNotFoundException{
		
		ParkAttraction pA = ParkAttractionDAO.selectAttractionByName(pAB.getName());
		
		List<Report> listOfLastReport = ReportDAO.selectLastReport(pA, null);
		
		//fill the bean to return
		ParkAttractionBean pABean = new ParkAttractionBean();
		pABean.setImgC(pA.getCategory().getImgC());
		pABean.setName(pA.getName());
		pABean.setImg(pA.getImg());
		
		List<ReportBean> listOfReportBean= new ArrayList<>();
		Iterator<Report> i = listOfLastReport.iterator();
		while(i.hasNext()) {
			Report r = i.next();
			
			ReportBean rB = new ReportBean();
			rB.setLenQueue(r.getLengthQueue());
			rB.setDate(r.getDate());

			listOfReportBean.add(rB);
		}
		
		pABean.setListOfReports(listOfReportBean);
		
		return pABean;
	}
	
	public List<ParkAttractionBean> showAttractions(ShowAttractionsBean sAB) throws ParkAttractionNotFoundException{
		
		List<ParkAttraction> listOfParkAttractions;
		//flag order
		boolean needToOrder = false;
		
		if(sAB.getOrder() == null || sAB.getOrder().equals("distance")) {
			//devo ordinare
			needToOrder = true;
		}
		
		listOfParkAttractions = getListOfParkAttraction(sAB);
		
		//fill the parkAttractionBean
		VerifyConditionReportControl vCC = new VerifyConditionReportControl();
		
		List<ParkAttractionBean> listOfParkAttractionBean = new ArrayList<>();
		Iterator<ParkAttraction> i = listOfParkAttractions.iterator();
		while(i.hasNext()) {
			ParkAttraction pA = i.next();
			ParkAttractionBean pABean = new ParkAttractionBean();
			pABean.setName(pA.getName());
			pABean.setCategoryName(pA.getCategory().getName());
			pABean.setImg(pA.getImg());
			pABean.setImgC(pA.getCategory().getImgC());

			//convert time in minutes
			if(pA.getQueue().getWaitingTime() != null) {
				long millisecondsGTW = pA.getQueue().getWaitingTime().getTime();
				long milliseconds = millisecondsGTW+(60*60*1000);
				int seconds = (int) milliseconds/1000;
				int minutes = seconds/60;
				pABean.setWaitingTime(minutes);
			} else {
				//non ci sono informazioni attuali sulla coda
				pABean.setWaitingTime(-1);
			}
			
			//fill the distance from ParkVisitor
			if(sAB.getPositionBean()!=null) {
				double lat1 = sAB.getPositionBean().getLatitude();
				double lng1 = sAB.getPositionBean().getLongitude();
				double lat2 = pA.getPosition().getLatitude();
				double lng2 = pA.getPosition().getLongitude();
				double distance = vCC.distanceInKmBetweenEarthCoordinates(lat1, lng1, lat2, lng2);
				int distanceInMeters = (int)(distance*1000);
				pABean.setDistanceFromUser(distanceInMeters);
			}
			
			listOfParkAttractionBean.add(pABean);
		}
		
		//List<ParkAttraction> ordina by distance
		if(needToOrder) {
			orderByDistanceAsc(listOfParkAttractionBean);
		}
		
		return listOfParkAttractionBean;
	}
	
	public void orderByDistanceAsc(List<ParkAttractionBean> listOfParkAttractionBean){
		
		Collections.sort(listOfParkAttractionBean, (p1, p2) -> {
		    if(p1.getDistanceFromUser() <= p2.getDistanceFromUser()) {
		    	return -1;
		    } else {
		    	return 1;
		    }
		});
	}
	
	protected List<ParkAttraction> getListOfParkAttraction(ShowAttractionsBean sAB) throws ParkAttractionNotFoundException {
		//per ogni attrazione devo ritornare il nome, il tempo di attesa, la categoria, e la distanza
		//se sAB.getOrder() == null per default e' by distance e verrà calcolato dopo
		//se sAB.getFilter() == null
		boolean expression1 = sAB.getFilter() == null;
		//ordinare
		boolean expression2 = sAB.getOrder() == null || sAB.getOrder().equals("distance");

		return ParkAttractionDAO.selectAttractionsFilterOrder(sAB.getFilter(), expression1, !expression2);

	}
}
