package com.cleartrip.local.camp.ttd;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class EditorialHomePageLayoutActivityList extends CampActivities
{
	@Parameters({"user"})
	@Test
	public void editorial_HomePage_ActivityList_39688(String user) throws Exception
	{
		campActivities_SignIN(driver,user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver,"TTDActivityList");
		homeEditorialactivityList(driver,"TTD");
		mapHomePage(driver,"");
		if(homePageConfig(driver,"TTDActivityList")==true) {
			System.out.println("pass");
		}else {
			System.out.println("Did not found in API");
			Assert.fail();
			
		}
		
	}
}
