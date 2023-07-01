package cn.techtutorial.connection;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class dbcon {
	
	private static Connection connection=null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		if(connection==null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart","root","NisalSYapa");
			System.out.println("connected");
		}
		
		return connection;
	}
	

}
