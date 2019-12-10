package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Store {

	
	int id;
	String name;
	String Founded;
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
		return Founded;
	}
	public void setFounded(String founded) {
		Founded = founded;
	}
	
	
}
