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
	
		        rs.next();
		        
		        ParkVisitor pV = Factory.getParkVisitor();
		        pV.setUserID(rs.getString("userID"));
		        
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
	
	public static void  incrementCoin(ParkVisitor pV) {
		//dummy
	}
	
	public static boolean searchUserByID(String userID) throws DBFailureException{
		
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
	
		        if(!rs.next()) {
		        	//l'utente non è registrato
		        	rs.close();
		        	return false;
		        } else {
		        	//l'utente è registrato
			        rs.close();
			        //chiudo la connessione poichè non devo inserire l'utente nel db, quindi il caso d'uso è terminato
			        return true;
		        }
		        
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new DBFailureException(e.getMessage());
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
