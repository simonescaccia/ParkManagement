package logic.control.controlapplicativo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logic.control.bean.ParkAttractionBean;
import logic.control.bean.ReportBean;
import logic.control.bean.UserBean;
import logic.entities.dao.ReportDAO;
import logic.entities.model.Report;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ReportNotFoundException;

public class InsertFeedbackControl {

	public List<ReportBean> showReports(UserBean uB) throws ReportNotFoundException, ParkAttractionNotFoundException{
		
		List<Report> listOfReports = ReportDAO.selectLastReport(null, uB.getUserID());
		
		List<ReportBean> listOfReportBean= new ArrayList<>();
		Iterator<Report> i = listOfReports.iterator();
		while(i.hasNext()) {
			Report r = i.next();
			
			ReportBean rB = new ReportBean();
			rB.setLenQueue(r.getLengthQueue());
			rB.setDate(r.getDate());
			
			ParkAttractionBean pAB = new ParkAttractionBean();
			pAB.setName(r.getParkAttraction().getName());
			pAB.setImg(r.getParkAttraction().getImg());
			rB.setParkAttraction(pAB);

			listOfReportBean.add(rB);
		}
		
		return listOfReportBean;
		
	}
}
