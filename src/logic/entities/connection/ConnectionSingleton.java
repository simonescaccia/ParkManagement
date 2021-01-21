package logic.entities.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import logic.exception.ConnectionDBFailedException;

public class ConnectionSingleton {
	
	private static ConnectionSingleton instance = null;
	
	private static String user = "speedyfila";
	private static String pwd = "Speedyfila1.";
	private static String dbUrl = "jdbc:mysql://localhost:3306/speedyfila";
	private static String driver = "com.mysql.jdbc.Driver";
	private Connection connection;
	
	private final Object mutex = new Object();
	
	private int numberOfAttachment;
	
	protected ConnectionSingleton() {}
	
	public static synchronized ConnectionSingleton getConnectionSingletonInstance() {
		if (ConnectionSingleton.instance == null)
			ConnectionSingleton.instance = new ConnectionSingleton();		
		return instance;
	}

	public Connection attach() throws ConnectionDBFailedException{
		//se nessuna istanza DAO sta usando la connessione ne creo una nuova 
		synchronized (mutex) {
			numberOfAttachment++;
			if(numberOfAttachment == 1) {
				try {
		            // loading dinamico del driver mysql
		            Class.forName(driver);
		
		            // apertura connessione
		            connection = DriverManager.getConnection(dbUrl, user, pwd);
				} catch (ClassNotFoundException e) {
					throw new ConnectionDBFailedException("Dynamic load failure");
				} catch (SQLException e) {
					throw new ConnectionDBFailedException("Driver Manager failure ");
				}
			}
		}
		return connection;
	}
	
	public void detach() {
		synchronized (mutex) {
			numberOfAttachment--;
			if(numberOfAttachment == 0) {
				connection = null;
			}
		}
	}
	
}
