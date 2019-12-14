package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class StoreProduct {

	//variables from database
	int id;
	String name;
	String founded;
	int pid;
	String prodName;
	double price;
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFounded() {
		return founded;
	}
	public void setFounded(String founded) {
		this.founded = founded;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
