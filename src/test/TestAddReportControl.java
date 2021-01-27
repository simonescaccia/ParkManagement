package test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;

import logic.control.bean.AddReportBean;
import logic.control.bean.MessageBean;
import logic.control.bean.PositionBean;
import logic.control.controlapplicativo.AddReportControl;
import logic.entities.dao.ReportDAO;
import logic.entities.model.Report;
import logic.exception.DBFailureException;
import logic.exception.NullAttractionNameException;
import logic.exception.NullLoginException;
import logic.exception.ReportNotFoundException;
import test.cleanup.CleanUP;

public class TestAddReportControl {

	@Test
	public void testAddQueueReportConsecutiveInsert() throws NullAttractionNameException, NullLoginException, ReportNotFoundException, DBFailureException, SQLException {
		
		String attractionName = "The Lego Store";
		String userID = "105206612834247983099";
		
		//consecutive report for the same attraction should be not allowed
		AddReportBean aRB = new AddReportBean();
		PositionBean pB = new PositionBean();
		AddReportControl aRC = new AddReportControl();
		MessageBean mB1;
		MessageBean mB2;
		
		pB.setLatitude(41.6428);
		pB.setLongitude(13.3465);
		
		aRB.setAttractionName(attractionName);
		aRB.setIsLast(false);
		aRB.setPositionBean(pB);
		aRB.setQueueLen(20);
		aRB.setUserID(userID);
		
		mB1 = aRC.addQueueReport(aRB);
		mB2 = aRC.addQueueReport(aRB);
		
		boolean expression = mB1.getType() && !mB2.getType() && mB2.getMessage().equals("Aspetta ancora un po'");
		
		//clean up the DB, delete the report. Need this for consecutive test or Junit suite
		Report r = ReportDAO.selectLastReport(userID, attractionName);
		CleanUP.deleteReport(attractionName, userID, r.getDate());
		
		assertTrue(expression);
				
	}
	
}
