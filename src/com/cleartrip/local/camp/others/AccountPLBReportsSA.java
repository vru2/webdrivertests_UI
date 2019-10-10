package com.cleartrip.local.camp.others;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AccountPLBReportsSA extends CampActivities {

	@Parameters({"user"})
	@Test
	public void accountPLBReports_37806(String user) throws Exception {
		campActivities_SignIN(driver,user);
		driver.get(baseUrl + "/accounts/plb_config");
		plbReports(driver);
	}

}
