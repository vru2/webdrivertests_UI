package com.cleartrip.local.camp.ttd;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageLayoutCollectionCards extends CampActivities
{
	@Parameters({"user"})
	@Test
	public void editorialHomePageLayoutCollectionCards_39723(String user) throws Exception
	{
		campActivities_SignIN(driver,user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver,"TTDCollectionCard");
		homeEditorialCollection(driver,"TTD");
		mapHomePage(driver,"");
		if(homePageConfig(driver,"TTDCollectionCard")==true) {
			System.out.println("pass");
		}else {
			System.out.println("Did not found in elastic search");
			Assert.fail();
			
		}
		
	}
}
