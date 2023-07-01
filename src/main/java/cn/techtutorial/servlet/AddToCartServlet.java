package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.techtutorial.model.Cart;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			
			ArrayList<Cart> cartlist =new ArrayList<>(); //initialy empty object arraylist 
			
			int id= Integer.parseInt(request.getParameter("id")); //receive product id from url
			Cart cm = new Cart();
			cm.setId(id);
			cm.setQuantity(1);
			
			
			HttpSession session=request.getSession();     //request session if there..
			ArrayList<Cart> cart_list= (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list==null) { //if no product ,session added
				cartlist.add(cm);                             //add product to empty arraylist & create session
				session.setAttribute("cart-list", cartlist);
				out.println("session created and added the list"); //newly add
			}else {
				cartlist=cart_list;
				boolean exist =false;
				
				
				
				for(Cart c:cart_list) {
					  if(c.getId()==id) { //checking id from url equal to existing id
						  exist=true;
						  out.println("<h3 style='color:crimson; text-align:center'>Item already exist in the cart.<a href='cart.jsp'>Go to cart page</a></h3>");
					  }
					  
			
				}
				
				  if(!exist) {          //if not exist
					  
					  cartlist.add(cm);
					  response.sendRedirect("index.jsp");
				  }
			}
			
			for(Cart c:cart_list) {
				out.println(c.getId());
			}
		}
	}

}
