package logic.entity.dao;

import logic.entity.factory.Factory;
import logic.entity.model.Position;

public class PositionDAO {

	private PositionDAO() {}
	
	public static Position selectPositionByID(int ID) {
		return Factory.getPosition();
	}
}
