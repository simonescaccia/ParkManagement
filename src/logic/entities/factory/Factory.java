package logic.entities.factory;

import logic.entities.model.Category;
import logic.entities.model.ParkAttraction;
import logic.entities.model.ParkVisitor;
import logic.entities.model.Position;
import logic.entities.model.Queue;
import logic.entities.model.Report;
import logic.entities.model.VideoAds;

public class Factory {
	
	private Factory() {}
	
	public static ParkAttraction getParkAttraction() {
		return new ParkAttraction();
	}
	
	public static ParkVisitor getParkVisitor() {
		return new ParkVisitor();
	}
	
	public static Report getReport() {
		return new Report();
	}
	
	public static VideoAds getVideoAds() {
		return new VideoAds();
	}
	
	public static Queue getQueue() {
		return new Queue();
	}
	
	public static Position getPosition() {
		return new Position();
	}
	
	public static Category getCategory() {
		return new Category();
	}
}
