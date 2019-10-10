package com.cleartrip.local.camp.events;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ProductEditorial extends CampActivities

{
	@Parameters({ "user" })
	@Test
	public void eventproductEditorial_39741(String user) throws Exception {
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver, "ProductEditorial");
		prodEditorial(driver, "Events");
		mapProdEditorial(driver, "Events");
		driver.get(baseUrl+"/editorial/configuration");
		orderProductEditorial(driver, "Events");

	}

}
