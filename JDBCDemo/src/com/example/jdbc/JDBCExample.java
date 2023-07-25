package com.example.jdbc;

import java.sql.*;

public class JDBCExample {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", 
				"scott", "tiger");
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from employees");
		
		while(rs.next()) {
			System.out.println(rs.getInt(1)+ ".." + rs.getString(2)+ ".." + rs.getDouble(3)+ 
					".." + rs.getString(4));
		}
		
		con.close();
		
	}
}
