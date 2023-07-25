package Default;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	
	static Connection con;
	
	public static Connection createConnection() throws Exception {
		//load the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//create the connection
		
		String user = "root";
		String pass = "root";
		String url = "jdbc:mysql://localhost:3306/managestudent";
		
		con = DriverManager.getConnection(url, user, pass);
		
		return con;		
		
	}
}	
