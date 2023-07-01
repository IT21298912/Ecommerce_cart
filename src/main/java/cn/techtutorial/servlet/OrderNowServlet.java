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

@WebServlet("/orde-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// index page eke buy btn eken link ekak ekka id ek pass krn nisa doget eka thmi
	// use wenne..also hit the dopost method
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
			
			Date date=new Date(0);
			
			User auth = (User) request.getSession().getAttribute("auth"); // find the user using session

			if (auth != null) {
				String productID=request.getParameter("id");
				int productQuantity=Integer.parseInt(request.getParameter("quantity")); // fetch data from cart page
				if(productQuantity<0) {
					productQuantity=1;
				}
				
				Order orderModel=new Order();
				orderModel.setId(Integer.parseInt(productID));  //order extend with product
				orderModel.setUid(auth.getId());          //user eken aran order ekt set krnwa
				orderModel.setQuantity(productQuantity);
				orderModel.setDate(formatter.format(date));
				
				OrderDao orderDao=new OrderDao(dbcon.getConnection());
				boolean result= orderDao.insertOrder(orderModel);
				
				
				
				if(result) {
					
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					
					if(cart_list!=null) {
						for(Cart c:cart_list) {
							
							if(c.getId()==Integer.parseInt(productID)) {
								
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
						
						
					}
					
					
					
					
					
					response.sendRedirect("order.jsp");
				}else {
					out.print("order failed");
				}
				

			}else {
				response.sendRedirect("login.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
