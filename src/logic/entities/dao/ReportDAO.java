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
import logic.entities.model.Report;
import logic.exception.DBFailureException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.ReportNotFoundException;

public class ReportDAO {

	private static final String REPORT_NOT_FOUND = "Report not found";
	
	private ReportDAO(){}
	
	public static Report selectLastReport(String parkVisitor, String parkAttraction) throws ReportNotFoundException, DBFailureException {
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
		        
		        ResultSet rs;
		        
		        if(parkVisitor != null) {
		        	//ultimi report di un'attrazione inseriti da un visitatore
		        	rs = Queries.selectLastestReportPvPa(stmt, parkVisitor, parkAttraction);
		        } else {
		        	//ultimi report di un'attrazione
		        	rs = Queries.selectLastestReportPA(stmt, parkAttraction);
		        }
		        
		        Report r = new Report(); 
                		
		        if(!rs.next()) {
                	throw new ReportNotFoundException(REPORT_NOT_FOUND);
                }
		        
		        r.setDate(rs.getTimestamp("date"));
		        
		        return r;
				
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
		        
		        
		        Updates.insertReport(stmt, report.getDate(), report.getParkAttraction().getName(), report.getParkVisitor().getUserID(), Boolean.compare(report.getIsLast(), false), report.getLengthQueue(), report.getWaitingTime());
			        
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
	
	public static List<Report> selectListOfLastReports(String attractionName, String userID) throws ReportNotFoundException, ParkAttractionNotFoundException{
		
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
		        if(attractionName != null) {
		        	rs = Queries.selectLastestReportPA(stmt, attractionName);
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
	
	protected static void closeStmt(Statement stmt) throws ReportNotFoundException {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				throw new ReportNotFoundException("stmt close failure");
			}
		}
	}
	
	public static Report selectReport(String userID, String attractionName, Timestamp date) throws ReportNotFoundException {
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
		        
		        ResultSet rs = Queries.selectReportByPK(stmt, userID, attractionName, date);
	
		        if(!rs.next()) {
		        	throw new ReportNotFoundException(REPORT_NOT_FOUND);
		        }
		        
		        //fill the report
		        
		        Report r = Factory.getReport(); 
		        r.setWaitingTime(rs.getTime("waitingtime"));
		        r.setLengthQueue(rs.getInt("lengthQueue"));
		        
		        rs.close();        
		        stmt.close();
		        
		        return r;
		        
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new ReportNotFoundException(e.getMessage());
		}
	}

	public static void updateReportSetFeedback(String attrName, String userID, Timestamp date) throws DBFailureException {
		
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
		        
		        Updates.updateReportSetIsFeedback(stmt, attrName, userID, date);
		        
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new DBFailureException("Update feedback DB failure");
		}
		
	}
	
}
