package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

	private DataSource mysqlDS;

	/*
	 * =============================================================================
	 * ========================= Constructor
	 * =============================================================================
	 * =========================
	 */
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}

	//loads products
	//
	public ArrayList<Product> loadProducts() throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from product";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Product> products = new ArrayList<Product>();

		// process result set
		while (myRs.next()) {
			Product p = new Product();
			p.setPid(myRs.getInt("pid"));
			p.setSid(myRs.getInt("sid"));
			p.setPrice(myRs.getDouble("price"));
			p.setProdName(myRs.getString("prodName"));
			products.add(p);
		}
		return products;
	}

	//loads stores
	//
	public ArrayList<Store> loadStores() throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select * from store";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<Store> stores = new ArrayList<Store>();

		// process result set
		while (myRs.next()) {
			Store s = new Store();
			s.setId(myRs.getInt("id"));
			s.setName(myRs.getString("name"));
			s.setFounded(myRs.getString("founded"));
			stores.add(s);
		}
		return stores;
	}
	
	//loadStoreProducts
	public ArrayList<StoreProduct> loadStoreProducts(int spId) throws Exception {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();

		String sql = "select p.pid, p.prodName, p.price, s.name, s.founded, s.id from product p inner join store s on p.sid = s.id where p.sid like "+spId;
		

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		ArrayList<StoreProduct> storeProducts = new ArrayList<StoreProduct>();
		System.out.println("DEBUG before while");
		// process result set
		while (myRs.next()) {
			StoreProduct sProd = new StoreProduct();
			sProd.setPid(myRs.getInt("pid"));
			sProd.setPrice(myRs.getDouble("price"));
			sProd.setName(myRs.getString("name"));
			sProd.setProdName(myRs.getString("prodName"));
			sProd.setFounded(myRs.getString("founded"));
			sProd.setId(myRs.getInt("id"));
			
			System.out.println(sProd.name);
			storeProducts.add(sProd);
		}
		return storeProducts;
	}

	//add product
	public void addProduct(Product product) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();
		String sql = "insert into product values (?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, product.getPid());
		myStmt.setInt(2, product.getSid());
		myStmt.setDouble(3, product.getPrice());
		myStmt.setString(4, product.getProdName());
		myStmt.execute();
	}

	//add store
	//add product
		public void addStore(Store store) throws Exception {
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;

			myConn = mysqlDS.getConnection();
			String sql = "insert into store values (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, store.getId());
			myStmt.setString(2, store.getName());
			myStmt.setString(3, store.getFounded());
			myStmt.execute();
		}
	
	//delete product
	public void deleteProduct(int pid) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();
		String sql = "delete from product where pid = " + pid;
		myStmt = myConn.prepareStatement(sql);
		myStmt.execute();
	}
	
	//delete store
	public void deleteStore(int id) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		myConn = mysqlDS.getConnection();
		String sql = "delete from store where id = " + id;
		myStmt = myConn.prepareStatement(sql);
		myStmt.execute();
	}
}
