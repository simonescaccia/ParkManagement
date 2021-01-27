package logic.entities.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class Queries {
	
	private static final String SELECT_STAR_FROM_REPORT_WH_PV = "SELECT * FROM report WHERE userID_parkvisitor='";
	
	private Queries() {}

	//park visitors query
    public static ResultSet selectUserByID(Statement stmt, String userID) throws SQLException  {
        String sql = "SELECT * FROM parkvisitor WHERE userID='" + userID + "';";
        return stmt.executeQuery(sql);
    }
    
    //category query
    public static ResultSet selectCategoryByID(Statement stmt, int id) throws SQLException  {
        String sql = "SELECT * FROM category WHERE ID='" + id + "';";
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectCategoryByName(Statement stmt, String name) throws SQLException  {
        String sql = "SELECT * FROM category WHERE name='" + name + "';";
        return stmt.executeQuery(sql);
    }
    
    //park attraction query
    public static ResultSet selectAttractionByName(Statement stmt, String name) throws SQLException  {
        String sql = "SELECT * FROM parkattraction WHERE name='" + name + "';";
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectAttractionsFilterOrder(Statement stmt, int idCategory) throws SQLException  {
        String sql = "SELECT * FROM parkattraction,queue WHERE ID_category='" + idCategory + "' and ID_queue=queue.ID ORDER BY waiting_time ASC;";
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectAttractionsFilterOrderNoNullQueue(Statement stmt, int idCategory) throws SQLException  {
        String sql = "SELECT * FROM parkattraction,queue WHERE ID_category='" + idCategory + "' and ID_queue=queue.ID and length IS NOT NULL ORDER BY waiting_time ASC;";
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectAttractions(Statement stmt) throws SQLException  {
        String sql = "SELECT * FROM parkattraction,queue WHERE ID_queue=queue.ID ORDER BY waiting_time ASC;";
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectAttractionsNoNullQueue(Statement stmt) throws SQLException  {
        String sql = "SELECT * FROM parkattraction,queue WHERE ID_queue=queue.ID and length IS NOT NULL ORDER BY waiting_time ASC;";
        return stmt.executeQuery(sql);
    }
    
    //queue query
    public static ResultSet selectQueueByID(Statement stmt, int queueID) throws SQLException  {
        String sql = "SELECT * FROM queue WHERE ID='" + queueID + "';";
        return stmt.executeQuery(sql);
    }
    
    //position query
    public static ResultSet selectPositionByID(Statement stmt, int positionID) throws SQLException  {
        String sql = "SELECT * FROM position WHERE ID='" + positionID + "';";
        return stmt.executeQuery(sql);
    }
    
    //report query
    public static ResultSet selectLastestReportPvPa(Statement stmt, String userID, String pAName) throws SQLException  {
        String sql = SELECT_STAR_FROM_REPORT_WH_PV+userID+"' AND name_parkattraction ='"+pAName+"' ORDER BY date DESC;";
        return stmt.executeQuery(sql);
    }
    
	public static ResultSet selectLastestReportPA(Statement stmt, String pAName) throws SQLException  {
        String sql = "SELECT * FROM report WHERE name_parkattraction ='"+pAName+"' ORDER BY date DESC;";
        return stmt.executeQuery(sql);
    }

	public static ResultSet selectLastestReportPV(Statement stmt, String userID) throws SQLException {
        String sql = SELECT_STAR_FROM_REPORT_WH_PV+userID+"' AND isFeedback ='0' AND isLast = '1' ORDER BY date DESC;";
        return stmt.executeQuery(sql);
	}

	public static ResultSet selectReportByPK(Statement stmt, String userID, String attractionName, Timestamp date) throws SQLException {
        String sql =SELECT_STAR_FROM_REPORT_WH_PV+userID+"' AND name_parkattraction ='"+attractionName+"' AND date = '"+date+"';";
        return stmt.executeQuery(sql);
	}
   
	
}
