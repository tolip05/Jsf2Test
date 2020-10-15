package com.test.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.cc.annotation.PojoCreator;
import com.cc.annotation.PojoProperty;
import com.cc.db.models.ModelWithId;

public class Package extends ModelWithId{
	private String title;
	private String description;
	private Integer weight;
	private Address shippingAddress;
	private String status;
	private String recipient;
	private Date estimatedDeliveryTime;
	private UserPanda userPanda;
	 

	public Package() {
			super(NOT_DEFINED_ID);
		}

	 public Package(Long id) {
			super(id);
		}
	 @PojoCreator
	 public Package(@PojoProperty("package_id") Long id,
			 @PojoProperty("title") String title,
			 @PojoProperty("description") String description,
			 @PojoProperty("weight") Integer weight,
			 @PojoProperty("shipping_address") Address shippingAddress,
			 @PojoProperty("status") String status,
			 @PojoProperty("recipient") String recipient,
			 @PojoProperty("estimated_delivery_time") Date estimatedDeliveryTime,
			 @PojoProperty("user_id") UserPanda user) {
		 super(id);
		 this.title = title;
		 this.description = description;
		 this.weight = weight;
		 this.shippingAddress = shippingAddress;
		 this.status = status;
		 this.recipient = recipient;
		 this.estimatedDeliveryTime = estimatedDeliveryTime;
		 this.userPanda = user;
	 }
	 
	 public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Date getEstimatedDeliveryTime() {
			return estimatedDeliveryTime;
		}

		public void setEstimatedDeliveryTime(Date estimatedDeliveryTime) {
			this.estimatedDeliveryTime = estimatedDeliveryTime;
		}
		
		
		
		public UserPanda getUserPanda() {
			return userPanda;
		}

		public void setUserPanda(UserPanda userPanda) {
			this.userPanda = userPanda;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Address getShippingAddress() {
			return shippingAddress;
		}

		public void setShippingAddress(Address shippingAddress) {
			this.shippingAddress = shippingAddress;
		}
		
		public String getRecipient() {
			return recipient;
		}

		public void setRecipient(String recipient) {
			this.recipient = recipient;
		}
}
