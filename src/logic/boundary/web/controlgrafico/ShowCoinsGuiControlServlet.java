package logic.boundary.web.controlgrafico;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.control.bean.CoinsBean;
import logic.control.bean.MessageBean;
import logic.control.bean.UserBean;
import logic.control.controlapplicativo.ConvertCouponControl;
import logic.exception.NullLoginException;

import logic.exception.ParkVisitorNotFoundException;

public class ShowCoinsGuiControlServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
			
			RequestDispatcher rd = request.getRequestDispatcher("/jspPages/coupons.jsp?forward=false");
		    try {
		    	rd.forward(request, response);
		    } catch (ServletException | IOException ex) {
		    	ex.printStackTrace();
		    }
			
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		
		RequestDispatcher rd = null;
		
		try {
			UserBean uB = new UserBean();
			uB.setUserID((String)request.getSession().getAttribute("userID"));
			
			ConvertCouponControl cCC = new ConvertCouponControl();
			CoinsBean cB = cCC.getCoins(uB);
			
			request.setAttribute("coins", cB.getCoins());
			rd = request.getRequestDispatcher("/jspPages/coupons.jsp?forward=true");
			
		} catch (NullLoginException | ParkVisitorNotFoundException e) {
			MessageBean mB = new MessageBean();
			mB.setMessage(e.getMessage());
			mB.setType(false);
			
			request.setAttribute("mB", mB);
			rd = request.getRequestDispatcher("/jspPages/coupons.jsp?forward=false");
		}
		
	    try {
	    	rd.forward(request, response);
	    } catch (ServletException | IOException e) {
	    	e.printStackTrace();
	    }
	
	}


}
