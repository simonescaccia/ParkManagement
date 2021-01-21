package logic.entities.dao;

import logic.entities.connection.ConnectionSingleton;
import logic.entities.dao.queries.Queries;
import logic.entities.factory.Factory;
import logic.entities.model.ParkAttraction;
import logic.exception.DBFailureException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.PositionNotFoundException;
import logic.exception.QueueNotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

public class ParkAttractionDAO {

	private ParkAttractionDAO() {}
	
	public static ParkAttraction selectAttractionByName(String attractionName) throws ParkAttractionNotFoundException {
		Statement stmt = null;
		Connection connection = null;
		ConnectionSingleton cS = ConnectionSingleton.getConnectionSingletonInstance();
		try {
			try {
				//ConnectionSingleton instance and attach
				connection = cS.attach();
				
				//creazione ed esecuzione della query su ParkAttraction
		        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);	        
		        
		        ResultSet rs = Queries.selectAttractionByName(stmt, attractionName);
		        if(!rs.next()) {
		        	throw new ParkAttractionNotFoundException("ParkAttraction not found");
		        }
		        
		        //fill the park attraction
		        ParkAttraction pA = Factory.getParkAttraction();
		        pA.setName(rs.getString("name"));
		        int queueID = (rs.getInt("ID_queue"));
		        int positionID = (rs.getInt("ID_position"));
		        
		        rs.close();
		        stmt.close();
		        
		        pA.setPosition(PositionDAO.selectPositionByID(positionID));
		        pA.setQueue(QueueDAO.selectQueueByID(queueID));		        
		        
		        return pA;
				
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException | PositionNotFoundException | QueueNotFoundException e) {
			throw new ParkAttractionNotFoundException(e.getMessage());
		}
	}
	
	public static void updateParkAttraction(ParkAttraction pA, String lenQueue, LocalTime newWT) {
		//dummy
	}
}
