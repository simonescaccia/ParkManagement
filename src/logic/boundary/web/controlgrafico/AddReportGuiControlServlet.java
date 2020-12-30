package logic.boundary.web.controlgrafico;

import logic.control.bean.MessageBean;
import logic.control.bean.AddReportBean;
import logic.control.controlapplicativo.AddReportControl;

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
		
		Object obj = request.getSession().getAttribute("addReportBean");
		aRB.setAttractionName(((AddReportBean)obj).getAttractionName());
		
		//checkbox is null or on
		aRB.setIsLast(request.getParameter("isLast") != null);
		
		aRB.setQueueLen(request.getParameter("queueLen"));
		
		//convert String to int
		int queueLen;
		try {
			queueLen= Integer.parseInt(aRB.getQueueLen());
			//call the controller
			AddReportControl aRC = new AddReportControl();
			MessageBean res = aRC.tryToUpdate(queueLen, aRB.getAttractionName(), aRB.getIsLast());
			
			//comunico l'esito
			mB.setMessage(res.getMessage());
			mB.setType(res.getType());
		} catch (NumberFormatException e) {
			//return failure
			mB.setMessage("Inserire un numero da 0 a 100");
			mB.setType(false);
		}
		
		
	    request.setAttribute("mB", mB);
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/jspPages/addReport.jsp?forward=true");
	    try {
	    	rd.forward(request, response);
	    } catch (ServletException|IOException e) {
	    	request.setAttribute("mB", mB);
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
	    	request.setAttribute("mB", "exception");
	    }
	}
}
