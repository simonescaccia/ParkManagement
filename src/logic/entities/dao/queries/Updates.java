package logic.entities.dao.queries;

import java.sql.SQLException;
import java.sql.Statement;

public class Updates {
	
	private Updates() {}
	
    public static int insertUser(Statement stmt, String userID) throws SQLException  {
        String sql = "INSERT INTO parkvisitor (userID) VALUES (" + userID + ");";
        return stmt.executeUpdate(sql);
    }
}
