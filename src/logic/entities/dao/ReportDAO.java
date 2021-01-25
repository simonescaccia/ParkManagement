package logic.entities.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import logic.entities.connection.ConnectionSingleton;
import logic.entities.dao.queries.Queries;
import logic.entities.dao.queries.Updates;
import logic.entities.factory.Factory;
import logic.entities.model.ParkAttraction;
import logic.entities.model.ParkVisitor;
import logic.entities.model.Report;
import logic.exception.DBFailureException;
import logic.exception.ParkAttractionNotFoundException;
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
		        
		        ResultSet rs = Queries.selectLastestReportPV(stmt, parkVisitor.getUserID(), parkAttraction.getName());
		        
		        return getDate(rs, stmt);
				
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
		        
		        ResultSet rs = Queries.selectLastestReportPA(stmt, parkAttraction.getName());

				return getDate(rs, stmt);
				
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new ReportNotFoundException("Report query failure");
		} finally {
			closeStmt(stmt);
		}
	}
	
	protected static Timestamp getDate(ResultSet rs, Statement stmt) throws SQLException, ReportNotFoundException {
        if(!rs.next()) {
        	throw new ReportNotFoundException("Report not found");
        }
        
        //fill the park attraction
        Timestamp date = rs.getTimestamp("date");
        
        rs.close();        
        stmt.close();
        
        return date;
	}
	
	public static List<Report> selectLastReport(ParkAttraction pA, String userID) throws ReportNotFoundException, ParkAttractionNotFoundException{
		
		Statement stmt = null;
		Connection connection = null;
		ConnectionSingleton cS = ConnectionSingleton.getConnectionSingletonInstance();
		
		List<Report> listOfReports = new ArrayList<>();
		
		try {
			try {
				//ConnectionSingleton instance and attach
				connection = cS.attach();
				
				//creazione ed esecuzione della query su Report
		        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);	        
		        
		        
		        ResultSet rs;
		        if(pA != null) {
		        	rs = Queries.selectLastestReportPA(stmt, pA.getName());
		        } else {
		        	rs = Queries.selectLastestReportPV(stmt, userID);
		        }
		        
		        if(!rs.next()) {
		        	return listOfReports;
		        }

		        // riposizionamento del cursore
	            rs.first();
	            do{
	                
	            	Report r = Factory.getReport();
	            	r.setDate(rs.getTimestamp("date"));
			        r.setLengthQueue(rs.getInt("lengthQueue"));
			        
			        ParkAttraction pAttr = ParkAttractionDAO.selectAttractionByName(rs.getString("name_parkattraction"));
			        
			        r.setParkAttraction(pAttr);
	                
			        listOfReports.add(r);

	            }while(rs.next());
	            
	            // STEP 5.1: Clean-up dell'ambiente
		        
		        rs.close();        
		        stmt.close();
				
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new ReportNotFoundException("Report list query failure");
		} finally {
			closeStmt(stmt);
		}
		
		return listOfReports;
		
	}
	
	public static void closeStmt(Statement stmt) throws ReportNotFoundException {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				throw new ReportNotFoundException("stmt close failure");
			}
		}
	}
	
}
