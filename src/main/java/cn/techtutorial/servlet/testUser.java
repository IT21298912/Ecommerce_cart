package cn.techtutorial.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.techtutorial.connection.dbcon;
import cn.techtutorial.dao.testDAO;

@WebServlet("/testUser")
public class testUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("pass");
		String email = request.getParameter("emails");;
		String userName = request.getParameter("username");
		int result=0;
		
		testDAO testDAO;
		try {
			testDAO = new testDAO(dbcon.getConnection());
			result=testDAO.insertToUser(email, password, userName);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		if(result==1) {
			System.out.println("Inserted");
		}else {
			System.out.println("error Insertion");
		}
		doGet(request, response);
	}

}
