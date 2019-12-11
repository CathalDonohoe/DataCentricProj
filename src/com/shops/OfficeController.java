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
public class OfficeController {

	MongoDAO mongo;
	ArrayList<Offices> offices;
	
	//Default Constructor
	public OfficeController() {
		super();
		try {
			mongo = new MongoDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//load offices from mongoDAO function loadOffices()
	public void loadOffices() {
		System.out.println("Loading Offices now...");
		try {
			offices = mongo.loadOffices();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String addOffice(Offices o) {
		System.out.println("In addOffices!");
		try {
			mongo.addOffice(o);
			return "list_offices.xhtml";
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = 
					new FacesMessage("Error: Product ID already exists");
					FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (CommunicationsException e) {
			FacesMessage message = 
					new FacesMessage("Error: Can't communicate with DB");
					FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (Exception e) {
			FacesMessage message = 
					new FacesMessage("Error: " + e.getMessage());
					FacesContext.getCurrentInstance().addMessage(null, message);

			e.printStackTrace();
		}
		return null;
	}


	public ArrayList<Offices> getOffices() {
		return offices;
	}

}
