package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AccountApprovalSA extends CampActivities {

	@Parameters({"user"})
	@Test
	public void accountApprovalSA_39742(String user) throws Exception {
		campActivities_SignIN(driver,user);
		campAccountApprove(driver);
	}

}
