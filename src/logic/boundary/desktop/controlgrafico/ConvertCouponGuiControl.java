package logic.boundary.desktop.controlgrafico;

import logic.boundary.desktop.view.ConvertCouponView;
import logic.control.bean.CoinsBean;
import logic.control.bean.MessageBean;
import logic.control.bean.UserBean;
import logic.control.controlapplicativo.ConvertCouponControl;
import logic.exception.NullLoginException;
import logic.exception.ParkVisitorNotFoundException;

public class ConvertCouponGuiControl  extends GenericGuiControl{

	public ConvertCouponGuiControl(ConvertCouponView cCV) {
		super.gV = cCV;
	}
	
	public int getCoins() {
		
		try {
			UserBean uB = new UserBean();
			uB.setUserID(super.lGC.getLoginControl().getLoginBean().getUserID());
			
			ConvertCouponControl cCC = new ConvertCouponControl();
			CoinsBean cB = cCC.getCoins(uB);
			
			return cB.getCoins();
		} catch (NullLoginException | ParkVisitorNotFoundException e) {
			MessageBean mB = new MessageBean();
			mB.setMessage(e.getMessage());
			mB.setType(false);
			super.showMessage(mB);
			return -1;
		}
		
	}
}
