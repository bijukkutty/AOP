package com.rest.AOPBackendApp;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;

import java.lang.String;
import java.util.Date;
import java.util.List;

/**
 * The @Entity tells Objectify about our entity. We also register it in
 * {@link OfyHelper} Our primary key @Id is set automatically by the Google
 * Datastore for us.
 * 
 * We add a @Parent to tell the object about its ancestor. We are doing this to
 * support many guestbooks. Objectify, unlike the AppEngine library requires
 * that you specify the fields you want to index using @Index. Only indexing the
 * fields you need can lead to substantial gains in performance -- though if not
 * indexing your data from the start will require indexing it later.
 * 
 * NOTE - all the properties are PUBLIC so that can keep the code simple.
 **/
@Entity
public class AOPPricingEntity {
	@Id
	public Long id;
	@Index
	public String itemName;
	public String itemPrice;
	@Index
	public Date date;

	/**
	 * Simple constructor just sets the date
	 **/
	public AOPPricingEntity() {
		date = new Date();
	}

	/**
	 * Takes all important fields
	 **/
	public AOPPricingEntity(String strItemName, String strItemPrice) {
		itemName = strItemName;
		itemPrice = strItemPrice;
		date = new Date();
	}

}