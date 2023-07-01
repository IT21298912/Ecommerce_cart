package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import com.mysql.cj.protocol.Resultset;

import cn.techtutorial.model.User;


public class userdao {

	private Connection connection ;
	private String queryString;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	
	public userdao(Connection connection) {

		this.connection = connection;
	}                                      
	
	
	public User userlogin(String email, String password) {
		
		
		User user=null;
		try {
			
			queryString="select * from users where email=? and password=?";
			pst=this.connection.prepareStatement(queryString);
			pst.setString(1, email);
			pst.setString(2, password);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				user=new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		return user;
	}
	
	
	
}
