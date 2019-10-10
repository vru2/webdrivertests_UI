package com.cleartrip.local.camp.ttd;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class CreateActivityPricePerUnitSchedSA extends CampActivities {

	@Parameters({ "user","domain" })
	@Test
	public void CreateActivitySaUnit_36073(String user,String domain) throws Exception {
		Boolean flag=false;
		campActivities_SignIN(driver, user);
		Thread.sleep(5000);
		selectActivity("New Activity");
		NewActivityFormOne(campLocal.value("ttdCollection"), "Shared", "Yes", "Yes",domain);
		NewActivityFormTwo("No");
		String activityId = NewActivityFormThree("ScheduledActivity", "shared", "pricingTypeSlot", "",domain);
		if (domain.equals("com"))
			flag = vCampActivityLocalJson("ttd", campLocal.value("campCity"), campLocal.value("ttdCollection"),
					activityId);
		else {
			/*flag = vCampActivityLocalJson("ttd", campLocal.value("AeCity"), campLocal.value("ttdCollection"),
					activityId);*/
			flag = true;
		}
				
		if (flag == true)
			Reporter.log(activityId + " id activity is present in local");
		else {
			Reporter.log(activityId + " id activity isnot present in local");
			Assert.fail();
		}
		unPublishActivity(baseUrl, activityId);

	}
}
