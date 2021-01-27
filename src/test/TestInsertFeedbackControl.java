package test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.sql.Time;

import org.junit.Test;

import logic.control.bean.AddReportBean;
import logic.control.bean.FeedbackBean;
import logic.control.bean.MessageBean;
import logic.control.bean.PositionBean;
import logic.control.controlapplicativo.AddReportControl;
import logic.control.controlapplicativo.InsertFeedbackControl;
import logic.entities.dao.ReportDAO;
import logic.entities.model.Report;
import logic.exception.DBFailureException;
import logic.exception.NullAttractionNameException;
import logic.exception.NullLoginException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ParkVisitorNotFoundException;
import logic.exception.QueueNotFoundException;
import logic.exception.ReportNotFoundException;
import test.cleanup.CleanUP;

public class TestInsertFeedbackControl {

	@Test
	public void testInsertFeedbackImmediatlyAfterReport() throws NullAttractionNameException, NullLoginException, DBFailureException, ReportNotFoundException, ParkAttractionNotFoundException, QueueNotFoundException, ParkVisitorNotFoundException, SQLException {
		
		String userID = "105206612834247983099" ;
		String attractionName = "Rustler Roundup Shootin Gallery";
		
		//inserisco un report
		AddReportBean aRB = new AddReportBean();
		PositionBean pB = new PositionBean();
		AddReportControl aRC = new AddReportControl();
		MessageBean mB1;
		
		pB.setLatitude(41.6497);
		pB.setLongitude(13.3479999);
		
		//fill the bean
		aRB.setAttractionName(attractionName);
		aRB.setIsLast(true);
		aRB.setPositionBean(pB);
		aRB.setQueueLen(30);
		aRB.setUserID(userID);
		
		mB1 = aRC.addQueueReport(aRB);
		if(!mB1.getType()) {
			throw new DBFailureException("Add report failure");
		}		
		
		//l'inserimento del feedback dovrebbe avvenire quando la coda è terminata
		MessageBean mB2;
		FeedbackBean fB = new FeedbackBean();
		Report r = ReportDAO.selectLastReport(userID, attractionName);
		//fill the bean
		fB.setDate(r.getDate());
		fB.setUserID(userID);
		fB.setAttrName(attractionName);
		
		Time time = new Time(50000);
		fB.setTimeFeedback(time);
		
		InsertFeedbackControl iFC = new InsertFeedbackControl();
		mB2 = iFC.insertFeedback(fB);
		
		boolean expression = !mB2.getType() && mB2.getMessage().equals("Sei ancora in coda!");
		
		//clean up the DB, delete the report. Need this for consecutive test or Junit suite
		Report report = ReportDAO.selectLastReport(userID, attractionName);
		CleanUP.deleteReport(attractionName, userID, report.getDate());
		
		assertTrue(expression);
		
		//clean up the DB delete the report
		
	}
}
