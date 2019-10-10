package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditVariantPricingSA extends CampActivities {
	@Parameters({"user"})
	@Test
	public void Fnb_VariantPriceEdit_35741(String user) throws Exception {
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/activities");
		fnbVariantPricing();
	}
}
