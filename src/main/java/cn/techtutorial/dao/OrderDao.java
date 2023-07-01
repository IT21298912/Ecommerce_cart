package cn.techtutorial.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.*;

import javax.swing.text.StyledEditorKit.BoldAction;

import cn.techtutorial.model.*;

public class OrderDao {
	
	private Connection connection ;
	private String queryString;
	private PreparedStatement pst;
	private ResultSet rs;

	
	public OrderDao(Connection connection) {
		this.connection=connection;
	}
	
	
	
	public boolean insertOrder(Order model) {
		boolean result=false;
		
		try {
			queryString="insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
			pst=this.connection.prepareStatement(queryString);
			
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			
			pst.executeUpdate();
			result=true;
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return result;
		
	}
	
	public List<Order> userOrders(int id){
		List<Order> list=new ArrayList<>();
		
		try {
			
			queryString="select * from orders where u_id=? order by orders.o_id desc";
			pst=this.connection.prepareStatement(queryString);
			pst.setInt(1, id);
			rs=pst.executeQuery();  //return result set
			
			while(rs.next()) {
				
				Order order = new Order();
				ProductDao productDao=new ProductDao(this.connection); //method
				
				int pId=rs.getInt("p_id");  //result set eken pid eka gnnwa 
				
				Product product = productDao.getSingleProduct(pId);
				order.setOrderId(rs.getInt("o_id"));
				order.setId(pId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				list.add(order);
				
				
				
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public void cancelOrder(int id) {
		
		try {
			queryString="delete from orders where o_id=?";
			pst=this.connection.prepareStatement(queryString);
			pst.setInt(1, id);
			pst.execute();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
