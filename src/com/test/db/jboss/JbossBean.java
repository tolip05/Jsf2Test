package com.test.db.jboss;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "testBean1")
@SessionScoped
public class JbossBean {
	private String name = "Julie";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
