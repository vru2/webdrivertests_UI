package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalActivity extends CampActivities
{
	@Parameters({"user"})
	@Test
	public void fnbEditorialCarousalActivities_39725(String user) throws Exception
	{
		campActivities_SignIN(driver,user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver,"CarousalActivity");
		EditorialType(driver, "Carousal", "FNB");
		contentType(driver,"Activities", "FNB");
		mapCarousal(driver, "FNB",false);
		orderCarousal(driver,"FNB",campLocal.value("campCity"),"CarousalActivity");
		
		
	}
}
