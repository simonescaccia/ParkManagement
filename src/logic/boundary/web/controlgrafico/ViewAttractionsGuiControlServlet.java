package logic.boundary.web.controlgrafico;

import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import logic.boundary.desktop.view.PositionGoogleMapsView;
import logic.control.bean.MessageBean;
import logic.control.bean.ParkAttractionBean;
import logic.control.bean.PositionBean;
import logic.control.bean.ShowAttractionsBean;
import logic.control.controlapplicativo.ViewAttractionsControl;
import logic.exception.FilterValueErrorException;
import logic.exception.OrderValueErrorException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.PositionNotFoundException;


public class ViewAttractionsGuiControlServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException
	{
		//showAttractionsBean
		ShowAttractionsBean sAB = new ShowAttractionsBean();
		MessageBean mB = new MessageBean();

		String filter = request.getParameter("filter");
		String order = request.getParameter("order");
		
		try {
			
			sAB.setFilter(filter);
			sAB.setOrder(order);
			
			mB = fillPositionBean(sAB);
			
			ViewAttractionsControl vAC = new ViewAttractionsControl();
			List<ParkAttractionBean> listOfParkAttractionBean = vAC.showAttractions(sAB);

			Iterator<ParkAttractionBean> i = listOfParkAttractionBean.iterator();
			while(i.hasNext()) {
				ParkAttractionBean pABean = i.next();
				
				//encode the InputStream to string base64
				byte[] bytes = IOUtils.toByteArray(pABean.getImg());
				pABean.setImgAttractionString(Base64.getEncoder().encodeToString(bytes));
				
				byte[] bytes2 = IOUtils.toByteArray(pABean.getImgC());
				pABean.setImgCategoryString(Base64.getEncoder().encodeToString(bytes2));
			}
			
			request.setAttribute("listOfParkAttraction", listOfParkAttractionBean);
			
		} catch (OrderValueErrorException | FilterValueErrorException | ParkAttractionNotFoundException e) {
			//return failure
			mB.setMessage(e.getMessage());
			mB.setType(false);	
		}
		
		request.setAttribute("mB", mB);
		
	    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?forward=true");
	    try {
	    	rd.forward(request, response);
	    } catch (ServletException|IOException e) {
	    	e.printStackTrace();
	    }
	
	}
	
	protected MessageBean fillPositionBean(ShowAttractionsBean sAB) {
		try {
			//get the position of user
			PositionBean pB = new PositionBean();
			PositionGoogleMapsView pGM = new PositionGoogleMapsView();
			pGM.getPosition(pB);
			sAB.setPositionBean(pB);
			return null;
		} catch (PositionNotFoundException e){
			//internet connection failure
			MessageBean mB = new MessageBean();
			
			mB.setMessage(e.getMessage());
			mB.setType(false);
			return mB;
		}
	}
}
