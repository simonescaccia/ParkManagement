package logic.entity.factory;

import logic.entity.model.ParkAttraction;
import logic.entity.model.ParkVisitor;
import logic.entity.model.Position;
import logic.entity.model.Queue;
import logic.entity.model.Report;
import logic.entity.model.VideoAds;

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
}
