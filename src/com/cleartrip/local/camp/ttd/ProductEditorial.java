package com.cleartrip.local.camp.ttd;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class ProductEditorial extends CampActivities
{
	@Parameters({"user"})
	@Test
	public void ttdProductEditorial_39724(String user) throws Exception
	{
		campActivities_SignIN(driver,user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver,"ProductEditorial");
		prodEditorial(driver,"TTD");
		mapProdEditorial(driver,"");
		driver.get(baseUrl+"/editorial/configuration");
		orderProductEditorial(driver,"TTD");
		
		
	}
}
