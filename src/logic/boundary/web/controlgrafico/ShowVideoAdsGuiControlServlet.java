package logic.boundary.web.controlgrafico;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.control.bean.UserBean;
import logic.control.controlapplicativo.AddReportControl;
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
		
		String userID = (String)request.getSession().getAttribute("userID");
		
		//call the controller
		AddReportControl aRC = new AddReportControl();
		UserBean vAB= new UserBean();
		try {
			vAB.setUserID(userID);
		} catch (NullLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		aRC.showVideoAds(vAB);

		
	
	}
	
}
