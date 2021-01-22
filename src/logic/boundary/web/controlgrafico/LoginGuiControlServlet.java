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
import logic.control.controlapplicativo.LoginControl;
import logic.exception.DBFailureException;

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
		
		//comunico il token da validare alla view LoginGoogleView per la verifica
		LoginGoogleView lGV = new LoginGoogleView();
		MessageBean mB = new MessageBean();
		
		try {
			String idUser;
			idUser = lGV.loginWebVerifyToken(idToken);
			
			//verificare se l'utente è già stato inserito nel database
			//aggiungere il Park Visitor se non presente nel database
			LoginControl lC = new LoginControl();
			
			lC.verifyUserOnDB(idUser);
			
			request.getSession().setAttribute("userID", idUser);
			
			mB.setMessage("Utente Loggato");
			mB.setType(true);
			
		} catch (GeneralSecurityException| IOException e) {
			mB.setMessage("Token non sicuro");
			mB.setType(false);
		} catch (DBFailureException exc) {
			mB.setMessage(exc.getMessage());
			mB.setType(false);
		}
			
		//risposta
	    request.setAttribute("mB", mB);
		
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
