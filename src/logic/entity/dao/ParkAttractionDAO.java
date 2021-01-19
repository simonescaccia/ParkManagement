package logic.entity.dao;

import logic.entity.factory.Factory;
import logic.entity.model.ParkAttraction;
import java.time.LocalTime;

public class ParkAttractionDAO {

	private ParkAttractionDAO() {}
	
	public static ParkAttraction selectAttractionByName(String attractionName) {
		//dummy
		return Factory.getParkAttraction();
	}
	
	public static void updateParkAttraction(ParkAttraction pA, String lenQueue, LocalTime newWT) {
		//dummy
	}
}
