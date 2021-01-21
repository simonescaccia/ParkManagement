package logic.entities.dao;

import java.time.LocalDateTime;

import logic.entities.model.ParkAttraction;
import logic.entities.model.ParkVisitor;
import logic.entities.model.Report;

public class ReportDAO {

	private ReportDAO(){}
	
	public static LocalDateTime selectDateLastReportPV(ParkVisitor parkVisitor, ParkAttraction parkAttraction) {
		return LocalDateTime.now();
	}
	
	public static void insertReport(Report report) {
//		dummy
	}
	
	public static LocalDateTime selectDateLastReport(ParkAttraction parkAttraction) {
		return LocalDateTime.now();
	}
}
