package com.test.db.register;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import javax.faces.bean.ManagedProperty;
import com.cc.db.exception.EmptyResultSetException;
import com.cc.db.exception.PojoCreationException;
import com.cc.pojo.helper.PojoHelper;
import com.test.db.address.AddressBean;
import com.test.db.city.CitiesBean3;
import com.test.db.person.PersonBean;
import com.test.db.register.builder.UserInsertFullParamsBuilder;
import com.test.db.register.builder.UserSelectByEmeilBuilder;
import com.test.models.Person;
import com.test.models.User;

@ManagedBean(name = "uBean")
@SessionScoped
public class UserBean {
	@ManagedProperty(value="#{pBean}")
	private PersonBean personBean;
	@ManagedProperty(value = "#{aBean}")
	private AddressBean addressBean;
	@ManagedProperty(value = "#{cBean}")
	private CitiesBean3 cityBean;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String confirmPassword;
	
	public UserBean() {
		
	}
	
	public void addUser() throws PojoCreationException, SQLException, IOException {
		User user;
		try {
			user = PojoHelper.fillPojo(User.class, UserSelectByEmeilBuilder.create().setParam1_Email(email)
					.build().selectOneObject());
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			user = null;
		}
		if(user != null) {
			return;
		}
		if(!this.getPassword().equals(this.getConfirmPassword())) {
			return;
		}
	   
	   this.setPassword(DigestUtils.sha256Hex(this.getPassword()));
	   UserInsertFullParamsBuilder.create().setParam1_FirstName(firstName).setParam2_LastName(lastName)
	   .setParam3_Email(email).setParam4_Password(this.getPassword()).build().execute();
	   FacesContext.getCurrentInstance()
       .getExternalContext().redirect("/JsfTest/login.jsf");
	}
	
	public void loginUser() throws IOException {
		User user;
		try {
		      user = PojoHelper.fillPojo(User.class, UserSelectByEmeilBuilder.create().setParam1_Email(email).build()
					.selectOneObject());
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			user = null;
		}
		if(user == null || !DigestUtils.sha256Hex(this.getPassword()).equals(user.getPassword())) {
			HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
	                .getExternalContext().getSession(false);
	        httpSession.setAttribute("username", null);
	        
	        FacesContext.getCurrentInstance()
	                .getExternalContext().redirect("/JsfTest/login.jsf");
			return;
		}
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        httpSession.setAttribute("username", user.getFirstName());
        
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("/JsfTest/persons.jsf");
	}
	
	public void logout() {
		this.password = null;
		this.email = null;
		personBean.selectAll();
		addressBean.selectAll();
		cityBean.selectAll();
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        httpSession.setAttribute("username", null);
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 public void setPersonBean(PersonBean personBean) {
			this.personBean = personBean;
		}
	 public void setAddressBean(AddressBean addressBean) {
			this.addressBean = addressBean;
		}

		public void setCityBean(CitiesBean3 cityBean) {
			this.cityBean = cityBean;
		}
}
