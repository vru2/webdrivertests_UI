package com.cleartrip.local.camp.others;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AccountPLBConfigurationSA extends CampActivities {

	@Parameters({ "user" })
	@Test
	public void accountPLBConfigureSA_37805(String user) throws Exception {
		HashMap<String, String> inputs = new HashMap<>();
		inputs.put("espName", "activitycleartrip@gmail.com");
		campActivities_SignIN(driver, user);
		driver.get(baseUrl + "/accounts/plb_config");
		accountPLBConfigure(driver, inputs);

	}

}
