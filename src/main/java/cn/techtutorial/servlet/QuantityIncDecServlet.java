package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.techtutorial.model.Cart;

/**
 * Servlet implementation class QuantityIncDecServlet
 */
@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out=response.getWriter();){
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));  //initially get parameter return string
			
			ArrayList<Cart> cart_list= (ArrayList<Cart>)request.getSession().getAttribute("cart-list"); //get session
			
			if(action!=null&&id>=1) {
				if(action.equals("inc")) {
					for(Cart c:cart_list) {    //find the product
						if(c.getId()==id) {    //if cartlist id is matching to url id from inc,dec btns
							int quantity=c.getQuantity();
							quantity++;
							c.setQuantity(quantity); //set to arrylist
							response.sendRedirect("cart.jsp");
							
							
						}
						
					}
					
					
				}
				
				if(action.equals("dec")) {
					for(Cart c:cart_list) {    //find the product
						if(c.getId()==id&& c.getQuantity()>1) {    //if cartlist id is matching to url id from inc,dec btns
							int quantity=c.getQuantity();
							quantity--;
							c.setQuantity(quantity); //set to arrylist
							
							break;
							
						}
						
					}
					response.sendRedirect("cart.jsp");      //3.0
					
				}
				
				
			}else {
				response.sendRedirect("cart.jsp"); 
				
			}
			
			
			
		}
		
		
	}

}
