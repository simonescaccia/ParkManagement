package logic.entity.dao;

import logic.entity.factory.Factory;
import logic.entity.model.Queue;

public class QueueDAO {

	private QueueDAO() {}
	
	public static Queue selectQueueByID(int ID) {
		return Factory.getQueue();
	}
}
