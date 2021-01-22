package logic.control.controlapplicativo;

import java.sql.Time;
import java.sql.Timestamp;

import logic.entities.dao.ParkAttractionDAO;
import logic.entities.dao.ParkVisitorDAO;
import logic.entities.dao.ReportDAO;
import logic.entities.model.Report;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ParkVisitorNotFoundException;
import logic.exception.ReportNotFoundException;

public class CommitReportControl {
	
	public void commitTheReport(Report report) throws ParkAttractionNotFoundException, ReportNotFoundException, ParkVisitorNotFoundException {
			//new waiting time for the attraction
			Time newWt = calculateWaitingTime(report);
			
			ParkAttractionDAO.updateParkAttraction(report.getParkAttraction(), report.getLengthQueue(), newWt);
			ReportDAO.insertReport(report);
			//this is a thread
			//create thread for a sleep method
			new Thread(() -> 
				waitToSetNullQueue(report, newWt)	
			).start();
			
			ParkVisitorDAO.incrementCoin(report.getParkVisitor());
			
	}
	
	protected Time calculateWaitingTime(Report report) {
		double timeInMinute = report.getParkAttraction().getQueue().getAvgWaitingTime() * report.getLengthQueue();		
		long milliseconds = (long) timeInMinute*60*1000;
		//fuso orario
		long millisecondsIt = milliseconds-(60*60*1000);

		return new Time(millisecondsIt);
	}
	
	protected void waitToSetNullQueue(Report report, Time queueWT) {
		
		//get the waiting time of the attraction for this report and set null to the queue at the end 
		//of this time if there aren't new report for this attracton
		double millisecondsGTW = queueWT.getTime();
		double milliseconds = millisecondsGTW+(60*60*1000);
		try {
			//sleep for queueWT
			Thread.sleep((long)milliseconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		try {
			Timestamp date = ReportDAO.selectDateLastReport(report.getParkAttraction());
			//The DB set the milliseconds to 0 to the Timestamp
			report.getDate().setNanos(0);
			if(date.equals(report.getDate())) {
				//no report change, then set queue length and the queue waiting time to his default value
				long minusOneHour= (long) -60*60*1000;
				Time nullTime = new Time(minusOneHour);
				ParkAttractionDAO.updateParkAttraction(report.getParkAttraction(), 0, nullTime);
			}
		} catch (ReportNotFoundException | ParkAttractionNotFoundException e) {
			e.printStackTrace();
		}

		
	}
}
