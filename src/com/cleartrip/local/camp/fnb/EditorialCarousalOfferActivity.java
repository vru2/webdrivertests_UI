package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalOfferActivity extends CampActivities
{
	@Parameters({"user"})
	@Test
	public void fnbEditorialCarousalOffeActivity_39727(String user) throws Exception
	{
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver,"CarousalOfferActivity");
		EditorialType(driver, "Carousal", "FNB");
		contentType(driver,"OfferActivity", "FNB");
		mapCarousal(driver, "FNB",true);
		orderCarousal(driver,"FNB",campLocal.value("campCity"),"CarousalOfferActivity");
		
		
	}
}
