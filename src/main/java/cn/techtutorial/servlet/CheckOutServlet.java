package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.techtutorial.connection.dbcon;
import cn.techtutorial.dao.OrderDao;
import cn.techtutorial.model.Cart;
import cn.techtutorial.model.Order;
import cn.techtutorial.model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date date = new Date(0);
			
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");  //Retrieving the cart list
			User auth = (User) request.getSession().getAttribute("auth");  //get the authentication
			
			//check auth and cart_list
			
			if(cart_list!=null && auth!=null) {
				
				for(Cart c:cart_list) {
					
					//prepare the object
					Order order=new Order();
					
					order.setId(c.getId());
					order.setUid(auth.getId()); //user id using session
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					//instantiate dao class
					OrderDao oDao = new OrderDao(dbcon.getConnection());
					
					//calling the function
					boolean result = oDao.insertOrder(order);
					
					if(!result) break;//break the loop
				}
				
				cart_list.clear();
				response.sendRedirect("order.jsp");
				
				
				
			}else {
				if(auth==null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");  //else
				
				
			}
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
