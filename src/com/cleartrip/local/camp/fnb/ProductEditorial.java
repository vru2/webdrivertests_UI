package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ProductEditorial extends CampActivities {
	@Parameters({"user"})
	@Test
	public void fnbproductEditorial_39731(String user) throws Exception {
		campActivities_SignIN(driver,user);
		waitForPageLoading(3);
		selectManageEditorial(driver);
		EditorialDetails(driver, "ProductEditorial");
		prodEditorial(driver, "FNB");
		mapProdEditorial(driver, "");
		driver.get(baseUrl+"/editorial/configuration");
		orderProductEditorial(driver, "FNB");

	}
}
