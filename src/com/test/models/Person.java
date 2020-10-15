package com.test.models;

import com.cc.annotation.PojoCreator;
import com.cc.annotation.PojoProperty;
import com.cc.db.models.ModelWithId;

public class Person extends ModelWithId{
   private String firstName;
   private String lastName;
   private String uid;
   private Address address;
   private String gender;
   

   public Person() {
		super(NOT_DEFINED_ID);
	}

	public Person(Long id) {
		super(id);
	}
    
	@PojoCreator
	public Person(@PojoProperty ("person_id") Long id,@PojoProperty("first_name") String firstName,
			@PojoProperty("last_name") String lastName,@PojoProperty("uid") String uid,
			@PojoProperty("address_id") Address address,@PojoProperty("gender") String gender) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.uid = uid;
		this.address = address;
		this.gender = gender;
		
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

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}
   
}
