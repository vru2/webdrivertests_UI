package com.cleartrip.local.camp.fnb;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class CreateActivityRejectSA extends CampActivities {

	@Parameters({"user","domain"})
	@Test
	public void FNBPublishReject_35738(String user,String domain) throws Exception {
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/activities/new?code=fnb");
		fnbCreateFormOne(domain);
		fnbCreateFormTwo("Pricing Per Person", "",domain);
		Thread.sleep(5000);
		safeClick(driver, By.linkText("Reject"));
		Thread.sleep(5000);
		Assert.assertTrue(
				driver.findElement(By.xpath("html/body/div[1]/div[1]/div")).getText().contains("Activity Rejected"),
				"Activity Is not rejected");
		Reporter.log("Activity rejected",true);
	}
}
