package logic.entities.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logic.entities.connection.ConnectionSingleton;
import logic.entities.dao.queries.Queries;
import logic.entities.factory.Factory;
import logic.entities.model.Category;
import logic.exception.CategoryNotFoundException;
import logic.exception.DBFailureException;

public class CategoryDAO {
	
	private CategoryDAO() {}
	
	public static Category selectCategory(int id, String name) throws CategoryNotFoundException {
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
		        
		        ResultSet rs;
		        if(name == null) {
		        	rs = Queries.selectCategoryByID(stmt, id);
		        } else {
		        	rs = Queries.selectCategoryByName(stmt, name);
		        } 
		        
		        if(!rs.next()) {
		        	throw new CategoryNotFoundException("Category not found");
		        }
		        
		        //fill the Category
		        Category c = Factory.getCategory();
		        c.setId(rs.getInt("ID"));
		        c.setName(rs.getString("name"));
		        c.setImgC(rs.getBinaryStream("img"));
		        
		        rs.close();
		        stmt.close();
		        
		        return c;
		        
			} finally {
				cS.detach();
				if(stmt != null) {
					stmt.close();
				}
			}
		} catch(DBFailureException | SQLException e) {
			throw new CategoryNotFoundException(e.getMessage());
		}    
	}
	
}
