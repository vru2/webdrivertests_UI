package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AddVariantSA extends CampActivities {
	
	/**
	 * create FNB tag creation
	 * 
	 * @throws Exception
	 */
	@Parameters({"user"})
	@Test
	public void addNewVariant_35744(String user) throws Exception {
		campActivities_SignIN(driver,user);
		driver.get(baseUrl + "/manage_local_data/chain");
		newAddVariant();
	}
}
