package com.cleartrip.local.camp.events;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialCarousalCollection extends CampActivities
{
	@Parameters({"user"})
	@Test
	public void eventEditorialCarousalCollections_39733(String user) throws Exception
	{
		campActivities_SignIN(driver,user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		EditorialDetails(driver,"CarousalCollection");
		EditorialType(driver, "Carousal", "Events");
		contentType(driver,"Collection", "Events");
		mapCarousalEvent(driver,false);
		orderCarousal(driver,"Events",campLocal.value("campCity"),"CarousalCollection");
	}
}
