package logic.entities.dao.queries;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;

public class Updates {
	
	private Updates() {}
	
    public static int insertUser(Statement stmt, String userID) throws SQLException  {
        String sql = "INSERT INTO parkvisitor (userID) VALUES (" + userID + ");";
        return stmt.executeUpdate(sql);
    }
    
    public static int updateQueue(Statement stmt, int id, int lenQueue, Time newWt) throws SQLException  {
        String sql = "UPDATE queue SET length='"+lenQueue+"', waiting_time='"+newWt+"' WHERE ID = '"+id+"';";
        return stmt.executeUpdate(sql);
    }
    
    public static int setNullToQueue(Statement stmt, int id) throws SQLException  {
        String sql = "UPDATE queue SET length=NULL, waiting_time=NULL WHERE ID = '"+id+"';";
        return stmt.executeUpdate(sql);
    }
    
    public static int insertReport(Statement stmt, Timestamp date, String pAName, String userID, int isLast, int lenQueue, Time waitingTime) throws SQLException  {
        String sql = "INSERT INTO report (date, name_parkattraction, userID_parkvisitor, isLast, lengthQueue, waitingtime) VALUES ('"+date+"','"+pAName+"','"+userID+"','"+isLast+"','"+lenQueue+"','"+waitingTime+"');";
        return stmt.executeUpdate(sql);
    }
    
    public static int incrementCoins(Statement stmt, String userID) throws SQLException  {
        String sql = "UPDATE parkvisitor SET coins=coins+1 WHERE userID = '"+userID+"';";
        return stmt.executeUpdate(sql);
    }
    
    public static int incrementVisual(Statement stmt) throws SQLException  {
        String sql = "UPDATE videoads SET visual=visual+1 WHERE ID = '1';";
        return stmt.executeUpdate(sql);
    }

	public static int updateQueue(Statement stmt, int idQueue, double newAVG) throws SQLException {
        String sql = "UPDATE queue SET avg_waiting_time='"+newAVG+"' WHERE ID ='"+idQueue+"' ;";
        return stmt.executeUpdate(sql);		
	}

	public static int updateReportSetIsFeedback(Statement stmt, String attrName, String userID, Timestamp date) throws SQLException {
        String sql = "UPDATE report SET isFeedback='1' WHERE name_parkattraction='"+attrName+"' AND userID_parkvisitor='"+userID+"' AND date='"+date+"' ;";
        return stmt.executeUpdate(sql);				
	}
    
}
