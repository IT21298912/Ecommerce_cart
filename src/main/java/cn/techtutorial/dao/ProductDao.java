package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import cn.techtutorial.model.Cart;
import cn.techtutorial.model.Product;

public class ProductDao {
	
	private Connection connection ;
	private String queryString;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public ProductDao(Connection connection) {
		
		this.connection = connection;
	}
	
	public List<Product> getAllProducts(){
		
		
		List<Product> products=new ArrayList<Product>();
		
		try {
			
			queryString= "select * from products";
			pst=this.connection.prepareStatement(queryString);
			rs= pst.executeQuery();
			
			while(rs.next()) {
				
				Product row =new Product(); 
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return products;
		
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){  //get product data to cart
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if(cartList.size()>0) {       //fetch all data
				
				for(Cart item:cartList) {
					queryString="select * from products where id=?";
					pst=this.connection.prepareStatement(queryString);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					
					while(rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));     // name -collumn name in db
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row); 
					}
				}

			}
			
			
		} catch (Exception e) {
//			e.printStackTrace();
			
			System.out.println(e.getMessage());
		}
		
		
		
		return products;
		}
	
	
	
	public Product getSingleProduct(int id) {
		
		Product row = null;
		
		try {
			queryString="select * from products where id=?";
			pst= this.connection.prepareStatement(queryString);
			pst.setInt(1, id);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
			row = new Product();
			row.setId(rs.getInt("id"));
			row.setName(rs.getString("name"));
			row.setCategory(rs.getString("category"));
			row.setPrice(rs.getDouble("price"));
			row.setImage(rs.getString("image"));
			
			
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return row;
		
	}
	
	
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		
		double sum=0;
		
		try {
			if(cartList.size()>0) {
				
				for(Cart item:cartList) {
					queryString="select price from products where id=?";
					pst=this.connection.prepareStatement(queryString);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					
					
					while(rs.next()) {            //eka product ekka prices qty eken * wenwa ,saha loop eka nisa muliply unu gana sum ekt ekathu wenwa
						sum+=rs.getDouble("price")*item.getQuantity();
					}
				}
				 
			}
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return sum;
	}
	
	
	}
