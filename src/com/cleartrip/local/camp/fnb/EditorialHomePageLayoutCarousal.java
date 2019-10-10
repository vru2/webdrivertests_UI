package com.cleartrip.local.camp.fnb;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import domainServices.CampActivities;

public class EditorialHomePageLayoutCarousal extends CampActivities
{
	@Parameters({"user"})
	@Test
	public void editorial_HomePage_Layout_Carousal_39689(String user) throws Exception
	{
		campActivities_SignIN(driver,user);
		Thread.sleep(5000);
		selectManageEditorial(driver);
		homeEditorialDetails(driver,"FNBCarousal");
		homeEditorialCarousal(driver,"FNB");
		mapHomePage(driver,"");
		if(homePageConfig(driver,"FNBCarousal")==true) {
			System.out.println("pass");
		}else {
			System.out.println("Did not found in API");
			Assert.fail();
			
		}
		
		
	}
}
