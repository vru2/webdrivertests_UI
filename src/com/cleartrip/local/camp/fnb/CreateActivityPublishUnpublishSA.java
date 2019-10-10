package com.cleartrip.local.camp.fnb;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

/**
 * Create FNB Activity with SA User
 * 
 * @author Amit Singh
 *
 */
public class CreateActivityPublishUnpublishSA extends CampActivities {

	@Parameters({ "user", "domain" })
	@Test
	public void FNBPublish_35734(String user, String domain) throws Exception {
		Boolean flag = false;
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/activities/new?code=fnb");
		fnbCreateFormOne(domain);
		String activityId = fnbCreateFormTwo("Pricing Per Person", "", domain);
		//String activityId = "808586";
		if (domain.equals("com"))
			flag = vCampActivityLocalJson("fnb", campLocal.value("campCity"), campLocal.value("fnbCollection"),
					activityId);
		else
			flag = vCampActivityLocalJson("fnb", campLocal.value("AeCity"), campLocal.value("AefnbCollection"),
					activityId);

		if (flag == true)
			Reporter.log(activityId + " id activity is present in local");
		else {
			Reporter.log(activityId + " id activity isnot present in local");
			Assert.fail();
		}

		unPublishActivity(baseUrl, activityId);

	}
}