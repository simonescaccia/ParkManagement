package logic.control.controlapplicativo;

import logic.control.bean.PositionBean;
import logic.entities.model.ParkVisitor;
import logic.entities.model.Position;

public class VerifyConditionReportControl {
	
	public boolean verifyDistance(PositionBean pBPV, Position pBPA) {
		//distanza accettabile <= 100 metri
		return (distanceInKmBetweenEarthCoordinates(pBPV.getLatitude(),pBPV.getLongitude(),pBPA.getLatitude(),pBPA.getLongitude()) <= 0.1);
	}
	
	public double degreesToRadiants(double degrees) {
		return degrees*Math.PI/180;
	}
	
	public double distanceInKmBetweenEarthCoordinates(double lat1, double lng1, double lat2, double lng2) {
		double earthRadiusKm = 6371;
		
		double distanceLat = degreesToRadiants(lat2-lat1);
		double distanceLng = degreesToRadiants(lng2-lng1);
		
		double lat1r = degreesToRadiants(lat1);
		double lat2r = degreesToRadiants(lat1);
		
		//haversine formula
		double a = Math.sin(distanceLat/2)*Math.sin(distanceLat/2) + Math.sin(distanceLng/2)*Math.sin(distanceLng/2)*Math.cos(lat1r)*Math.cos(lat2r);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return earthRadiusKm * c;
	}
	
	public boolean verifyCountDown(ParkVisitor user) {
		//dummy method
		return true;
	}
	
}
