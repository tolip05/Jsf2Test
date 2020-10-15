package com.test.models;

import com.cc.annotation.PojoCreator;
import com.cc.annotation.PojoProperty;
import com.cc.db.models.ModelWithId;

public class Address extends ModelWithId {
	private City city;
	private String street;
	private int numberOfStreet;

	public Address() {
		super(NOT_DEFINED_ID);
	}

	public Address(Long id) {
		super(id);
	}

	@PojoCreator
	public Address(@PojoProperty("address_id") Long id,
			@PojoProperty("city_id") City city,
			@PojoProperty("street") String street,
			@PojoProperty("number_of_street") int numberOfStreet) {
		super(id);
		this.city = city;
		this.street = street;
		this.numberOfStreet = numberOfStreet;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumberOfStreet() {
		return numberOfStreet;
	}

	public void setNumberOfStreet(int numberOfStreet) {
		numberOfStreet = numberOfStreet;
	}
}
