package com.cleartrip.local.camp.accountflow;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AccountFlowVerificationCM extends CampActivities {
	public String locationPath;
	ArrayList<String> verifyData = new ArrayList<>();

	@Parameters({"user"})
	@Test(priority=1)
	public void campActiveTabAllActivityCM_36494(String user) throws Exception {
		campActivities_SignIN(driver,user);
		locationPath = "//section[@id='supplier-dashboard']/h4";
		verifyData.add("Activity");
		verifyData.add("All Activities");
		verifyData.add("Activities Listing");
		verifyData.add(locationPath);
		campActiveLinks(driver,verifyData);
	}
	
	@Test(priority=2)
	public void campActiveDashBoardCM_36495() throws Exception {
		locationPath = "//div[@id='superadminDashboard']//h4[text()='Dashboard Menu']";
		safeClick(driver, By.linkText("Dashboard"));
		Thread.sleep(3000);
		if(isElementPresent(driver,By.xpath(locationPath))==false){
			Reporter.log("DashBoard Menu isn't present for CM User");
		}
	}
}
