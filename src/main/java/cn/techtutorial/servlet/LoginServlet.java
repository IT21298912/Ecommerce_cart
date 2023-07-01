package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.techtutorial.connection.dbcon;
import cn.techtutorial.dao.userdao;
import cn.techtutorial.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out= response.getWriter()){
			String emailString=request.getParameter("login-email");
			String passwordString=request.getParameter("login-password");
			
			try {
				userdao udao = new userdao(dbcon.getConnection());
				User user=udao.userlogin(emailString, passwordString);
				
				
				if(user!=null) {
					request.getSession().setAttribute("auth", user);
					response.sendRedirect("index.jsp");


				}else {
					out.print("login failed");
					
				}
				
				
			} catch (ClassNotFoundException|SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			out.print(emailString+passwordString);

		
		}
	}

}
