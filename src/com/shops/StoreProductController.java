package com.shops;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.CommunicationsException;


@ManagedBean
@SessionScoped
public class StoreProductController {

	DAO dao;
	ArrayList<StoreProduct> storeProducts;
	
	public StoreProductController(){
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public String loadStoreProducts(int spId) {
		System.out.println("In loadStoreProducts()");
		try {
			storeProducts = dao.loadStoreProducts(spId);
			return "storeProducts";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<StoreProduct> getStoreProducts() {
		return storeProducts;
	}
	
}
