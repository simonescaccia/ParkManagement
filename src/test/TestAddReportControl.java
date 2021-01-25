package test;

import org.junit.Test;
import static org.junit.Assert.*;

import logic.control.bean.AddReportBean;
import logic.control.bean.MessageBean;
import logic.control.bean.PositionBean;
import logic.control.controlapplicativo.AddReportControl;
import logic.exception.NullAttractionNameException;
import logic.exception.NullLoginException;

public class TestAddReportControl {

	@Test
	public void testQueueReportConsecutiveInsert() throws NullAttractionNameException, NullLoginException {
		//consecutive report for the same attraction should be not allowed
		AddReportBean aRB = new AddReportBean();
		PositionBean pB = new PositionBean();
		AddReportControl aRC = new AddReportControl();
		MessageBean mB1;
		MessageBean mB2;
		
		pB.setLatitude(41.6434);
		pB.setLongitude(13.3473);
		
		aRB.setAttractionName("Capitan Jack-Restaurant");
		aRB.setIsLast(false);
		aRB.setPositionBean(pB);
		aRB.setQueueLen(20);
		aRB.setUserID("105206612834247983099");
		
		mB1 = aRC.addQueueReport(aRB);
		mB2 = aRC.addQueueReport(aRB);
		
		boolean expression = mB1.getType() && !mB2.getType() && mB2.getMessage().equals("Aspetta ancora un po'");
		
		assertTrue(expression);
		
	}
}
