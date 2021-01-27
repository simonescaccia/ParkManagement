package test.cleanup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import logic.entities.connection.ConnectionSingleton;
import logic.exception.DBFailureException;

public class CleanUP {

	private CleanUP() {}
	
	public static void deleteReport(String attractionName, String userID, Timestamp date) throws DBFailureException, SQLException {
		Statement stmt = null;
		Connection connection = null;
		ConnectionSingleton cS = ConnectionSingleton.getConnectionSingletonInstance();
		
		try {
			
			//ConnectionSingleton instance and attach
			connection = cS.attach();
			
			//creazione ed esecuzione della query
	        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY);
	        
	        String sql = "DELETE FROM report WHERE name_parkattraction ='"+attractionName+"' AND userID_parkvisitor = '"+userID+"' AND date = '"+date+"';";
	        stmt.executeUpdate(sql);	
		        

		} catch(DBFailureException | SQLException e) {
			throw new DBFailureException("Clean UP DB failure");
		} finally {
			cS.detach();
			if(stmt != null) {
				stmt.close();
			}
		}
	}
}
