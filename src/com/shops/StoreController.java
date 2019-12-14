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
public class StoreController {
	
	DAO dao;
	ArrayList<Store> stores;
	
	public StoreController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//load stores method
	public void loadStores() {
		System.out.println("In loadstores()");
		try {
			stores = dao.loadStores();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//method to add store
	public String addStore(Store s) {
		System.out.println(s.getId() + " " + s.getName());
		try {
			dao.addStore(s);
			return "index";
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = 
					new FacesMessage("Error: "+s.getName()+" already exists");
					FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (CommunicationsException e) {
			FacesMessage message = 
					new FacesMessage("Error: Can't communicate with SQL");
					FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			FacesMessage message = 
					new FacesMessage("Error: " + e.getMessage());
					FacesContext.getCurrentInstance().addMessage(null, message);

			e.printStackTrace();
		}
		return null;
	}

	//method to delete store
	public void deleteStore(int s) {
		try {
			dao.deleteStore(s);	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public ArrayList<Store> getStores() {
		return stores;
	}
}
