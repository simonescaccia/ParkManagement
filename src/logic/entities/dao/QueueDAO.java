package logic.entities.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import logic.entities.connection.ConnectionSingleton;
import logic.entities.dao.queries.Queries;
import logic.entities.dao.queries.Updates;
import logic.entities.factory.Factory;
import logic.entities.model.Queue;
import logic.exception.DBFailureException;
import logic.exception.QueueNotFoundException;

public class QueueDAO {

	private QueueDAO() {}
	
	public static Queue selectQueueByID(int id) throws QueueNotFoundException {
		Statement stmt = null;
		Connection connection = null;
		ConnectionSingleton cS = ConnectionSingleton.getConnectionSingletonInstance();
		try {
			try {
				//ConnectionSingleton instance and attach
				connection = cS.attach();
				
				//creazione ed esecuzione della query su Queue
		        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY);	        
		        
		        ResultSet rs = Queries.selectQueueByID(stmt, id);
		        if(!rs.next()) {
		        	throw new QueueNotFoundException("Queue not found");
		        }
		        
		        //fill the Queue
		        Queue q = Factory.getQueue();
		        q.setAvgWaitingTime(rs.getDouble("avg_waiting_time"));
		        q.setLength(rs.getInt("length"));
		        q.setWaitingTime(rs.getTime("waiting_time"));
		        q.setIdQueue(rs.getInt("ID"));
		        
		        rs.close();
		        stmt.close();
		        
		        return q;
		        
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new QueueNotFoundException(e.getMessage());
		}    
	}
	
	public static void updateQueue(int id, int lenQueue, Time newWt) throws DBFailureException {
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
		        
		        if(lenQueue == -1) {
		        	//set null to queue lenght&waitingtime
		        	Updates.setNullToQueue(stmt, id);
		        } else {
		        	Updates.updateQueue(stmt, id, lenQueue, newWt);
		        }
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

	public static void updateAVGWaitingTime(int idQueue, double newAVG) throws DBFailureException {
		
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
		        
	        	Updates.updateQueue(stmt, idQueue, newAVG);
	        	
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new DBFailureException("Queue DB failure");
		}	
	}
}
