package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditInventorySA extends CampActivities {

	@Parameters({"user"})
	@Test
	public void Fnb_InventoryEdit_35740(String user) throws Exception {
		campActivities_SignIN(driver,user);
		driver.get(baseUrl + "/activities");
		inventoryEditFnb();
	}
}
