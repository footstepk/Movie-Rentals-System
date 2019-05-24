package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * MyJDBCUtil is a class that represents the connection to the database.
 * @author Kok Heng
 * Date of Implementation: 21 March 2019 
 */

public class MyJDBCUtils {

	/**
	 * Get the database connection
	 * @return the connection object
	 * @throws the SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection connection = null;

		/** Get the properties for the connection. */
		try(FileInputStream file = new FileInputStream("C:\\Program Files\\eclipse_java_workspace\\MRS\\src\\utilities\\db.properties")) {
			// load the properties file.
			Properties p = new Properties();
			p.load(file);

			// Assign the database parameters
			String DRIVER_URL = p.getProperty("driver_url");
			String USERNAME = p.getProperty("username");
			String PASSWORD = p.getProperty("password");
			// Create connection to the database
			connection = DriverManager.getConnection(DRIVER_URL, USERNAME, PASSWORD);
		}
		catch(IOException ex) {
			System.out.println("Errored! No such properties file can be found in the system");
			//ex.printStackTrace();
			//System.err.println(ex.getMessage());
		}
		return connection;
	}
}
