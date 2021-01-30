package logic.boundary.web.controlgrafico;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import logic.control.bean.MessageBean;
import logic.control.bean.ParkAttractionBean;
import logic.control.controlapplicativo.ViewAttractionsControl;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ReportNotFoundException;

public class ShowAttractionInfoGuiControlServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		
		RequestDispatcher rd = null;
		
		String attractionName = request.getParameter("attractionName");
		
		//fill the bean
		ParkAttractionBean pAB = new ParkAttractionBean();
		pAB.setName(attractionName);
		MessageBean mB = new MessageBean();
		
		ViewAttractionsControl vAC = new ViewAttractionsControl();
		try {
			ParkAttractionBean pABean = vAC.showAttractionInformation(pAB);
			
			//encode the InputStream to string base64
			byte[] bytes = IOUtils.toByteArray(pABean.getImg());
			pABean.setImgAttractionString(Base64.getEncoder().encodeToString(bytes));
			
			byte[] bytes2 = IOUtils.toByteArray(pABean.getImgC());
			pABean.setImgCategoryString(Base64.getEncoder().encodeToString(bytes2));
			
			request.setAttribute("parkAttractionInfo", pABean);
			
			rd = request.getRequestDispatcher("/jspPages/addReport.jsp?forward=true&attractionName="+attractionName);
			
		} catch (ParkAttractionNotFoundException | ReportNotFoundException e) {
			mB.setMessage(e.getMessage());
			mB.setType(false);
			request.setAttribute("mB", mB);
			rd = request.getRequestDispatcher("/jspPages/addReport.jsp?forward=false&attractionName="+attractionName);
		}		
		
	    try {
	    	rd.forward(request, response);
	    } catch (ServletException | IOException e) {
	    	e.printStackTrace();
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
			
			RequestDispatcher rd = request.getRequestDispatcher("/jspPages/addReport.jsp?attractionName"+request.getAttribute("attractionName")+"&forward=false");
		    try {
		    	rd.forward(request, response);
		    } catch (ServletException | IOException ex) {
		    	ex.printStackTrace();
		    }
			
		}
	}
	
}
