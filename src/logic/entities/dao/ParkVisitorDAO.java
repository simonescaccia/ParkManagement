package logic.entities.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.entities.connection.ConnectionSingleton;
import logic.entities.dao.queries.Queries;
import logic.entities.dao.queries.Updates;
import logic.entities.factory.Factory;
import logic.entities.model.ParkVisitor;
import logic.exception.DBFailureException;
import logic.exception.ParkVisitorNotFoundException;

public class ParkVisitorDAO {

	private ParkVisitorDAO() {}
	
	public static ParkVisitor selectParkVisitor(String userID) throws ParkVisitorNotFoundException{
		
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
		        
		        ResultSet rs = Queries.selectUserByID(stmt, userID);
		        
		        ParkVisitor pV = Factory.getParkVisitor();
		        if(!rs.next()) {
		        	return pV;
		        }
		        
		        pV.setUserID(rs.getString("userID"));
		        pV.setCoins(rs.getInt("coins"));
		        
		        return pV;
		        
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new ParkVisitorNotFoundException(e.getMessage());
		}

	}
	
	public static void  incrementCoin(String userID) throws ParkVisitorNotFoundException {
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

		        Updates.incrementCoins(stmt, userID);
			        
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			e.printStackTrace();
			throw new ParkVisitorNotFoundException("Increment coins failure");
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
	
	public static void insertNewUser(String userID) throws DBFailureException{
		
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
		        
		        
		        Updates.insertUser(stmt, userID);
			        
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new DBFailureException("DB failure");
		}
		
	}
	
}
