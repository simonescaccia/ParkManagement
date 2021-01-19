package logic.entity.dao;

import logic.entity.factory.Factory;
import logic.entity.model.ParkVisitor;

public class ParkVisitorDAO {

	private ParkVisitorDAO() {}
	
	public static ParkVisitor selectParkVisitor(String userID) {
		//dummy
		return Factory.getParkVisitor();
	}
	
	public static void  incrementCoin(ParkVisitor pV) {
		//dummy
	}
	
}
