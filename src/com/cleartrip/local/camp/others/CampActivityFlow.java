package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class CampActivityFlow extends CampActivities {

	@Parameters({"domain"})
	@Test
	public void campActivityFlow(String domain) throws Exception {
		driver.get(baseUrl + "/accounts/sign_in");
		campActivities_SignIN(driver, "ESP");
		selectActivity("New Activity");
		NewActivityFormOneESP(campLocal.value("ttdCollection"), "Shared", "Yes", "Yes","Qa2Automation",domain);
		NewActivityFormTwo("Yes");
		String actId = NewActivityFormThreeESP("ScheduledActivity", "shared", "Pricing Per Person", "");

		System.out.println(actId);
		campActivities_SignOut(driver);
		//String actId="820352";
		campActivities_SignIN(driver, "SAM");
		selectAllActivities(driver);
		activitySAMApprove(driver, actId, "Approve");
		campActivities_SignOut(driver);
		campActivities_SignIN(driver, "MM");
		waitForPageLoading(2);
		selectAllActivities(driver);
		activityMMApprove(driver, actId);
		campActivities_SignOut(driver);
		campActivities_SignIN(driver, "SCM");
		selectAllActivities(driver);
		allocateActivityToCM(driver, actId);
		campActivities_SignOut(driver);

		campActivities_SignIN(driver, "CM");
		allocateActivityToCE(driver, actId);
		campActivities_SignOut(driver);
		campActivities_SignIN(driver, "CE");
		contentApprovalfromCE(driver, actId);
		campActivities_SignOut(driver);
		campActivities_SignIN(driver, "CM");
		contentApprovalfromCM(driver, actId);
		campActivities_SignOut(driver);
		campActivities_SignIN(driver, "SCM");
		contentApprovalfromSCM(driver, actId);
		campActivities_SignOut(driver);
		campActivities_SignIN(driver, "SA");
		selectAllActivities(driver);
		unPublishfromSA(driver, actId);
		selectAllActivities(driver);
		publishfromSA(driver, actId);

	}

}
