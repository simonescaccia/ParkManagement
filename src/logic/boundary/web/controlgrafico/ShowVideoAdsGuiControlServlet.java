package logic.boundary.web.controlgrafico;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.control.bean.MessageBean;
import logic.control.bean.UserBean;
import logic.control.controlapplicativo.ShowVideoAdsControl;
import logic.exception.NullLoginException;

public class ShowVideoAdsGuiControlServlet extends HttpServlet{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		MessageBean mB = new MessageBean();
		String userID = (String)request.getSession().getAttribute("userID");
		
		//call the controller
		try {
			UserBean vAB= new UserBean();
			vAB.setUserID(userID);
			ShowVideoAdsControl sVAC = new ShowVideoAdsControl();
			sVAC.loadVideoAds(vAB.getUserID());
		} catch (NullLoginException e) {
			//return failure login
			mB.setMessage(e.getMessage());
			mB.setType(false);
			
			//forward e invia la risposta
		    request.setAttribute("mB", mB);
		    
		    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		    try {
		    	rd.forward(request, response);
		    } catch (ServletException|IOException ex) {
		    	ex.printStackTrace();
		    }
		}
	
	}
	
}