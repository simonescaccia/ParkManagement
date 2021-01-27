package logic.entities.dao;

import logic.entities.connection.ConnectionSingleton;
import logic.entities.dao.queries.Queries;
import logic.entities.factory.Factory;
import logic.entities.model.Category;
import logic.entities.model.ParkAttraction;
import logic.exception.CategoryNotFoundException;
import logic.exception.ConnectionDBFailedException;
import logic.exception.DBFailureException;
import logic.exception.ParkAttractionNotFoundException;
import logic.exception.PositionNotFoundException;
import logic.exception.QueueNotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ParkAttractionDAO {

	private static final String ID_QUEUE = "ID_QUEUE";
	private static final String PARK_ATTRACTION_NOT_FOUND = "ParkAttraction not found";
	
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
		        	throw new ParkAttractionNotFoundException(PARK_ATTRACTION_NOT_FOUND);
		        }
		        
		        //fill the park attraction
		        ParkAttraction pA = Factory.getParkAttraction();
		        pA.setName(rs.getString("name"));
		        pA.setImg(rs.getBinaryStream("img_g"));
		        int queueID = (rs.getInt(ID_QUEUE));
		        int positionID = (rs.getInt("ID_position"));
		        int categoryID = (rs.getInt("ID_category"));
		        
		        rs.close();
		        stmt.close();
		        
		        pA.setPosition(PositionDAO.selectPositionByID(positionID));
		        pA.setQueue(QueueDAO.selectQueueByID(queueID));		        
		        pA.setCategory(CategoryDAO.selectCategory(categoryID, null));
		        
		        return pA;
				
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException | PositionNotFoundException | QueueNotFoundException | CategoryNotFoundException e) {
			throw new ParkAttractionNotFoundException(e.getMessage());
		}
	}
	
	public static void updateParkAttraction(ParkAttraction pA, int lenQueue, Time newWt) throws ParkAttractionNotFoundException {
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
		        
		        ResultSet rs = Queries.selectAttractionByName(stmt, pA.getName());
		        if(!rs.next()) {
		        	throw new ParkAttractionNotFoundException(PARK_ATTRACTION_NOT_FOUND);
		        }
		        
		        int queueID = (rs.getInt(ID_QUEUE));
		        
		        QueueDAO.updateQueue(queueID, lenQueue, newWt);
			        
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new ParkAttractionNotFoundException("ParkAttraction update failure");
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
	
	public static List<ParkAttraction>  selectAttractionsFilterOrder(String filter, boolean noFilter, boolean noNull) throws ParkAttractionNotFoundException{

		List<ParkAttraction> listOfParkAttractions = new ArrayList<>();
		
		try {
			listOfParkAttractions = getListParkAttraction(filter, noFilter, noNull);
		} catch(SQLException e) {
			throw new ParkAttractionNotFoundException(e.getMessage()+ " ParkAttraction update failure");
		} 
		
		return listOfParkAttractions;
	}
	
	protected static List<ParkAttraction> getListParkAttraction (String filter, boolean noFilter, boolean noNull) throws ParkAttractionNotFoundException, SQLException{
		Statement stmt = null;
		Connection connection = null;
		ConnectionSingleton cS = ConnectionSingleton.getConnectionSingletonInstance();
		List<ParkAttraction> listOfParkAttractions = new ArrayList<>();
		
		try {
			Category c = Factory.getCategory();
			int idCategory = 0;
			//select categoria solo se noFileter is false
			if(!noFilter) {
			c = CategoryDAO.selectCategory(0, filter);
			idCategory = c.getId();
			}
			
			//ConnectionSingleton instance and attach
			connection = cS.attach();
			
			//creazione ed esecuzione della query su ParkAttraction
	        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY);
	        
	        ResultSet rs;
	        
	        rs = selectQuery(stmt, noFilter, noNull, idCategory);
	        
	        if(!rs.next()) {
	        	return listOfParkAttractions;
	        }
	        
	        // riposizionamento del cursore
            rs.first();
            do{
                
            	ParkAttraction p = Factory.getParkAttraction();
            	p.setName(rs.getString("name"));
		        p.setImg(rs.getBinaryStream("img_g"));

		        int queueID = (rs.getInt(ID_QUEUE));
		        int positionID = (rs.getInt("ID_position"));
		        int categoryID = (rs.getInt("ID_category"));
		        
		        p.setCategory(CategoryDAO.selectCategory(categoryID, null));	        
		        p.setPosition(PositionDAO.selectPositionByID(positionID));
		        p.setQueue(QueueDAO.selectQueueByID(queueID));	
                
		        listOfParkAttractions.add(p);

            }while(rs.next());
            
            // STEP 5.1: Clean-up dell'ambiente
            rs.close();
		        
		} catch (CategoryNotFoundException | PositionNotFoundException | QueueNotFoundException | ConnectionDBFailedException | SQLException e) {
			throw new ParkAttractionNotFoundException(e.getMessage());
		} finally {
			cS.detach();
			if(stmt != null) {
				stmt.close();
			}
		}
		
		return listOfParkAttractions;
	}
	
	protected static ResultSet selectQuery(Statement stmt, boolean noFilter, boolean noNull, int idCategory) throws SQLException {
        ResultSet rs;
		if(noFilter) {
        	if(noNull) {
        		rs = Queries.selectAttractionsNoNullQueue(stmt);
        	} else {
        		rs = Queries.selectAttractions(stmt);
        	}
        } else {
        	if(noNull) {
        		rs = Queries.selectAttractionsFilterOrderNoNullQueue(stmt, idCategory);
        	} else {
        		rs = Queries.selectAttractionsFilterOrder(stmt, idCategory);
        	}
        }
		return rs;
	}
	
}
