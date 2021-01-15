package logic.boundary.web.controlgrafico;

import logic.control.bean.MessageBean;
import logic.control.bean.AddReportBean;
import logic.control.controlapplicativo.AddReportControl;
import logic.exception.NullLoginException;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddReportGuiControlServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		//control the value of the form	
		MessageBean mB = new MessageBean();
		AddReportBean aRB = new AddReportBean();
		
		//fill the AddReportBean
		String queueLenS = request.getParameter("queueLen");
		boolean isLast = (request.getParameter("isLast") != null);
		String attractionName = (String)request.getSession().getAttribute("attractionName");
		
		aRB.setIsLast(isLast);
		aRB.setAttractionName(attractionName);
		
		try {
			//convert queueLen String to int
			int queueLen;
			queueLen= Integer.parseInt(queueLenS);
			aRB.setQueueLen(queueLen);
			
			//controll login
			String userID = (String)request.getSession().getAttribute("userID");
			if(userID != null) {
				aRB.setUserID(userID);
			} else {
				throw new NullLoginException("Add Report need a not null idUser");
			}
			
			//call the controller
			AddReportControl aRC = new AddReportControl();
			MessageBean res = aRC.tryToUpdate(aRB);
			
			//comunico l'esito 
			mB.setMessage(res.getMessage());
			mB.setType(res.getType());
		} catch (NumberFormatException e) {
			//comunico l'errore di conversione
			mB.setMessage("Inserire un numero da 0 a 100");
			mB.setType(false);
		} catch (NullLoginException e) {
			//return failure login
			mB.setMessage("Login richiesto");
			mB.setType(false);			
		}

		//forward e invia la risposta
	    request.setAttribute("mB", mB);
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/jspPages/addReport.jsp?attractionName="+attractionName);
	    try {
	    	rd.forward(request, response);
	    } catch (ServletException|IOException e) {
	    	e.printStackTrace();
	    }
	
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
	    try {
	    	rd.forward(request, response);
	    } catch (ServletException|IOException e) {
	    	e.printStackTrace();
	    }
	}
}
