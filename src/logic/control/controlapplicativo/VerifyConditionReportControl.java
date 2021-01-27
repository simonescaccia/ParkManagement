package logic.control.controlapplicativo;

import java.sql.Timestamp;

import logic.control.bean.PositionBean;
import logic.entities.model.ParkVisitor;
import logic.entities.dao.ReportDAO;
import logic.entities.model.ParkAttraction;
import logic.entities.model.Position;
import logic.entities.model.Report;
import logic.exception.DBFailureException;
import logic.exception.ReportNotFoundException;

public class VerifyConditionReportControl {
	
	public boolean verifyDistance(PositionBean pBPV, Position pBPA) {
		//distanza accettabile <= 150 metri
		return (distanceInKmBetweenEarthCoordinates(pBPV.getLatitude(),pBPV.getLongitude(),pBPA.getLatitude(),pBPA.getLongitude()) <= 0.15);
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
	
	public boolean verifyCountDown(ParkVisitor pV, ParkAttraction pA) throws DBFailureException {
		//get the Date of last report of pV for the pA
		Timestamp result;
		
		try {
			Report r = ReportDAO.selectLastReport(pV.getUserID(), pA.getName());
			result = r.getDate();
		} catch (ReportNotFoundException e) {
			return true;
		}
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		//differenza tra le due date in millisecondi
		long diffMilliseconds = now.getTime() - result.getTime();
		int diffSeconds = (int) diffMilliseconds / 1000;
		int diffMinute =  diffSeconds / 60;

		//intervallo accettabile se >= 15 minuti
		return diffMinute >= 15;
	}
	
}
