package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalCollection extends CampActivities

{
	@Parameters({"user"})
	@Test
	public void fnbEditorialCarousalCollections_39726(String user) throws Exception
	{
		campActivities_SignIN(driver,user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver,"CarousalCollection");
		EditorialType(driver, "Carousal", "FNB");
		contentType(driver,"Collection", "FNB");
		mapCarousal(driver, "FNB",false);
		orderCarousal(driver,"TTD",campLocal.value("campCity"),"CarousalCollection");
		
		
	}
}


