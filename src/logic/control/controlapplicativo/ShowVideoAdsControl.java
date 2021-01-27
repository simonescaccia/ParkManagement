package logic.control.controlapplicativo;

import logic.entities.dao.ParkVisitorDAO;
import logic.entities.dao.VideoAdsDAO;
import logic.entities.model.ParkVisitor;
import logic.exception.ParkVisitorNotFoundException;
import logic.exception.VideoAdsNotFoundException;

public class ShowVideoAdsControl {

	public boolean loadVideoAds(String userID) {
		ParkVisitor pV;
		try {
			//increment coins to park visitor
			pV = ParkVisitorDAO.selectParkVisitor(userID);
			ParkVisitorDAO.incrementCoin(pV.getUserID());
		} catch (ParkVisitorNotFoundException e) {
			return false;
		}
			
		//increment visual to ads
		try {
			VideoAdsDAO.incrementVisual();
		} catch (VideoAdsNotFoundException e) {
			return false;
		}
		
		return true;
	}
}
