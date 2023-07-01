package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class testDAO {
	
	private Connection con;
	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
	public testDAO(Connection con) {
		
		this.con = con;

}
	
	public int insertToUser(String email,String password,String username) {
		int result=0;
		
		try {
			query = "Insert into user (username,email,password) values(?,?,?)";
			pst = this.con.prepareStatement(query);

			pst.setString(1, email);
			pst.setString(2, password);
			pst.setString(3, username);

			pst.executeUpdate();
			result = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
