package com.test.models;

import com.cc.annotation.PojoCreator;
import com.cc.annotation.PojoProperty;
import com.cc.db.models.ModelWithId;

public class UserPanda extends ModelWithId{
private String userName;
private String password;
private String email;
private String role;

	public UserPanda() {
			super(NOT_DEFINED_ID);
		}

	 public UserPanda(Long id) {
			super(id);
		}
	 
	 
	 @PojoCreator
	 public UserPanda(@PojoProperty("user_id") Long id,
				@PojoProperty("user_name") String userName,
				@PojoProperty("password")String password,
				@PojoProperty("email") String email,
				@PojoProperty("role") String role) {
		 super(id);
			this.userName = userName;
			this.password = password;
			this.email = email;
			this.role = role;
		  }
	 
	 public String getUserName() {
			return this.userName;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
}
