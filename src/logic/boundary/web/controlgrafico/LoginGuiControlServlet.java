package logic.boundary.web.controlgrafico;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.control.bean.MessageBean;
import logic.control.controlapplicativo.LoginControl;

public class LoginGuiControlServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		
		System.out.println("qui");
		String idToken = request.getParameter("idtoken");
		
		//comunico il token da validare al Controller applicativo per la verifica
		LoginControl loginControl = new LoginControl();
		try {
			loginControl.verifyToken(idToken);
		} catch (GeneralSecurityException| IOException e) {
			MessageBean mB = new MessageBean();
			mB.setMessage("Token non sicuro");
			mB.setType(false);
		    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		    try {
		    	rd.forward(request, response);
		    } catch (ServletException|IOException ev) {
		    	request.setAttribute("mB", mB);
		    }
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
