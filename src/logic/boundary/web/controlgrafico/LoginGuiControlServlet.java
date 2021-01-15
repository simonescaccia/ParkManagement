package logic.boundary.web.controlgrafico;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.boundary.web.view.LoginGoogleView;
import logic.control.bean.MessageBean;

public class LoginGuiControlServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		
		String idToken = request.getParameter("idtoken");
		String pageRedirect = request.getParameter("page");
		String param = request.getParameter("param");
		
		//comunico il token da validare alla view LoginGoogleView per la verifica
		LoginGoogleView lGV = new LoginGoogleView();
		try {
			String idUser;
			idUser = lGV.loginWebVerifyToken(idToken);
			request.getSession().setAttribute("userID", idUser);
			
		} catch (GeneralSecurityException| IOException e) {
			MessageBean mB = new MessageBean();
			mB.setMessage("Token non sicuro");
			mB.setType(false);
		    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			try {
		    	rd.forward(request, response);
		    } catch (ServletException|IOException ev) {
		    	ev.printStackTrace();
		    }
		}
		
	    RequestDispatcher rd = request.getRequestDispatcher(pageRedirect.substring(11)+"?attractionName="+param);
		try {
	    	rd.forward(request, response);
	    } catch (ServletException|IOException ev) {
	    	ev.printStackTrace();
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
