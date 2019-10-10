package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AddFNBTagSA extends CampActivities {

	/**
	 * create FNB tag creation
	 * 
	 * @throws Exception
	 */
	@Parameters({"user"})
	@Test
	public void createFNBTag_35743(String user) throws Exception {
		campActivities_SignIN(driver,user);
		driver.get(baseUrl + "/manage_data/master_list");
		fnbCreateTag();
	}

}
