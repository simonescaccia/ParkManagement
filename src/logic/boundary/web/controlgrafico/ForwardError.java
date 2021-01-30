package logic.boundary.web.controlgrafico;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.control.bean.MessageBean;

public class ForwardError {
	
	private ForwardError() {}
	
	public static void forwardError(MessageBean mB, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mB", mB);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jspPages/yourReports.jsp?forward=false");
	    try {
	    	rd.forward(request, response);
	    } catch (ServletException | IOException ex) {
	    	ex.printStackTrace();
	    }
	}
}
