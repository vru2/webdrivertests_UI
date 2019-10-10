package com.cleartrip.local.camp.fnb;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import domainServices.CampActivities;

public class AddChainSA extends CampActivities {

	@Parameters({"user"})
	@Test
	public void addChain_35745(String user) throws Exception {
		campActivities_SignIN(driver,user);
		driver.get(baseUrl + "/manage_local_data/chain");
		fnbAddChainActivity();
	}

}
