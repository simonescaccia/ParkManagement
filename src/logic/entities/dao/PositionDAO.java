package logic.entities.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.entities.connection.ConnectionSingleton;
import logic.entities.dao.queries.Queries;
import logic.entities.factory.Factory;
import logic.entities.model.Position;
import logic.exception.DBFailureException;
import logic.exception.PositionNotFoundException;

public class PositionDAO {

	private PositionDAO() {}
	
	public static Position selectPositionByID(int id) throws PositionNotFoundException {
		Statement stmt = null;
		Connection connection = null;
		ConnectionSingleton cS = ConnectionSingleton.getConnectionSingletonInstance();
		try {
			try {
				//ConnectionSingleton instance and attach
				connection = cS.attach();
				
				//creazione ed esecuzione della query su Position
		        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);	        
		        
		        ResultSet rs = Queries.selectPositionByID(stmt, id);
		        if(!rs.next()) {
		        	throw new PositionNotFoundException("Position not found");
		        }
		        
		        //fill the Position 
		        Position p = Factory.getPosition();
		        p.setLatitude(rs.getDouble("latitude"));
		        p.setLongitude(rs.getDouble("longitude"));
		        
		        rs.close();
		        stmt.close();
		        
		        return p;
		        
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new PositionNotFoundException(e.getMessage());
		} 
	}
}
