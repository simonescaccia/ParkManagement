package logic.control.controlapplicativo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import logic.control.bean.FeedbackBean;
import logic.control.bean.MessageBean;
import logic.control.bean.ParkAttractionBean;
import logic.control.bean.ReportBean;
import logic.control.bean.UserBean;
import logic.entities.dao.ParkAttractionDAO;
import logic.entities.dao.ParkVisitorDAO;
import logic.entities.dao.QueueDAO;
import logic.entities.dao.ReportDAO;
import logic.entities.model.ParkAttraction;
import logic.entities.model.Report;
import logic.exception.DBFailureException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ParkVisitorNotFoundException;
import logic.exception.QueueNotFoundException;
import logic.exception.ReportNotFoundException;

public class InsertFeedbackControl {

	public List<ReportBean> showReports(UserBean uB) throws ReportNotFoundException, ParkAttractionNotFoundException{
		
		List<Report> listOfReports = ReportDAO.selectListOfLastReports(null, uB.getUserID());
		
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
	
	public MessageBean insertFeedback(FeedbackBean fB) throws ReportNotFoundException, ParkAttractionNotFoundException, QueueNotFoundException, ParkVisitorNotFoundException {
		
		Report r = ReportDAO.selectReport(fB.getUserID(), fB.getAttrName(), fB.getDate());
		
		MessageBean mB = new MessageBean();
		//verifica se è passato abbastanza tempo per poter inserire il feedback
		boolean res = verifyCountDown(fB, r.getWaitingTime());
		
		if(res) {
			//set the new avg waiting time for this attraction, update the report and add coin to the parkvisitor
			setNewAverageTime(fB, r.getLengthQueue());

		} else {
			mB.setMessage("Sei ancora in coda!");
			mB.setType(false);
			return mB;
		}
		
		mB.setMessage("inserimento completato");
		mB.setType(true);
		return mB;
	}
	
	protected boolean verifyCountDown(FeedbackBean fB, Time time) {
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		long difference = now.getTime()-(fB.getDate().getTime()+time.getTime());
		
		return difference >= 0;
	}
	
	protected void setNewAverageTime(FeedbackBean fB, int lenQueue) throws ParkAttractionNotFoundException, QueueNotFoundException, ParkVisitorNotFoundException {
		ParkAttraction pA = ParkAttractionDAO.selectAttractionByName(fB.getAttrName());
		double lastwtime = pA.getQueue().getAvgWaitingTime();
		
		long milliseconds = (fB.getTimeFeedback().getTime() +60*60*1000);
		long seconds = milliseconds/1000;
		int minute = (int)seconds/60;
		double avgWTimeFeedback = (double)minute/lenQueue;
		
		//calcola la nuova media tramite una media esponenziale dando piu valore alla nuova media
		double perc= 0.60;
		
		try {
			int idQueue = pA.getQueue().getIdQueue();
			QueueDAO.updateAVGWaitingTime(idQueue, (perc*(avgWTimeFeedback)) + ((1.0-perc)*(lastwtime)));
			ReportDAO.updateReportSetFeedback(fB.getAttrName(),fB.getUserID(),fB.getDate());
			ParkVisitorDAO.incrementCoin(fB.getUserID());
		} catch (DBFailureException e) {
			throw new QueueNotFoundException("Update queue failure");
		}
		
	}
}	
