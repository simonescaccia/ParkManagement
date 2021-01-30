package logic.boundary.web.controlgrafico;

import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import logic.control.bean.MessageBean;
import logic.control.bean.ReportBean;
import logic.control.bean.UserBean;
import logic.control.controlapplicativo.InsertFeedbackControl;
import logic.exception.NullLoginException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ReportNotFoundException;

public class ShowYourReportsGuiControlServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		
		//showAttractionsBean
		String userID = (String)request.getSession().getAttribute("userID");
		RequestDispatcher rd = request.getRequestDispatcher("/jspPages/yourReports.jsp?forward=false");
		
		try {
			
			UserBean uB = new UserBean();
			uB.setUserID(userID);
			
			InsertFeedbackControl iFC = new InsertFeedbackControl();
			List<ReportBean> listOfReports = iFC.showReports(uB);
				
			Iterator<ReportBean> i = listOfReports.iterator();
			while(i.hasNext()) {
				ReportBean reportBean = i.next();
				
				//encode the InputStream to string base64
				byte[] bytes = IOUtils.toByteArray(reportBean.getParkAttraction().getImg());
				reportBean.getParkAttraction().setImgAttractionString(Base64.getEncoder().encodeToString(bytes));
				
			}
			
			request.setAttribute("listOfReports", listOfReports);
			
		    rd = request.getRequestDispatcher("/jspPages/yourReports.jsp?forward=true");
			
		} catch (NullLoginException | ReportNotFoundException | ParkAttractionNotFoundException e) {
			//return failure
			MessageBean mB = new MessageBean();
			
			mB.setMessage(e.getMessage());
			mB.setType(false);
			
			request.setAttribute("mB", mB);

		} finally {
		    try {
		    	rd.forward(request, response);
		    } catch (ServletException|IOException ex) {
		    	ex.printStackTrace();
		    }
		}
		
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		try {
			doPost(request, response);
		} catch (ServletException | IOException e) {
			MessageBean mB = new MessageBean();
			mB.setMessage(e.getMessage());
			mB.setType(false);
			request.setAttribute("mB", mB);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jspPages/yourReports.jsp?forward=false");
		    try {
		    	rd.forward(request, response);
		    } catch (ServletException | IOException ex) {
		    	ex.printStackTrace();
		    }
			
		}
	}
	
}
