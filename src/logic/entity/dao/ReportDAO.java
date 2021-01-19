package logic.entity.dao;

import logic.entity.model.ParkVisitor;
import logic.entity.model.Report;
import logic.entity.model.ParkAttraction;
import java.time.LocalDateTime;

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
