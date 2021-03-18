package com.example.demo.request;

import java.io.Serializable;

public class AccountRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1693598028785003060L;
	private String name;
	private String type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
