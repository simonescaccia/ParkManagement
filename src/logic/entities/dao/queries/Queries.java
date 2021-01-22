package logic.entities.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {
	
	private Queries() {}

    public static ResultSet selectUserByID(Statement stmt, String userID) throws SQLException  {
        String sql = "SELECT * FROM parkvisitor WHERE userID='" + userID + "';";
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectAttractionByName(Statement stmt, String name) throws SQLException  {
        String sql = "SELECT * FROM parkattraction WHERE name='" + name + "';";
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectQueueByID(Statement stmt, int queueID) throws SQLException  {
        String sql = "SELECT * FROM queue WHERE ID='" + queueID + "';";
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectPositionByID(Statement stmt, int positionID) throws SQLException  {
        String sql = "SELECT * FROM position WHERE ID='" + positionID + "';";
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectDateOfLastReportPV(Statement stmt, String userID, String pAName) throws SQLException  {
        String sql = "SELECT * FROM report WHERE userID_parkvisitor='"+userID+"' AND name_parkattraction ='"+pAName+"' ORDER BY date DESC;";
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectDateOfLastReport(Statement stmt, String pAName) throws SQLException  {
        String sql = "SELECT * FROM report WHERE name_parkattraction ='"+pAName+"' ORDER BY date DESC;";
        return stmt.executeQuery(sql);
    }
	
}
