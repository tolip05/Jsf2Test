package com.test.models;

import com.cc.annotation.PojoCreator;
import com.cc.annotation.PojoProperty;
import com.cc.db.models.ModelWithId;

public class User extends ModelWithId{
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	 public User() {
			super(NOT_DEFINED_ID);
		}

		public User(Long id) {
			super(id);
		}
		@PojoCreator
		public User(@PojoProperty("user_id") Long id,
				@PojoProperty("first_name") String firstName,
				@PojoProperty("email") String email,
				@PojoProperty("last_name") String lastName,
				@PojoProperty("password")String password) {
			super(id);
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
		}
		
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public void setEmeil(String email) {
		this.email = email;
	}
	
}
