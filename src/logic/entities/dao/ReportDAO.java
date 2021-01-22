package logic.entities.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import logic.entities.connection.ConnectionSingleton;
import logic.entities.dao.queries.Queries;
import logic.entities.dao.queries.Updates;
import logic.entities.model.ParkAttraction;
import logic.entities.model.ParkVisitor;
import logic.entities.model.Report;
import logic.exception.DBFailureException;

import logic.exception.ReportNotFoundException;

public class ReportDAO {

	private ReportDAO(){}
	
	public static Timestamp selectDateLastReportPV(ParkVisitor parkVisitor, ParkAttraction parkAttraction) throws ReportNotFoundException, DBFailureException {
		Statement stmt = null;
		Connection connection = null;
		ConnectionSingleton cS = ConnectionSingleton.getConnectionSingletonInstance();
		try {
			try {
				//ConnectionSingleton instance and attach
				connection = cS.attach();
				
				//creazione ed esecuzione della query su Report
		        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);	        
		        
		        ResultSet rs = Queries.selectDateOfLastReportPV(stmt, parkVisitor.getUserID(), parkAttraction.getName());
		        if(!rs.next()) {
		        	throw new ReportNotFoundException("Report not found");
		        }
		        
		        //fill the park attraction
		        Timestamp date = rs.getTimestamp("date");
		        
		        rs.close();        
		        stmt.close();
		        
		        return date;
				
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new DBFailureException(e.getMessage());
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					stmt = null;
				}
			}
		}
	}
	
	public static void insertReport(Report report) throws ReportNotFoundException {
		Statement stmt = null;
		Connection connection = null;
		ConnectionSingleton cS = ConnectionSingleton.getConnectionSingletonInstance();
		
		try {
			try {
				//ConnectionSingleton instance and attach
				connection = cS.attach();
				
				//creazione ed esecuzione della query
		        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);
		        
		        
		        Updates.insertReport(stmt, report.getDate(), report.getParkAttraction().getName(), report.getParkVisitor().getUserID(), Boolean.compare(report.getIsLast(), false), report.getLengthQueue());
			        
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			e.printStackTrace();
			throw new ReportNotFoundException("Report update failure");
		}finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					stmt = null;
				}
			}
		}
	}
	
	public static Timestamp selectDateLastReport(ParkAttraction parkAttraction) throws ReportNotFoundException {
		Statement stmt = null;
		Connection connection = null;
		ConnectionSingleton cS = ConnectionSingleton.getConnectionSingletonInstance();
		try {
			try {
				//ConnectionSingleton instance and attach
				connection = cS.attach();
				
				//creazione ed esecuzione della query su Report
		        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);	        
		        
		        ResultSet rs = Queries.selectDateOfLastReport(stmt, parkAttraction.getName());
		        if(!rs.next()) {
		        	throw new ReportNotFoundException("Report not found");
		        }
		        
		        //fill the park attraction
		        Timestamp date = rs.getTimestamp("date");
		        
		        rs.close();        
		        stmt.close();
		        
		        return date;
				
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new ReportNotFoundException("Report query failure");
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
					stmt = null;
				}
			}
		}
	}
}
