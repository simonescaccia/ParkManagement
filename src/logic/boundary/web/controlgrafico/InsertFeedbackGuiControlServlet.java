package logic.boundary.web.controlgrafico;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.control.bean.FeedbackBean;
import logic.control.bean.MessageBean;
import logic.control.controlapplicativo.InsertFeedbackControl;
import logic.exception.NullAttractionNameException;
import logic.exception.NullLoginException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ParkVisitorNotFoundException;
import logic.exception.QueueNotFoundException;
import logic.exception.ReportNotFoundException;

public class InsertFeedbackGuiControlServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		
		String attractionName = request.getParameter("attractionName");
		String feedbackS = request.getParameter("feedback");
		String dateS = request.getParameter("date");
		
		MessageBean mB = new MessageBean();
		FeedbackBean fB = new FeedbackBean();
		
		try {
			//insert park attraction name into the bean
			fB.setAttrName(attractionName);
			
			//convert feedback String to int and to Time	
			int feedbackI = Integer.parseInt(feedbackS);
			if((Integer.signum(feedbackI) == -1) || feedbackI>200) {
				throw new NumberFormatException();
			}
			long milliseconds = (long)((feedbackI*60*1000)-(60*60*1000));
			Time feedbackTime = new Time(milliseconds);
			fB.setTimeFeedback(feedbackTime);
			
			fB.setDate(Timestamp.valueOf(dateS));
			
			//insert userID into the bean
			fB.setUserID((String)request.getSession().getAttribute("userID"));
			
			InsertFeedbackControl iFC = new InsertFeedbackControl();
			mB = iFC.insertFeedback(fB);
			
		} catch (NumberFormatException e) {
			//return failure convertion
			mB.setMessage("Valore non valido");
			mB.setType(false);
		} catch (NullAttractionNameException | NullLoginException | ReportNotFoundException | QueueNotFoundException | ParkAttractionNotFoundException | ParkVisitorNotFoundException e) {
			mB.setMessage(e.getMessage());
			mB.setType(false);
		} 
		
		//forward e invia la risposta
		request.setAttribute("mB", mB);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jspPages/yourReports.jsp");
	    try {
	    	rd.forward(request, response);
	    } catch (ServletException | IOException e) {
	    	e.printStackTrace();
	    }
	
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		RequestDispatcher rd = request.getRequestDispatcher("/jspPages/yourReports.jsp");
	    try {
	    	rd.forward(request, response);
	    } catch (ServletException|IOException e) {
	    	e.printStackTrace();
	    }
	}

}
