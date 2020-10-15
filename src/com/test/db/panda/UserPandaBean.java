package com.test.db.panda;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.cc.db.exception.EmptyResultSetException;
import com.cc.db.exception.PojoCreationException;
import com.cc.pojo.helper.PojoHelper;
import com.test.db.panda.builder.PandaUserSelectByEmailAnRoleBuilder;
import com.test.db.panda.builder.PandaUserSelectByEmeilBuilder;
import com.test.db.panda.builder.PandaUserSelectByRoleBuilder;
import com.test.db.panda.builder.UserPandaInsertFullParamsBuilder;
import com.test.models.UserPanda;

@ManagedBean(name = "uPBean")
@SessionScoped
public class UserPandaBean {
	private final static String MANAGER = "Manager";
    private final static String EMPLOYEE = "Employee";
    private final static String USER_PANDA = "User";
	private UserPanda editUser;
	private String userName;
	private String password;
	private String email;
	private String confirmPassword;
	
	public UserPandaBean() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public UserPanda getEditUser() {
		return editUser;
	}

	public void setEditUser(UserPanda editUser) {
		this.editUser = editUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addUser() throws IOException, SQLException {
		
		if(isDbContainsEmeil()) {
			return;
		}
		
		if(!password.equals(confirmPassword)) {
			return;
		}
		
		editUser = new UserPanda();
		editUser.setUserName(userName);
		editUser.setEmail(email);
		if(checkForFirst()) {
			editUser.setRole(MANAGER);
		}else if(checkForAsigment()) {
			editUser.setRole(EMPLOYEE);
		}else{
			editUser.setRole(USER_PANDA);	
		}
		editUser.setPassword(DigestUtils.sha256Hex(this.getPassword()));
		UserPandaInsertFullParamsBuilder.create().setParam1_UserName(editUser.getUserName()).setParam2_Password(editUser.getPassword())
		.setParam3_Email(editUser.getEmail()).setParam4_Role(editUser.getRole()).build().execute();
		 FacesContext.getCurrentInstance()
	       .getExternalContext().redirect("/jsfVersion2/pandalogin.jsf");
	}
	
	public boolean isDbContainsEmeil() {
		
		try {
			editUser = PojoHelper.fillPojo(UserPanda.class, PandaUserSelectByEmeilBuilder.create().setParam1_Email(email)
					.build().selectOneObject());
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			editUser = null;
		}
		if(editUser != null) {
			return true;
		}
		return false;
	}
	
	public boolean checkForFirst() {
		UserPanda pandaUser = null;
		try {
			 pandaUser = PojoHelper.fillPojo(UserPanda.class, PandaUserSelectByRoleBuilder.create()
					.setParam1_Role(MANAGER).build().selectOneObject());
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			pandaUser = null;
		}
		
		if(pandaUser!= null) {
			return false;
		}
		return true;
	}
	
	public boolean checkForAsigment() {
		//TODO
		return false;
	}
	
	public void loginUser() throws IOException {
		UserPanda user;
		try {
			 user = PojoHelper.fillPojo(UserPanda.class, PandaUserSelectByEmeilBuilder.create()
					.setParam1_Email(this.getEmail()).build().selectOneObject());
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			user = null;
		}
		if(user == null || !DigestUtils.sha256Hex(this.getPassword()).equals(user.getPassword())) {
			HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
	                .getExternalContext().getSession(false);
	        httpSession.setAttribute("username", null);
	        
	        FacesContext.getCurrentInstance()
	                .getExternalContext().redirect("/jsfVersion2/pandalogin.jsf");
			return;
		}
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        httpSession.setAttribute("username", user.getUserName());
        
        httpSession.setAttribute("role",USER_PANDA);
        
        if(checkUserForAdmin(user.getEmail())) {
        	httpSession.setAttribute("role",MANAGER);
        }
        httpSession.setAttribute("email", this.getEmail());
        
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("/jsfVersion2/home.jsf");
	}
	
	public void logout() throws IOException {
		this.password = null;
		this.email = null;
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        httpSession.setAttribute("username", null);
        httpSession.invalidate();
        FacesContext.getCurrentInstance()
        .getExternalContext().redirect("/jsfVersion2/start.jsf");
	}
	
	private boolean checkUserForAdmin(String email) {
		UserPanda user = null;
		try {
			user = PojoHelper.fillPojo(UserPanda.class, PandaUserSelectByEmailAnRoleBuilder.create()
					.setParam1_Email(email).setParam2_Role(MANAGER).build().selectOneObject());
		} catch (PojoCreationException | EmptyResultSetException | SQLException e) {
			// TODO Auto-generated catch block
			user = null;
		}
		return user != null;
	}
}
