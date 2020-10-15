package com.test.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cc.annotation.PojoCreator;
import com.cc.annotation.PojoProperty;
import com.cc.db.models.ModelWithId;

public class Receipt extends ModelWithId{
	
	private BigDecimal fee;
	private LocalDateTime issuedOn;
	private User recipient;
	private Package aPackage;
	
	
  

public Receipt() {
	   super(NOT_DEFINED_ID);
   }
   
   public Receipt(Long id) {
		super(id);
	}
   
   @PojoCreator
   public Receipt(@PojoProperty("receipt_id") Long id,
		   @PojoProperty("fee") BigDecimal fee,
		   @PojoProperty("issued_on") LocalDateTime issuedOn,
		   @PojoProperty("recipient") User recipient,
		   @PojoProperty("a_package") Package aPackage) {
	   super(id);
	   this.fee = fee;
	   this.issuedOn = issuedOn;
	   this.recipient = recipient;
	   this.aPackage = aPackage;
   }

   public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public LocalDateTime getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(LocalDateTime issuedOn) {
		this.issuedOn = issuedOn;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public Package getaPackage() {
		return aPackage;
	}

	public void setaPackage(Package aPackage) {
		this.aPackage = aPackage;
	}
}
