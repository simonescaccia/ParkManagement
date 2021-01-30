package logic.control.controlapplicativo;

import logic.control.bean.CoinsBean;
import logic.control.bean.UserBean;
import logic.entities.dao.ParkVisitorDAO;
import logic.entities.model.ParkVisitor;
import logic.exception.ParkVisitorNotFoundException;

public class ConvertCouponControl {

	public CoinsBean getCoins(UserBean uB) throws ParkVisitorNotFoundException {
		ParkVisitor pV = ParkVisitorDAO.selectParkVisitor(uB.getUserID());
		CoinsBean cB = new CoinsBean();
		cB.setCoins(pV.getCoins());
		
		return cB;
	}
}
