<%@page import="cn.techtutorial.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page import="cn.techtutorial.dao.ProductDao"%>
<%@page import="cn.techtutorial.model.*"%>
<%@page import="cn.techtutorial.connection.dbcon"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {

	request.setAttribute("auth", auth);
}
//----------------------------------------------------------------
ProductDao pd=new ProductDao(dbcon.getConnection());
List<Product> products = pd.getAllProducts();

//-----------------------------------------------------------------
ArrayList<Cart> cart_list=(ArrayList<Cart>) session.getAttribute("cart-list");


if(cart_list!=null){

	request.setAttribute("cart_list", cart_list);
}


%>


<!DOCTYPE html>
<html>
<head>
<title>welcome to shopping cart</title>
<%@include file="includes/head.jsp"%>
</head>
<body>

	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All products</div>

		<div class="row">
		
		<%
			if(!products.isEmpty()){   //not empty
				
				for(Product p:products){ %>
			
					<div class="col-md-3 my-3">

					<div class="card w-100" style="width: 18rem;">
						<img src="productimg/<%= p.getImage() %>" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title"><%= p.getName() %></h5>
							<h6 class="price">Price: $<%=p.getPrice() %></h6>
							<h6 class="category">category: <%= p.getCategory() %></h6>
							<div class="mt-3 d-flex justify-content-between">
								<a href="add-to-cart?id=<%= p.getId() %>" class="btn btn-dark">Add to Cart</a>
								<a href="orde-now?quantity=1&id=<%=p.getId()%>" class="btn btn-primary">Buy Now</a>
								

							</div>

						</div>
					</div>

				</div>
				<% }
			}
		
		
		%>

			
		</div>

	</div>


	<%@include file="includes/footer.jsp"%>
</body>
</html>