package com.cleartrip.local.camp.fnb;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageLayoutCollectionCards extends CampActivities
{
	@Parameters({"user"})
	@Test
	public void editorialHomePageLayoutCollectionCards_39730(String user) throws Exception
	{
		campActivities_SignIN(driver,user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver,"FNBCollectionCard");
		homeEditorialCollection(driver,"FNB");
		mapHomePage(driver,"");
		if(homePageConfig(driver,"FNBCollectionCard")==true) {
			System.out.println("pass");
		}else {
			System.out.println("Did not found in elastic search");
			Assert.fail();
			
		}
		
	}
}
